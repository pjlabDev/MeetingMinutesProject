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
import com.pedro.modelo.Tareas;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.ReunionRepository;
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
	UserRepository ur;

	@Override
	public List<Tareas> getTareasByCodReunion(int codreunion) {
		return tareasRepo.getTareasByCodReunion(codreunion);
	}

	@Override
	public void crearTareas(Tareas tarea, int codreunion, int[] codusu) {
		Set<Usuarios> usuario = new HashSet<>();
		
		Reunion reu = rr.findOne(codreunion);
		
		for (int i : codusu) {
			Usuarios user = ur.findOne(i);
			
			usuario.add(user);
		}
		
		if(reu != null && !usuario.isEmpty()) {
			
			Tareas newTarea = new Tareas(tarea.getTitulo(), tarea.getDescripcion(), usuario, reu);
			
			tareasRepo.save(newTarea);
			
		}
		
		
	}

	@Override
	public List<Tareas> getAllTareas() {
		return tareasRepo.getAllTareas();
	}
	
}
