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
	
	@Query(value = "SELECT * FROM usuarios WHERE codusu NOT IN(SELECT codusu FROM usuarios JOIN serie_reunion_usuarios WHERE serie_reunion_usuarios.usuarios_codusu = codusu AND serie_reunion_usuarios.serie_reunion_codsreunion like ?1)", nativeQuery = true)
	List<Usuarios> getUsuariosNotInSerieReunion(int codsreunion);
	
	@Query(value = "SELECT * FROM usuarios JOIN serie_reunion_usuarios WHERE serie_reunion_usuarios.usuarios_codusu = codusu AND serie_reunion_usuarios.serie_reunion_codsreunion like ?1", nativeQuery = true)
	List<Usuarios> getUsuariosInSerieReunion(int codsreunion);
	
	@Query(value = "SELECT * FROM usuarios JOIN reunion_usuarios WHERE reunion_usuarios.usuarios_codusu = usuarios.codusu AND reunion_usuarios.reunion_codreunion like ?1", nativeQuery = true)
	List<Usuarios> getUsuariosByCodReunion(int codreunion);
}
