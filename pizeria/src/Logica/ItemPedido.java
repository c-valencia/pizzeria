/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author android
 */
@Entity
@Table(name = "item_pedido", catalog = "pizeria", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i"),
    @NamedQuery(name = "ItemPedido.findByFacturaId", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.facturaId = :facturaId"),
    @NamedQuery(name = "ItemPedido.findByItem", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.item = :item")})
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPedidoPK itemPedidoPK;
    @JoinColumn(name = "factura_id", referencedColumnName = "factura_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public ItemPedido(int facturaId, int item) {
        this.itemPedidoPK = new ItemPedidoPK(facturaId, item);
    }

    public ItemPedidoPK getItemPedidoPK() {
        return itemPedidoPK;
    }

    public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemPedidoPK != null ? itemPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.itemPedidoPK == null && other.itemPedidoPK != null) || (this.itemPedidoPK != null && !this.itemPedidoPK.equals(other.itemPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.ItemPedido[ itemPedidoPK=" + itemPedidoPK + " ]";
    }
    
}
