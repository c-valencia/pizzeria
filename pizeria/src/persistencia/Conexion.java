/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author invitado
 */
public class Conexion {
    private EntityManagerFactory conn;
    
    public Conexion () {
        conn = Persistence.createEntityManagerFactory("pizeriaPU");
    }
    
    public EntityManagerFactory getConn() {
        return conn;
    }
}
