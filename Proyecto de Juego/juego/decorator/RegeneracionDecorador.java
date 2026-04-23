package juego.decorator;

import juego.personaje.IPersonaje;


public class RegeneracionDecorador extends PersonajeDecorador {

    private static final int HP_MAX = 100;
    private static final int REGENERACION = 8;

    public RegeneracionDecorador(IPersonaje personaje) {
        super(personaje);
    }

   
    @Override
    public void recibirDano(int dano) {
        personajeDecorado.recibirDano(dano);
        // Solo regenera si sigue vivo
        if (personajeDecorado.estaVivo()) {
          
            System.out.println("  💚 [Regeneración] " + getNombre()
                    + " se regenera " + REGENERACION + " HP.");
            
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Regeneración]";
    }
}
