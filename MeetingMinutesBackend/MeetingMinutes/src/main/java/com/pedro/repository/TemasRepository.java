/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedro.modelo.Temas;

/**
 * @author Westermeyer
 *
 */
public interface TemasRepository extends JpaRepository<Temas, Integer> {
	
	@Query(value = "SELECT * FROM temas WHERE reunion_codreunion like ?1 AND cerrado = 0", nativeQuery = true)
	List<Temas> getTemasByReunion(int codreunion);
	
	@Query(value = "SELECT * FROM temas WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Temas> getAllTemasByCodSReunion(int codsreunion);
}
