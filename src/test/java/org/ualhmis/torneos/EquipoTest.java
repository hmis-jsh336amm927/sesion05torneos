package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EquipoTest {

	@Test
	@DisplayName("SetNombre actualiza correctamente el nombre")
	void testSetNombre() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setNombre("Nuevo Equipo");
		assertEquals("Nuevo Equipo", equipo.getNombre());
	}

	@Test
	@DisplayName("SetCategoria actualiza correctamente la categoría")
	void testSetCategoria() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setCategoria("Junior");
		assertEquals("Junior", equipo.getCategoria());
	}

	@Test
	@DisplayName("SetModalidad actualiza correctamente la modalidad")
	void testSetModalidad() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setModalidad("Femenino");
		assertEquals("Femenino", equipo.getModalidad());
	}

	@Test
	@DisplayName("Agregar jugador válido lo añade correctamente a la lista")
	void testAgregarJugadorValido() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		Jugador jugador = new Jugador("Carlos", "Masculino", LocalDate.of(2005, 1, 1)); // Categoría Junior
		equipo.agregarJugador(jugador);

		assertEquals(1, equipo.getJugadores().size());
		assertTrue(equipo.getJugadores().contains(jugador));
	}

	@Test
	@DisplayName("Agregar jugador con categoría incorrecta no lo añade")
	void testAgregarJugadorCategoriaIncorrecta() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		Jugador jugador = new Jugador("Carlos", "Masculino", LocalDate.of(2015, 1, 1)); // Categoría Infantil
		equipo.agregarJugador(jugador);

		assertTrue(equipo.getJugadores().isEmpty());
	}

	@Test
	@DisplayName("Agregar jugador duplicado no lo añade")
	void testAgregarJugadorDuplicado() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		Jugador jugador = new Jugador("Carlos", "Masculino", LocalDate.of(2005, 1, 1)); // Categoría Junior
		equipo.agregarJugador(jugador);
		equipo.agregarJugador(jugador);

		assertEquals(1, equipo.getJugadores().size());
	}

	@Test
	@DisplayName("Asignar segundo entrenador actualiza correctamente")
	void testAsignarSegundoEntrenador() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Entrenador segundoEntrenador = new Entrenador("Pedro", "M", LocalDate.of(1985, 5, 15));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.asignarSegundoEntrenador(segundoEntrenador);

		assertEquals(segundoEntrenador, equipo.getSegundoEntrenador());
	}

	@Test
	@DisplayName("ToString incluye información clave del equipo")
	void testToString() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		String toString = equipo.toString();

		assertTrue(toString.contains("Equipo A"));
		assertTrue(toString.contains("Junior"));
		assertTrue(toString.contains("Masculino"));
		assertTrue(toString.contains(entrenador.toString()));
	}

	@ParameterizedTest
	@CsvSource({ "'Nuevo Equipo'", "' '" })
	@DisplayName("SetNombre maneja entradas válidas e inválidas")
	void testSetNombreParameterized(String nombre) {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setNombre(nombre);
		assertEquals(nombre, equipo.getNombre());

	}

	@Test
	@DisplayName("SetEntrenador actualiza correctamente el entrenador")
	void testSetEntrenador() {
		Entrenador entrenador1 = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Entrenador entrenador2 = new Entrenador("Pedro", "M", LocalDate.of(1985, 5, 15));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador1);

		equipo.setEntrenador(entrenador2);
		assertEquals(entrenador2, equipo.getEntrenador());
	}

	@Test
	@DisplayName("SetSegundoEntrenador actualiza correctamente el segundo entrenador")
	void testSetSegundoEntrenador() {
		Entrenador entrenador = new Entrenador("Pedro", "M", LocalDate.of(1985, 5, 15));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		Entrenador segundoEntrenador = new Entrenador("Luis", "M", LocalDate.of(1990, 3, 10));
		equipo.setSegundoEntrenador(segundoEntrenador);
		assertEquals(segundoEntrenador, equipo.getSegundoEntrenador());
	}

	@Test
	@DisplayName("SetJugadores actualiza correctamente la lista de jugadores")
	void testSetJugadores() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		List<Jugador> jugadores = new ArrayList<>();
		jugadores.add(new Jugador("Carlos", "Masculino", LocalDate.of(2005, 1, 1)));
		jugadores.add(new Jugador("Luis", "Masculino", LocalDate.of(2006, 2, 15)));

		equipo.setJugadores(jugadores);
		assertEquals(jugadores, equipo.getJugadores());
	}

	@ParameterizedTest
	@CsvSource({ "'Junior'", "'Cadete'" })
	@DisplayName("SetCategoria maneja entradas válidas")
	void testSetCategoriaParameterized(String categoria) {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setCategoria(categoria);
		assertEquals(categoria, equipo.getCategoria());

	}

	@ParameterizedTest
	@CsvSource({ "'Masculino'", "'Femenino'" })
	@DisplayName("SetModalidad maneja entradas válidas")
	void testSetModalidadParameterized(String modalidad) {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		equipo.setModalidad(modalidad);
		assertEquals(modalidad, equipo.getModalidad());

	}

	@Test
	@DisplayName("Constructor lanza excepción para entradas inválidas")
	void testConstructorInvalidInputs() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));

		assertThrows(IllegalArgumentException.class, () -> new Equipo(null, "Junior", "Masculino", entrenador));
		assertThrows(IllegalArgumentException.class, () -> new Equipo("", "Junior", "Masculino", entrenador));
		assertThrows(IllegalArgumentException.class, () -> new Equipo("Equipo A", null, "Masculino", entrenador));
		assertThrows(IllegalArgumentException.class, () -> new Equipo("Equipo A", "Junior", null, entrenador));
		assertThrows(IllegalArgumentException.class, () -> new Equipo("Equipo A", "Junior", "Masculino", null));
	}

	@Test
	@DisplayName("Agregar jugador maneja entradas nulas e inválidas")
	void testAgregarJugadorInvalidInputs() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		assertThrows(NullPointerException.class, () -> equipo.agregarJugador(null));

		Jugador jugador = new Jugador("Carlos", "Masculino", LocalDate.of(2015, 1, 1)); // Categoría Infantil
		equipo.agregarJugador(jugador);
		assertTrue(equipo.getJugadores().isEmpty());
	}

	@Test
	@DisplayName("ToString incluye toda la información clave")
	void testToStringEdgeCases() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));
		Equipo equipo = new Equipo("Equipo A", "Junior", "Masculino", entrenador);

		String toString = equipo.toString();
		assertTrue(toString.contains("Equipo A"));
		assertTrue(toString.contains("Junior"));
		assertTrue(toString.contains("Masculino"));
		assertTrue(toString.contains(entrenador.toString()));
	}

	@Test
	@DisplayName("Equals explora todas las ramas posibles y casos extremos")
	void testEqualsAllCasesUnified() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));

		Equipo equipo1 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		assertEquals(equipo1, equipo1, "Un objeto debe ser igual a sí mismo");

		assertNotEquals(equipo1, null, "Un objeto no debe ser igual a null");

		assertNotEquals(equipo1, "No es un Equipo", "Un objeto no debe ser igual a un objeto de otra clase");

		Equipo equipo2 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		assertEquals(equipo1, equipo2, "Dos objetos con los mismos valores deben ser iguales");

		Equipo equipo3 = new Equipo("Equipo B", "Cadete", "Femenino", entrenador);
		assertNotEquals(equipo1, equipo3, "Dos objetos con valores diferentes no deben ser iguales");

		Equipo equipo4 = new Equipo("Equipo Null", "Junior", "Masculino", entrenador);
		equipo4.setNombre(null);
		assertNotEquals(equipo1, equipo4, "Un objeto con nombre null no debe ser igual");

		Equipo equipo5 = new Equipo("Equipo A", "Cadete", "Masculino", entrenador);
		equipo5.setModalidad(null);
		assertNotEquals(equipo1, equipo5, "Un objeto con modalidad null no debe ser igual");

		Equipo equipo6 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo6.setCategoria(null);
		assertNotEquals(equipo1, equipo6, "Un objeto con categoría null no debe ser igual");

		Equipo equipo7 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo7.setEntrenador(null);
		assertEquals(equipo1, equipo7, "El entrenador no afecta la igualdad, por lo que los objetos deben ser iguales");

		Equipo equipo8 = new Equipo("Prueba", "Prueba", "Prueba", entrenador);
		equipo8.setNombre(null);
		equipo8.setCategoria(null);
		equipo8.setModalidad(null);
		Equipo equipo9 = new Equipo("Prueba", "Prueba", "Prueba", entrenador);
		equipo9.setNombre(null);
		equipo9.setCategoria(null);
		equipo9.setModalidad(null);
		assertEquals(equipo8, equipo9, "Dos objetos con todos los atributos relevantes null deben ser iguales");

		Equipo equipo10 = new Equipo("Equipo A", "Cadete", "Masculino", entrenador);
		assertNotEquals(equipo1, equipo10, "Dos objetos con categorías diferentes no deben ser iguales");

		Equipo equipo11 = new Equipo("Equipo X", "Cadete", "Femenino", entrenador);
		assertNotEquals(equipo1, equipo11,
				"Dos objetos con todos los atributos relevantes diferentes no deben ser iguales");
	}

	@Test
	@DisplayName("Equals maneja correctamente los casos donde un atributo es null en un objeto pero no en el otro")
	void testEqualsNullAttributes() {
		Entrenador entrenador = new Entrenador("Juan", "M", LocalDate.of(1990, 1, 1));

		Equipo equipo1 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		Equipo equipo2 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo1.setCategoria(null);
		assertNotEquals(equipo1, equipo2,
				"Un objeto con categoria null no debe ser igual a otro con categoria no null");

		equipo1 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo2 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo1.setModalidad(null);
		assertNotEquals(equipo1, equipo2,
				"Un objeto con modalidad null no debe ser igual a otro con modalidad no null");

		equipo1 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo2 = new Equipo("Equipo A", "Junior", "Masculino", entrenador);
		equipo1.setNombre(null);
		assertNotEquals(equipo1, equipo2, "Un objeto con nombre null no debe ser igual a otro con nombre no null");

		Equipo equipo12 = new Equipo("Equipo A", "Junior", "Femenino", entrenador);
		assertNotEquals(equipo1, equipo12, "Dos objetos con modalidades diferentes no deben ser iguales");
	}
}