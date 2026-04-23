package juego.proxy;

import juego.personaje.IPersonaje;


public class PersonajeProxy implements IPersonaje {


    private IPersonaje personajeReal;

  
    private int contadorAtaques;

  
    private int contadorGolpesRecibidos;


    private int totalDanoInfligido;

    public PersonajeProxy(IPersonaje personaje) {
        this.personajeReal = personaje;
        this.contadorAtaques = 0;
        this.contadorGolpesRecibidos = 0;
        this.totalDanoInfligido = 0;
    }


    @Override
    public void atacar(IPersonaje oponente) {
    
        if (!personajeReal.estaVivo()) {
            System.out.println("  [PROXY] " + getNombre()
                    + " no puede atacar: ¡está derrotado!");
            return;
        }
  
        if (!oponente.estaVivo()) {
            System.out.println("  [PROXY] El oponente ya está derrotado.");
            return;
        }

        int hpAntes = oponente.getPuntosDeVida();
        contadorAtaques++;

     
        personajeReal.atacar(oponente);

        int danoRealCausado = hpAntes - oponente.getPuntosDeVida();
        totalDanoInfligido += danoRealCausado;
    }


    @Override
    public void recibirDano(int dano) {
        contadorGolpesRecibidos++;
        personajeReal.recibirDano(dano);
    }

    @Override
    public boolean estaVivo() {
        return personajeReal.estaVivo();
    }

    @Override
    public String getNombre() {
        return personajeReal.getNombre();
    }

    @Override
    public int getPuntosDeVida() {
        return personajeReal.getPuntosDeVida();
    }

   

    public int getContadorAtaques() {
        return contadorAtaques;
    }

    public int getContadorGolpesRecibidos() {
        return contadorGolpesRecibidos;
    }

    public int getTotalDanoInfligido() {
        return totalDanoInfligido;
    }

    public void mostrarEstadisticas() {
        System.out.println("  📊 Estadísticas de " + getNombre() + ":");
        System.out.println("     - Ataques realizados : " + contadorAtaques);
        System.out.println("     - Golpes recibidos   : " + contadorGolpesRecibidos);
        System.out.println("     - Daño total causado : " + totalDanoInfligido);
        System.out.println("     - HP final           : " + getPuntosDeVida());
    }
}
