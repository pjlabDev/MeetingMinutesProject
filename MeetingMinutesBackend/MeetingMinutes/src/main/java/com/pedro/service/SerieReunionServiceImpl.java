/**
 * 
 */
package com.pedro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.SerieReunion;
import com.pedro.repository.SerieReunionRepository;

/**
 * @author Westermeyer
 *
 */

@Service
public class SerieReunionServiceImpl implements SerieReunionService {

	@Autowired
	SerieReunionRepository srRepo;
	
	@Override
	public List<SerieReunion> getSerieReunionByUsuario(int codusu) {
		return srRepo.getSerieReunionByUsuario(codusu);
	}

	@Override
	public void cerrarSerieReunion(SerieReunion reunion) {
		Optional<SerieReunion> updateserie;
		srRepo.save(reunion);
	}
	
	
	
}
