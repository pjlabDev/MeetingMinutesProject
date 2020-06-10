/**
 * 
 */
package com.pedro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.modelo.Tareas;
import com.pedro.service.TareasService;

/**
 * The Class TareasController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/tareas")
public class TareasController {

	/** The ts. */
	@Autowired
	TareasService ts;
	
	/**
	 * Gets the tareas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the tareas by cod reunion
	 */
	@GetMapping("/tareasreunion/{codreunion}")
	public List<Tareas> getTareasByCodReunion(@PathVariable int codreunion) {
		return ts.getTareasByCodReunion(codreunion);
	}
	
	/**
	 * Crear tareas.
	 *
	 * @param tarea the tarea
	 * @param codreunion the codreunion
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 */
	@PostMapping("/creartarea/{codreunion}/{codusu}/{codsreunion}")
	public void crearTareas(@RequestBody Tareas tarea, @PathVariable int codreunion, @PathVariable int[] codusu, @PathVariable int codsreunion) {
		ts.crearTareas(tarea, codreunion, codusu, codsreunion);
	}
	
	/**
	 * Gets the all tareas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all tareas by cod S reunion
	 */
	@GetMapping("/gettareas/{codsreunion}")
	public List<Tareas> getAllTareasByCodSReunion(@PathVariable int codsreunion) {
		return ts.getAllTareasByCodSReunion(codsreunion);
	}
	
	/**
	 * Cerrar tareas.
	 *
	 * @param tarea the tarea
	 */
	@PutMapping("/cerrartareas")
	public void cerrarTareas(@RequestBody Tareas tarea) {
		ts.cerrarTareas(tarea);
	}
	
	/**
	 * Gets the tareas by cod reunion antigua and no cerrada.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the tareas by cod reunion antigua and no cerrada
	 */
	@GetMapping("/tareasreuantiguanocerr/{codreunion}/{codsreunion}")
	public List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(@PathVariable int codreunion, @PathVariable int codsreunion) {
		return ts.getTareasByCodReunionAntiguaAndNoCerrada(codreunion, codsreunion);
	}
	
	/**
	 * Save tareas antiguas.
	 *
	 * @param tareas the tareas
	 * @param codreunion the codreunion
	 */
	@PostMapping("/savetareasantiguas/{codreunion}")
	public void saveTareasAntiguas(@RequestBody Tareas[] tareas, @PathVariable int codreunion) {
		ts.saveTareasAntiguas(tareas, codreunion);
	}
	
	/**
	 * Gets the tarea by cod tarea.
	 *
	 * @param codtarea the codtarea
	 * @return the tarea by cod tarea
	 */
	@GetMapping("/gettarea/{codtarea}")
	public Tareas getTareaByCodTarea(@PathVariable int codtarea) {
		return ts.getTareaByCodTarea(codtarea);
	}
	
	/**
	 * Modificar tarea.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	@PutMapping("/modificartarea/{codusu}")
	public void modificarTarea(@RequestBody Tareas tarea, @PathVariable int[] codusu) {
		ts.modificarTarea(tarea, codusu);
	}
	
	/**
	 * Eliminar responsable.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	@PutMapping("/eliminarresponsable/{codusu}")
	public void eliminarResponsable(@RequestBody Tareas tarea, @PathVariable int codusu) {
		ts.eliminarResponsable(tarea, codusu);
	}
	
}
