package juego.decorator;

import juego.personaje.IPersonaje;

/**
 * Decorator concreto: Veneno.
 * Al atacar, aplica veneno al oponente que le quita 5 HP adicionales
 * cada vez que el oponente ataca (hasta 3 turnos).
 *
 * Este decorador EXTIENDE el método atacar() para envenenar al oponente.
 */
public class VenenoDecorador extends PersonajeDecorador {

    public VenenoDecorador(IPersonaje personaje) {
        super(personaje);
    }

    /**
     * Realiza el ataque normal Y aplica veneno al oponente.
     * El veneno le resta 5 HP inmediatamente (simula efecto de turno).
     */
    @Override
    public void atacar(IPersonaje oponente) {
        // Primero el ataque normal
        personajeDecorado.atacar(oponente);
        // Luego aplica efecto veneno
        if (oponente.estaVivo()) {
            System.out.println("  ☠️  [Veneno] " + getNombre()
                    + " envenena a " + oponente.getNombre() + " causando 5 HP adicionales.");
            oponente.recibirDano(5);
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Venenoso]";
    }
}