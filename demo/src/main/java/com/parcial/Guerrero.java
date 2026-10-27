package com.parcial;

/**
 * Subclase concreta que representa a un Guerrero.
 * Ataca de cerca con su arma y se defiende firmemente con armadura y escudo.
 */
public class Guerrero extends Criatura {
    // Atributo específico requerido por el diagrama UML
    private String arma;

    /**
     * Constructor del Guerrero.
     * Vida inicial: 150 HP.
     * Fuerza base: 15 (ataca con su arma).
     */
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

    /**
     * El Guerrero ataca con su arma. Rango de daño base: 1 - 20 con efectividad x1.0 a x2.5.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int i = random.nextInt(atqs.length);
        // Rango de ataque 1-20
        int atq = 1 + random.nextInt(20 - 1 + 1);
        // Efectividad entre 1.0 y 2.5
        double efectividad = 1.0 + (random.nextDouble() * (2.5 - 1.0));
        int danoTotal = (int) Math.round(atq * efectividad);

        System.out.printf("%s %s, atacando a %s con %d puntos de daño.%n", 
                this.nombre, this.atqs[i], objetivo.getNombre(), danoTotal);

        // El objetivo recibe el ataque y se defiende
        objetivo.defender(danoTotal);
    }

    /**
     * El Guerrero se defiende con armadura y escudo. Multiplicador de defensa entre x0.4 y x1.1.
     */
    @Override
    public void defender(int danoRecibido) {
        int i = random.nextInt(defs.length);
        // Multiplicador de defensa entre 0.4 y 1.1
        double def = 0.4 + (random.nextDouble() * (1.1 - 0.4));
        int danoFinal = (int) Math.round(danoRecibido * def);

        this.salud -= danoFinal;
        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.printf("%s %s para defenderse, recibió %d puntos de daño.%n", 
                this.nombre, this.defs[i], danoFinal);
        System.out.printf("%s quedó con %d puntos de vida.%n", this.nombre, this.salud);
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }
}
