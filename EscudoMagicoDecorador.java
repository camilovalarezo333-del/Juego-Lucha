package juego.decorator;

import juego.personaje.IPersonaje;

/**
 * Decorator concreto: Escudo Mágico.
 * Reduce el daño recibido en un 40% (escudo absorbe parte del impacto).
 *
 * Uso: new EscudoMagicoDecorador(personaje)
 * Resultado: el personaje ahora recibe solo el 60% del daño.
 */
public class EscudoMagicoDecorador extends PersonajeDecorador {

    private int cargasEscudo;

    /**
     * @param personaje el personaje a decorar
     * @param cargas    número de veces que el escudo puede absorber daño
     */
    public EscudoMagicoDecorador(IPersonaje personaje, int cargas) {
        super(personaje);
        this.cargasEscudo = cargas;
    }

    /**
     * Intercepta el daño: si quedan cargas, reduce el daño en un 40%.
     */
    @Override
    public void recibirDano(int dano) {
        if (cargasEscudo > 0) {
            int danoReducido = (int) (dano * 0.6); // absorbe 40%
            int absorbido = dano - danoReducido;
            System.out.println("  🛡️  [Escudo Mágico] Absorbe " + absorbido
                    + " puntos de daño. Cargas restantes: " + (cargasEscudo - 1));
            cargasEscudo--;
            personajeDecorado.recibirDano(danoReducido);
        } else {
            System.out.println("  🛡️  [Escudo Mágico] Sin cargas. Daño completo aplicado.");
            personajeDecorado.recibirDano(dano);
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Escudo Mágico x" + cargasEscudo + "]";
    }
}