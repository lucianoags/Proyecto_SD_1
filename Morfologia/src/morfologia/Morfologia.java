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
        int columnas = lector.data2D[0].length;
        Thread hilo;
        Figura figura = new Figura(0, lector, true);
        ArrayList<Thread> thread = new ArrayList<>();
        for (int i = 0; i < columnas; i++) {
            hilo = new Modificador(lector, 0, i, figura);
            hilo.start();
            thread.add(hilo);            
        }
        for (int i = 0; i < columnas; i++) {
            thread.get(i).join();
        }
        sleep(1000);
        for (int row = 0; row < lector.getEditado().length; row++) {
            for (int col = 0; col < lector.getEditado()[0].length; col++) {
                System.out.print(lector.getEditado()[row][col]+" ");
            }
            System.out.println("");
        }  
        
    }
    
}
