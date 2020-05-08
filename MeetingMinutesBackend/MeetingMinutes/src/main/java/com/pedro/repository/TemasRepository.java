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
	
	@Query(value = "SELECT * FROM temas JOIN temas_reunion WHERE temas_reunion.temas_codtema = codtema AND temas_reunion.reunion_codreunion like ?1 ORDER BY codtema DESC", nativeQuery = true)
	List<Temas> getTemasByCodReunion(int codreunion);
	
	@Query(value = "SELECT * FROM temas WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Temas> getAllTemasByCodSReunion(int codsreunion);
	
	@Query(value = "SELECT * FROM temas JOIN temas_reunion WHERE temas_reunion.reunion_codreunion < ?1 AND cerrado = 0", nativeQuery = true)
	List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(int codreunion);
	
}
