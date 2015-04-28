/** 
 * Nombre del Archivo: Vegetal.java 
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
@Table(name = "vegetal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vegetal.findAll", query = "SELECT v FROM Vegetal v"),
    @NamedQuery(name = "Vegetal.findByIngredienteId", query = "SELECT v FROM Vegetal v WHERE v.ingredienteId = :ingredienteId"),
    @NamedQuery(name = "Vegetal.findByNombre", query = "SELECT v FROM Vegetal v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Vegetal.findByPrecioporcion", query = "SELECT v FROM Vegetal v WHERE v.precioporcion = :precioporcion"),
    @NamedQuery(name = "Vegetal.findByCantidad", query = "SELECT v FROM Vegetal v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Vegetal.findByTipo", query = "SELECT v FROM Vegetal v WHERE v.tipo = :tipo"),
    @NamedQuery(name = "Vegetal.findByVegetalId", query = "SELECT v FROM Vegetal v WHERE v.vegetalId = :vegetalId"),
    @NamedQuery(name = "Vegetal.findByCarbohidratos", query = "SELECT v FROM Vegetal v WHERE v.carbohidratos = :carbohidratos")})
public class Vegetal implements Serializable {
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
    @Column(name = "vegetal_id")
    private Integer vegetalId;
    @Column(name = "carbohidratos")
    private Integer carbohidratos;

    public Vegetal() {
    }

    public Vegetal(Integer vegetalId) {
        this.vegetalId = vegetalId;
    }

    public Vegetal(Integer vegetalId, int ingredienteId) {
        this.vegetalId = vegetalId;
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

    public Integer getVegetalId() {
        return vegetalId;
    }

    public void setVegetalId(Integer vegetalId) {
        this.vegetalId = vegetalId;
    }

    public Integer getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Integer carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vegetalId != null ? vegetalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vegetal)) {
            return false;
        }
        Vegetal other = (Vegetal) object;
        if ((this.vegetalId == null && other.vegetalId != null) || (this.vegetalId != null && !this.vegetalId.equals(other.vegetalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Vegetal[ vegetalId=" + vegetalId + " ]";
    }

} // Fin de la clase Vegetal
