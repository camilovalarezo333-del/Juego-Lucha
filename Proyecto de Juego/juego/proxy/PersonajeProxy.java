package juego.proxy;

import juego.personaje.IPersonaje;

/**
 * ============================================================
 * PATRÓN ESTRUCTURAL 2: PROXY
 * ============================================================
 * El Proxy actúa como intermediario entre el cliente (JuegoLucha)
 * y el personaje real. Añade:
 * 1. Control de acceso: no permite atacar si el personaje está muerto.
 * 2. Logging: registra cada acción con detalle para el log de batalla.
 * 3. Validación: verifica el estado antes de delegar al sujeto real.
 *
 * Estructura:
 * IPersonaje (interfaz)
 * ├── Personaje (sujeto real)
 * └── PersonajeProxy <--- Esta clase (mismo contrato, control extra)
 *
 * El cliente (JuegoLucha/Facade) solo ve IPersonaje, sin saber
 * si está hablando con el real o el proxy.
 */
public class PersonajeProxy implements IPersonaje {

    /** El personaje real al que delegamos las operaciones. */
    private IPersonaje personajeReal;

    /** Contador de ataques realizados por este personaje. */
    private int contadorAtaques;

    /** Contador de veces que recibió daño. */
    private int contadorGolpesRecibidos;

    /** Total de daño infligido al oponente. */
    private int totalDanoInfligido;

    public PersonajeProxy(IPersonaje personaje) {
        this.personajeReal = personaje;
        this.contadorAtaques = 0;
        this.contadorGolpesRecibidos = 0;
        this.totalDanoInfligido = 0;
    }

    /**
     * Control de acceso: verifica que el personaje esté vivo antes de atacar.
     * Delega al sujeto real y registra estadísticas.
     */
    @Override
    public void atacar(IPersonaje oponente) {
        // Validación: no puede atacar si está muerto
        if (!personajeReal.estaVivo()) {
            System.out.println("  ❌ [PROXY] " + getNombre()
                    + " no puede atacar: ¡está derrotado!");
            return;
        }
        // Validación: no puede atacar a un oponente ya derrotado
        if (!oponente.estaVivo()) {
            System.out.println("  ❌ [PROXY] El oponente ya está derrotado.");
            return;
        }

        int hpAntes = oponente.getPuntosDeVida();
        contadorAtaques++;

        // Delega al personaje real (o decorado)
        personajeReal.atacar(oponente);

        // Registrar cuánto daño causó
        int danoRealCausado = hpAntes - oponente.getPuntosDeVida();
        totalDanoInfligido += danoRealCausado;
    }

    /**
     * Intercepta el daño recibido para contabilizarlo.
     */
    @Override
    public void recibirDano(int dano) {
        contadorGolpesRecibidos++;
        personajeReal.recibirDano(dano);
    }

    @Override
    public boolean estaVivo() {
        return personajeReal.estaVivo();
    }

    @Override
    public String getNombre() {
        return personajeReal.getNombre();
    }

    @Override
    public int getPuntosDeVida() {
        return personajeReal.getPuntosDeVida();
    }

    // ─── Métodos extra del Proxy (estadísticas) ─────────────────────────────

    public int getContadorAtaques() {
        return contadorAtaques;
    }

    public int getContadorGolpesRecibidos() {
        return contadorGolpesRecibidos;
    }

    public int getTotalDanoInfligido() {
        return totalDanoInfligido;
    }

    /**
     * Muestra un resumen de estadísticas del personaje al final de la pelea.
     */
    public void mostrarEstadisticas() {
        System.out.println("  📊 Estadísticas de " + getNombre() + ":");
        System.out.println("     - Ataques realizados : " + contadorAtaques);
        System.out.println("     - Golpes recibidos   : " + contadorGolpesRecibidos);
        System.out.println("     - Daño total causado : " + totalDanoInfligido);
        System.out.println("     - HP final           : " + getPuntosDeVida());
    }
}