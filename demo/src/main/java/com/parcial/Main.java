package com.parcial;

import java.util.Scanner;

public class Main {

    /**
     * Ejecuta el combate por turnos entre dos criaturas según las reglas del parcial.
     */
    public static void simularBatalla(Criatura a, Criatura b) {
        System.out.println("\n==================================================");
        System.out.println("   INICIANDO SORTEO DE TURNOS: " + a.getNombre() + " vs " + b.getNombre());
        System.out.println("==================================================");

        Criatura criatura1 = null;
        Criatura criatura2 = null;
        int dado1, dado2;

        // 2. Asignar aleatoria y paralelamente un número entre 1 y 6. Repetir en caso de empate.
        do {
            dado1 = a.turno();
            dado2 = b.turno();
            System.out.println(a.getNombre() + " sacó en el dado: " + dado1);
            System.out.println(b.getNombre() + " sacó en el dado: " + dado2);

            if (dado1 > dado2) {
                criatura1 = a;
                criatura2 = b;
            } else if (dado2 > dado1) {
                criatura1 = b;
                criatura2 = a;
            } else {
                System.out.println("¡Empate! Volviendo a lanzar los dados...\n");
            }
        } while (dado1 == dado2);

        System.out.println("\n>>> " + criatura1.getNombre() + " obtuvo mayor puntaje. ¡Es la Criatura 1 y atacará primero!\n");

        int ronda = 1;
        // 3. El ciclo se romperá solo cuando la validación de vida de alguna de las 2 criaturas sea negativa / falsa (hp > 0)
        while (criatura1.vida() && criatura2.vida()) {
            System.out.println("----------------- Turno " + ronda + " -----------------");

            // - criatura1 atacará >>> atqTotal = (atq * efectividad)
            double atqC1 = criatura1.atacar();
            // - criatura 2 se defenderá >>> hp = hp - (criatura1.atacar * def)
            criatura2.defender(atqC1);

            // - validar vida criatura 2 >>> hp > 0
            if (!criatura2.vida()) {
                System.out.println("\n[!] " + criatura2.getNombre() + " se ha quedado sin puntos de vida.");
                break;
            }

            System.out.println();

            // - criatura 2 atacará >>> atqTotal = (atq * efectividad)
            double atqC2 = criatura2.atacar();
            // - criatura 1 se defenderá >>> hp = hp - (criatura2.atacar * def)
            criatura1.defender(atqC2);

            // - validar vida criatura 1 >>> hp > 0
            if (!criatura1.vida()) {
                System.out.println("\n[!] " + criatura1.getNombre() + " se ha quedado sin puntos de vida.");
                break;
            }

            ronda++;
            System.out.println();
        }

        System.out.println("\n==================================================");
        System.out.println("               ¡BATALLA TERMINADA!");
        System.out.println("==================================================");
        if (criatura1.vida()) {
            System.out.printf("Ganador: %s con %.2f puntos de vida restantes.%n", criatura1.getNombre(), criatura1.getSalud());
        } else {
            System.out.printf("Ganador: %s con %.2f puntos de vida restantes.%n", criatura2.getNombre(), criatura2.getSalud());
        }
        System.out.println("==================================================\n");
    }

    /**
     * Fabrica una nueva instancia con vida completa para simular el combate.
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
        Scanner scanner = new Scanner(System.in);

        // Array donde se guardan las criaturas base disponibles
        Criatura[] listaCriaturas = new Criatura[] {
            new Dragon("Smaug el Dragón", "Obsidiana"),
            new Mago("Gandalf el Mago", "Arcano"),
            new Guerrero("Aragorn el Guerrero", "Acero Valyrio")
        };

        System.out.println("==================================================");
        System.out.println("   SIMULADOR DE COMBATE DE CRIATURAS (PARCIAL 2)  ");
        System.out.println("==================================================");

        // Ciclo que permite la impresión y la selección de las criaturas a simular
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\nCriaturas disponibles:");
            for (int i = 0; i < listaCriaturas.length; i++) {
                System.out.printf("  [%d] %s (Vida inicial: %.0f HP)%n", (i + 1), listaCriaturas[i].getNombre(), listaCriaturas[i].getSalud());
            }
            System.out.println("  [0] Salir del simulador");

            System.out.print("\nSeleccione la Criatura A (1-3) o 0 para salir: ");
            if (!scanner.hasNextInt()) {
                System.out.println("\n(Modo no interactivo detectado: ejecutando simulación predeterminada: Dragón vs Mago)");
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

            // Ejecución de la simulación
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

        scanner.close();
    }
}
