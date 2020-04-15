/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Tareas;

/**
 * @author Westermeyer
 *
 */
public interface TareasService {
	
	List<Tareas> getTareasByCodReunion(int codreunion);
	void crearTareas(Tareas tarea, int codreunion, int[] codusu);
	List<Tareas> getAllTareas();
}
