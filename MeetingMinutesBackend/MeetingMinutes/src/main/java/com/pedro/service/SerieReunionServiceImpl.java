/**
 * 
 */
package com.pedro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.SerieReunion;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.SerieReunionRepository;
import com.pedro.repository.UserRepository;

/**
 * @author Westermeyer
 *
 */

@Service
public class SerieReunionServiceImpl implements SerieReunionService {

	@Autowired
	SerieReunionRepository srRepo;
	
	@Autowired
	UserRepository userR;
	
	@Override
	public List<SerieReunion> getSerieReunionByUsuario(int codusu) {
		return srRepo.getSerieReunionByUsuario(codusu);
	}

	@Override
	public void crearSerieReunion(SerieReunion reunion, int[] codusu) {
		
		Set<Usuarios> usuario = new HashSet<>();
		
		for (int i : codusu) {			
			Usuarios user = userR.findOne(i);
			
			usuario.add(user);
		}
		
		SerieReunion reu = new SerieReunion(reunion.getEquipo(),reunion.getNombre(),usuario);
		
		srRepo.save(reu);
		
	}

	@Override
	public SerieReunion getSerieReunionByCodReunion(int codsreunion) {
		return srRepo.getSerieReunionByCodReunion(codsreunion);
	}

	@Override
	public void modificarSerieReunion(SerieReunion reunion) {
		SerieReunion serieReunion = srRepo.findOne(reunion.getCodSReunion());
		
		if(serieReunion != null) {
			
			serieReunion.setEquipo(reunion.getEquipo());
			serieReunion.setNombre(reunion.getNombre());
			serieReunion.setCerrado(reunion.getCerrado());
			srRepo.save(serieReunion);
			
		}
		
	}

	@Override
	public void modificarSerieReunionInvitandoMasUsuarios(SerieReunion reunion, int[] codusu) {
		
		SerieReunion serieReunion = srRepo.findOne(reunion.getCodSReunion());
		Set<Usuarios> usuario = reunion.getUsuarios();
		
		if(serieReunion != null) {
			
			for (int i : codusu) {
				Usuarios user = userR.findOne(i);
				
				usuario.add(user);
			}
			
			serieReunion.setEquipo(reunion.getEquipo());
			serieReunion.setNombre(reunion.getNombre());
			serieReunion.setCerrado(reunion.getCerrado());
			serieReunion.setUsuarios(usuario);
			srRepo.save(serieReunion);
			
		}
		
	}
	
}
