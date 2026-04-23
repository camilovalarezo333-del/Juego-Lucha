package juego.decorator;

import juego.personaje.IPersonaje;


public class VenenoDecorador extends PersonajeDecorador {

    public VenenoDecorador(IPersonaje personaje) {
        super(personaje);
    }

   
    @Override
    public void atacar(IPersonaje oponente) {
        // Primero el ataque normal
        personajeDecorado.atacar(oponente);
        // Luego aplica efecto veneno
        if (oponente.estaVivo()) {
            System.out.println("    [Veneno] " + getNombre()
                    + " envenena a " + oponente.getNombre() + " causando 5 HP adicionales.");
            oponente.recibirDano(5);
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Venenoso]";
    }
}
