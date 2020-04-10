/**
 * 
 */
package com.pedro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.modelo.Temas;
import com.pedro.service.EmailService;

/**
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/email")
public class EmailController {
	
	@Autowired
	EmailService es;
	
	@PostMapping("/enviar/{receptores}/{fechaReunion}")
	public void enviarAgenda(@RequestBody Temas tema[], @PathVariable int receptores[], @PathVariable String fechaReunion) {
		es.enviarAgenda(receptores, fechaReunion, tema);
	}
	
}
