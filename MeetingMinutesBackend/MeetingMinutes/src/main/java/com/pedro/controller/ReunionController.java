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

import com.pedro.modelo.Reunion;
import com.pedro.service.ReunionService;

/**
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/reunion")
public class ReunionController {
	
	@Autowired
	ReunionService rs;
	
	@GetMapping("/reuniones/{codsreunion}")
	public List<Reunion> getReunionBySerieReunion(@PathVariable int codsreunion) {
		return rs.getReunionBySerieReunion(codsreunion);
	}
	
	@PostMapping("/addreunion/{codsreunion}")
	public void crearReunion(@RequestBody Reunion reunion, @PathVariable int codsreunion) {
		rs.crearReunion(reunion, codsreunion);
	}
	
	@GetMapping("/reunionbycodreunion/{codreunion}")
	public Reunion getReunionByFecha(@PathVariable int codreunion) {
		return rs.getReunionByCodReunion(codreunion);
	}
	
}
