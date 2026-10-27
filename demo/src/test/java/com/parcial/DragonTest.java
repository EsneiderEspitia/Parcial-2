package com.parcial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase Dragon")
class DragonTest {

    private Dragon dragon;

    @BeforeEach
    void setUp() {
        dragon = new Dragon("Smaug", "Obsidiana");
    }

    @Test
    @DisplayName("Constructor estándar inicializa 300 HP, 20 fuerza, escamas y frases")
    void testConstructorEstandar() {
        assertEquals("Smaug", dragon.getNombre());
        assertEquals(300.0, dragon.getHp(), 0.001);
        assertEquals(300, dragon.getSalud());
        assertEquals(20, dragon.getFuerza());
        assertEquals("Obsidiana", dragon.getEscamas());

        assertNotNull(dragon.getAtqs());
        assertEquals(4, dragon.getAtqs().length);
        assertNotNull(dragon.getDefs());
        assertEquals(4, dragon.getDefs().length);
    }

    @Test
    @DisplayName("Constructor sobrecargado con valores positivos y valores por defecto cuando son <= 0")
    void testConstructorSobrecargado() {
        Dragon dragonCustom = new Dragon("Balerion", 350, 30, "Diamante");
        assertEquals("Balerion", dragonCustom.getNombre());
        assertEquals(350.0, dragonCustom.getHp(), 0.001);
        assertEquals(30, dragonCustom.getFuerza());
        assertEquals("Diamante", dragonCustom.getEscamas());

        // Valores no positivos deben usar los valores por defecto (300 y 20)
        Dragon dragonFallback = new Dragon("Vhagar", 0, -5, "Rubí");
        assertEquals(300.0, dragonFallback.getHp(), 0.001);
        assertEquals(20, dragonFallback.getFuerza());
    }

    @RepeatedTest(value = 50, name = "Ataque Dragón dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("atacar() genera daño dentro del rango teórico del README (9.0 a 57.5)")
    void testAtacarRangoTeorico() {
        // Rango de ataque: 10 - 25
        // Rango de efectividad: 0.9 - 2.3
        // Mínimo teórico: 10 * 0.9 = 9.0
        // Máximo teórico: 25 * 2.3 = 57.5
        double dano = dragon.atacar();
        assertTrue(dano >= 9.0 && dano <= 57.5,
                "El daño del dragón debe estar entre 9.0 y 57.5. Obtenido: " + dano);
    }

    @RepeatedTest(value = 50, name = "Defensa Dragón dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("defender(double) aplica multiplicador entre x0.7 y x3.9 según el README")
    void testDefenderRangoMultiplicador() {
        double hpInicial = dragon.getHp();
        double impactoBase = 10.0;

        double hpRestante = dragon.defender(impactoBase);
        double danoRecibido = hpInicial - hpRestante;

        // Multiplicador entre 0.7 y 3.9 -> Daño recibido para 10.0 debe estar entre 7.0 y 39.0
        assertTrue(danoRecibido >= 7.0 && danoRecibido <= 39.0,
                "El daño recibido con defensa debe estar entre 7.0 y 39.0. Obtenido: " + danoRecibido);
        assertEquals(hpRestante, dragon.getHp(), 0.001);
        assertEquals((int) Math.round(hpRestante), dragon.getSalud());
    }

    @Test
    @DisplayName("defender(double) no permite que la vida baje de 0 y actualiza vida() a false")
    void testDefenderDanoLetalNoPasaDeCero() {
        dragon.defender(1000.0);
        assertEquals(0.0, dragon.getHp(), 0.001);
        assertEquals(0, dragon.getSalud());
        assertFalse(dragon.vida());
        assertFalse(dragon.estaViva());
    }

    @Test
    @DisplayName("defender(Criatura) calcula el daño atacando con la criatura rival")
    void testDefenderContraCriaturaRival() {
        Guerrero rival = new Guerrero("Rival", "Espada");
        double hpInicial = dragon.getHp();

        double hpRestante = dragon.defender(rival);
        assertTrue(hpRestante < hpInicial, "La vida del dragón debió reducirse tras el ataque del rival");
    }

    @Test
    @DisplayName("Getter y Setter de escamas funcionan correctamente")
    void testGetSetEscamas() {
        dragon.setEscamas("Titanio");
        assertEquals("Titanio", dragon.getEscamas());
    }
}
