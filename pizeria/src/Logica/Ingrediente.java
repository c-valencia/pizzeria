/** 
 * Nombre del Archivo: Ingrediente.java 
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
@Table(name = "ingrediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingrediente.findAll", query = "SELECT i FROM Ingrediente i"),
    @NamedQuery(name = "Ingrediente.findByIngredienteId", query = "SELECT i FROM Ingrediente i WHERE i.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Ingrediente.findByNombre", query = "SELECT i FROM Ingrediente i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Ingrediente.findByPrecioporcion", query = "SELECT i FROM Ingrediente i WHERE i.precioporcion = :precioporcion"),
    @NamedQuery(name = "Ingrediente.findByCantidad", query = "SELECT i FROM Ingrediente i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Ingrediente.findByTipo", query = "SELECT i FROM Ingrediente i WHERE i.tipo = :tipo")})
public class Ingrediente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingrediente_id")
    private Integer ingredienteId;
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

    public Ingrediente() {
    }

    public Ingrediente(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredienteId != null ? ingredienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.ingredienteId == null && other.ingredienteId != null) || (this.ingredienteId != null && !this.ingredienteId.equals(other.ingredienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Ingrediente[ ingredienteId=" + ingredienteId + " ]";
    }

} // Fin de la clase Ingrediente
