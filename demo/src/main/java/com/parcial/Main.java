package com.parcial;

import java.util.Scanner;

/**
 * Clase Principal (Main)
 * Contiene el método main y simularBatalla(Criatura criatura1, Criatura criatura2),
 * cumpliendo con la estructura del diagrama UML y aplicando polimorfismo.
 */
public class Main {

    /**
     * Simula una batalla entre dos criaturas aplicando polimorfismo,
     * tal como lo define el diagrama UML y los requisitos del examen.
     * @param criaturaA Primera criatura en combate
     * @param criaturaB Segunda criatura en combate
     */
    public static void simularBatalla(Criatura criaturaA, Criatura criaturaB) {
        System.out.println("\n==================================================");
        System.out.println("   INICIANDO SORTEO DE TURNOS: " + criaturaA.getNombre() + " vs " + criaturaB.getNombre());
        System.out.println("==================================================");

        Criatura criatura1 = null;
        Criatura criatura2 = null;
        int dado1, dado2;

        // Asignación aleatoria de dados (1 a 6). Repetir en caso de empate.
        do {
            dado1 = criaturaA.turno();
            dado2 = criaturaB.turno();
            System.out.println(criaturaA.getNombre() + " sacó en el dado: " + dado1);
            System.out.println(criaturaB.getNombre() + " sacó en el dado: " + dado2);

            if (dado1 > dado2) {
                criatura1 = criaturaA;
                criatura2 = criaturaB;
            } else if (dado2 > dado1) {
                criatura1 = criaturaB;
                criatura2 = criaturaA;
            } else {
                System.out.println("¡Empate! Volviendo a lanzar los dados...\n");
            }
        } while (dado1 == dado2);

        System.out.println("\n>>> " + criatura1.getNombre() + " obtuvo mayor puntaje. ¡Es la Criatura 1 y atacará primero!\n");

        int ronda = 1;
        // La batalla continúa hasta que una de las criaturas muera (estaViva() == false)
        while (criatura1.estaViva() && criatura2.estaViva()) {
            System.out.println("----------------- Ronda / Turno " + ronda + " -----------------");

            // Criatura 1 ataca a Criatura 2
            criatura1.atacar(criatura2);

            // Validar si Criatura 2 sigue viva
            if (!criatura2.estaViva()) {
                System.out.println("\n[!] " + criatura2.getNombre() + " ha muerto en combate.");
                break;
            }

            System.out.println();

            // Criatura 2 ataca a Criatura 1
            criatura2.atacar(criatura1);

            // Validar si Criatura 1 sigue viva
            if (!criatura1.estaViva()) {
                System.out.println("\n[!] " + criatura1.getNombre() + " ha muerto en combate.");
                break;
            }

            ronda++;
            System.out.println();
        }

        System.out.println("\n==================================================");
        System.out.println("               ¡BATALLA TERMINADA!");
        System.out.println("==================================================");
        if (criatura1.estaViva()) {
            System.out.printf("Ganador: %s con %d puntos de vida restantes.%n", criatura1.getNombre(), criatura1.getSalud());
        } else {
            System.out.printf("Ganador: %s con %d puntos de vida restantes.%n", criatura2.getNombre(), criatura2.getSalud());
        }
        System.out.println("==================================================\n");
    }

    /**
     * Fabrica una nueva instancia de Criatura con salud completa para la simulación.
     */
    public static Criatura instanciarCriatura(int opcion) {
        switch (opcion) {
            case 1:
                return new Dragon("Smaug el Dragón", "Obsidiana");
            case 2:
                return new Mago("Gandalf el Mago", "Arcano");
            case 3:
                return new Guerrero("Aragorn el Guerrero", "Acero Valyrio");
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        // Catálogo de criaturas base disponibles usando Polimorfismo
        try (Scanner scanner = new Scanner(System.in)) {
            // Catálogo de criaturas base disponibles usando Polimorfismo
            Criatura[] listaCriaturas = new Criatura[] {
                new Dragon("Smaug el Dragón", "Obsidiana"),
                new Mago("Gandalf el Mago", "Arcano"),
                new Guerrero("Aragorn el Guerrero", "Acero Valyrio")
            };
            
            System.out.println("==================================================");
            System.out.println("   SIMULADOR DE COMBATE DE CRIATURAS (PARCIAL 2)  ");
            System.out.println("==================================================");
            
            boolean ejecutando = true;
            while (ejecutando) {
                System.out.println("\nCriaturas disponibles:");
                for (int i = 0; i < listaCriaturas.length; i++) {
                    System.out.printf("  [%d] %s (Vida inicial: %d HP)%n", (i + 1), listaCriaturas[i].getNombre(), listaCriaturas[i].getSalud());
                }
                System.out.println("  [0] Salir del simulador");
                
                System.out.print("\nSeleccione la Criatura A (1-3) o 0 para salir: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("\n(Modo predeterminado: simulando Dragón vs Mago)");
                    simularBatalla(instanciarCriatura(1), instanciarCriatura(2));
                    break;
                }
                
                int opA = scanner.nextInt();
                if (opA == 0) {
                    System.out.println("¡Gracias por usar el simulador! Saliendo...");
                    break;
                }
                if (opA < 1 || opA > listaCriaturas.length) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }
                
                System.out.print("Seleccione la Criatura B (1-3): ");
                if (!scanner.hasNextInt()) {
                    break;
                }
                
                int opB = scanner.nextInt();
                if (opB < 1 || opB > listaCriaturas.length) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }
                
                // Crear instancias con vida completa para la simulación
                Criatura c1 = instanciarCriatura(opA);
                Criatura c2 = instanciarCriatura(opB);
                
                // Simular batalla polimórficamente
                simularBatalla(c1, c2);
                
                System.out.print("¿Desea simular otra batalla? (1: Sí, 0: No): ");
                if (scanner.hasNextInt()) {
                    int repetir = scanner.nextInt();
                    if (repetir != 1) {
                        ejecutando = false;
                        System.out.println("¡Gracias por usar el simulador! Saliendo...");
                    }
                } else {
                    ejecutando = false;
                }
            }
        }
    }
}
