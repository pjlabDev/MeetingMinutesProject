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
	
	@GetMapping("/tareasreunion/{codreunion}")
	public List<Tareas> getSerieReunionByUsuario(@PathVariable int codreunion) {
		return ts.getTareasByCodReunion(codreunion);
	}
	
	@PostMapping("/creartarea/{codreunion}/{codusu}")
	public void crearTareas(@RequestBody Tareas tarea, @PathVariable int codreunion, @PathVariable int[] codusu) {
		ts.crearTareas(tarea, codreunion, codusu);
	}
	
	@GetMapping("/gettareas")
	public List<Tareas> getAllTareas() {
		return ts.getAllTareas();
	}
}
