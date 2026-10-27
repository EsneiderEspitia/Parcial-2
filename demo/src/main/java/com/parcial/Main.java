package com.parcial;

import java.util.Scanner;

/**
 * Clase Principal (Main)
 * Implementa el funcionamiento lógico y el combate por turnos especificado en el README.
 */
public class Main {

    /**
     * Simulación de combate por turnos según el pseudocódigo del README:
     *
     * simularBatalla(criatura1, criatura2) {
     *   while (criatura1.vida y criatura2.vida) {
     *       criatura2.defender
     *       criatura1.defender
     *   }
     * }
     *
     * Siguiendo el orden de ejecución especificado en la sección 3 del README:
     * 1. criatura1 atacará >>> atqTotal = (atq * efectividad)
     * 2. criatura 2 se defenderá >>> hp = hp - (criatura1.atacar * def)
     * 3. validar vida criatura 2 >>> hp > 0
     * 4. criatura 2 atacará >>> atqTotal = (atq * efectividad)
     * 5. criatura 1 se defenderá >>> hp = hp - (criatura2.atacar * def)
     * 6. validar vida criatura 1 >>> hp > 0
     *
     * Este ciclo se rompe cuando la validación de vida de alguna criatura sea negativa (hp <= 0).
     */
    public static void simularBatalla(Criatura criatura1, Criatura criatura2) {
        System.out.println("==================================================");
        System.out.println("   SIMULACIÓN DE BATALLA: " + criatura1.getNombre() + " vs " + criatura2.getNombre());
        System.out.println("==================================================");

        int ronda = 1;
        while (criatura1.vida() && criatura2.vida()) {
            System.out.println("----------------- Turno " + ronda + " -----------------");

            // criatura 2 se defiende (ejecuta criatura1.atacar() y aplica def)
            criatura2.defender(criatura1);

            // validar vida criatura 2
            if (!criatura2.vida()) {
                System.out.println("\n[!] " + criatura2.getNombre() + " se ha quedado sin vida.");
                break;
            }

            System.out.println();

            // criatura 1 se defiende (ejecuta criatura2.atacar() y aplica def)
            criatura1.defender(criatura2);

            // validar vida criatura 1
            if (!criatura1.vida()) {
                System.out.println("\n[!] " + criatura1.getNombre() + " se ha quedado sin vida.");
                break;
            }

            ronda++;
            System.out.println();
        }

        System.out.println("\n==================================================");
        System.out.println("               FIN DE LA BATALLA");
        System.out.println("==================================================");
        if (criatura1.vida()) {
            System.out.printf("Ganador: %s con %.2f puntos de vida restantes.%n", criatura1.getNombre(), criatura1.getHp());
        } else {
            System.out.printf("Ganador: %s con %.2f puntos de vida restantes.%n", criatura2.getNombre(), criatura2.getHp());
        }
        System.out.println("==================================================\n");
    }

    /**
     * Fabrica una nueva instancia de la criatura seleccionada con vida completa.
     */
    public static Criatura instanciarCriatura(int opcion) {
        switch (opcion) {
            case 1:
                return new Dragon("Dragón", "Obsidiana");
            case 2:
                return new Mago("Mago", "Arcano");
            case 3:
                return new Guerrero("Guerrero", "Acero Valyrio");
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        // Array donde se guardan las criaturas según el README
        Criatura[] catalogo = new Criatura[] {
            new Dragon("Dragón", "Obsidiana"),
            new Mago("Mago", "Arcano"),
            new Guerrero("Guerrero", "Acero Valyrio")
        };

        System.out.println("==================================================");
        System.out.println("   SIMULADOR DE COMBATE DE CRIATURAS (PARCIAL 2)  ");
        System.out.println("==================================================");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean ejecutando = true;
            while (ejecutando) {
                // Ciclo que permite la impresión y la selección de las criaturas a simular
                System.out.println("\nCriaturas disponibles:");
                for (int i = 0; i < catalogo.length; i++) {
                    System.out.printf("  [%d] %s (Vida inicial: %.0f HP)%n", (i + 1), catalogo[i].getNombre(), catalogo[i].getHp());
                }
                System.out.println("  [0] Salir del simulador");

                System.out.print("\nSeleccione la criatura a (1-3) o 0 para salir: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("\n(Entrada no interactiva: simulando Dragón vs Mago)");
                    ejecutarFlujoREADME(instanciarCriatura(1), instanciarCriatura(2));
                    break;
                }

                int opA = scanner.nextInt();
                if (opA == 0) {
                    System.out.println("¡Gracias por usar el simulador! Saliendo...");
                    break;
                }
                if (opA < 1 || opA > catalogo.length) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }

                System.out.print("Seleccione la criatura b (1-3): ");
                if (!scanner.hasNextInt()) {
                    break;
                }

                int opB = scanner.nextInt();
                if (opB < 1 || opB > catalogo.length) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }

                Criatura a = instanciarCriatura(opA);
                Criatura b = instanciarCriatura(opB);

                // En caso de que se enfrenten criaturas del mismo tipo
                if (opA == opB) {
                    a.setNombre(a.getNombre() + " 1");
                    b.setNombre(b.getNombre() + " 2");
                }

                ejecutarFlujoREADME(a, b);

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

    /**
     * Ejecuta el flujo exacto del README para asignación de turnos y combate:
     *
     * do {
     *     a.turno
     *     b.turno
     * } while (a.t === b.t)
     *
     * if (a.t > b.t) criatura1 = a
     *                criatura2 = b
     * else           criatura1 = b
     *                criatura2 = a
     *
     * simularBatalla(criatura1, criatura2)
     */
    public static void ejecutarFlujoREADME(Criatura a, Criatura b) {
        System.out.println("\n==================================================");
        System.out.println("   SORTEO DE TURNOS (DADOS 1 - 6): " + a.getNombre() + " vs " + b.getNombre());
        System.out.println("==================================================");

        int tA, tB;
        do {
            tA = a.turno();
            tB = b.turno();
            System.out.println(a.getNombre() + " sacó en el dado: " + tA);
            System.out.println(b.getNombre() + " sacó en el dado: " + tB);
            if (tA == tB) {
                System.out.println("¡Empate en los dados! Volviendo a asignar de manera aleatoria...\n");
            }
        } while (tA == tB);

        Criatura criatura1;
        Criatura criatura2;
        if (tA > tB) {
            criatura1 = a;
            criatura2 = b;
        } else {
            criatura1 = b;
            criatura2 = a;
        }

        System.out.println("\n>>> " + criatura1.getNombre() + " obtuvo mayor puntaje (" + Math.max(tA, tB) + " vs " + Math.min(tA, tB) + "). ¡Es criatura1 y atacará primero!\n");

        simularBatalla(criatura1, criatura2);
    }
}
