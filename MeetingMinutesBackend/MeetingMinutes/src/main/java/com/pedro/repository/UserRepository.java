/**
 * 
 */
package com.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.modelo.Usuarios;

/**
 * @author Westermeyer
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Usuarios, Integer>{
	
	@Query(value = "SELECT * FROM usuarios WHERE nombre like ?1 and clave like ?2", nativeQuery = true)
	Usuarios login(String nombre, String clave);
	
	@Query(value = "SELECT * FROM usuarios", nativeQuery = true)
	List<Usuarios> getAllUsuarios();
	
}
