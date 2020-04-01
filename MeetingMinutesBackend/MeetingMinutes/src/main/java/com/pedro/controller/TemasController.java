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

import com.pedro.modelo.Temas;
import com.pedro.service.TemasService;

/**
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/temas")
public class TemasController {

	@Autowired
	TemasService ts;
	
	@GetMapping("/tema/{codsreunion}")
	public List<Temas> getTemasBySerieReunion(@PathVariable int codsreunion) {
		return ts.getTemasBySerieReunion(codsreunion);
	}
	
	@PostMapping("/creartema/{codsreunion}")
	public void crearTema(@RequestBody Temas tema, @PathVariable int codsreunion) {
		ts.crearTemas(tema, codsreunion);
	}
	
}
