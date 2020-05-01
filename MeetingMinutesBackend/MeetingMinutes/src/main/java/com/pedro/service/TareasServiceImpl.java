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
	public List<Tareas> getTareasByCodSReunion(int codsreunion) {
		return tareasRepo.getTareasByCodSReunion(codsreunion);
	}

	@Override
	public void crearTareas(Tareas tarea, int codreunion, int[] codusu, int codsreunion) {
		Set<Usuarios> usuario = new HashSet<>();
		
		Reunion reu = rr.findOne(codreunion);
		
		SerieReunion serieR = sr.findOne(codsreunion);
		
		for (int i : codusu) {
			Usuarios user = ur.findOne(i);
			
			usuario.add(user);
		}
		
		if(reu != null && !usuario.isEmpty() && serieR != null) {
			
			Tareas newTarea = new Tareas(tarea.getTitulo(), tarea.getDescripcion(), usuario, reu, serieR);
			
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
	public List<Tareas> getTareasByCodReunionAndNoCerrado(int codreunion) {
		return tareasRepo.getTareasByCodReunionAndNoCerrado(codreunion);
	}

	@Override
	public List<Tareas> getTareasByCodReunionAntiguaAndNoCerrado(int codreunion) {
		return tareasRepo.getTareasByCodReunionAntiguaAndNoCerrado(codreunion);
	}
	
}
