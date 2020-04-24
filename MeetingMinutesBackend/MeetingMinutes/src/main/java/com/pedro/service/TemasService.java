/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Temas;

/**
 * @author Westermeyer
 *
 */
public interface TemasService {
	
	List<Temas> getTemasBySerieReunion(int codsreunion);
	void crearTemas(Temas tema, int codsreunion);
	void añadirInfoTema(Temas tema, int codTema);
	void añadirDecisionTema(Temas tema, int codTema);
	
}
