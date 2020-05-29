/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.SerieReunion;

/**
 * @author Westermeyer
 *
 */
public interface SerieReunionService {
	
	List<SerieReunion> getSerieReunionByUsuario(int codusu);
	void crearSerieReunion(SerieReunion reunion, int[] codusu);
	SerieReunion getSerieReunionByCodReunion(int codsreunion);
	void modificarSerieReunion(SerieReunion reunion);
	void modificarSerieReunionInvitandoMasUsuarios(SerieReunion reunion, int[] codusu);
	void eliminarParticipante(SerieReunion sr, int codusu);
	
}
