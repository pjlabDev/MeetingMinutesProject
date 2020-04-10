/**
 * 
 */
package com.pedro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Reunion;
import com.pedro.modelo.SerieReunion;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.ReunionRepository;
import com.pedro.repository.SerieReunionRepository;
import com.pedro.repository.UserRepository;

/**
 * @author Westermeyer
 *
 */
@Service
public class ReunionServiceImpl implements ReunionService {

	@Autowired
	ReunionRepository rr;
	
	@Autowired
	SerieReunionRepository sr;
	
	@Autowired
	UserRepository userR;
	
	@Override
	public List<Reunion> getReunionBySerieReunion(int codsreunion) {
		return rr.getReunionBySerieReunion(codsreunion);
	}

	@Override
	public void crearReunion(Reunion reunion, int codsreunion, int[] codsusu) {
		
		Set<Usuarios> usuario = new HashSet<>();
		
		SerieReunion newSR = sr.findOne(codsreunion);
		
		for (int i : codsusu) {			
			Usuarios user = userR.findOne(i);
			
			usuario.add(user);
		}
		
		if(newSR != null && !usuario.isEmpty()) {
			
			Reunion newReunion = new Reunion(reunion.getFecha(),newSR,usuario);
			
			rr.save(newReunion);
			
		}
		
	}
	
	@Override
	public Reunion getReunionByCodReunion(int codreunion) {
		return rr.getReunionByCodReunion(codreunion);
	}

	@Override
	public List<Reunion> getReunionByUsuario(int codusu) {
		return rr.getReunionByUsuario(codusu);
	}	
	

}
