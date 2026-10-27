package com.parcial;

/**
 * Subclase concreta que representa a un Mago.
 * Ataca utilizando poderosos hechizos mágicos y barreras arcanas.
 */
public class Mago extends Criatura {
    // Atributo específico requerido por el diagrama UML
    private String hechizos;

    /**
     * Constructor del Mago.
     * Vida inicial: 100 HP.
     * Fuerza base: 12 (ataca con hechizos).
     */
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

    /**
     * El Mago ataca con hechizos. Rango de daño base: 1 - 15 con alta efectividad (x1.3 a x4.5).
     */
    @Override
    public void atacar(Criatura objetivo) {
        int i = random.nextInt(atqs.length);
        // Rango de ataque 1-15
        int atq = 1 + random.nextInt(15 - 1 + 1);
        // Efectividad entre 1.3 y 4.5
        double efectividad = 1.3 + (random.nextDouble() * (4.5 - 1.3));
        int danoTotal = (int) Math.round(atq * efectividad);

        System.out.printf("%s %s, atacando a %s con %d puntos de daño.%n", 
                this.nombre, this.atqs[i], objetivo.getNombre(), danoTotal);

        // El objetivo recibe el ataque y se defiende
        objetivo.defender(danoTotal);
    }

    /**
     * El Mago se defiende con sus escudos arcanos. Multiplicador de defensa entre x0.9 y x1.4.
     */
    @Override
    public void defender(int danoRecibido) {
        int i = random.nextInt(defs.length);
        // Multiplicador de defensa entre 0.9 y 1.4
        double def = 0.9 + (random.nextDouble() * (1.4 - 0.9));
        int danoFinal = (int) Math.round(danoRecibido * def);

        this.salud -= danoFinal;
        if (this.salud < 0) {
            this.salud = 0;
        }

        System.out.printf("%s %s para defenderse, recibió %d puntos de daño.%n", 
                this.nombre, this.defs[i], danoFinal);
        System.out.printf("%s quedó con %d puntos de vida.%n", this.nombre, this.salud);
    }

    public String getHechizos() {
        return hechizos;
    }

    public void setHechizos(String hechizos) {
        this.hechizos = hechizos;
    }
}
