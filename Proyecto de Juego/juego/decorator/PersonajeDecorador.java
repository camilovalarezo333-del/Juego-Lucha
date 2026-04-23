package juego.decorator;

import juego.personaje.IPersonaje;

/**
 * ============================================================
 * PATRÓN ESTRUCTURAL 1: DECORATOR
 * ============================================================
 * Clase abstracta base para todos los decoradores de personaje.
 *
 * El patrón Decorator permite añadir responsabilidades adicionales
 * a un objeto de forma dinámica, sin modificar su clase.
 *
 * En este juego: los decoradores añaden habilidades especiales
 * a los personajes (escudo mágico, veneno, regeneración) en
 * tiempo de ejecución, componiendo comportamientos de forma flexible.
 *
 * Estructura:
 * IPersonaje (componente)
 * └── Personaje (concreto)
 * └── PersonajeDecorador (abstracto) <--- Esta clase
 * └── EscudoMagicoDecorador
 * └── VenenoDecorador
 * └── RegeneracionDecorador
 */
public abstract class PersonajeDecorador implements IPersonaje {

    /** Referencia al componente que se está decorando. */
    protected IPersonaje personajeDecorado;

    public PersonajeDecorador(IPersonaje personaje) {
        this.personajeDecorado = personaje;
    }

    // Delegación por defecto al componente real
    @Override
    public void atacar(IPersonaje oponente) {
        personajeDecorado.atacar(oponente);
    }

    @Override
    public void recibirDano(int dano) {
        personajeDecorado.recibirDano(dano);
    }

    @Override
    public boolean estaVivo() {
        return personajeDecorado.estaVivo();
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre();
    }

    @Override
    public int getPuntosDeVida() {
        return personajeDecorado.getPuntosDeVida();
    }
}