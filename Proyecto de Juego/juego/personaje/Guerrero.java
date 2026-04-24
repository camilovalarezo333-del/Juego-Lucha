package juego.personaje;

import java.util.Random;


public class Guerrero extends Personaje {
    private String tipoArma;

    public Guerrero(String nombre, String tipoArma) {
        super(nombre);
        this.tipoArma = tipoArma;
    }

    

    @Override
    public void atacar(IPersonaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt(21) + 10; // 10-30 base
        if (rand.nextInt(10) < 3) { // 30% golpe crítico
            dano += 10;
            System.out.println("  ¡GOLPE CRÍTICO con " + tipoArma + "!");
        }
        oponente.recibirDano(dano);
        System.out.println("    " + getNombre() + " [Guerrero] ataca a " + oponente.getNombre()
                + " con " + tipoArma + " causando " + dano + " puntos de daño.");
    }

    public String getTipoArma() {
        return tipoArma;
    }
}
