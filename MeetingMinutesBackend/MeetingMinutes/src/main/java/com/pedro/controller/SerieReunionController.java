/**
 * 
 */
package com.pedro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.modelo.SerieReunion;
import com.pedro.service.SerieReunionService;

/**
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/serie")
public class SerieReunionController {

	@Autowired
	SerieReunionService srService;
	
	@GetMapping("/seriereuniones/{codusu}")
	public List<SerieReunion> getSerieReunionByUsuario(@PathVariable int codusu) {
		return srService.getSerieReunionByUsuario(codusu);
	}
	
	@PutMapping("/cerrar")
	public void cerrarSerieReunion(@RequestBody SerieReunion reunion) {
		srService.cerrarSerieReunion(reunion);
	}
	
}
