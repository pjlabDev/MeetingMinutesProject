/**
 * 
 */
package com.pedro.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "Temas")
public class Temas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codtema")
	private int codTema;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "etiqueta")
	private String etiqueta;
	
	@Column(name = "decision")
	private String decision;
	
	@OneToOne
	private Reunion reunion;

	@Column(name = "cerrado")
	@NotNull
	private int cerrado;

	@OneToOne
	private SerieReunion seriereunion;
	
	
	/**
	 * 
	 */
	
	public Temas(int codTema, String titulo, String info, String decision, int cerrado) {
		this.codTema = codTema;
		this.titulo = titulo;
		this.info = info;
		this.decision = decision;
		this.cerrado = cerrado;
	}
	
	public Temas() {
		super();
	}

	public int getCodTema() {
		return codTema;
	}

	public void setCodTema(int codTema) {
		this.codTema = codTema;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	public SerieReunion getSeriereunion() {
		return seriereunion;
	}

	public void setSeriereunion(SerieReunion seriereunion) {
		this.seriereunion = seriereunion;
	}

	public int getCerrado() {
		return cerrado;
	}

	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}

}
