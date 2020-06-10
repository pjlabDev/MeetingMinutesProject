/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.Reunion;

/**
 * The Interface ReunionRepository.
 *
 * @author Westermeyer
 */
@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Integer> {
	
	/**
	 * Gets the reunion by serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the reunion by serie reunion
	 */
	@Query(value = "SELECT * FROM reunion WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Reunion> getReunionBySerieReunion(int codsreunion);
	
	/**
	 * Gets the reunion by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the reunion by cod reunion
	 */
	@Query(value = "SELECT * FROM reunion WHERE codreunion like ?1", nativeQuery = true)
	Reunion getReunionByCodReunion(int codreunion);
	
	/**
	 * Gets the reuniones.
	 *
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 * @return the reuniones
	 */
	@Query(value = "SELECT * FROM reunion JOIN reunion_usuarios WHERE reunion_usuarios.reunion_codreunion = codreunion AND reunion_usuarios.usuarios_codusu like ?1 AND seriereunion_codsreunion like ?2 ORDER BY codreunion DESC", nativeQuery = true)
	List<Reunion> getReuniones(int codusu, int codsreunion);
	
}
