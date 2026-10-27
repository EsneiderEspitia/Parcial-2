package com.parcial;

import java.util.Random;

/**
 * Clase abstracta que define las características y comportamientos comunes
 * para todas las criaturas del juego de batalla según el diagrama UML.
 */
public abstract class Criatura {
    // Atributos protegidos requeridos por el diagrama UML
    protected String nombre;
    protected int salud;
    protected int fuerza;

    // Arreglos de frases descriptivas para personalizar los ataques y defensas
    protected String[] atqs;
    protected String[] defs;
    protected final Random random = new Random();

    /**
     * Constructor principal para inicializar los atributos de la criatura.
     * @param nombre Nombre de la criatura.
     * @param salud Puntos de vida iniciales.
     * @param fuerza Fuerza base para el ataque.
     */
    public Criatura(String nombre, int salud, int fuerza) {
        this.nombre = nombre;
        this.salud = salud;
        this.fuerza = fuerza;
    }

    /**
     * Constructor sobrecargado con salud y fuerza por defecto según el tipo.
     */
    public Criatura(String nombre, int salud) {
        this(nombre, salud, 10);
    }

    /**
     * Método abstracto que permite a una criatura atacar a otra.
     * @param objetivo Criatura que recibirá el ataque.
     */
    public abstract void atacar(Criatura objetivo);

    /**
     * Método abstracto que permite a una criatura defenderse de un ataque.
     * @param dano Cantidad de daño recibido del atacante.
     */
    public abstract void defender(int dano);

    /**
     * Método concreto que indica si la criatura sigue con vida (salud > 0).
     * @return true si la salud es mayor a cero, false en caso contrario.
     */
    public boolean estaViva() {
        return this.salud > 0;
    }

    /**
     * Alias de estaViva() para máxima compatibilidad.
     */
    public boolean vida() {
        return estaViva();
    }

    /**
     * Determina el turno inicial lanzando un dado aleatorio de 1 a 6.
     * @return Número entero entre 1 y 6.
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

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
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
