package juego.decorator;

import juego.personaje.IPersonaje;


    public EscudoMagicoDecorador(IPersonaje personaje, int cargas) {
        super(personaje);
        this.cargasEscudo = cargas;
    }

    @Override
    public void recibirDano(int dano) {
        if (cargasEscudo > 0) {
            int danoReducido = (int) (dano * 0.6); // absorbe 40%
            int absorbido = dano - danoReducido;
            System.out.println("    [Escudo Mágico] Absorbe " + absorbido
                    + " puntos de daño. Cargas restantes: " + (cargasEscudo - 1));
            cargasEscudo--;
            personajeDecorado.recibirDano(danoReducido);
        } else {
            System.out.println("   [Escudo Mágico] Sin cargas. Daño completo aplicado.");
            personajeDecorado.recibirDano(dano);
        }
    }

    @Override
    public String getNombre() {
        return personajeDecorado.getNombre() + " [Escudo Mágico x" + cargasEscudo + "]";
    }
}
