/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Tareas;

/**
 * The Interface TareasService.
 *
 * @author Westermeyer
 */
public interface TareasService {
	
	/**
	 * Gets the tareas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the tareas by cod reunion
	 */
	List<Tareas> getTareasByCodReunion(int codreunion);
	
	/**
	 * Crear tareas.
	 *
	 * @param tarea the tarea
	 * @param codreunion the codreunion
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 */
	void crearTareas(Tareas tarea, int codreunion, int[] codusu, int codsreunion);
	
	/**
	 * Gets the all tareas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all tareas by cod S reunion
	 */
	List<Tareas> getAllTareasByCodSReunion(int codsreunion);
	
	/**
	 * Cerrar tareas.
	 *
	 * @param tarea the tarea
	 */
	void cerrarTareas(Tareas tarea);
	
	/**
	 * Gets the tareas by cod reunion antigua and no cerrada.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the tareas by cod reunion antigua and no cerrada
	 */
	List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(int codreunion, int codsreunion);
	
	/**
	 * Save tareas antiguas.
	 *
	 * @param tareas the tareas
	 * @param codreunion the codreunion
	 */
	void saveTareasAntiguas(Tareas[] tareas, int codreunion);
	
	/**
	 * Gets the tarea by cod tarea.
	 *
	 * @param codtarea the codtarea
	 * @return the tarea by cod tarea
	 */
	Tareas getTareaByCodTarea(int codtarea);
	
	/**
	 * Modificar tarea.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	void modificarTarea(Tareas tarea, int[] codusu);
	
	/**
	 * Eliminar responsable.
	 *
	 * @param tarea the tarea
	 * @param codusu the codusu
	 */
	void eliminarResponsable(Tareas tarea, int codusu);
}
