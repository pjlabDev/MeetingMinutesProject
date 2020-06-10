/**
 * 
 */
package com.pedro.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pedro.modelo.Archivos;

/**
 * The Interface ArchivosService.
 *
 * @author Westermeyer
 */
public interface ArchivosService {
	
	/**
	 * Adjuntar archivo.
	 *
	 * @param archivo the archivo
	 * @param nombre the nombre
	 * @param codReunion the cod reunion
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void adjuntarArchivo(MultipartFile archivo, String nombre, int codReunion) throws IOException;
	
	/**
	 * Gets the archivos by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the archivos by cod reunion
	 */
	List<Archivos> getArchivosByCodReunion(int codreunion);
	
	/**
	 * Gets the archivo by cod archivo.
	 *
	 * @param codarchivo the codarchivo
	 * @return the archivo by cod archivo
	 */
	byte[] getArchivoByCodArchivo(int codarchivo);
	
	/**
	 * Borrar archivo.
	 *
	 * @param codarchivo the codarchivo
	 */
	void borrarArchivo(int codarchivo);
	
}
