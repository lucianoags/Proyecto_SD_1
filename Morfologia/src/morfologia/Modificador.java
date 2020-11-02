/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luciano
 */
public class Modificador extends Thread{
    private static  Archivo archivo;
    private static  int fila;
    private static  int col;
    private static  Figura figura;

    public Modificador(final Archivo archivo, final int fila, final int col, final Figura figura) {
        this.archivo = archivo;
        this.fila = fila;
        this.col = col;
        this.figura = figura;
    }
    
    @Override
    public void run()
    {
        int tamaño = archivo.getData2D().length;
        for (int i = 0; i < tamaño; i++) {
          //  try {
                this.fila=i;
                figura.modificar(fila, col);
               // archivo.editado=archivo.editado;
              //  System.out.print(" ");
          //  } catch (Exception ex) {
         //      Logger.getLogger(Modificador.class.getName()).log(Level.SEVERE, null, ex);
          //  }
                
        }
    }

    
    
    
    
}
