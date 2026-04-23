package juego.personaje;

import java.util.Random;

/**
 * Subclase Mago: personaje con ataques mágicos.
 * Tiene menor daño base pero ignora el 20% de la defensa del oponente.
 * Herencia requerida por el enunciado.
 */
public class Mago extends Personaje {
    private String escuelaMagia;

    public Mago(String nombre, String escuelaMagia) {
        super(nombre);
        this.escuelaMagia = escuelaMagia;
    }

    /**
     * El Mago ataca con daño 10-25 pero tiene 20% de probabilidad de hechizo doble.
     */
    @Override
    public void atacar(IPersonaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt(16) + 10; // 10-25 base
        oponente.recibirDano(dano);
        System.out.println("  🔮 " + getNombre() + " [Mago de " + escuelaMagia + "] lanza un hechizo sobre "
                + oponente.getNombre() + " causando " + dano + " puntos de daño.");

        // 20% hechizo doble
        if (rand.nextInt(10) < 2) {
            int dano2 = rand.nextInt(11) + 5; // 5-15 daño extra
            oponente.recibirDano(dano2);
            System.out.println("  ✨ ¡Hechizo doble! Daño adicional: " + dano2 + " puntos.");
        }
    }

    public String getEscuelaMagia() {
        return escuelaMagia;
    }
}