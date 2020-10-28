/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologia;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Luciano
 */
public class Figura {
    
    int forma;
    boolean erosion;
    Archivo archivo;
    int[][] original;
    int[][] editado;
    int min;
    int max;

    
    

    public Figura(int forma, Archivo archivo, boolean erosion) {
        this.forma = forma;
        this.archivo = archivo;
        this.erosion = erosion;
        this.original = archivo.getData2D();
        this.editado = archivo.getEditado();
                
    }
    
    public void modificar(int fila, int columna) 
    {
            switch(forma)
            {
                case 0:
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(fila+1<original.length)
                    {
                        verificarMax(original[fila+1][columna]);    //abajo
                        verificarMin(original[fila+1][columna]);
                    }
                    if(fila-1>=0)
                    {
                        verificarMax(original[fila-1][columna]);
                        verificarMin(original[fila-1][columna]);
                    }
                    if(columna+1<original[0].length)
                    {
                        verificarMax(original[fila][columna+1]);    //derecha
                        verificarMin(original[fila][columna+1]);
                    }
                    if(columna-1>=0)
                    {
                        verificarMax(original[fila][columna-1]);
                        verificarMin(original[fila][columna-1]);
                    }                
                //    System.out.println(original[fila][columna]+" "+this.min+" "+this.max);
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                case 1:
                    //figura 1
                    break;
                case 2:
                    //figura 2
                    break;
                case 3:
                    //figura 3
                    break;
                case 4:
                    //figura 4
                    break;
                case 5:
                    //figura 5
                    break;
            }
        
    }
    
    public void verificarMin(int i)
    {
        if(i<this.min)
            this.min=i;
    }
    
    public void verificarMax(int i)
    {
        if(i>this.max)
            this.max=i;
    }
}