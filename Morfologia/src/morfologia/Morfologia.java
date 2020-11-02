/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologia;

import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Luciano
 */
public class Morfologia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        Archivo lector = new Archivo();
        lector.leer();
        boolean compare = true;
        int columnas = lector.data2D[0].length;
        Thread hilo;
        Figura figura = new Figura(0, lector, true);
        ArrayList<Thread> thread = new ArrayList<>();
        
        
        
        long startTimeLineal = System.nanoTime();     
        for (int i = 0; i < columnas; i++) {
            Modificador modificador = new Modificador(lector, 0, i, figura);
            modificador.run();
        //    System.out.println("nueva col");
        }
        long endTimeLineal = System.nanoTime();
        
        lector.leer();
        
        long startTimeThreads = System.nanoTime();
        for (int i = 0; i < columnas; i++) {
            hilo = new Modificador(lector, 0, i, figura);
            hilo.start();
            thread.add(hilo);            
        }
        for (int i = 0; i < columnas; i++) {
            thread.get(i).join();
        }
        long endTimeThreads = System.nanoTime();
      
        
        
        
        for (int row = 0; row < lector.getEditado().length; row++) {
            for (int col = 0; col < lector.getEditado()[0].length; col++) {
                System.out.print(lector.getEditado()[row][col]+" ");
            }
            System.out.println("");
        }
        
        System.out.println("Tiempo hilo: "+((endTimeThreads-startTimeThreads)/1000000)+" Tiempo lineal: "+((endTimeLineal- startTimeLineal)/1000000));
        lector.crearArchivo();
    }
    
    
}
