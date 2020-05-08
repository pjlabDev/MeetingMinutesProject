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
	
	public void enviarAgenda(int[] receptores, String fechaReunion, Temas[] tema);
	public void enviarActa(int[] receptores, String fechaActa, int[] codtema, int[] codtarea, String conclusion);
	
}
