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
	void cerrarSerieReunion(SerieReunion reunion);
	
}
