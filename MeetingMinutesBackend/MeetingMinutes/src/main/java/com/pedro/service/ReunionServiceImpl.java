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
 * The Class ReunionServiceImpl.
 *
 * @author Westermeyer
 */
@Service
public class ReunionServiceImpl implements ReunionService {

	/** The rr. */
	@Autowired
	ReunionRepository rr;
	
	/** The sr. */
	@Autowired
	SerieReunionRepository sr;
	
	/** The userR. */
	@Autowired
	UserRepository userR;
	
	/**
	 * Gets the reunion by serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the reunion by serie reunion
	 */
	@Override
	public List<Reunion> getReunionBySerieReunion(int codsreunion) {
		return rr.getReunionBySerieReunion(codsreunion);
	}

	/**
	 * Crear reunion.
	 *
	 * @param reunion the reunion
	 * @param codsreunion the codsreunion
	 * @param codsusu the codsusu
	 */
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
	
	/**
	 * Gets the reunion by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the reunion by cod reunion
	 */
	@Override
	public Reunion getReunionByCodReunion(int codreunion) {
		return rr.getReunionByCodReunion(codreunion);
	}

	/**
	 * Gets the reuniones.
	 *
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 * @return the reuniones
	 */
	@Override
	public List<Reunion> getReuniones(int codusu, int codsreunion) {
		return rr.getReuniones(codusu, codsreunion);
	}

	/**
	 * Modificar reunion.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@Override
	public void modificarReunion(Reunion reunion, int[] codusu) {
		
		Reunion updateReunion = rr.findOne(reunion.getCodReunion());
		Set<Usuarios> participantes = reunion.getUsuarios();
		
		if(updateReunion != null) {
			
			updateReunion.setFecha(reunion.getFecha());
			
			if(codusu[0] != -1) {
				for (int cods : codusu) {
					Usuarios us = userR.findOne(cods);
					participantes.add(us);
				}
				updateReunion.setUsuarios(participantes);
			}
			rr.save(updateReunion);
		}
		
		
	}

	/**
	 * Eliminar participante.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	@Override
	public void eliminarParticipante(Reunion reunion, int codusu) {
		
		Reunion reu = rr.findOne(reunion.getCodReunion());
		Usuarios us = userR.findOne(codusu);
		
		if(reu != null && us != null) {
			
			if(reu.getUsuarios().contains(us)) {
				reu.getUsuarios().remove(us);
			}
			rr.save(reu);			
		}
		
	}	
	

}
