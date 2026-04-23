package juego.personaje;

import java.util.Random;

/**
 * Implementación concreta de un Personaje del juego de lucha.
 * Esta clase representa el "componente concreto" en el patrón DECORATOR
 * y el "sujeto real" en el patrón PROXY.
 *
 * Contiene la lógica básica: nombre, HP, atacar y recibir daño.
 */
public class Personaje implements IPersonaje {
    private String nombre;
    private int puntosDeVida;
    private final int MAX_DANO = 30;
    private final int MIN_DANO = 10;
    private static final int HP_INICIAL = 100;

    /**
     * Constructor del personaje.
     * 
     * @param nombre nombre del personaje
     */
    public Personaje(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = HP_INICIAL;
    }

    /**
     * Realiza un ataque causando daño aleatorio entre 10 y 30 puntos.
     */
    @Override
    public void atacar(IPersonaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println("  ⚔️  " + this.nombre + " ataca a " + oponente.getNombre()
                + " causando " + dano + " puntos de daño.");
    }

    /**
     * Recibe daño. Los HP no pueden bajar de 0.
     */
    @Override
    public void recibirDano(int dano) {
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0;
        }
    }

    @Override
    public boolean estaVivo() {
        return this.puntosDeVida > 0;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public int getPuntosDeVida() {
        return this.puntosDeVida;
    }
}