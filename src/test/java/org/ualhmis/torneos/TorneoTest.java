package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// Registro de partidos y validación de resultados
class TorneoTest {

	@ParameterizedTest
	@CsvSource({
		"Liga Juvenil, Fútbol, Juvenil, Masculino, Liga, Tigres, Juvenil, Masculino, true",
		"Liga Juvenil, Fútbol, Juvenil, Masculino, Liga, Tigres, Cadete, Masculino, false",
		"Liga Juvenil, Fútbol, Juvenil, Masculino, Liga, Tigres, Juvenil, Femenino, false"
	})
	void testRegistrarEquipoParametrizado(String torneoNombre, String deporte, String categoria, String modalidad, String tipo,
										  String equipoNombre, String equipoCategoria, String equipoModalidad, boolean shouldRegister) {
		Torneo torneo = new Torneo(torneoNombre, deporte, categoria, modalidad, tipo);

		Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
		Equipo equipo = new Equipo(equipoNombre, equipoCategoria, equipoModalidad, entrenador);

		if (shouldRegister) {
			torneo.registrarEquipo(equipo);
			assertEquals(1, torneo.getEquipos().size());
		} else {
			assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
		}
	}

	@ParameterizedTest
	@CsvSource({
		"Liga Juvenil, Fútbol, Juvenil, Masculino, Liga",
		"Copa Senior, Baloncesto, Senior, Femenino, Copa"
	})
	void testGettersAndSettersParametrizado(String nombre, String deporte, String categoria, String modalidad, String tipo) {
		Torneo torneo = new Torneo("Temp", "Temp", "Temp", "Temp", "Temp");

		torneo.setNombre(nombre);
		torneo.setDeporte(deporte);
		torneo.setCategoria(categoria);
		torneo.setModalidad(modalidad);
		torneo.setTipo(tipo);

		assertEquals(nombre, torneo.getNombre());
		assertEquals(deporte, torneo.getDeporte());
		assertEquals(categoria, torneo.getCategoria());
		assertEquals(modalidad, torneo.getModalidad());
		assertEquals(tipo, torneo.getTipo());
	}
}
