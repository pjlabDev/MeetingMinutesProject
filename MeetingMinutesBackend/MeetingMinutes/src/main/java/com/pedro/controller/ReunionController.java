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

import com.pedro.modelo.Reunion;
import com.pedro.service.ReunionService;

/**
 * The Class ReunionController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/reunion")
public class ReunionController {
	
	/** The rs. */
	@Autowired
	ReunionService rs;
	
	/**
	 * Gets the reunion by serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the reunion by serie reunion
	 */
	@GetMapping("/reuniones/{codsreunion}")
	public List<Reunion> getReunionBySerieReunion(@PathVariable int codsreunion) {
		return rs.getReunionBySerieReunion(codsreunion);
	}
	
	/**
	 * Gets the reunion by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the reunion by cod reunion
	 */
	@GetMapping("/reunionbycodreunion/{codreunion}")
	public Reunion getReunionByCodReunion(@PathVariable int codreunion) {
		return rs.getReunionByCodReunion(codreunion);
	}
	
	/**
	 * Gets the reuniones.
	 *
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 * @return the reuniones
	 */
	@GetMapping("/reuniones/{codusu}/{codsreunion}")
	public List<Reunion> getReuniones(@PathVariable int codusu, @PathVariable int codsreunion) {
		return rs.getReuniones(codusu, codsreunion);
	}
	
	/**
	 * Crear reunion con.
	 *
	 * @param reunion the reunion
	 * @param codsreunion the codsreunion
	 * @param codsusu the codsusu
	 */
	@PostMapping("addnewreunion/{codsreunion}/{codsusu}")
	public void crearReunionCon(@RequestBody Reunion reunion, @PathVariable int codsreunion, @PathVariable int[] codsusu) {
		rs.crearReunion(reunion, codsreunion, codsusu);
	}
	
	/**
	 * Modificar reunion.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@PutMapping("/modificarreunion/{codusu}")
	public void modificarReunion(@RequestBody Reunion reunion, @PathVariable int[] codusu) {
		rs.modificarReunion(reunion, codusu);
	}
	
	/**
	 * Eliminar participante.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@PutMapping("/eliminarparticipante/{codusu}")
	public void eliminarParticipante(@RequestBody Reunion reunion, @PathVariable int codusu) {
		rs.eliminarParticipante(reunion, codusu);
	}
	
}
