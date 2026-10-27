package com.parcial;

// Clase abstracta que define las características comunes.
public abstract class Criatura {

    protected String nombre;
    protected int salud;
    protected int fuerza;

    // Constructor para inicializar una criatura.
    public Criatura(String nombre, int salud, int fuerza) {
        this.nombre = nombre;
        this.salud = salud;
        this.fuerza = fuerza;
    }

    // Cada criatura define su propio ataque.
    public abstract void atacar(Criatura objetivo);

    // Cada criatura define cómo se defiende.
    public abstract void defender(int dano);

    // Comprueba si la criatura sigue viva.
    public boolean estaViva() {
        return salud > 0;
    }
}

public void atacar(Criatura objetivo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'atacar'");
}