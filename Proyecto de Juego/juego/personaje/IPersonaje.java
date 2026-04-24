package juego.personaje;


public interface IPersonaje {

    void atacar(IPersonaje oponente);

    
    void recibirDano(int dano);

    boolean estaVivo();
    
    String getNombre();

    int getPuntosDeVida();
}
