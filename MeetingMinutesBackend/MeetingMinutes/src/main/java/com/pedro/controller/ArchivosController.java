/**
 * 
 */
package com.pedro.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pedro.modelo.Archivos;
import com.pedro.service.ArchivosService;

/**
 * The Class ArchivosController.
 *
 * @author Westermeyer
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/archivos")
public class ArchivosController {

	/** The as. */
	@Autowired
	ArchivosService as;	
	
	/**
	 * Método que recibe un archivo junto con su nombre y el codigo de reunion 
	 * para posteriormente guardarlo en base de datos.
	 *
	 * @param archivo the archivo
	 * @param nombre the nombre
	 * @param codReunion the cod reunion
	 */
	
	@PostMapping("/adjuntar/{nombre}/{codReunion}")
	public void adjuntarArchivo(@RequestParam("archivo") MultipartFile archivo, @PathVariable String nombre, @PathVariable int codReunion) {
		try {
			
			as.adjuntarArchivo(archivo, nombre, codReunion);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para recibir los archivos por código de reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the archivos by cod reunion
	 */
	
	@GetMapping("/listar/{codreunion}")
	public List<Archivos> getArchivosByCodReunion(@PathVariable int codreunion) {
		return as.getArchivosByCodReunion(codreunion);
	}
	
	/**
	 * Método para obtener el archivo por su código.
	 *
	 * @param codarchivo the codarchivo
	 * @return the archivo by cod archivo
	 */
	
	@GetMapping("/verarchivo/{codarchivo}")
	public byte[] getArchivoByCodArchivo(@PathVariable int codarchivo) {
		return as.getArchivoByCodArchivo(codarchivo);
	}
	
	/**
	 * Método para eliminar un archivo de la reunion.
	 *
	 * @param codarchivo the codarchivo
	 */
	
	@DeleteMapping("/borrar/{codarchivo}")
	public void borrarArchivo(@PathVariable int codarchivo) {
		as.borrarArchivo(codarchivo);
	}
	
}
