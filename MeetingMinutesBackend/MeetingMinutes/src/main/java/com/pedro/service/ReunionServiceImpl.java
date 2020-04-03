/**
 * 
 */
package com.pedro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Reunion;
import com.pedro.modelo.SerieReunion;
import com.pedro.repository.ReunionRepository;
import com.pedro.repository.SerieReunionRepository;

/**
 * @author Westermeyer
 *
 */
@Service
public class ReunionServiceImpl implements ReunionService {

	@Autowired
	private ReunionRepository rr;
	
	@Autowired
	private SerieReunionRepository sr;
	
	@Override
	public List<Reunion> getReunionBySerieReunion(int codsreunion) {
		return rr.getReunionBySerieReunion(codsreunion);
	}

	@Override
	public void crearReunion(Reunion reunion, int codsreunion) {
		
		SerieReunion newSR = sr.findOne(codsreunion);
		
		if(newSR != null) {
			Reunion newReunion = new Reunion();
			
			newReunion.setFecha(reunion.getFecha());
			newReunion.setParticipantes(reunion.getParticipantes());
			newReunion.setSeriereunion(newSR);
			
			rr.save(newReunion);
			
		}
		
	}

	@Override
	public Reunion getReunionByCodReunion(int codreunion) {
		return rr.getReunionByCodReunion(codreunion);
	}
	
	

}
