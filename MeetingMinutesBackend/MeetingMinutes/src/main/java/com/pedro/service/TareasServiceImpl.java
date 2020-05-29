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
 * @author Westermeyer
 *
 */
@Service
public class TareasServiceImpl implements TareasService {

	@Autowired
	TareasRepository tareasRepo;
	
	@Autowired
	ReunionRepository rr;
	
	@Autowired
	SerieReunionRepository sr;

	@Autowired
	UserRepository ur;

	@Override
	public List<Tareas> getTareasByCodReunion(int codreunion) {
		return tareasRepo.getTareasByCodReunion(codreunion);
	}

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

	@Override
	public List<Tareas> getAllTareasByCodSReunion(int codsreunion) {
		return tareasRepo.getAllTareasByCodSReunion(codsreunion);
	}

	@Override
	public void cerrarTareas(Tareas tarea) {
		
		Tareas tar = tareasRepo.findOne(tarea.getCodTarea());
		
		if(tar != null) {
			tar.setCerrado(1);
			tareasRepo.save(tar);
		}
		
	}

	@Override
	public List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(int codreunion, int codsreunion) {
		return tareasRepo.getTareasByCodReunionAntiguaAndNoCerrada(codreunion, codsreunion);
	}

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

	@Override
	public Tareas getTareaByCodTarea(int codtarea) {
		return tareasRepo.getTareaByCodTarea(codtarea);
	}

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
