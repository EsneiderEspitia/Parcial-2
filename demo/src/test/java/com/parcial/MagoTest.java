package com.parcial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase Mago")
class MagoTest {

    private Mago mago;

    @BeforeEach
    void setUp() {
        mago = new Mago("Gandalf", "Luz de Eärendil");
    }

    @Test
    @DisplayName("Constructor estándar inicializa 100 HP, 12 fuerza, hechizos y frases")
    void testConstructorEstandar() {
        assertEquals("Gandalf", mago.getNombre());
        assertEquals(100.0, mago.getHp(), 0.001);
        assertEquals(100, mago.getSalud());
        assertEquals(12, mago.getFuerza());
        assertEquals("Luz de Eärendil", mago.getHechizos());

        assertNotNull(mago.getAtqs());
        assertEquals(4, mago.getAtqs().length);
        assertNotNull(mago.getDefs());
        assertEquals(4, mago.getDefs().length);
    }

    @Test
    @DisplayName("Constructor sobrecargado con valores positivos y valores por defecto cuando son <= 0")
    void testConstructorSobrecargado() {
        Mago magoCustom = new Mago("Saruman", 120, 18, "Voz de Mando");
        assertEquals("Saruman", magoCustom.getNombre());
        assertEquals(120.0, magoCustom.getHp(), 0.001);
        assertEquals(18, magoCustom.getFuerza());
        assertEquals("Voz de Mando", magoCustom.getHechizos());

        Mago magoFallback = new Mago("Novato", 0, -2, "Chispa");
        assertEquals(100.0, magoFallback.getHp(), 0.001);
        assertEquals(12, magoFallback.getFuerza());
    }

    @RepeatedTest(value = 50, name = "Ataque Mago dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("atacar() genera daño dentro del rango teórico del README (1.3 a 82.5)")
    void testAtacarRangoTeorico() {
        // Rango de ataque: 1 - 15
        // Rango de efectividad: 1.3 - 5.5
        // Mínimo teórico: 1 * 1.3 = 1.3
        // Máximo teórico: 15 * 5.5 = 82.5
        double dano = mago.atacar();
        assertTrue(dano >= 1.3 && dano <= 82.5,
                "El daño del mago debe estar entre 1.3 y 82.5. Obtenido: " + dano);
    }

    @RepeatedTest(value = 50, name = "Defensa Mago dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("defender(double) aplica multiplicador entre x0.9 y x1.1 según el README")
    void testDefenderRangoMultiplicador() {
        double hpInicial = mago.getHp();
        double impactoBase = 10.0;

        double hpRestante = mago.defender(impactoBase);
        double danoRecibido = hpInicial - hpRestante;

        // Multiplicador entre 0.9 y 1.1 -> Daño recibido para 10.0 debe estar entre 9.0 y 11.0
        assertTrue(danoRecibido >= 9.0 && danoRecibido <= 11.0,
                "El daño recibido con defensa debe estar entre 9.0 y 11.0. Obtenido: " + danoRecibido);
        assertEquals(hpRestante, mago.getHp(), 0.001);
        assertEquals((int) Math.round(hpRestante), mago.getSalud());
    }

    @Test
    @DisplayName("defender(double) no permite que la vida baje de 0 y actualiza vida() a false")
    void testDefenderDanoLetalNoPasaDeCero() {
        mago.defender(300.0);
        assertEquals(0.0, mago.getHp(), 0.001);
        assertEquals(0, mago.getSalud());
        assertFalse(mago.vida());
        assertFalse(mago.estaViva());
    }

    @Test
    @DisplayName("defender(Criatura) calcula el daño atacando con la criatura rival")
    void testDefenderContraCriaturaRival() {
        Dragon rival = new Dragon("RivalDragon", "Escamas Rojas");
        double hpInicial = mago.getHp();

        double hpRestante = mago.defender(rival);
        assertTrue(hpRestante < hpInicial, "La vida del mago debió reducirse tras el ataque del dragón");
    }

    @Test
    @DisplayName("Getter y Setter de hechizos funcionan correctamente")
    void testGetSetHechizos() {
        mago.setHechizos("Rayo Arcano");
        assertEquals("Rayo Arcano", mago.getHechizos());
    }
}
