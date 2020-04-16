/**
 * 
 */
package com.pedro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.SerieReunion;
import com.pedro.modelo.Temas;
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
	private SerieReunionRepository sr;

	@Override
	public List<Temas> getTemasBySerieReunion(int codsreunion) {
		return tr.getTemasBySerieReunion(codsreunion);
	}

	@Override
	public void crearTemas(Temas tema, int codsreunion) {
		
		SerieReunion newSR = sr.findOne(codsreunion);
		
		if(newSR != null) {
			Temas newTema = new Temas();
			
			newTema.setTitulo(tema.getTitulo());
			newTema.setEtiqueta(tema.getEtiqueta());
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

}
