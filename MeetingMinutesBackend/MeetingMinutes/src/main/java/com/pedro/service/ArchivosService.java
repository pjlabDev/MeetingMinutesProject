/**
 * 
 */
package com.pedro.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pedro.modelo.Archivos;

/**
 * @author Westermeyer
 *
 */
public interface ArchivosService {
	
	void adjuntarArchivo(MultipartFile archivo, String nombre, int codReunion) throws IOException;
	List<Archivos> getArchivosByCodReunion(int codreunion);
	byte[] ejecutarArchivo(int codarchivo);
	void borrarArchivo(int codarchivo);
	
}
