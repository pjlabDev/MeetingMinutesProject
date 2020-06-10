/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.Temas;

/**
 * The Interface TemasRepository.
 *
 * @author Westermeyer
 */
@Repository
public interface TemasRepository extends JpaRepository<Temas, Integer> {
	
	/**
	 * Gets the temas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the temas by cod reunion
	 */
	@Query(value = "SELECT * FROM temas JOIN temas_reunion WHERE temas_reunion.temas_codtema = codtema AND temas_reunion.reunion_codreunion like ?1 ORDER BY codtema DESC", nativeQuery = true)
	List<Temas> getTemasByCodReunion(int codreunion);
	
	/**
	 * Gets the all temas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all temas by cod S reunion
	 */
	@Query(value = "SELECT * FROM temas WHERE seriereunion_codsreunion like ?1", nativeQuery = true)
	List<Temas> getAllTemasByCodSReunion(int codsreunion);
	
	/**
	 * Gets the temas by cod reunion antigua and no cerrado.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the temas by cod reunion antigua and no cerrado
	 */
	@Query(value = "SELECT * FROM temas JOIN temas_reunion WHERE temas_reunion.reunion_codreunion < ?1 AND cerrado = 0 AND temas.seriereunion_codsreunion like ?2", nativeQuery = true)
	List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(int codreunion, int codsreunion);
	
	/**
	 * Gets the tema by cod tema.
	 *
	 * @param codtema the codtema
	 * @return the tema by cod tema
	 */
	@Query(value = "SELECT * FROM temas WHERE codtema like ?1", nativeQuery = true)
	Temas getTemaByCodTema(int codtema);
	
}
