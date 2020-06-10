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
import com.pedro.modelo.Tareas;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.ReunionRepository;
import com.pedro.repository.SerieReunionRepository;
import com.pedro.repository.TareasRepository;
import com.pedro.repository.UserRepository;

/**
 * The Class TareasServiceImpl.
 *
 * @author Westermeyer
 */
@Service
public class TareasServiceImpl implements TareasService {

	/** The tareasRepo. */
	@Autowired
	TareasRepository tareasRepo;
	
	/** The rr. */
	@Autowired
	ReunionRepository rr;
	
	/** The sr. */
	@Autowired
	SerieReunionRepository sr;

	/** The ur. */
	@Autowired
	UserRepository ur;

	/**
	 * Gets the tareas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the tareas by cod reunion
	 */
	@Override
	public List<Tareas> getTareasByCodReunion(int codreunion) {
		return tareasRepo.getTareasByCodReunion(codreunion);
	}

	/**
	 * Crear tareas.
	 *
	 * @param tarea the tarea
	 * @param codreunion the codreunion
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 */
	@Override
	public void crearTareas(Tareas tarea, int codreunion, int[] codusu, int codsreunion) {
		Set<Usuarios> usuario = new HashSet<>();
		
		Set<Reunion> reunion = new HashSet<>();
		
		Reunion reu = rr.findOne(codreunion);
		
		SerieReunion serieR = sr.findOne(codsreunion);
		
		for (int i : codusu) {
			Usuarios user = ur.findOne(i);
			
			usuario.add(user);
		}
		
		if(reu != null && !usuario.isEmpty() && serieR != null) {
			
			reunion.add(reu);
			
			Tareas newTarea = new Tareas(tarea.getTitulo(), tarea.getDescripcion(), usuario, reunion, serieR);
			
			tareasRepo.save(newTarea);
			
		}
		
	}

	/**
	 * Gets the all tareas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all tareas by cod S reunion
	 */
	@Override
	public List<Tareas> getAllTareasByCodSReunion(int codsreunion) {
		return tareasRepo.getAllTareasByCodSReunion(codsreunion);
	}

	/**
	 * Cerrar tareas.
	 *
	 * @param tarea the tarea
	 */
	@Override
	public void cerrarTareas(Tareas tarea) {
		
		Tareas tar = tareasRepo.findOne(tarea.getCodTarea());
		
		if(tar != null) {
			tar.setCerrado(1);
			tareasRepo.save(tar);
		}
		
	}

	/**
	 * Gets the tareas by cod reunion antigua and no cerrada.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the tareas by cod reunion antigua and no cerrada
	 */
	@Override
	public List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(int codreunion, int codsreunion) {
		return tareasRepo.getTareasByCodReunionAntiguaAndNoCerrada(codreunion, codsreunion);
	}

	/**
	 * Save tareas antiguas.
	 *
	 * @param tareas the tareas
	 * @param codreunion the codreunion
	 */
	@Override
	public void saveTareasAntiguas(Tareas[] tareas, int codreunion) {
		
		Reunion reunion = rr.findOne(codreunion);
		
		if(tareas.length > 0 && reunion != null) {
			
			for (Tareas tar : tareas) {
				tar.getReunion().add(reunion);
				tareasRepo.save(tar);
			}
			
		}

	}

	/**
	 * Gets the tarea by cod tarea.
	 *
	 * @param codtarea the codtarea
	 * @return the tarea by cod tarea
	 */
	@Override
	public Tareas getTareaByCodTarea(int codtarea) {
		return tareasRepo.getTareaByCodTarea(codtarea);
	}

	/**
	 * Modificar tarea.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	@Override
	public void modificarTarea(Tareas tarea, int[] codusu) {
		
		Tareas updateTarea = tareasRepo.findOne(tarea.getCodTarea());
		Set<Usuarios> usuario = tarea.getUsuarios();
		
		if(updateTarea != null) {
			
			updateTarea.setTitulo(tarea.getTitulo());
			updateTarea.setDescripcion(tarea.getDescripcion());
			
			if(codusu[0] != -1) {
				for (int cod : codusu) {
					Usuarios usu = ur.findOne(cod);
					usuario.add(usu);
				}
				updateTarea.setUsuarios(usuario);
			}
			tareasRepo.save(updateTarea);
		}
		
	}

	/**
	 * Eliminar responsable.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	@Override
	public void eliminarResponsable(Tareas tarea, int codusu) {
		
		Tareas tar = tareasRepo.findOne(tarea.getCodTarea());
		Usuarios us = ur.findOne(codusu);
		
		if(tar != null && us != null) {
			
			if(tar.getUsuarios().contains(us)) {
				tar.getUsuarios().remove(us);
			}
			tareasRepo.save(tar);
		}
		
	}
	
}
