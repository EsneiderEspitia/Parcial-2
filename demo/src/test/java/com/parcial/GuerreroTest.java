package com.parcial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase Guerrero")
class GuerreroTest {

    private Guerrero guerrero;

    @BeforeEach
    void setUp() {
        guerrero = new Guerrero("Aragorn", "Andúril");
    }

    @Test
    @DisplayName("Constructor estándar inicializa 150 HP, 15 fuerza, arma y frases")
    void testConstructorEstandar() {
        assertEquals("Aragorn", guerrero.getNombre());
        assertEquals(150.0, guerrero.getHp(), 0.001);
        assertEquals(150, guerrero.getSalud());
        assertEquals(15, guerrero.getFuerza());
        assertEquals("Andúril", guerrero.getArma());

        assertNotNull(guerrero.getAtqs());
        assertEquals(4, guerrero.getAtqs().length);
        assertNotNull(guerrero.getDefs());
        assertEquals(4, guerrero.getDefs().length);
    }

    @Test
    @DisplayName("Constructor sobrecargado con valores positivos y valores por defecto cuando son <= 0")
    void testConstructorSobrecargado() {
        Guerrero guerreroCustom = new Guerrero("Conan", 200, 25, "Hacha Doble");
        assertEquals("Conan", guerreroCustom.getNombre());
        assertEquals(200.0, guerreroCustom.getHp(), 0.001);
        assertEquals(25, guerreroCustom.getFuerza());
        assertEquals("Hacha Doble", guerreroCustom.getArma());

        Guerrero guerreroFallback = new Guerrero("Recluta", -10, 0, "Espada Corta");
        assertEquals(150.0, guerreroFallback.getHp(), 0.001);
        assertEquals(15, guerreroFallback.getFuerza());
    }

    @RepeatedTest(value = 50, name = "Ataque Guerrero dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("atacar() genera daño dentro del rango teórico del README (1.0 a 50.0)")
    void testAtacarRangoTeorico() {
        // Rango de ataque: 1 - 20
        // Rango de efectividad: 1.0 - 2.5
        // Mínimo teórico: 1 * 1.0 = 1.0
        // Máximo teórico: 20 * 2.5 = 50.0
        double dano = guerrero.atacar();
        assertTrue(dano >= 1.0 && dano <= 50.0,
                "El daño del guerrero debe estar entre 1.0 y 50.0. Obtenido: " + dano);
    }

    @RepeatedTest(value = 50, name = "Defensa Guerrero dentro de rango (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("defender(double) aplica multiplicador entre x0.4 y x1.1 según el README")
    void testDefenderRangoMultiplicador() {
        double hpInicial = guerrero.getHp();
        double impactoBase = 10.0;

        double hpRestante = guerrero.defender(impactoBase);
        double danoRecibido = hpInicial - hpRestante;

        // Multiplicador entre 0.4 y 1.1 -> Daño recibido para 10.0 debe estar entre 4.0 y 11.0
        assertTrue(danoRecibido >= 4.0 && danoRecibido <= 11.0,
                "El daño recibido con defensa debe estar entre 4.0 y 11.0. Obtenido: " + danoRecibido);
        assertEquals(hpRestante, guerrero.getHp(), 0.001);
        assertEquals((int) Math.round(hpRestante), guerrero.getSalud());
    }

    @Test
    @DisplayName("defender(double) no permite que la vida baje de 0 y actualiza vida() a false")
    void testDefenderDanoLetalNoPasaDeCero() {
        guerrero.defender(500.0);
        assertEquals(0.0, guerrero.getHp(), 0.001);
        assertEquals(0, guerrero.getSalud());
        assertFalse(guerrero.vida());
        assertFalse(guerrero.estaViva());
    }

    @Test
    @DisplayName("defender(Criatura) calcula el daño atacando con la criatura rival")
    void testDefenderContraCriaturaRival() {
        Mago rival = new Mago("RivalMago", "Fuego");
        double hpInicial = guerrero.getHp();

        double hpRestante = guerrero.defender(rival);
        assertTrue(hpRestante < hpInicial, "La vida del guerrero debió reducirse tras el ataque del mago");
    }

    @Test
    @DisplayName("Getter y Setter de arma funcionan correctamente")
    void testGetSetArma() {
        guerrero.setArma("Martillo de Guerra");
        assertEquals("Martillo de Guerra", guerrero.getArma());
    }
}
