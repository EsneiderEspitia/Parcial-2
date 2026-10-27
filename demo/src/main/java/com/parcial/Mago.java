package com.parcial;

public class Mago extends Criatura {
    private String hechizos;

    public Mago(String nombre, String hechizos) {
        super(nombre, 100);
        this.hechizos = hechizos;
        inicializarFrases();
    }

    public Mago(String nombre, int salud, int fuerza, String hechizos) {
        super(nombre, salud > 0 ? salud : 100, fuerza);
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
        // Mago: rango de 1-15 puntos
        int atq = 1 + random.nextInt(15 - 1 + 1);
        // Efectividad: de x1.3 a x4.5 (amplitud = 3.2)
        double efectividad = 1.3 + (random.nextDouble() * (4.5 - 1.3));
        double atqTotal = atq * efectividad;

        System.out.printf("%s %s, hizo %.2f puntos de daño.%n", this.nombre, this.atqs[i], atqTotal);
        return atqTotal;
    }

    @Override
    public double defender(double atqCriaturaRival) {
        int i = random.nextInt(defs.length);
        // Mago: defensa entre x0.9 y x1.4 (amplitud = 0.5)
        double def = 0.9 + (random.nextDouble() * (1.4 - 0.9));
        double danhoR = atqCriaturaRival * def;

        this.salud -= danhoR;
        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.printf("%s %s para defenderse, recibió %.2f puntos de daño.%n", this.nombre, this.defs[i], danhoR);
        System.out.printf("%s quedó con %.2f puntos de vida.%n", this.nombre, this.salud);
        return this.salud;
    }

    public String getHechizos() {
        return hechizos;
    }

    public void setHechizos(String hechizos) {
        this.hechizos = hechizos;
    }
}
