package juego.decorator;

import juego.personaje.IPersonaje;


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
