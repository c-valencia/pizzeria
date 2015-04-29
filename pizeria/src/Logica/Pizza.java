/** 
 * Nombre del Archivo: Pizza.java 
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
@Table(name = "pizza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p"),
    @NamedQuery(name = "Pizza.findByPizzaId", query = "SELECT p FROM Pizza p WHERE p.pizzaId = :pizzaId"),
    @NamedQuery(name = "Pizza.findByNombre", query = "SELECT p FROM Pizza p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pizza.findByTamanio", query = "SELECT p FROM Pizza p WHERE p.tamanio = :tamanio"),
    @NamedQuery(name = "Pizza.findByPrecentacion", query = "SELECT p FROM Pizza p WHERE p.precentacion = :precentacion"),
    @NamedQuery(name = "Pizza.findByPrecio", query = "SELECT p FROM Pizza p WHERE p.precio = :precio")})
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pizza_id")
    private Integer pizzaId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tamanio")
    private Integer tamanio;
    @Column(name = "precentacion")
    private String precentacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Pizza() {
    }

    public Pizza(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }
    
    public Pizza(String nombre, int tamanio, String precentacion, double precio, byte[] foto){
        this.nombre=nombre;
        this.tamanio=tamanio;
        this.precentacion=precentacion;
        this.precio=precio;
        this.foto=foto;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public String getPrecentacion() {
        return precentacion;
    }

    public void setPrecentacion(String precentacion) {
        this.precentacion = precentacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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
        hash += (pizzaId != null ? pizzaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pizza)) {
            return false;
        }
        Pizza other = (Pizza) object;
        if ((this.pizzaId == null && other.pizzaId != null) || (this.pizzaId != null && !this.pizzaId.equals(other.pizzaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Pizza[ pizzaId=" + pizzaId + " ]";
    }

} // Fin de la clase Pizza
