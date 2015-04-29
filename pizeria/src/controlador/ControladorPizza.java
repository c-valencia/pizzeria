/** 
 * Nombre del Archivo: ControladorPizza.java 
 * Fecha de Creacion: 28/04/2015 
 * Autores: 	JULIAN GARCIA RICO (1225435)
		DIEGO FERNANDO BEDOYA (1327749)
		CRISTIAN ALEXANDER VALENCIA TORRES (1329454)
		OSCAR STEVEN ROMERO BERON (1326750) 
 */

package controlador;

import persistencia.Conexion;
import persistencia.DaoPizza;
import java.util.logging.Level;
import java.util.logging.Logger;
import Logica.Pizza;


public class ControladorPizza {

    private Conexion conn;
    private DaoPizza daoPizza;
    
    public ControladorPizza(){
        conn = new Conexion();
        daoPizza = new DaoPizza(conn.getConn());
    }
    
    public boolean ingresarPizza(String nombre, int tamanio, String presentacion, double precio, byte[] foto){
       boolean resultado = false;
       Pizza newPizza = new Pizza(nombre, tamanio, presentacion, precio, foto);
       try{
           daoPizza.create(newPizza);
           resultado=true;
       } catch (Exception ex) {
            Logger.getLogger(ControladorPizza.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return resultado;
    }
    
    
} 
