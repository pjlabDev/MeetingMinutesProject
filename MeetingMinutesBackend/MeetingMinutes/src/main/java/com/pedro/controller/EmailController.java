/**
 * 
 */
package com.pedro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.modelo.Temas;
import com.pedro.service.EmailService;

/**
 * The Class EmailController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/email")
public class EmailController {
	
	/** The es. */
	@Autowired
	EmailService es;
	
	/**
	 * Enviar agenda.
	 *
	 * @param tema the tema
	 * @param receptores the receptores
	 * @param fechaReunion the fecha reunion
	 */
	@PostMapping("/enviar/{receptores}/{fechaReunion}")
	public void enviarAgenda(@RequestBody Temas tema[], @PathVariable int receptores[], @PathVariable String fechaReunion) {
		es.enviarAgenda(receptores, fechaReunion, tema);
	}
	
	/**
	 * Enviar acta.
	 *
	 * @param fechaActa the fecha acta
	 * @param receptores the receptores
	 * @param codtema the codtema
	 * @param codtarea the codtarea
	 * @param conclusion the conclusion
	 */
	@GetMapping("/enviaracta/{fechaActa}/{receptores}/{codtema}/{codtarea}/{conclusion}")
	public void enviarActa(@PathVariable String fechaActa, @PathVariable int[] receptores, @PathVariable int[] codtema, @PathVariable int[] codtarea, @PathVariable String conclusion) {
		es.enviarActa(receptores, fechaActa, codtema, codtarea, conclusion);
	}
	
	/**
	 * Enviar tarea.
	 *
	 * @param codtarea the codtarea
	 */
	@GetMapping("/enviartarea/{codtarea}")
	public void enviarTarea(@PathVariable int codtarea) {
		es.enviarTarea(codtarea);
	}
	
	/**
	 * Enviar comentario.
	 *
	 * @param nombre the nombre
	 * @param correo the correo
	 * @param comentario the comentario
	 */
	@GetMapping("/enviarcomentario/{nombre}/{correo}/{comentario}")
	public void enviarComentario(@PathVariable String nombre, @PathVariable String correo, @PathVariable String comentario) {
		es.enviarComentario(nombre, correo, comentario);
	}
	
}
