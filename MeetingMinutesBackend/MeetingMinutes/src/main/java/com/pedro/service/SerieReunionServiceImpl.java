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
	public void crearSerieReunion(SerieReunion reunion, int codusu) {
		Usuarios user = userR.findOne(codusu);
		
		Set<Usuarios> usuario = new HashSet<>();
		usuario.add(user);
		
		SerieReunion reu = new SerieReunion(reunion.getEquipo(),reunion.getNombre(),usuario);
		
		srRepo.save(reu);
		
	}	
	
}
