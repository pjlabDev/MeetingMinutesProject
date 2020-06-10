/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.Archivos;

/**
 * The Interface ArchivosRepository.
 *
 * @author Westermeyer
 */
@Repository
public interface ArchivosRepository extends JpaRepository<Archivos, Integer> {
	
	/**
	 * Gets the archivos by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the archivos by cod reunion
	 */
	@Query(value = "SELECT * FROM archivos WHERE reunion_codreunion like ?1", nativeQuery = true)
	List<Archivos> getArchivosByCodReunion(int codreunion);
	
	/**
	 * Gets the archivo by cod archivo.
	 *
	 * @param codarchivo the codarchivo
	 * @return the archivo by cod archivo
	 */
	@Query(value = "SELECT archivo FROM archivos WHERE codarchivo like ?1", nativeQuery = true)
	byte[] getArchivoByCodArchivo(int codarchivo);
	
}
