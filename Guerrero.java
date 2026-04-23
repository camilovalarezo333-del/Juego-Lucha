package juego.personaje;

import java.util.Random;

/**
 * Subclase Guerrero: personaje especializado en combate cuerpo a cuerpo.
 * Tiene un bono de daño extra con probabilidad del 30%.
 *
 * Herencia requerida por el enunciado para implementar patrones creacionales.
 */
public class Guerrero extends Personaje {
    private String tipoArma;

    public Guerrero(String nombre, String tipoArma) {
        super(nombre);
        this.tipoArma = tipoArma;
    }

    /**
     * El Guerrero tiene un 30% de probabilidad de golpe crítico (+10 daño extra).
     */
    @Override
    public void atacar(IPersonaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt(21) + 10; // 10-30 base
        if (rand.nextInt(10) < 3) { // 30% golpe crítico
            dano += 10;
            System.out.println("  💥 ¡GOLPE CRÍTICO con " + tipoArma + "!");
        }
        oponente.recibirDano(dano);
        System.out.println("  ⚔️  " + getNombre() + " [Guerrero] ataca a " + oponente.getNombre()
                + " con " + tipoArma + " causando " + dano + " puntos de daño.");
    }

    public String getTipoArma() {
        return tipoArma;
    }
}