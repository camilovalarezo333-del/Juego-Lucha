import juego.decorator.EscudoMagicoDecorador;
import juego.decorator.VenenoDecorador;
import juego.decorator.RegeneracionDecorador;
import juego.facade.JuegoLuchaFacade;
import juego.personaje.*;

import java.util.Scanner;

/**
 * Clase principal del Juego de Lucha.
 *
 * Demostración de los 3 patrones estructurales aplicados:
 *
 * 1. DECORATOR: Se añaden habilidades a los personajes dinámicamente
 * (EscudoMagicoDecorador, VenenoDecorador, RegeneracionDecorador).
 *
 * 2. PROXY: PersonajeProxy envuelve a cada personaje para control de acceso
 * y recolección de estadísticas (aplicado automáticamente dentro de Facade).
 *
 * 3. FACADE: JuegoLuchaFacade es la única clase con la que interactúa Main.
 * Oculta toda la complejidad del sistema de combate.
 *
 * Flujo de construcción de un personaje:
 * Personaje concreto (Guerrero/Mago/Arquero)
 * → Decoradores apilados (Escudo, Veneno, etc.)
 * → PersonajeProxy (control + logging)
 * → JuegoLuchaFacade (orquesta la pelea)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      JUEGO DE LUCHA CON PATRONES ESTRUCTURALES   ║");
        System.out.println("║      Decorator · Proxy · Facade                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();

        // ─── Configuración del Jugador 1 ────────────────────────────────────
        System.out.print("Introduce el nombre del Jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.println("Elige clase para " + nombre1 + ": [1] Guerrero  [2] Mago  [3] Arquero");
        int clase1 = leerOpcion(scanner, 1, 3);

        IPersonaje personaje1 = crearPersonaje(nombre1, clase1, scanner);

        System.out.println("¿Quieres añadir habilidades a " + nombre1 + "?");
        personaje1 = aplicarDecoradores(personaje1, scanner);

        System.out.println();

        // ─── Configuración del Jugador 2 ────────────────────────────────────
        System.out.print("Introduce el nombre del Jugador 2: ");
        String nombre2 = scanner.nextLine();
        System.out.println("Elige clase para " + nombre2 + ": [1] Guerrero  [2] Mago  [3] Arquero");
        int clase2 = leerOpcion(scanner, 1, 3);

        IPersonaje personaje2 = crearPersonaje(nombre2, clase2, scanner);

        System.out.println("¿Quieres añadir habilidades a " + nombre2 + "?");
        personaje2 = aplicarDecoradores(personaje2, scanner);

        System.out.println();

        // ─── FACADE: una sola línea para iniciar toda la pelea ──────────────
        JuegoLuchaFacade juego = new JuegoLuchaFacade(personaje1, personaje2);
        juego.iniciarPelea();

        scanner.close();
    }

    // ─── Métodos auxiliares ──────────────────────────────────────────────────

    private static IPersonaje crearPersonaje(String nombre, int clase, Scanner scanner) {
        switch (clase) {
            case 1:
                System.out.print("¿Qué arma usa " + nombre + "? (ej: Espada, Hacha): ");
                String arma = scanner.nextLine();
                return new Guerrero(nombre, arma);
            case 2:
                System.out.print("¿Escuela de magia de " + nombre + "? (ej: Fuego, Hielo): ");
                String magia = scanner.nextLine();
                return new Mago(nombre, magia);
            case 3:
            default:
                return new Arquero(nombre);
        }
    }

    /**
     * Permite apilar decoradores sobre un personaje (patrón Decorator en acción).
     * Los decoradores se pueden combinar en cualquier orden y cantidad.
     */
    private static IPersonaje aplicarDecoradores(IPersonaje personaje, Scanner scanner) {
        System.out.println("  [1] Escudo Mágico (reduce daño 40%, 3 cargas)");
        System.out.println("  [2] Veneno (daño +5 al atacar)");
        System.out.println("  [3] Regeneración (recupera HP al recibir daño)");
        System.out.println("  [4] Sin habilidades");
        System.out.println("  (Puedes elegir varias, escribe 4 para terminar)");

        boolean continuar = true;
        while (continuar) {
            System.out.print("  Opción: ");
            int op = leerOpcion(scanner, 1, 4);
            switch (op) {
                case 1:
                    personaje = new EscudoMagicoDecorador(personaje, 3);
                    System.out.println("  ✅ Escudo Mágico añadido.");
                    break;
                case 2:
                    personaje = new VenenoDecorador(personaje);
                    System.out.println("  ✅ Veneno añadido.");
                    break;
                case 3:
                    personaje = new RegeneracionDecorador(personaje);
                    System.out.println("  ✅ Regeneración añadida.");
                    break;
                case 4:
                    continuar = false;
                    break;
            }
        }
        return personaje;
    }

    private static int leerOpcion(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int op = Integer.parseInt(scanner.nextLine().trim());
                if (op >= min && op <= max)
                    return op;
            } catch (NumberFormatException ignored) {
            }
            System.out.print("  Opción inválida. Intenta de nuevo (" + min + "-" + max + "): ");
        }
    }
}