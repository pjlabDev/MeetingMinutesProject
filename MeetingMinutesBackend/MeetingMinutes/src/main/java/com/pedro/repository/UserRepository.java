/**
 * 
 */
package com.pedro.repository;

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
	
	@Query(value = "select * from usuarios where nombre like ?1 and clave like ?2", nativeQuery = true)
	Usuarios login(String nombre, String clave);
	
}
