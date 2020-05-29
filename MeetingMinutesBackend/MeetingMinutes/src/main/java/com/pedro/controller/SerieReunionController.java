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
	
	@PostMapping("/addseriereunion/{codusu}")
	public void crearSerieReunion(@RequestBody SerieReunion reunion, @PathVariable int[] codusu) {
		srService.crearSerieReunion(reunion, codusu);
	}
	
	@GetMapping("/sereunion/{codsreunion}")
	public SerieReunion getSerieReunionByCodReunion(@PathVariable int codsreunion) {
		return srService.getSerieReunionByCodReunion(codsreunion);
	}
	
	@PutMapping("/modifseriereunion")
	public void modificarSerieReunion(@RequestBody SerieReunion reunion) {
		srService.modificarSerieReunion(reunion);
	}
	
	@PutMapping("/modifseriereunionconinvitado/{codusu}")
	public void modificarSerieReunionInvitandoMasUsuarios(@RequestBody SerieReunion reunion, @PathVariable int[] codusu) {
		srService.modificarSerieReunionInvitandoMasUsuarios(reunion, codusu);
	}
	
	@PutMapping("/eliminarparticipante/{codusu}")
	public void eliminarParticipante(@RequestBody SerieReunion sr, @PathVariable int codusu) {
		srService.eliminarParticipante(sr, codusu);
	}
	
}
