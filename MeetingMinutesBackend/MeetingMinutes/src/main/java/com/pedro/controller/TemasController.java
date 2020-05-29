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
	
	@GetMapping("/tema/{codreunion}")
	public List<Temas> getTemasByCodReunion(@PathVariable int codreunion) {
		return ts.getTemasByCodReunion(codreunion);
	}
	
	@GetMapping("/alltemas/{codsreunion}")
	public List<Temas> getAllTemasByCodSReunion(@PathVariable int codsreunion) {
		return ts.getAllTemasByCodSReunion(codsreunion);
	}
	
	@PostMapping("/creartema/{codreunion}/{codsreunion}")
	public void crearTema(@RequestBody Temas tema, @PathVariable int codreunion, @PathVariable int codsreunion) {
		ts.crearTemas(tema, codreunion, codsreunion);
	}
	
	@PutMapping("/addinfo/{codTema}")
	public void añadirInfoTema(@RequestBody Temas tema, @PathVariable int codTema) {
		ts.añadirInfoTema(tema, codTema);
	}
	
	@PutMapping("/adddecision/{codTema}")
	public void añadirDecisionTema(@RequestBody Temas tema, @PathVariable int codTema) {
		ts.añadirDecisionTema(tema, codTema);
	}
	
	@PutMapping("/cerrartema")
	public void cerrarTemas(@RequestBody Temas tema) {
		ts.cerrarTemas(tema);
	}
	
	@GetMapping("temasreuantnocerr/{codreunion}/{codsreunion}")
	List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(@PathVariable int codreunion, @PathVariable int codsreunion) {
		return ts.getTemasByCodReunionAntiguaAndNoCerrado(codreunion, codsreunion);
	}
	
	@PostMapping("savetemasant/{codreunion}")
	void saveTemasAntiguos(@RequestBody Temas[] tema, @PathVariable int codreunion) {
		ts.saveTemaAntiguo(tema, codreunion);
	}
	
	@GetMapping("/gettema/{codtema}")
	public Temas getTemaByCodTema(@PathVariable int codtema) {
		return ts.getTemaByCodTema(codtema);
	}
	
	@PutMapping("/modificarTema")
	public void modificarTema(@RequestBody Temas tema) {
		ts.modificarTema(tema);
	}
	
	@PutMapping("/addseguimiento")
	public void añadirSeguimientoTemaCerrado(@RequestBody Temas tema) {
		ts.añadirSeguimientoTemaCerrado(tema);
	}
	
}
