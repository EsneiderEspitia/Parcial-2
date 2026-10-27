package com.parcial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase abstracta Criatura")
class CriaturaTest {

    // Subclase concreta auxiliar para probar la lógica base de Criatura
    static class CriaturaDePrueba extends Criatura {
        public CriaturaDePrueba(String nombre, double hp, int fuerza) {
            super(nombre, hp, fuerza);
            this.atqs = new String[]{"ataque simple"};
            this.defs = new String[]{"defensa simple"};
        }

        public CriaturaDePrueba(String nombre, int salud) {
            super(nombre, salud);
            this.atqs = new String[]{"ataque simple"};
            this.defs = new String[]{"defensa simple"};
        }

        @Override
        public double atacar() {
            return 10.0;
        }

        @Override
        public double defender(Criatura criaturax) {
            return defender(criaturax.atacar());
        }

        @Override
        public double defender(double danoRecibido) {
            this.hp = Math.max(0, this.hp - danoRecibido);
            this.salud = (int) Math.round(this.hp);
            return this.hp;
        }
    }

    private CriaturaDePrueba criatura;

    @BeforeEach
    void setUp() {
        criatura = new CriaturaDePrueba("TestCreature", 100.0, 15);
    }

    @Test
    @DisplayName("Constructor completo inicializa correctamente los atributos y sincroniza salud")
    void testConstructorCompleto() {
        assertEquals("TestCreature", criatura.getNombre());
        assertEquals(100.0, criatura.getHp(), 0.001);
        assertEquals(100, criatura.getSalud());
        assertEquals(15, criatura.getFuerza());
    }

    @Test
    @DisplayName("Constructor con salud asigna hp y fuerza predeterminada")
    void testConstructorSalud() {
        CriaturaDePrueba c2 = new CriaturaDePrueba("CreatureSalud", 80);
        assertEquals("CreatureSalud", c2.getNombre());
        assertEquals(80.0, c2.getHp(), 0.001);
        assertEquals(80, c2.getSalud());
        assertEquals(10, c2.getFuerza());
    }

    @Test
    @DisplayName("vida() y estaViva() retornan true cuando hp > 0 y false cuando hp <= 0")
    void testVidaYEstaViva() {
        assertTrue(criatura.vida());
        assertTrue(criatura.estaViva());

        criatura.setHp(0.0);
        assertFalse(criatura.vida());
        assertFalse(criatura.estaViva());

        criatura.setHp(-10.0);
        assertFalse(criatura.vida());
        assertFalse(criatura.estaViva());
    }

    @RepeatedTest(value = 50, name = "Validar turno en rango 1-6 (repetición {currentRepetition}/{totalRepetitions})")
    @DisplayName("turno() genera números enteros estrictamente entre 1 y 6")
    void testTurnoRango() {
        int turno = criatura.turno();
        assertTrue(turno >= 1 && turno <= 6, "El turno debe estar entre 1 y 6, valor obtenido: " + turno);
    }

    @Test
    @DisplayName("setHp() actualiza tanto hp como salud sincronizada")
    void testSetHpSincronizaSalud() {
        criatura.setHp(45.6);
        assertEquals(45.6, criatura.getHp(), 0.001);
        assertEquals(46, criatura.getSalud());
    }

    @Test
    @DisplayName("setSalud() actualiza tanto salud como hp")
    void testSetSaludActualizaHp() {
        criatura.setSalud(75);
        assertEquals(75, criatura.getSalud());
        assertEquals(75.0, criatura.getHp(), 0.001);
    }

    @Test
    @DisplayName("atacar(Criatura objetivo) delega en objetivo.defender(this)")
    void testAtacarCriaturaObjetivo() {
        CriaturaDePrueba objetivo = new CriaturaDePrueba("Objetivo", 50.0, 10);
        criatura.atacar(objetivo);
        // criatura.atacar() retorna 10.0, por lo que objetivo debe quedar con 40.0 hp
        assertEquals(40.0, objetivo.getHp(), 0.001);
    }

    @Test
    @DisplayName("defender(int dano) sobrecarga convierte correctamente a double")
    void testDefenderSobrecargaInt() {
        criatura.defender(30);
        assertEquals(70.0, criatura.getHp(), 0.001);
    }

    @Test
    @DisplayName("Getters y Setters de nombre, fuerza y arrays de frases")
    void testGettersYSetters() {
        criatura.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", criatura.getNombre());

        criatura.setFuerza(30);
        assertEquals(30, criatura.getFuerza());

        String[] nuevosAtqs = {"ataque 1", "ataque 2"};
        criatura.setAtqs(nuevosAtqs);
        assertArrayEquals(nuevosAtqs, criatura.getAtqs());

        String[] nuevosDefs = {"defensa 1", "defensa 2"};
        criatura.setDefs(nuevosDefs);
        assertArrayEquals(nuevosDefs, criatura.getDefs());
    }
}
