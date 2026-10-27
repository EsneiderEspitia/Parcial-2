package com.parcial;

import java.util.Random;

public abstract class Criatura {
    protected String nombre;
    protected double salud; // hp
    protected int fuerza;   // atq base
    protected String[] atqs;
    protected String[] defs;
    protected Random random = new Random();

    public Criatura(String nombre, double salud) {
        this.nombre = nombre;
        this.salud = salud;
        this.fuerza = 0;
    }

    public Criatura(String nombre, double salud, int fuerza) {
        this.nombre = nombre;
        this.salud = salud;
        this.fuerza = fuerza;
    }

    // Métodos abstractos según la guía del parcial
    public abstract double atacar();
    public abstract double defender(double atqCriaturaRival);

    // Método concreto vida(): return hp > 0
    public boolean vida() {
        return this.salud > 0;
    }

    // Método turno(): dado de 1 a 6
    public int turno() {
        return random.nextInt(6) + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalud() {
        return salud;
    }

    public void setSalud(double salud) {
        this.salud = salud;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public String[] getAtqs() {
        return atqs;
    }

    public String[] getDefs() {
        return defs;
    }
}
