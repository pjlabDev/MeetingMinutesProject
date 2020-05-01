/**
 * 
 */
package com.pedro.service;

import com.pedro.modelo.Temas;

/**
 * @author Westermeyer
 *
 */
public interface EmailService {
	
	public void enviarAgenda(int[] receptores, String fechaReunion, Temas[] tema, int[] temasAntiguos);
	
}
