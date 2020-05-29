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
	public List<Temas> getTemasByCodReunion(int codreunion) {
		return tr.getTemasByCodReunion(codreunion);
	}

	@Override
	public List<Temas> getAllTemasByCodSReunion(int codsreunion) {
		return tr.getAllTemasByCodSReunion(codsreunion);
	}
	
	@Override
	public void crearTemas(Temas tema, int codreunion, int codsreunion) {
		
		Set<Reunion> reu = new HashSet<>();
		
		Reunion newR = rr.findOne(codreunion);
		
		SerieReunion newSR = sr.findOne(codsreunion);
		
		if(newR != null && newSR != null) {
			reu.add(newR);
			Temas newTema = new Temas();
			
			newTema.setTitulo(tema.getTitulo());
			newTema.setEtiqueta(tema.getEtiqueta());
			newTema.setReunion(reu);
			newTema.setSeriereunion(newSR);
			
			tr.save(newTema);
		}
		
	}

	@Override
	public void añadirInfoTema(Temas tema, int codTema) {
		
		Temas updateTema = tr.findOne(codTema);
		String infoTema = "";
		
		if(updateTema != null) {
			if(updateTema.getInfo() == null) {
				infoTema += tema.getInfo();				
			}else {
				infoTema += updateTema.getInfo() + "\n" + tema.getInfo();
			}
			updateTema.setInfo(infoTema);
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

	@Override
	public List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(int codreunion, int codsreunion) {
		return tr.getTemasByCodReunionAntiguaAndNoCerrado(codreunion, codsreunion);
	}

	@Override
	public void saveTemaAntiguo(Temas[] tema, int codreunion) {
		
		Reunion reunion = rr.findOne(codreunion);
		
		if(tema.length > 0 && reunion != null) {
			
			for (Temas tem : tema) {
				tem.getReunion().add(reunion);
				tr.save(tem);
			}
			
		}
	
		
	}

	@Override
	public Temas getTemaByCodTema(int codtema) {
		return tr.getTemaByCodTema(codtema);
	}

	@Override
	public void modificarTema(Temas tema) {
		
		Temas updateTema = tr.findOne(tema.getCodTema());
		
		if(updateTema != null) {
			
			updateTema.setTitulo(tema.getTitulo());
			updateTema.setInfo(tema.getInfo());
			updateTema.setEtiqueta(tema.getEtiqueta());
			updateTema.setDecision(tema.getDecision());
			tr.save(updateTema);
			
		}
		
	}

	@Override
	public void añadirSeguimientoTemaCerrado(Temas tema) {
		
		Temas segTema = tr.findOne(tema.getCodTema());
		String seguimiento = "";
		
		if(segTema != null) {
			if(segTema.getSeguimiento() == null) {
				seguimiento = tema.getSeguimiento();
			}else {
				seguimiento = tema.getSeguimiento() + "\n" + tema.getSeguimiento();
			}
			segTema.setSeguimiento(seguimiento);
			tr.save(segTema);
		}
		
	}

}
