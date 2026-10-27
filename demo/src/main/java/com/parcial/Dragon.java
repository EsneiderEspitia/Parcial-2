package com.parcial;

/**
 * Subclase concreta que representa a un Dragón.
 * Posee escamas resistentes y un ataque más fuerte potenciado (fuerza * 2).
 */
public class Dragon extends Criatura {
    // Atributo específico requerido por el diagrama UML
    private String escamas;

    /**
     * Constructor del Dragón.
     * Vida inicial: 300 HP.
     * Fuerza base: 20 (ataque fuerte: fuerza * 2).
     */
    public Dragon(String nombre, String escamas) {
        super(nombre, 300, 20);
        this.escamas = escamas;
        inicializarFrases();
    }

    public Dragon(String nombre, int salud, int fuerza, String escamas) {
        super(nombre, salud > 0 ? salud : 300, fuerza > 0 ? fuerza : 20);
        this.escamas = escamas;
        inicializarFrases();
    }

    private void inicializarFrases() {
        this.atqs = new String[] {
            "escupe una ráfaga devastadora de fuego infernal",
            "golpea violentamente con su gigantesca cola con púas",
            "se lanza en picada desgarrando con sus garras afiladas",
            "arroja una llamarada ardiente que calcina el suelo"
        };
        this.defs = new String[] {
            "interpone sus duras escamas de " + escamas,
            "despliega sus enormes alas blindadas",
            "alza el vuelo rápidamente amortiguando el impacto",
            "exhala una densa nube de humo ardiente para desviar el golpe"
        };
    }

    /**
     * El Dragón realiza un ataque más fuerte (fuerza * 2) con efectividad entre x0.9 y x2.3.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int i = random.nextInt(atqs.length);
        // Rango de ataque 10-25 basado en su fuerza
        int atq = 10 + random.nextInt(25 - 10 + 1);
        // Efectividad de x0.9 a x2.3
        double efectividad = 0.9 + (random.nextDouble() * (2.3 - 0.9));
        int danoTotal = (int) Math.round(atq * efectividad);

        System.out.printf("%s %s, atacando a %s con %d puntos de daño.%n", 
                this.nombre, this.atqs[i], objetivo.getNombre(), danoTotal);
        
        // El objetivo recibe el ataque y se defiende
        objetivo.defender(danoTotal);
    }

    /**
     * El Dragón se defiende con sus escamas. Multiplicador de defensa entre x0.7 y x1.9.
     */
    @Override
    public void defender(int danoRecibido) {
        int i = random.nextInt(defs.length);
        // Multiplicador de defensa entre 0.7 y 1.9
        double def = 0.7 + (random.nextDouble() * (1.9 - 0.7));
        int danoFinal = (int) Math.round(danoRecibido * def);

        this.salud -= danoFinal;
        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.printf("%s %s para defenderse, recibió %d puntos de daño.%n", 
                this.nombre, this.defs[i], danoFinal);
        System.out.printf("%s quedó con %d puntos de vida.%n", this.nombre, this.salud);
    }

    public String getEscamas() {
        return escamas;
    }

    public void setEscamas(String escamas) {
        this.escamas = escamas;
    }
}
