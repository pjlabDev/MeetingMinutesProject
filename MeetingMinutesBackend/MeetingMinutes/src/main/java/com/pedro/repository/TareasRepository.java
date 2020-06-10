/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.Tareas;

/**
 * The Interface TareasRepository.
 *
 * @author Westermeyer
 */
@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
	
	/**
	 * Gets the tareas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the tareas by cod reunion
	 */
	@Query(value = "SELECT * FROM tareas JOIN tareas_reunion WHERE tareas_reunion.tareas_codtarea = codtarea AND tareas_reunion.reunion_codreunion like ?1 ORDER BY codtarea DESC", nativeQuery = true)
	List<Tareas> getTareasByCodReunion(int codreunion);

	/**
	 * Gets the all tareas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all tareas by cod S reunion
	 */
	@Query(value = "SELECT * FROM tareas WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Tareas> getAllTareasByCodSReunion(int codsreunion);
	
	/**
	 * Gets the tareas by cod reunion and no cerrado.
	 *
	 * @param codreunion the codreunion
	 * @return the tareas by cod reunion and no cerrado
	 */
	@Query(value = "SELECT * FROM tareas WHERE reunion_codreunion like ?1 AND cerrado = 0", nativeQuery = true)
	List<Tareas> getTareasByCodReunionAndNoCerrado(int codreunion);
	
	/**
	 * Gets the tareas by cod reunion antigua and no cerrada.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the tareas by cod reunion antigua and no cerrada
	 */
	@Query(value = "SELECT * FROM tareas JOIN tareas_reunion WHERE tareas_reunion.reunion_codreunion < ?1 AND cerrado = 0 AND tareas.seriereunion_codsreunion like ?2", nativeQuery = true)
	List<Tareas> getTareasByCodReunionAntiguaAndNoCerrada(int codreunion, int codsreunion);
	
	/**
	 * Gets the tarea by cod tarea.
	 *
	 * @param codtarea the codtarea
	 * @return the tarea by cod tarea
	 */
	@Query(value = "SELECT * FROM tareas WHERE codtarea like ?1", nativeQuery = true)
	Tareas getTareaByCodTarea(int codtarea);
	
}
