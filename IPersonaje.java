package juego.personaje;

/**
 * Interfaz base que define el contrato de un Personaje en el juego.
 * Esta interfaz es la base para el patrón DECORATOR y PROXY,
 * ya que tanto los decoradores como el proxy implementan esta misma interfaz.
 *
 * Patrón: Decorator / Proxy (componente base)
 */
public interface IPersonaje {
    /**
     * Ataca a un oponente, reduciendo sus puntos de vida aleatoriamente entre 10 y
     * 30.
     * 
     * @param oponente el personaje que recibirá el daño
     */
    void atacar(IPersonaje oponente);

    /**
     * Recibe daño reduciendo los puntos de vida.
     * 
     * @param dano cantidad de puntos a restar
     */
    void recibirDano(int dano);

    /**
     * @return true si el personaje aún tiene puntos de vida (HP > 0)
     */
    boolean estaVivo();

    /**
     * @return el nombre del personaje
     */
    String getNombre();

    /**
     * @return los puntos de vida actuales del personaje
     */
    int getPuntosDeVida();
}