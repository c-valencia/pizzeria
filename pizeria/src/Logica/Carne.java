/** 
 * Nombre del Archivo: Carne.java 
 * Fecha de Creacion: 28/04/2015 
 * Autores: 	JULIAN GARCIA RICO (1225435)
		DIEGO FERNANDO BEDOYA (1327749)
		CRISTIAN ALEXANDER VALENCIA TORRES (1329454)
		OSCAR STEVEN ROMERO BERON (1326750) 
 */

package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "carne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carne.findAll", query = "SELECT c FROM Carne c"),
    @NamedQuery(name = "Carne.findByIngredienteId", query = "SELECT c FROM Carne c WHERE c.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Carne.findByNombre", query = "SELECT c FROM Carne c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Carne.findByPrecioporcion", query = "SELECT c FROM Carne c WHERE c.precioporcion = :precioporcion"),
    @NamedQuery(name = "Carne.findByCantidad", query = "SELECT c FROM Carne c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Carne.findByTipo", query = "SELECT c FROM Carne c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Carne.findByCarneId", query = "SELECT c FROM Carne c WHERE c.carneId = :carneId"),
    @NamedQuery(name = "Carne.findByPrecentacion", query = "SELECT c FROM Carne c WHERE c.precentacion = :precentacion"),
    @NamedQuery(name = "Carne.findByCantidadgrasas", query = "SELECT c FROM Carne c WHERE c.cantidadgrasas = :cantidadgrasas"),
    @NamedQuery(name = "Carne.findByAnimal", query = "SELECT c FROM Carne c WHERE c.animal = :animal")})
public class Carne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ingrediente_id")
    private int ingredienteId;
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioporcion")
    private Double precioporcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "tipo")
    private String tipo;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carne_id")
    private Integer carneId;
    @Column(name = "precentacion")
    private String precentacion;
    @Column(name = "cantidadgrasas")
    private Integer cantidadgrasas;
    @Column(name = "animal")
    private String animal;

    public Carne() {
    }

    public Carne(Integer carneId) {
        this.carneId = carneId;
    }

    public Carne(Integer carneId, int ingredienteId) {
        this.carneId = carneId;
        this.ingredienteId = ingredienteId;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioporcion() {
        return precioporcion;
    }

    public void setPrecioporcion(Double precioporcion) {
        this.precioporcion = precioporcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getCarneId() {
        return carneId;
    }

    public void setCarneId(Integer carneId) {
        this.carneId = carneId;
    }

    public String getPrecentacion() {
        return precentacion;
    }

    public void setPrecentacion(String precentacion) {
        this.precentacion = precentacion;
    }

    public Integer getCantidadgrasas() {
        return cantidadgrasas;
    }

    public void setCantidadgrasas(Integer cantidadgrasas) {
        this.cantidadgrasas = cantidadgrasas;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carneId != null ? carneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carne)) {
            return false;
        }
        Carne other = (Carne) object;
        if ((this.carneId == null && other.carneId != null) || (this.carneId != null && !this.carneId.equals(other.carneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Carne[ carneId=" + carneId + " ]";
    }

} // Fin de la clase Carne
