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
 * @author Westermeyer
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/archivos")
public class ArchivosController {

	@Autowired
	ArchivosService as;	
	
	@PostMapping("/adjuntar/{nombre}/{codReunion}")
	public void enviarAgenda(@RequestParam("archivo") MultipartFile archivo, @PathVariable String nombre, @PathVariable int codReunion) {
		try {
			
			as.adjuntarArchivo(archivo, nombre, codReunion);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/listar/{codreunion}")
	public List<Archivos> getArchivosByCodReunion(@PathVariable int codreunion) {
		return as.getArchivosByCodReunion(codreunion);
	}
	
	@GetMapping("/verarchivo/{codarchivo}")
	public byte[] ejecutarArchivo(@PathVariable int codarchivo) {
		return as.ejecutarArchivo(codarchivo);
	}
	
	@DeleteMapping("/borrar/{codarchivo}")
	public void borrarArchivo(@PathVariable int codarchivo) {
		as.borrarArchivo(codarchivo);
	}
	
}
