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

import com.pedro.modelo.SerieReunion;
import com.pedro.service.SerieReunionService;

/**
 * The Class SerieReunionController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/serie")
public class SerieReunionController {

	/** The sr service. */
	@Autowired
	SerieReunionService srService;
	
	/**
	 * Gets the serie reunion by usuario.
	 *
	 * @param codusu the codusu
	 * @return the serie reunion by usuario
	 */
	@GetMapping("/seriereuniones/{codusu}")
	public List<SerieReunion> getSerieReunionByUsuario(@PathVariable int codusu) {
		return srService.getSerieReunionByUsuario(codusu);
	}
	
	/**
	 * Crear serie reunion.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@PostMapping("/addseriereunion/{codusu}")
	public void crearSerieReunion(@RequestBody SerieReunion reunion, @PathVariable int[] codusu) {
		srService.crearSerieReunion(reunion, codusu);
	}
	
	/**
	 * Gets the serie reunion by cod reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the serie reunion by cod reunion
	 */
	@GetMapping("/sereunion/{codsreunion}")
	public SerieReunion getSerieReunionByCodReunion(@PathVariable int codsreunion) {
		return srService.getSerieReunionByCodReunion(codsreunion);
	}
	
	/**
	 * Modificar serie reunion.
	 *
	 * @param reunion the reunion
	 */
	@PutMapping("/modifseriereunion")
	public void modificarSerieReunion(@RequestBody SerieReunion reunion) {
		srService.modificarSerieReunion(reunion);
	}
	
	/**
	 * Modificar serie reunion invitando mas usuarios.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@PutMapping("/modifseriereunionconinvitado/{codusu}")
	public void modificarSerieReunionInvitandoMasUsuarios(@RequestBody SerieReunion reunion, @PathVariable int[] codusu) {
		srService.modificarSerieReunionInvitandoMasUsuarios(reunion, codusu);
	}
	
	/**
	 * Eliminar participante.
	 *
	 * @param sr the sr
	 * @param codusu the codusu
	 */
	@PutMapping("/eliminarparticipante/{codusu}")
	public void eliminarParticipante(@RequestBody SerieReunion sr, @PathVariable int codusu) {
		srService.eliminarParticipante(sr, codusu);
	}
	
}
