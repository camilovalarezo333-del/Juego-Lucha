package juego.facade;

import juego.personaje.IPersonaje;
import juego.proxy.PersonajeProxy;

/**
 * ============================================================
 * PATRÓN ESTRUCTURAL 3: FACADE
 * ============================================================
 * La Fachada proporciona una interfaz simplificada a todo el
 * subsistema del juego de lucha. Oculta la complejidad de:
 * - Gestión de turnos
 * - Verificación de estado de personajes
 * - Log de batalla detallado
 * - Recolección de estadísticas (via Proxy)
 * - Interacción con decoradores
 *
 * El cliente (Main) solo necesita llamar:
 * facade.iniciarPelea()
 *
 * Sin la Facade, el cliente debería coordinar manualmente
 * el Proxy, los Decoradores y el flujo de turnos.
 *
 * Estructura:
 * Main
 * └── JuegoLuchaFacade <--- Esta clase
 * ├── PersonajeProxy (x2)
 * ├── IPersonaje (jugador1, jugador2)
 * └── Lógica de turnos, log, ganador
 */
public class JuegoLuchaFacade {

    private PersonajeProxy jugador1;
    private PersonajeProxy jugador2;
    private int turnoActual;

    /**
     * La Facade recibe los personajes ya decorados (con Decorator aplicado)
     * y los envuelve automáticamente en Proxy para control y estadísticas.
     *
     * @param personaje1 primer personaje (puede estar decorado)
     * @param personaje2 segundo personaje (puede estar decorado)
     */
    public JuegoLuchaFacade(IPersonaje personaje1, IPersonaje personaje2) {
        // Envuelve cada personaje en un Proxy para logging y control de acceso
        this.jugador1 = new PersonajeProxy(personaje1);
        this.jugador2 = new PersonajeProxy(personaje2);
        this.turnoActual = 1;
    }

    /**
     * Punto de entrada único del juego.
     * Controla todo el flujo: inicio, turnos, ganador y estadísticas.
     *
     * Esta es la única operación que el cliente necesita conocer.
     */
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

    // ─── Métodos privados del subsistema (ocultos al cliente) ───────────────

    private void imprimirEncabezado() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         ⚔️  JUEGO DE LUCHA - POO PATTERNS ⚔️      ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  " + String.format("%-20s", jugador1.getNombre())
                + " VS  " + String.format("%-20s", jugador2.getNombre()) + " ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void imprimirEstadoTurno() {
        System.out.println("─── TURNO " + turnoActual + " ──────────────────────────────────────");
        System.out.printf("  ❤️  %-25s HP: %d%n", jugador1.getNombre(), jugador1.getPuntosDeVida());
        System.out.printf("  ❤️  %-25s HP: %d%n", jugador2.getNombre(), jugador2.getPuntosDeVida());
        System.out.println();
    }

    private void ejecutarTurno(PersonajeProxy atacante, PersonajeProxy defensor) {
        System.out.println("  🎮 Turno de: " + atacante.getNombre());
        atacante.atacar(defensor);
        System.out.println("     → " + defensor.getNombre()
                + " queda con " + defensor.getPuntosDeVida() + " HP.");
    }

    private void mostrarResultado() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 🏆  FIN DE LA PELEA              ║");
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