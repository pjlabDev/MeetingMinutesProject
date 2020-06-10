/**
 * 
 */
package com.pedro.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pedro.modelo.Archivos;
import com.pedro.modelo.Reunion;
import com.pedro.repository.ArchivosRepository;
import com.pedro.repository.ReunionRepository;

/**
 * The Class ArchivosServiceImpl.
 *
 * @author Westermeyer
 */
@Service
public class ArchivosServiceImpl implements ArchivosService {
	
	/** The arR. */
	@Autowired
	ArchivosRepository arR;
	
	/** The rr. */
	@Autowired
	ReunionRepository rr;
	
	/**
	 * Adjuntar archivo.
	 *
	 * @param archivo the archivo
	 * @param nombre the nombre
	 * @param codReunion the cod reunion
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void adjuntarArchivo(MultipartFile archivo, String nombre, int codReunion) throws IOException {
		
		Reunion reu = rr.findOne(codReunion);
		
		if(reu != null) {
			
			Archivos arch = new Archivos(nombre,archivo.getBytes(),reu);
			
			arR.save(arch);
			
		}
		
	}

	/**
	 * Gets the archivos by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the archivos by cod reunion
	 */
	@Override
	public List<Archivos> getArchivosByCodReunion(int codreunion) {
		return arR.getArchivosByCodReunion(codreunion);
	}


	/**
	 * Gets the archivo by cod archivo.
	 *
	 * @param codarchivo the codarchivo
	 * @return the archivo by cod archivo
	 */
	@Override
	public byte[] getArchivoByCodArchivo(int codarchivo) {
		return arR.getArchivoByCodArchivo(codarchivo);
	}

	/**
	 * Borrar archivo.
	 *
	 * @param codarchivo the codarchivo
	 */
	@Override
	public void borrarArchivo(int codarchivo) {
		
		Archivos arch = arR.findOne(codarchivo);
		
		if(arch != null) {
			arR.delete(arch);
		}
		
	}
	
	



}
