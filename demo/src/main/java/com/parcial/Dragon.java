package com.parcial;

public class Dragon extends Criatura {
    private String escamas;

    public Dragon(String nombre, String escamas) {
        super(nombre, 300);
        this.escamas = escamas;
        inicializarFrases();
    }

    public Dragon(String nombre, int salud, int fuerza, String escamas) {
        super(nombre, salud > 0 ? salud : 300, fuerza);
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

    @Override
    public double atacar() {
        int i = random.nextInt(atqs.length);
        // Dragón: rango de 10-25 puntos
        int atq = 10 + random.nextInt(25 - 10 + 1);
        // Efectividad: de x0.9 a x2.3 (amplitud = 1.4)
        double efectividad = 0.9 + (random.nextDouble() * (2.3 - 0.9));
        double atqTotal = atq * efectividad;

        System.out.printf("%s %s, hizo %.2f puntos de daño.%n", this.nombre, this.atqs[i], atqTotal);
        return atqTotal;
    }

    @Override
    public double defender(double atqCriaturaRival) {
        int i = random.nextInt(defs.length);
        // Dragón: defensa entre x0.7 y x1.9 (amplitud = 1.2)
        double def = 0.7 + (random.nextDouble() * (1.9 - 0.7));
        double danhoR = atqCriaturaRival * def;

        this.salud -= danhoR;
        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.printf("%s %s para defenderse, recibió %.2f puntos de daño.%n", this.nombre, this.defs[i], danhoR);
        System.out.printf("%s quedó con %.2f puntos de vida.%n", this.nombre, this.salud);
        return this.salud;
    }

    public String getEscamas() {
        return escamas;
    }

    public void setEscamas(String escamas) {
        this.escamas = escamas;
    }
}
