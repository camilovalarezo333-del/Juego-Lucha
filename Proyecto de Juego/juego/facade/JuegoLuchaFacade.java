package juego.facade;

import juego.personaje.IPersonaje;
import juego.proxy.PersonajeProxy;


public class JuegoLuchaFacade {

    private PersonajeProxy jugador1;
    private PersonajeProxy jugador2;
    private int turnoActual;


    public JuegoLuchaFacade(IPersonaje personaje1, IPersonaje personaje2) {
        // Envuelve cada personaje en un Proxy para logging y control de acceso
        this.jugador1 = new PersonajeProxy(personaje1);
        this.jugador2 = new PersonajeProxy(personaje2);
        this.turnoActual = 1;
    }

    public void iniciarPelea() {
        imprimirEncabezado();

        // Bucle principal de pelea por turnos
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            imprimirEstadoTurno();

            // Turno del jugador 1
            ejecutarTurno(jugador1, jugador2);

            // Si el jugador 2 sigue vivo, contraataca
            if (jugador2.estaVivo()) {
                ejecutarTurno(jugador2, jugador1);
            }

            turnoActual++;
            System.out.println();
        }

        // Determinar ganador y mostrar resultado
        mostrarResultado();
    }

    private void imprimirEncabezado() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║           JUEGO DE LUCHA - POO PATTERNS ⚔️      ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  " + String.format("%-20s", jugador1.getNombre())
                + " VS  " + String.format("%-20s", jugador2.getNombre()) + " ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void imprimirEstadoTurno() {
        System.out.println("─── TURNO " + turnoActual + " ──────────────────────────────────────");
        System.out.printf("    %-25s HP: %d%n", jugador1.getNombre(), jugador1.getPuntosDeVida());
        System.out.printf("    %-25s HP: %d%n", jugador2.getNombre(), jugador2.getPuntosDeVida());
        System.out.println();
    }

    private void ejecutarTurno(PersonajeProxy atacante, PersonajeProxy defensor) {
        System.out.println("   Turno de: " + atacante.getNombre());
        atacante.atacar(defensor);
        System.out.println("     → " + defensor.getNombre()
                + " queda con " + defensor.getPuntosDeVida() + " HP.");
    }

    private void mostrarResultado() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                     FIN DE LA PELEA              ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        PersonajeProxy ganador = jugador1.estaVivo() ? jugador1 : jugador2;
        System.out.println("║  ¡" + ganador.getNombre() + " ha GANADO la pelea!");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();

        // El Proxy provee estadísticas detalladas — otra ventaja del patrón
        System.out.println("═══════════════ ESTADÍSTICAS FINALES ═══════════════");
        jugador1.mostrarEstadisticas();
        System.out.println();
        jugador2.mostrarEstadisticas();
        System.out.println("════════════════════════════════════════════════════");
    }
}
