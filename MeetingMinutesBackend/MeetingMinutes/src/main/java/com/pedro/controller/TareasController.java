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
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/tareas")
public class TareasController {

	@Autowired
	TareasService ts;
	
	@GetMapping("/tareasreunion/{codsreunion}")
	public List<Tareas> getTareasByCodSReunion(@PathVariable int codsreunion) {
		return ts.getTareasByCodSReunion(codsreunion);
	}
	
	@PostMapping("/creartarea/{codreunion}/{codusu}/{codsreunion}")
	public void crearTareas(@RequestBody Tareas tarea, @PathVariable int codreunion, @PathVariable int[] codusu, @PathVariable int codsreunion) {
		ts.crearTareas(tarea, codreunion, codusu, codsreunion);
	}
	
	@GetMapping("/gettareas/{codsreunion}")
	public List<Tareas> getAllTareasByCodSReunion(@PathVariable int codsreunion) {
		return ts.getAllTareasByCodSReunion(codsreunion);
	}
	
	@PutMapping("/cerrartareas")
	public void cerrarTareas(@RequestBody Tareas tarea) {
		ts.cerrarTareas(tarea);
	}
	
	@GetMapping("tareasreunocerr/{codreunion}")
	List<Tareas> getTareasByCodReunionAndNoCerrado(@PathVariable int codreunion) {
		return ts.getTareasByCodReunionAndNoCerrado(codreunion);
	}
	
	@GetMapping("tareasreuantiguanocerr/{codreunion}")
	List<Tareas> getTareasByCodReunionAntiguaAndNoCerrado(@PathVariable int codreunion) {
		return ts.getTareasByCodReunionAntiguaAndNoCerrado(codreunion);
	}
	
}
