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
	
	@Query(value = "SELECT * FROM reunion WHERE codreunion like ?1", nativeQuery = true)
	Reunion getReunionByCodReunion(int codreunion);
	
	@Query(value = "SELECT * FROM reunion JOIN reunion_usuarios WHERE reunion_usuarios.reunion_codreunion = codreunion AND reunion_usuarios.usuarios_codusu like ?1", nativeQuery = true)
	List<Reunion> getReunionByUsuario(int codusu);
	
	@Query(value = "SELECT * FROM reunion JOIN reunion_usuarios WHERE reunion_usuarios.reunion_codreunion = codreunion AND reunion_usuarios.usuarios_codusu like ?1 AND seriereunion_codsreunion like ?2", nativeQuery = true)
	List<Reunion> getReuniones(int codusu, int codsreunion);
	
}
