package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InstalacionTest {

    private Instalacion instalacion;

    @BeforeEach
    void setUp() {
        instalacion = new Instalacion("Instalacion1", TiposInstalacion.Campo, Arrays.asList("Futbol", "Balonmano"));
    }

    @Test
    @DisplayName("Test getter y setter de nombre")
    void testNombre() {
        instalacion.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", instalacion.getNombre());
    }

    @Test
    @DisplayName("Test getter y setter de subtipo")
    void testSubtipo() {
        instalacion.setSubtipo(TiposInstalacion.Pabellon);
        assertEquals(TiposInstalacion.Pabellon, instalacion.getSubtipo());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Futbol", "Balonmano"})
    @DisplayName("Test deporte permitido")
    void testEsAdecuadaParaPermitido(String deporte) {
        assertTrue(instalacion.esAdecuadaPara(deporte));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tenis", "Natacion"})
    @DisplayName("Test deporte no permitido")
    void testEsAdecuadaParaNoPermitido(String deporte) {
        assertFalse(instalacion.esAdecuadaPara(deporte));
    }

    @Test
    @DisplayName("Asignación de partido sin solape")
    void testAsignarPartidoSinSolape() {
        Partido partido = new Partido(new Equipo("A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1))),
                                      new Equipo("B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1))));

        LocalTime inicio = LocalTime.of(10, 0);
        LocalTime fin = LocalTime.of(11, 0);

        assertDoesNotThrow(() -> instalacion.asignarPartido(partido, inicio, fin));
        assertEquals(instalacion, partido.getInstalacion());
    }

    @Test
    @DisplayName("Asignación de partido con solape lanza excepción")
    void testAsignarPartidoConSolape() {
        Partido partido1 = new Partido(new Equipo("A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1))),
                                       new Equipo("B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1))));
        Partido partido2 = new Partido(new Equipo("C", "Senior", "Masculino", new Entrenador("Luis", "M", java.time.LocalDate.of(1990, 1, 1))),
                                       new Equipo("D", "Senior", "Masculino", new Entrenador("Carlos", "M", java.time.LocalDate.of(1990, 1, 1))));

        instalacion.asignarPartido(partido1, LocalTime.of(10, 0), LocalTime.of(11, 0));

        assertThrows(IllegalArgumentException.class, () ->
                instalacion.asignarPartido(partido2, LocalTime.of(10, 30), LocalTime.of(11, 30)));
    }

    @Test
    @DisplayName("Asignar partido al límite exacto no lanza excepción")
    void testAsignarPartidoLimite() {
        Partido partido1 = new Partido(new Equipo("E", "Senior", "Masculino", new Entrenador("Toni", "M", java.time.LocalDate.of(1990, 1, 1))),
                                       new Equipo("F", "Senior", "Masculino", new Entrenador("Sergio", "M", java.time.LocalDate.of(1990, 1, 1))));
        Partido partido2 = new Partido(new Equipo("G", "Senior", "Masculino", new Entrenador("Mario", "M", java.time.LocalDate.of(1990, 1, 1))),
                                       new Equipo("H", "Senior", "Masculino", new Entrenador("David", "M", java.time.LocalDate.of(1990, 1, 1))));

        instalacion.asignarPartido(partido1, LocalTime.of(10, 0), LocalTime.of(11, 0));

        assertDoesNotThrow(() ->
                instalacion.asignarPartido(partido2, LocalTime.of(11, 0), LocalTime.of(12, 0)));

        assertEquals(instalacion, partido1.getInstalacion());
        assertEquals(instalacion, partido2.getInstalacion());
    }

    @Test
    @DisplayName("Instalación sin deportes permitidos devuelve false siempre")
    void testEsAdecuadaParaSinDeportes() {
        Instalacion vacia = new Instalacion("Vacia", TiposInstalacion.Pista, Collections.emptyList());
        assertFalse(vacia.esAdecuadaPara("Futbol"));
    }

    @Test
    @DisplayName("Constructor con nombre nulo lanza excepción")
    void testNombreNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Instalacion(null, TiposInstalacion.Campo, Arrays.asList("Futbol")));
    }

    @Test
    @DisplayName("Constructor con subtipo nulo lanza excepción")
    void testSubtipoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Instalacion("Instalacion2", null, Arrays.asList("Futbol")));
    }

    @Test
    @DisplayName("Constructor con deportesPermitidos nulo lanza excepción")
    void testDeportesPermitidosNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Instalacion("Instalacion3", TiposInstalacion.Campo, null));
    }

    @Test
    @DisplayName("Instalaciones iguales según equals")
    void testEquals() {
        Instalacion otra = new Instalacion("Instalacion1", TiposInstalacion.Campo, Arrays.asList("Futbol", "Balonmano"));
        assertEquals(instalacion, otra);
    }

    @Test
    @DisplayName("Instalaciones diferentes no son iguales")
    void testNotEquals() {
        Instalacion otra = new Instalacion("Otra", TiposInstalacion.Pista, Arrays.asList("Tenis"));
        assertNotEquals(instalacion, otra);
    }

    @Test
    @DisplayName("HashCode consistente para instalaciones iguales")
    void testHashCode() {
        Instalacion otra = new Instalacion("Instalacion1", TiposInstalacion.Campo, Arrays.asList("Futbol", "Balonmano"));
        assertEquals(instalacion.hashCode(), otra.hashCode());
    }

    @Test
    @DisplayName("ToString contiene información clave")
    void testToString() {
        String toString = instalacion.toString();
        assertTrue(toString.contains("Instalacion1"));
        assertTrue(toString.contains("Campo"));
        assertTrue(toString.contains("Futbol"));
    }
} 