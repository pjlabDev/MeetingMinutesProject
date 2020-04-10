/**
 * 
 */
package com.pedro.service;

import com.pedro.modelo.Actas;

/**
 * @author Westermeyer
 *
 */
public interface ActaService {
	
	void generarActa(Actas acta, int codReunion, int[] codusu);
	
}
