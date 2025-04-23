package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

public class PartidoTest {

    @Test
    @DisplayName("Constructor asigna correctamente los equipos")
    void testConstructor() {
        Equipo local = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo("Equipo B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        assertEquals(local, partido.getEquipo1());
        assertEquals(visitante, partido.getEquipo2());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Constructor lanza excepción si algún equipo es nulo")
    void testConstructorConEquiposNulos(Equipo equipoNulo) {
        Equipo equipoValido = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));

        assertThrows(IllegalArgumentException.class, () -> new Partido(equipoNulo, equipoValido));
        assertThrows(IllegalArgumentException.class, () -> new Partido(equipoValido, equipoNulo));
    }

    @ParameterizedTest
    @CsvSource({
        "Equipo A, Equipo B",
        "Equipo C, Equipo D"
    })
    @DisplayName("Getters devuelven los equipos correctos")
    void testGetters(String nombreLocal, String nombreVisitante) {
        Equipo local = new Equipo(nombreLocal, "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo(nombreVisitante, "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        assertEquals(local, partido.getEquipo1());
        assertEquals(visitante, partido.getEquipo2());
    }

    @Test
    @DisplayName("Setters asignan correctamente los equipos")
    void testSetters() {
        Equipo local = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo("Equipo B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        Equipo nuevoLocal = new Equipo("Equipo C", "Senior", "Masculino", new Entrenador("Luis", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo nuevoVisitante = new Equipo("Equipo D", "Senior", "Masculino", new Entrenador("Ana", "F", java.time.LocalDate.of(1990, 1, 1)));

        partido.setEquipo1(nuevoLocal);
        partido.setEquipo2(nuevoVisitante);

        assertEquals(nuevoLocal, partido.getEquipo1());
        assertEquals(nuevoVisitante, partido.getEquipo2());
    }

    @ParameterizedTest
    @CsvSource({
        "Equipo A, Equipo B",
        "Equipo C, Equipo D"
    })
    @DisplayName("Setters lanzan excepción si algún equipo es nulo")
    void testSettersConEquiposNulos(String nombreLocal, String nombreVisitante) {
        Equipo local = new Equipo(nombreLocal, "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo(nombreVisitante, "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        assertThrows(IllegalArgumentException.class, () -> partido.setEquipo1(null));
        assertThrows(IllegalArgumentException.class, () -> partido.setEquipo2(null));
    }

    @Test
    @DisplayName("Registrar resultado asigna correctamente los goles")
    void testRegistrarResultado() {
        Equipo local = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo("Equipo B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        partido.registrarResultado(3, 2);

        assertEquals(3, partido.getGolesEquipo1());
        assertEquals(2, partido.getGolesEquipo2());
    }

    @Test
    @DisplayName("Setters y getters de goles funcionan correctamente")
    void testSetGoles() {
        Equipo local = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo("Equipo B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        partido.setGolesEquipo1(4);
        partido.setGolesEquipo2(1);

        assertEquals(4, partido.getGolesEquipo1());
        assertEquals(1, partido.getGolesEquipo2());
    }

    @Test
    @DisplayName("Setters y getters de instalación funcionan correctamente")
    void testSetInstalacion() {
        Equipo local = new Equipo("Equipo A", "Senior", "Masculino", new Entrenador("Juan", "M", java.time.LocalDate.of(1990, 1, 1)));
        Equipo visitante = new Equipo("Equipo B", "Senior", "Masculino", new Entrenador("Pedro", "M", java.time.LocalDate.of(1990, 1, 1)));

        Partido partido = new Partido(local, visitante);

        Instalacion instalacion = new Instalacion("Instalacion1", TiposInstalacion.Campo, List.of("Futbol"));
        partido.setInstalacion(instalacion);

        assertEquals(instalacion, partido.getInstalacion());
    }
}