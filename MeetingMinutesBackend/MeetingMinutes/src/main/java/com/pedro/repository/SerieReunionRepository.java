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
	
	@Query(value = "SELECT * FROM serie_reunion JOIN serie_reunion_usuarios WHERE serie_reunion_usuarios.usuarios_codusu like ?1 AND serie_reunion_usuarios.serie_reunion_codsreunion = codsreunion AND cerrado = 0", nativeQuery = true)
	List<SerieReunion> getSerieReunionByUsuario(int codusu);
	
	@Query(value = "SELECT * FROM serie_reunion WHERE codsreunion like ?1", nativeQuery = true)
	SerieReunion getSerieReunionByCodReunion(int codsreunion);
}
