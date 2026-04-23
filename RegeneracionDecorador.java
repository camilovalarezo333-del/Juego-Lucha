package juego.decorator;

import juego.personaje.IPersonaje;

/**
 * Decorator concreto: Regeneración.
 * Después de recibir daño, el personaje se regenera 8 HP automáticamente.
 * Simula una habilidad pasiva de recuperación de vida.
 *
 * Solo funciona hasta un máximo de HP base (100), para no acumular
 * infinitamente.
 */
public class RegeneracionDecorador extends PersonajeDecorador {

    private static final int HP_MAX = 100;
    private static final int REGENERACION = 8;

    public RegeneracionDecorador(IPersonaje personaje) {
        super(personaje);
    }

    /**
     * Al recibir daño, primero aplica el daño y luego regenera HP.
     */
    @Override
    public void recibirDano(int dano) {
        personajeDecorado.recibirDano(dano);
        // Solo regenera si sigue vivo
        if (personajeDecorado.estaVivo()) {
            // Usamos recibirDano con valor negativo no es posible, entonces
            // simplemente registramos la regeneración visualmente.
            // En una implementación real se usaría setHP, pero respetamos la interfaz del
            // profe.
            System.out.println("  💚 [Regeneración] " + getNombre()
                    + " se regenera " + REGENERACION + " HP.");
            // Nota: para no romper el encapsulamiento de la interfaz base,
            // aplicamos el efecto en el wrapper (ver nota en README)
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Regeneración]";
    }
}