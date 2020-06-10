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
 * The Class TemasController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/temas")
public class TemasController {

	/** The ts. */
	@Autowired
	TemasService ts;
	
	/**
	 * Gets the temas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the temas by cod reunion
	 */
	@GetMapping("/tema/{codreunion}")
	public List<Temas> getTemasByCodReunion(@PathVariable int codreunion) {
		return ts.getTemasByCodReunion(codreunion);
	}
	
	/**
	 * Gets the all temas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all temas by cod S reunion
	 */
	@GetMapping("/alltemas/{codsreunion}")
	public List<Temas> getAllTemasByCodSReunion(@PathVariable int codsreunion) {
		return ts.getAllTemasByCodSReunion(codsreunion);
	}
	
	/**
	 * Crear tema.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 */
	@PostMapping("/creartema/{codreunion}/{codsreunion}")
	public void crearTema(@RequestBody Temas tema, @PathVariable int codreunion, @PathVariable int codsreunion) {
		ts.crearTemas(tema, codreunion, codsreunion);
	}
	
	/**
	 * Añadir info tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
	@PutMapping("/addinfo/{codTema}")
	public void añadirInfoTema(@RequestBody Temas tema, @PathVariable int codTema) {
		ts.añadirInfoTema(tema, codTema);
	}
	
	/**
	 * Añadir decision tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
	@PutMapping("/adddecision/{codTema}")
	public void añadirDecisionTema(@RequestBody Temas tema, @PathVariable int codTema) {
		ts.añadirDecisionTema(tema, codTema);
	}
	
	/**
	 * Cerrar temas.
	 *
	 * @param tema the tema
	 */
	@PutMapping("/cerrartema")
	public void cerrarTemas(@RequestBody Temas tema) {
		ts.cerrarTemas(tema);
	}
	
	/**
	 * Gets the temas by cod reunion antigua and no cerrado.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the temas by cod reunion antigua and no cerrado
	 */
	@GetMapping("temasreuantnocerr/{codreunion}/{codsreunion}")
	List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(@PathVariable int codreunion, @PathVariable int codsreunion) {
		return ts.getTemasByCodReunionAntiguaAndNoCerrado(codreunion, codsreunion);
	}
	
	/**
	 * Save temas antiguos.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 */
	@PostMapping("savetemasant/{codreunion}")
	void saveTemasAntiguos(@RequestBody Temas[] tema, @PathVariable int codreunion) {
		ts.saveTemaAntiguo(tema, codreunion);
	}
	
	/**
	 * Gets the tema by cod tema.
	 *
	 * @param codtema the codtema
	 * @return the tema by cod tema
	 */
	@GetMapping("/gettema/{codtema}")
	public Temas getTemaByCodTema(@PathVariable int codtema) {
		return ts.getTemaByCodTema(codtema);
	}
	
	/**
	 * Modificar tema.
	 *
	 * @param tema the tema
	 */
	@PutMapping("/modificarTema")
	public void modificarTema(@RequestBody Temas tema) {
		ts.modificarTema(tema);
	}
	
	/**
	 * Añadir seguimiento tema cerrado.
	 *
	 * @param tema the tema
	 */
	@PutMapping("/addseguimiento")
	public void añadirSeguimientoTemaCerrado(@RequestBody Temas tema) {
		ts.añadirSeguimientoTemaCerrado(tema);
	}
	
}
