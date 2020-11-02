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
    private final ReentrantLock lock = new ReentrantLock(true);
    
    

    public Figura(int forma, Archivo archivo, boolean erosion) {
        this.forma = forma;
        this.archivo = archivo;
        this.erosion = erosion;
        this.original = archivo.getData2D();
        this.editado = archivo.getEditado();
                
    }
    
    public void modificar(int fila, int columna) 
    {
        lock.lock();
        try {
            switch(forma)
            {
                case 0:             //cruzeta original
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(fila+1<original.length)
                    {
                        verificarMax(original[fila+1][columna]);    //abajo
                        verificarMin(original[fila+1][columna]);
                    }
                    if(fila-1>=0)
                    {
                        verificarMax(original[fila-1][columna]);    //arriba
                        verificarMin(original[fila-1][columna]);
                    }
                    if(columna+1<original[0].length)
                    {
                        verificarMax(original[fila][columna+1]);    //derecha
                        verificarMin(original[fila][columna+1]);
                    }
                    if(columna-1>=0)
                    {
                        verificarMax(original[fila][columna-1]);    //izquierda
                        verificarMin(original[fila][columna-1]);
                    }                
                //    System.out.println(original[fila][columna]+" "+this.min+" "+this.max);
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else    //Dilatación
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                    
                case 1:             //Figura 1 del problema 2
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(columna+1<original[0].length)
                    {
                        verificarMax(original[fila][columna+1]);    //derecha
                        verificarMin(original[fila][columna+1]);
                    }
                    if(fila+1<original.length)
                    {
                        verificarMax(original[fila+1][columna]);    //abajo
                        verificarMin(original[fila+1][columna]);
                    }                    
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                    
                case 2:             //Figura 2 del problema 2
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(fila-1>=0)
                    {
                        verificarMax(original[fila-1][columna]);    //arriba
                        verificarMin(original[fila-1][columna]);
                    }
                    if(columna+1<original[0].length)
                    {
                        verificarMax(original[fila][columna+1]);    //derecha
                        verificarMin(original[fila][columna+1]);
                    }
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                    
                case 3:             //Figura 3 del problema 2
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(fila-1>=0)
                    {
                        verificarMax(original[fila-1][columna]);    //arriba
                        verificarMin(original[fila-1][columna]);
                    }
                    if(fila+1<original.length)
                    {
                        verificarMax(original[fila+1][columna]);    //abajo
                        verificarMin(original[fila+1][columna]);
                    }     
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                    
                case 4:             //Figura 4 del problema 2
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(columna+1<original[0].length)
                    {
                        verificarMax(original[fila][columna+1]);    //derecha
                        verificarMin(original[fila][columna+1]);
                    }
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
                    
                case 5:            //Figura 5 del problema 2
                    this.min=(original[fila][columna]);
                    this.max=(original[fila][columna]);
                    if(fila+1<original.length && columna+1<original[0].length)
                    {
                        verificarMax(original[fila+1][columna+1]);    //abajo-derecha
                        verificarMin(original[fila+1][columna+1]);
                    }
                    if(fila-1>=0&&columna-1>=0)
                    {
                        verificarMax(original[fila-1][columna-1]);    //arriba-izquierda
                        verificarMin(original[fila-1][columna-1]);
                    }
                    if(columna+1<original[0].length && fila-1>=0)
                    {
                        verificarMax(original[fila-1][columna+1]);    //derecha-arriba
                        verificarMin(original[fila-1][columna+1]);
                    }
                    if(columna-1>=0 && fila+1<original.length)
                    {
                        verificarMax(original[fila+1][columna-1]);    //izquierda-abajo
                        verificarMin(original[fila+1][columna-1]);
                    }   
                    if (erosion) {
                        this.archivo.editado[fila][columna]=this.min;
                    }
                    else
                        this.archivo.editado[fila][columna]=this.max;
                    break;
            }
        } finally  {
            lock.unlock();
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