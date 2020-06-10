/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.SerieReunion;

/**
 * The Interface SerieReunionService.
 *
 * @author Westermeyer
 */
public interface SerieReunionService {
	
	/**
	 * Gets the serie reunion by usuario.
	 *
	 * @param codusu the codusu
	 * @return the serie reunion by usuario
	 */
	List<SerieReunion> getSerieReunionByUsuario(int codusu);
	
	/**
	 * Crear serie reunion.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	void crearSerieReunion(SerieReunion reunion, int[] codusu);
	
	/**
	 * Gets the serie reunion by cod reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the serie reunion by cod reunion
	 */
	SerieReunion getSerieReunionByCodReunion(int codsreunion);
	
	/**
	 * Modificar serie reunion.
	 *
	 * @param reunion the reunion
	 */
	void modificarSerieReunion(SerieReunion reunion);
	
	/**
	 * Modificar serie reunion invitando mas usuarios.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	void modificarSerieReunionInvitandoMasUsuarios(SerieReunion reunion, int[] codusu);
	
	/**
	 * Eliminar participante.
	 *
	 * @param sr the sr
	 * @param codusu the codusu
	 */
	void eliminarParticipante(SerieReunion sr, int codusu);
	
}
