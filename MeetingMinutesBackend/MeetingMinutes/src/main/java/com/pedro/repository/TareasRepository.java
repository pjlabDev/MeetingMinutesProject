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
 * @author Westermeyer
 *
 */
@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
	
	@Query(value = "SELECT * FROM tareas WHERE reunion_codreunion like ?1 AND cerrado = 0", nativeQuery = true)
	List<Tareas> getTareasByCodReunion(int codreunion);

	@Query(value = "SELECT * FROM tareas WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Tareas> getAllTareasByCodSReunion(int codsreunion);
}
