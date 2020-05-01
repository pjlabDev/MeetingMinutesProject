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
	
	List<Tareas> getTareasByCodSReunion(int codsreunion);
	void crearTareas(Tareas tarea, int codreunion, int[] codusu, int codsreunion);
	List<Tareas> getAllTareasByCodSReunion(int codsreunion);
	void cerrarTareas(Tareas tarea);
	List<Tareas> getTareasByCodReunionAndNoCerrado(int codreunion);
	List<Tareas> getTareasByCodReunionAntiguaAndNoCerrado(int codreunion);
}
