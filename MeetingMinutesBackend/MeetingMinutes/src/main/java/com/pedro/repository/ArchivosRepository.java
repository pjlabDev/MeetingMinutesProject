/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedro.modelo.Archivos;

/**
 * @author Westermeyer
 *
 */
public interface ArchivosRepository extends JpaRepository<Archivos, Integer> {
	
	@Query(value = "SELECT * FROM archivos WHERE reunion_codreunion like ?1", nativeQuery = true)
	List<Archivos> getArchivosByCodReunion(int codreunion);
	
	@Query(value = "SELECT archivo FROM archivos WHERE codarchivo like ?1", nativeQuery = true)
	byte[] getArchivoByCodArchivo(int codarchivo);
	
}
