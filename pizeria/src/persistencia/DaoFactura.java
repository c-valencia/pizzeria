/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Logica.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.ItemPedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author android
 */
public class DaoFactura implements Serializable {

    public DaoFactura(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getItemPedidoCollection() == null) {
            factura.setItemPedidoCollection(new ArrayList<ItemPedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ItemPedido> attachedItemPedidoCollection = new ArrayList<ItemPedido>();
            for (ItemPedido itemPedidoCollectionItemPedidoToAttach : factura.getItemPedidoCollection()) {
                itemPedidoCollectionItemPedidoToAttach = em.getReference(itemPedidoCollectionItemPedidoToAttach.getClass(), itemPedidoCollectionItemPedidoToAttach.getItemPedidoPK());
                attachedItemPedidoCollection.add(itemPedidoCollectionItemPedidoToAttach);
            }
            factura.setItemPedidoCollection(attachedItemPedidoCollection);
            em.persist(factura);
            for (ItemPedido itemPedidoCollectionItemPedido : factura.getItemPedidoCollection()) {
                Factura oldFacturaOfItemPedidoCollectionItemPedido = itemPedidoCollectionItemPedido.getFactura();
                itemPedidoCollectionItemPedido.setFactura(factura);
                itemPedidoCollectionItemPedido = em.merge(itemPedidoCollectionItemPedido);
                if (oldFacturaOfItemPedidoCollectionItemPedido != null) {
                    oldFacturaOfItemPedidoCollectionItemPedido.getItemPedidoCollection().remove(itemPedidoCollectionItemPedido);
                    oldFacturaOfItemPedidoCollectionItemPedido = em.merge(oldFacturaOfItemPedidoCollectionItemPedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactura(factura.getFacturaId()) != null) {
                throw new PreexistingEntityException("Factura " + factura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getFacturaId());
            Collection<ItemPedido> itemPedidoCollectionOld = persistentFactura.getItemPedidoCollection();
            Collection<ItemPedido> itemPedidoCollectionNew = factura.getItemPedidoCollection();
            List<String> illegalOrphanMessages = null;
            for (ItemPedido itemPedidoCollectionOldItemPedido : itemPedidoCollectionOld) {
                if (!itemPedidoCollectionNew.contains(itemPedidoCollectionOldItemPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItemPedido " + itemPedidoCollectionOldItemPedido + " since its factura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ItemPedido> attachedItemPedidoCollectionNew = new ArrayList<ItemPedido>();
            for (ItemPedido itemPedidoCollectionNewItemPedidoToAttach : itemPedidoCollectionNew) {
                itemPedidoCollectionNewItemPedidoToAttach = em.getReference(itemPedidoCollectionNewItemPedidoToAttach.getClass(), itemPedidoCollectionNewItemPedidoToAttach.getItemPedidoPK());
                attachedItemPedidoCollectionNew.add(itemPedidoCollectionNewItemPedidoToAttach);
            }
            itemPedidoCollectionNew = attachedItemPedidoCollectionNew;
            factura.setItemPedidoCollection(itemPedidoCollectionNew);
            factura = em.merge(factura);
            for (ItemPedido itemPedidoCollectionNewItemPedido : itemPedidoCollectionNew) {
                if (!itemPedidoCollectionOld.contains(itemPedidoCollectionNewItemPedido)) {
                    Factura oldFacturaOfItemPedidoCollectionNewItemPedido = itemPedidoCollectionNewItemPedido.getFactura();
                    itemPedidoCollectionNewItemPedido.setFactura(factura);
                    itemPedidoCollectionNewItemPedido = em.merge(itemPedidoCollectionNewItemPedido);
                    if (oldFacturaOfItemPedidoCollectionNewItemPedido != null && !oldFacturaOfItemPedidoCollectionNewItemPedido.equals(factura)) {
                        oldFacturaOfItemPedidoCollectionNewItemPedido.getItemPedidoCollection().remove(itemPedidoCollectionNewItemPedido);
                        oldFacturaOfItemPedidoCollectionNewItemPedido = em.merge(oldFacturaOfItemPedidoCollectionNewItemPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getFacturaId();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ItemPedido> itemPedidoCollectionOrphanCheck = factura.getItemPedidoCollection();
            for (ItemPedido itemPedidoCollectionOrphanCheckItemPedido : itemPedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the ItemPedido " + itemPedidoCollectionOrphanCheckItemPedido + " in its itemPedidoCollection field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
