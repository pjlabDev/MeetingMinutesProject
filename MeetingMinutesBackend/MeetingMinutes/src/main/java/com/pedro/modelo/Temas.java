/**
 * 
 */
package com.pedro.modelo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Class Temas.
 *
 * @author Westermeyer
 */
@Entity
@Table(name = "Temas")
public class Temas {
	
	/** The cod tema. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codtema")
	private int codTema;
	
	/** The titulo. */
	@Column(name = "titulo")
	private String titulo;
	
	/** The info. */
	@Lob
	@Column(name = "info")
	private String info;
	
	/** The etiqueta. */
	@Column(name = "etiqueta")
	private String etiqueta;
	
	/** The decision. */
	@Column(name = "decision")
	private String decision;
	
	/** The cerrado. */
	@Column(name = "cerrado")
	@NotNull
	private int cerrado;
	
	/** The seguimiento. */
	@Lob
	@Column(name = "seguimiento")
	private String seguimiento;
	
	/** The reunion. */
	@ManyToMany(targetEntity=Reunion.class)
	private Set<Reunion> reunion;

	/** The seriereunion. */
	@OneToOne
	private SerieReunion seriereunion;
	
	/**
	 * Instantiates a new temas.
	 *
	 * @param codTema the cod tema
	 * @param titulo the titulo
	 * @param info the info
	 * @param decision the decision
	 * @param cerrado the cerrado
	 * @param seguimiento the seguimiento
	 */
	
	public Temas(int codTema, String titulo, String info, String decision, int cerrado, String seguimiento) {
		this.codTema = codTema;
		this.titulo = titulo;
		this.info = info;
		this.decision = decision;
		this.cerrado = cerrado;
		this.seguimiento = seguimiento;
	}
	
	/**
	 * Instantiates a new temas.
	 */
	public Temas() {
		super();
	}

	/**
	 * Gets the cod tema.
	 *
	 * @return the cod tema
	 */
	public int getCodTema() {
		return codTema;
	}

	/**
	 * Sets the cod tema.
	 *
	 * @param codTema the new cod tema
	 */
	public void setCodTema(int codTema) {
		this.codTema = codTema;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info the new info
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Gets the etiqueta.
	 *
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Sets the etiqueta.
	 *
	 * @param etiqueta the new etiqueta
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	/**
	 * Gets the decision.
	 *
	 * @return the decision
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * Sets the decision.
	 *
	 * @param decision the new decision
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}

	/**
	 * Gets the reunion.
	 *
	 * @return the reunion
	 */
	public Set<Reunion> getReunion() {
		return reunion;
	}

	/**
	 * Sets the reunion.
	 *
	 * @param reunion the new reunion
	 */
	public void setReunion(Set<Reunion> reunion) {
		this.reunion = reunion;
	}

	/**
	 * Gets the seriereunion.
	 *
	 * @return the seriereunion
	 */
	public SerieReunion getSeriereunion() {
		return seriereunion;
	}

	/**
	 * Sets the seriereunion.
	 *
	 * @param seriereunion the new seriereunion
	 */
	public void setSeriereunion(SerieReunion seriereunion) {
		this.seriereunion = seriereunion;
	}

	/**
	 * Gets the cerrado.
	 *
	 * @return the cerrado
	 */
	public int getCerrado() {
		return cerrado;
	}

	/**
	 * Sets the cerrado.
	 *
	 * @param cerrado the new cerrado
	 */
	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}

	/**
	 * Gets the seguimiento.
	 *
	 * @return the seguimiento
	 */
	public String getSeguimiento() {
		return seguimiento;
	}

	/**
	 * Sets the seguimiento.
	 *
	 * @param seguimiento the new seguimiento
	 */
	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

}
