package juego.personaje;

import java.util.Random;

/**
 * Subclase Arquero: personaje ágil con ataques a distancia.
 * Tiene 25% de probabilidad de evadir el siguiente ataque recibido.
 * Herencia requerida por el enunciado.
 */
public class Arquero extends Personaje {
    private boolean evasionActiva;

    public Arquero(String nombre) {
        super(nombre);
        this.evasionActiva = false;
    }

    @Override
    public void atacar(IPersonaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt(21) + 10; // 10-30 base
        oponente.recibirDano(dano);
        System.out.println("  🏹 " + getNombre() + " [Arquero] dispara a " + oponente.getNombre()
                + " causando " + dano + " puntos de daño.");
        // Activa evasion con 25% de probabilidad tras atacar
        evasionActiva = rand.nextInt(4) == 0;
        if (evasionActiva) {
            System.out.println("  🌀 " + getNombre() + " se prepara para evadir el próximo ataque.");
        }
    }

    /**
     * Si la evasión está activa, anula el daño recibido una vez.
     */
    @Override
    public void recibirDano(int dano) {
        if (evasionActiva) {
            System.out.println("  💨 " + getNombre() + " ¡EVADE el ataque completamente!");
            evasionActiva = false;
        } else {
            super.recibirDano(dano);
        }
    }
}