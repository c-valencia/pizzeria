/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author android
 */
@Embeddable
public class ItemPedidoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "factura_id", nullable = false)
    private int facturaId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int item;

    public ItemPedidoPK() {
    }

    public ItemPedidoPK(int facturaId, int item) {
        this.facturaId = facturaId;
        this.item = item;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) facturaId;
        hash += (int) item;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedidoPK)) {
            return false;
        }
        ItemPedidoPK other = (ItemPedidoPK) object;
        if (this.facturaId != other.facturaId) {
            return false;
        }
        if (this.item != other.item) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.ItemPedidoPK[ facturaId=" + facturaId + ", item=" + item + " ]";
    }
    
}
