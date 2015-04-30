/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Factura;
import Logica.ItemPedido;
import Logica.ItemPedidoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author android
 */
public class DaoItemPedido implements Serializable {

    public DaoItemPedido(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemPedido itemPedido) throws PreexistingEntityException, Exception {
        if (itemPedido.getItemPedidoPK() == null) {
            itemPedido.setItemPedidoPK(new ItemPedidoPK());
        }
        itemPedido.getItemPedidoPK().setFacturaId(itemPedido.getFactura().getFacturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = itemPedido.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getFacturaId());
                itemPedido.setFactura(factura);
            }
            em.persist(itemPedido);
            if (factura != null) {
                factura.getItemPedidoCollection().add(itemPedido);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItemPedido(itemPedido.getItemPedidoPK()) != null) {
                throw new PreexistingEntityException("ItemPedido " + itemPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemPedido itemPedido) throws NonexistentEntityException, Exception {
        itemPedido.getItemPedidoPK().setFacturaId(itemPedido.getFactura().getFacturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemPedido persistentItemPedido = em.find(ItemPedido.class, itemPedido.getItemPedidoPK());
            Factura facturaOld = persistentItemPedido.getFactura();
            Factura facturaNew = itemPedido.getFactura();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getFacturaId());
                itemPedido.setFactura(facturaNew);
            }
            itemPedido = em.merge(itemPedido);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getItemPedidoCollection().remove(itemPedido);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getItemPedidoCollection().add(itemPedido);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ItemPedidoPK id = itemPedido.getItemPedidoPK();
                if (findItemPedido(id) == null) {
                    throw new NonexistentEntityException("The itemPedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ItemPedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemPedido itemPedido;
            try {
                itemPedido = em.getReference(ItemPedido.class, id);
                itemPedido.getItemPedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemPedido with id " + id + " no longer exists.", enfe);
            }
            Factura factura = itemPedido.getFactura();
            if (factura != null) {
                factura.getItemPedidoCollection().remove(itemPedido);
                factura = em.merge(factura);
            }
            em.remove(itemPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemPedido> findItemPedidoEntities() {
        return findItemPedidoEntities(true, -1, -1);
    }

    public List<ItemPedido> findItemPedidoEntities(int maxResults, int firstResult) {
        return findItemPedidoEntities(false, maxResults, firstResult);
    }

    private List<ItemPedido> findItemPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemPedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ItemPedido findItemPedido(ItemPedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemPedido> rt = cq.from(ItemPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
