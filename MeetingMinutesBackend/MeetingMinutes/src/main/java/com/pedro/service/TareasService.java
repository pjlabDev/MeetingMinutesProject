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
	void crearTareas(Tareas tarea, int codreunion, int[] codusu, int codsreunion);
	List<Tareas> getAllTareasByCodSReunion(int codsreunion);
	void cerrarTareas(Tareas tarea);
	List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(int codreunion, int codsreunion);
	void saveTareasAntiguas(Tareas[] tareas, int codreunion);
	Tareas getTareaByCodTarea(int codtarea);
	void modificarTarea(Tareas tarea, int[] codusu);
}
