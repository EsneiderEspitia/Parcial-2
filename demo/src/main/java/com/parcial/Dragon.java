package com.parcial;

/**
 * Subclase concreta Dragón según el README:
 * Posee 300 puntos de vida; su ataque estará en un rango de 10-25 puntos,
 * con una efectividad que puede ir de x0.9 a x2.3; y su defensa estará entre
 * x0.7 y x3.9
 */
public class Dragon extends Criatura {
    private String escamas;

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

    @Override
    public double atacar() {
        int i = random.nextInt(atqs.length);
        // Ataque en rango de 10-25 puntos
        int atq = 10 + random.nextInt(25 - 10 + 1);
        // Efectividad de x0.9 a x2.3
        double efectividad = 0.9 + (random.nextDouble() * (2.3 - 0.9));
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
        // Multiplicador de defensa entre x0.7 y x3.9
        double def = 0.7 + (random.nextDouble() * (3.9 - 0.7));
        double danhoR = danoRecibido * def;

        System.out.printf("%s %s para defenderse, recibió %.2f puntos de danho.%n", this.nombre, this.defs[i], danhoR);
        this.hp = Math.max(0, this.hp - danhoR);
        this.salud = (int) Math.round(this.hp);
        System.out.printf("%s quedó con %.2f puntos de vida.%n", this.nombre, this.hp);
        return this.hp;
    }

    public String getEscamas() {
        return escamas;
    }

    public void setEscamas(String escamas) {
        this.escamas = escamas;
    }
}
