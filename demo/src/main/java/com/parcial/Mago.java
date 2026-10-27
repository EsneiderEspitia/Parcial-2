package com.parcial;

/**
 * Subclase concreta Mago según el README:
 * Posee 100 puntos de vida; su ataque estará en un rango de 1-15 puntos,
 * con una efectividad que puede ir de x1.3 a x5.5; y su defensa estará entre
 * x0.9 y x1.1
 */
public class Mago extends Criatura {
    private String hechizos;

    public Mago(String nombre, String hechizos) {
        super(nombre, 100, 12);
        this.hechizos = hechizos;
        inicializarFrases();
    }

    public Mago(String nombre, int salud, int fuerza, String hechizos) {
        super(nombre, salud > 0 ? salud : 100, fuerza > 0 ? fuerza : 12);
        this.hechizos = hechizos;
        inicializarFrases();
    }

    private void inicializarFrases() {
        this.atqs = new String[] {
                "canaliza energía arcana y lanza el hechizo " + hechizos,
                "invoca una devastadora columna de rayos estelares",
                "conjura una esfera de fuego mágico abrasador",
                "dispara una ráfaga de hielo místico concentrado"
        };
        this.defs = new String[] {
                "proyecta un escudo mágico resplandeciente",
                "crea un portal dimensional que absorbe el impacto",
                "se desvanece brevemente en el plano astral",
                "conjura una barrera protectora de runas antiguas"
        };
    }

    @Override
    public double atacar() {
        int i = random.nextInt(atqs.length);
        // Ataque en rango de 1-15 puntos
        int atq = 1 + random.nextInt(15 - 1 + 1);
        // Efectividad de x1.3 a x5.5
        double efectividad = 1.3 + (random.nextDouble() * (5.5 - 1.3));
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
        // Multiplicador de defensa entre x0.9 y x1.1
        double def = 0.9 + (random.nextDouble() * (1.1 - 0.9));
        double danhoR = danoRecibido * def;

        System.out.printf("%s %s para defenderse, recibió %.2f puntos de danho.%n", this.nombre, this.defs[i], danhoR);
        this.hp = Math.max(0, this.hp - danhoR);
        this.salud = (int) Math.round(this.hp);
        System.out.printf("%s quedó con %.2f puntos de vida.%n", this.nombre, this.hp);
        return this.hp;
    }

    public String getHechizos() {
        return hechizos;
    }

    public void setHechizos(String hechizos) {
        this.hechizos = hechizos;
    }
}
