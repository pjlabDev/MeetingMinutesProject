/**
 * 
 */
package com.pedro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Reunion;
import com.pedro.modelo.SerieReunion;
import com.pedro.modelo.Temas;
import com.pedro.repository.ReunionRepository;
import com.pedro.repository.SerieReunionRepository;
import com.pedro.repository.TemasRepository;

/**
 * @author Westermeyer
 *
 */
@Service
public class TemasServiceImpl implements TemasService {

	@Autowired
	private TemasRepository tr;
	
	@Autowired
	private ReunionRepository rr;
	
	@Autowired
	private SerieReunionRepository sr;

	@Override
	public List<Temas> getTemasByReunion(int codreunion) {
		return tr.getTemasByReunion(codreunion);
	}

	@Override
	public List<Temas> getAllTemasByCodSReunion(int codsreunion) {
		return tr.getAllTemasByCodSReunion(codsreunion);
	}
	
	@Override
	public void crearTemas(Temas tema, int codreunion, int codsreunion) {
		
		Reunion newR = rr.findOne(codreunion);
		
		SerieReunion newSR = sr.findOne(codsreunion);
		
		if(newR != null && newSR != null) {
			Temas newTema = new Temas();
			
			newTema.setTitulo(tema.getTitulo());
			newTema.setEtiqueta(tema.getEtiqueta());
			newTema.setReunion(newR);
			newTema.setSeriereunion(newSR);
			
			tr.save(newTema);
		}
		
	}

	@Override
	public void añadirInfoTema(Temas tema, int codTema) {
		
		Temas updateTema = tr.findOne(codTema);
		
		if(updateTema != null) {
			updateTema.setInfo(tema.getInfo());
			tr.save(updateTema);
		}
		
	}

	@Override
	public void añadirDecisionTema(Temas tema, int codTema) {
		
		Temas updateTema = tr.findOne(codTema);
		
		if(updateTema != null) {
			updateTema.setDecision(tema.getDecision());
			tr.save(updateTema);
		}
				
	}

	@Override
	public void cerrarTemas(Temas tema) {
		
		Temas tem = tr.findOne(tema.getCodTema());
		
		if(tem != null) {
			tem.setCerrado(1);
			tr.save(tem);
		}
		
	}

}
