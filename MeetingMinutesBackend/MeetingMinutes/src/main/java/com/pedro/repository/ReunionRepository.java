/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedro.modelo.Reunion;

/**
 * @author Westermeyer
 *
 */
public interface ReunionRepository extends JpaRepository<Reunion, Integer> {
	
	@Query(value = "SELECT * FROM reunion WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Reunion> getReunionBySerieReunion(int codsreunion);
	
}
