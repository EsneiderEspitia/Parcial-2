package com.parcial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase Main (Simulador y Batallas)")
class MainTest {

    @Test
    @DisplayName("instanciarCriatura(1) genera un Dragón con 300 HP y escamas de Obsidiana")
    void testInstanciarDragon() {
        Criatura c = Main.instanciarCriatura(1);
        assertNotNull(c);
        assertInstanceOf(Dragon.class, c);
        assertEquals("Dragón", c.getNombre());
        assertEquals(300.0, c.getHp(), 0.001);
        assertEquals("Obsidiana", ((Dragon) c).getEscamas());
    }

    @Test
    @DisplayName("instanciarCriatura(2) genera un Mago con 100 HP y hechizos Arcano")
    void testInstanciarMago() {
        Criatura c = Main.instanciarCriatura(2);
        assertNotNull(c);
        assertInstanceOf(Mago.class, c);
        assertEquals("Mago", c.getNombre());
        assertEquals(100.0, c.getHp(), 0.001);
        assertEquals("Arcano", ((Mago) c).getHechizos());
    }

    @Test
    @DisplayName("instanciarCriatura(3) genera un Guerrero con 150 HP y arma Acero Valyrio")
    void testInstanciarGuerrero() {
        Criatura c = Main.instanciarCriatura(3);
        assertNotNull(c);
        assertInstanceOf(Guerrero.class, c);
        assertEquals("Guerrero", c.getNombre());
        assertEquals(150.0, c.getHp(), 0.001);
        assertEquals("Acero Valyrio", ((Guerrero) c).getArma());
    }

    @Test
    @DisplayName("instanciarCriatura con opción inválida retorna null")
    void testInstanciarInvalido() {
        assertNull(Main.instanciarCriatura(0));
        assertNull(Main.instanciarCriatura(4));
        assertNull(Main.instanciarCriatura(-1));
    }

    @Test
    @DisplayName("simularBatalla() culmina dejando exactamente a una criatura con vida y a la otra sin vida")
    void testSimularBatallaFinalizaConGanadorUnico() {
        Criatura c1 = Main.instanciarCriatura(1); // Dragón (300 HP)
        Criatura c2 = Main.instanciarCriatura(3); // Guerrero (150 HP)

        Main.simularBatalla(c1, c2);

        // Se verifica que no hayan quedado ambas vivas ni ambas con vida mayor a 0 al mismo tiempo
        boolean c1Viva = c1.vida();
        boolean c2Viva = c2.vida();

        assertTrue(c1Viva ^ c2Viva, "Exactamente una de las dos criaturas debe ser la ganadora (con vida)");

        if (c1Viva) {
            assertTrue(c1.getHp() > 0);
            assertEquals(0.0, c2.getHp(), 0.001);
        } else {
            assertTrue(c2.getHp() > 0);
            assertEquals(0.0, c1.getHp(), 0.001);
        }
    }

    @Test
    @DisplayName("ejecutarFlujoREADME() ejecuta sorteo de dados y batalla completa sin errores")
    void testEjecutarFlujoREADME() {
        Criatura a = Main.instanciarCriatura(2); // Mago
        Criatura b = Main.instanciarCriatura(3); // Guerrero

        assertDoesNotThrow(() -> Main.ejecutarFlujoREADME(a, b));

        assertTrue(a.vida() ^ b.vida(), "Al terminar el flujo del README debe haber un único ganador");
    }
}
