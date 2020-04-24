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
 * @author Westermeyer
 *
 */
@Service
public class ArchivosServiceImpl implements ArchivosService {
	
	@Autowired
	ArchivosRepository arR;
	
	@Autowired
	ReunionRepository rr;
	
	@Override
	public void adjuntarArchivo(MultipartFile archivo, String nombre, int codReunion) throws IOException {
		
		Reunion reu = rr.findOne(codReunion);
		
		if(reu != null) {
			
			Archivos arch = new Archivos(nombre,archivo.getBytes(),reu);
			
			arR.save(arch);
			
		}
		
	}

	@Override
	public List<Archivos> getArchivosByCodReunion(int codreunion) {
		return arR.getArchivosByCodReunion(codreunion);
	}


	@Override
	public byte[] ejecutarArchivo(int codarchivo) {
		return arR.getArchivoByCodArchivo(codarchivo);
	}

	@Override
	public void borrarArchivo(int codarchivo) {
		
		Archivos arch = arR.findOne(codarchivo);
		
		if(arch != null) {
			arR.delete(arch);
		}
		
	}
	
	



}
