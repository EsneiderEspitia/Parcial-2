package com.parcial;

/**
 * Subclase concreta Guerrero según el README:
 * Posee 150 puntos de vida; su ataque estará en un rango de 1-20 puntos,
 * con una efectividad que puede ir de x1.0 a x2.5; y su defensa estará entre x0.4 y x1.1
 */
public class Guerrero extends Criatura {
    private String arma;

    public Guerrero(String nombre, String arma) {
        super(nombre, 150, 15);
        this.arma = arma;
        inicializarFrases();
    }

    public Guerrero(String nombre, int salud, int fuerza, String arma) {
        super(nombre, salud > 0 ? salud : 150, fuerza > 0 ? fuerza : 15);
        this.arma = arma;
        inicializarFrases();
    }

    private void inicializarFrases() {
        this.atqs = new String[] {
            "asesta una estocada letal con su " + arma,
            "ejecuta un corte giratorio devastador con su " + arma,
            "embiste furiosamente quebrando la guardia rival con su " + arma,
            "descarga una combinación implacable de impactos con su " + arma
        };
        this.defs = new String[] {
            "bloquea firmemente el golpe con su escudo de guerra",
            "desvía el impacto utilizando la hoja de su " + arma,
            "resiste el embate protegiéndose con su armadura de placas",
            "rueda por el suelo reduciendo la fuerza del impacto"
        };
    }

    @Override
    public double atacar() {
        int i = random.nextInt(atqs.length);
        // Ataque en rango de 1-20 puntos
        int atq = 1 + random.nextInt(20 - 1 + 1);
        // Efectividad de x1.0 a x2.5
        double efectividad = 1.0 + (random.nextDouble() * (2.5 - 1.0));
        double atqTotal = atq * efectividad;

        System.out.printf("%s %s, hizo %.2f puntos de danho.%n", this.nombre, this.atqs[i], atqTotal);
        return atqTotal;
    }

    @Override
    public double defender(Criatura criaturax) {
        double atqTotal = criaturax.atacar();
        return defender(atqTotal);
    }

    @Override
    public double defender(double danoRecibido) {
        int i = random.nextInt(defs.length);
        // Multiplicador de defensa entre x0.4 y x1.1
        double def = 0.4 + (random.nextDouble() * (1.1 - 0.4));
        double danhoR = danoRecibido * def;

        System.out.printf("%s %s para defenderse, recibió %.2f puntos de danho.%n", this.nombre, this.defs[i], danhoR);
        this.hp = Math.max(0, this.hp - danhoR);
        this.salud = (int) Math.round(this.hp);
        System.out.printf("%s quedó con %.2f puntos de vida.%n", this.nombre, this.hp);
        return this.hp;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }
}
