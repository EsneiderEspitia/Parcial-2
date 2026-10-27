package com.parcial;

import java.util.Random;

/**
 * Clase abstracta que define las características y comportamientos base
 * para todas las criaturas, según los lineamientos del README.
 */
public abstract class Criatura {
    protected String nombre;
    protected double hp;
    protected int salud;  // Sincronizado con hp para compatibilidad con diagramas UML
    protected int fuerza; // Atributo base para compatibilidad con diagramas UML

    protected String[] atqs;
    protected String[] defs;
    protected final Random random = new Random();

    public Criatura(String nombre, double hp, int fuerza) {
        this.nombre = nombre;
        this.hp = hp;
        this.salud = (int) Math.round(hp);
        this.fuerza = fuerza;
    }

    public Criatura(String nombre, int salud) {
        this(nombre, (double) salud, 10);
    }

    /**
     * Método de ataque según el README:
     * random i (atqs.length)
     * random atq (rango según la criatura)
     * random efectividad (rango según la criatura)
     * atqTotal = atq * efectividad
     * print criatura atqs[i], hizo atqTotal puntos de danho
     * return atqTotal
     */
    public abstract double atacar();

    /**
     * Sobrecarga de atacar(Criatura objetivo) para compatibilidad con UML.
     */
    public void atacar(Criatura objetivo) {
        objetivo.defender(this);
    }

    /**
     * Método de defensa según el README:
     * random i (defs.length)
     * random def (multiplicador según la criatura)
     * danhoR = criaturax.atacar * def
     * print criatura defs[i] para defenderse, recibió danhoR puntos de danho
     * hp = hp - danhoR
     * print criatura quedó con hp puntos de vida
     * return hp
     */
    public abstract double defender(Criatura criaturax);

    /**
     * Sobrecarga de defender con daño recibido directo.
     */
    public abstract double defender(double danoRecibido);

    /**
     * Sobrecarga de defender(int) para compatibilidad con diagramas UML.
     */
    public void defender(int dano) {
        defender((double) dano);
    }

    /**
     * Validación de vida según el README:
     * return hp > 0
     */
    public boolean vida() {
        return this.hp > 0;
    }

    /**
     * Alias de vida() para compatibilidad con diagramas UML.
     */
    public boolean estaViva() {
        return vida();
    }

    /**
     * Asignación aleatoria de turno según el README:
     * t = random(1 - 6)
     * return t
     */
    public int turno() {
        return random.nextInt(6) + 1;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
        this.salud = (int) Math.round(hp);
    }

    public int getSalud() {
        return (int) Math.round(hp);
    }

    public void setSalud(int salud) {
        this.salud = salud;
        this.hp = salud;
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

    public void setAtqs(String[] atqs) {
        this.atqs = atqs;
    }

    public String[] getDefs() {
        return defs;
    }

    public void setDefs(String[] defs) {
        this.defs = defs;
    }
}
