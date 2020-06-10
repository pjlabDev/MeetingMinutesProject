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
 * The Class TemasServiceImpl.
 *
 * @author Westermeyer
 */
@Service
public class TemasServiceImpl implements TemasService {

	/** The tr. */
	@Autowired
	private TemasRepository tr;
	
	/** The rr. */
	@Autowired
	private ReunionRepository rr;
	
	/** The sr. */
	@Autowired
	private SerieReunionRepository sr;

	/**
	 * Gets the temas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the temas by cod reunion
	 */
	@Override
	public List<Temas> getTemasByCodReunion(int codreunion) {
		return tr.getTemasByCodReunion(codreunion);
	}

	/**
	 * Gets the all temas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all temas by cod S reunion
	 */
	@Override
	public List<Temas> getAllTemasByCodSReunion(int codsreunion) {
		return tr.getAllTemasByCodSReunion(codsreunion);
	}
	
	/**
	 * Crear temas.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 */
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

	/**
	 * Añadir info tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
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

	/**
	 * Añadir decision tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
	@Override
	public void añadirDecisionTema(Temas tema, int codTema) {
		
		Temas updateTema = tr.findOne(codTema);
		
		if(updateTema != null) {
			updateTema.setDecision(tema.getDecision());
			tr.save(updateTema);
		}
				
	}

	/**
	 * Cerrar temas.
	 *
	 * @param tema the tema
	 */
	@Override
	public void cerrarTemas(Temas tema) {
		
		Temas tem = tr.findOne(tema.getCodTema());
		
		if(tem != null) {
			tem.setCerrado(1);
			tr.save(tem);
		}
		
	}

	/**
	 * Gets the temas by cod reunion antigua and no cerrado.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the temas by cod reunion antigua and no cerrado
	 */
	@Override
	public List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(int codreunion, int codsreunion) {
		return tr.getTemasByCodReunionAntiguaAndNoCerrado(codreunion, codsreunion);
	}

	/**
	 * Save tema antiguo.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 */
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

	/**
	 * Gets the tema by cod tema.
	 *
	 * @param codtema the codtema
	 * @return the tema by cod tema
	 */
	@Override
	public Temas getTemaByCodTema(int codtema) {
		return tr.getTemaByCodTema(codtema);
	}

	/**
	 * Modificar tema.
	 *
	 * @param tema the tema
	 */
	@Override
	public void modificarTema(Temas tema) {
		
		Temas updateTema = tr.findOne(tema.getCodTema());
		
		if(updateTema != null) {
			
			updateTema.setTitulo(tema.getTitulo());
			updateTema.setInfo(tema.getInfo());
			updateTema.setEtiqueta(tema.getEtiqueta());
			updateTema.setDecision(tema.getDecision());
			updateTema.setSeguimiento(tema.getSeguimiento());
			tr.save(updateTema);
			
		}
		
	}

	/**
	 * Añadir seguimiento tema cerrado.
	 *
	 * @param tema the tema
	 */
	@Override
	public void añadirSeguimientoTemaCerrado(Temas tema) {
		
		Temas segTema = tr.findOne(tema.getCodTema());
		String seguimiento = "";
		
		if(segTema != null) {
			if(segTema.getSeguimiento() == null) {
				seguimiento += tema.getSeguimiento();
			}else {
				seguimiento += segTema.getSeguimiento() + "\n" + tema.getSeguimiento();
			}
			segTema.setSeguimiento(seguimiento);
			tr.save(segTema);
		}
		
	}

}
