/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.SerieReunion;

/**
 * The Interface SerieReunionRepository.
 *
 * @author Westermeyer
 */
@Repository
public interface SerieReunionRepository extends JpaRepository<SerieReunion, Integer> {
	
	/**
	 * Gets the serie reunion by usuario.
	 *
	 * @param codusu the codusu
	 * @return the serie reunion by usuario
	 */
	@Query(value = "SELECT * FROM serie_reunion JOIN serie_reunion_usuarios WHERE serie_reunion_usuarios.usuarios_codusu like ?1 AND serie_reunion_usuarios.serie_reunion_codsreunion = codsreunion AND cerrado = 0", nativeQuery = true)
	List<SerieReunion> getSerieReunionByUsuario(int codusu);
	
	/**
	 * Gets the serie reunion by cod reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the serie reunion by cod reunion
	 */
	@Query(value = "SELECT * FROM serie_reunion WHERE codsreunion like ?1", nativeQuery = true)
	SerieReunion getSerieReunionByCodReunion(int codsreunion);
}
