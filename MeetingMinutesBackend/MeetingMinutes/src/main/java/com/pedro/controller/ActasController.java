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

import com.pedro.modelo.Actas;
import com.pedro.service.ActaService;

/**
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/actas")
public class ActasController {

	@Autowired
	ActaService as;
	
	@PostMapping("/newacta/{codReunion}/{codusu}")
	public void generarActa(@RequestBody Actas acta, @PathVariable int codReunion, @PathVariable int[] codusu) {
		as.generarActa(acta, codReunion, codusu);
	}
	
	
}
