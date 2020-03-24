/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedro.modelo.SerieReunion;

/**
 * @author Westermeyer
 *
 */
public interface SerieReunionRepository extends JpaRepository<SerieReunion, Integer> {
	
	@Query(value = "SELECT * FROM serie_reunion JOIN usuario_seriereunion WHERE usuario_seriereunion.id_usuario like ?1 AND usuario_seriereunion.id_seriereunion = codsreunion AND cerrado = 0", nativeQuery = true)
	List<SerieReunion> getSerieReunionByUsuario(int codusu);
}
