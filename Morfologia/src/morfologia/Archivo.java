/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologia;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author RubenR
 */
public class Archivo {
    static volatile String filePath = "imgEdit2.pgm";
    static volatile AtomicInteger[][] data2D;
    static volatile AtomicInteger[][] editado;
    static volatile String infoArchivo;
    static volatile AtomicInteger valByte;
    static volatile AtomicInteger picWidth;
    static volatile AtomicInteger picHeight;
    static volatile int maxvalue;
    static volatile AtomicInteger row;
    static volatile AtomicInteger col;
    
    public Archivo() throws FileNotFoundException, IOException {
    }
        
    
    public void leer() throws FileNotFoundException, IOException
    {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner scan = new Scanner(fileInputStream);
        // Discard the magic number
        scan.nextLine();
        infoArchivo="P2\n# Proyecto U1 SD\n";
        // Discard the comment line
        scan.nextLine();
        picWidth = new AtomicInteger(scan.nextInt());
        picHeight = new AtomicInteger(scan.nextInt());
        maxvalue = scan.nextInt();
        infoArchivo+=(picWidth+" "+picHeight+"\n"+maxvalue+"\n");
        fileInputStream.close();
        
        fileInputStream = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fileInputStream);
        
        int numnewlines = 4;
        while (numnewlines > 0) {
            char c;
            do {
                c = (char)(dis.readUnsignedByte());
            } while (c != '\n');
            numnewlines--;
        }
        data2D = new AtomicInteger[picHeight.get()][picWidth.get()];
        editado = new AtomicInteger[picHeight.get()][picWidth.get()];
        
        
        
        for (row= new AtomicInteger(0); row.get() < picHeight.get(); row.set(row.get()+1)) {
            for (col = new AtomicInteger(0); col.intValue() < picWidth.get(); col.set(col.intValue()+1)) {
                valByte = new AtomicInteger(dis.readUnsignedByte());
                data2D[row.get()][col.get()] = valByte;
                editado[row.get()][col.get()] = valByte;
     //           System.out.print(data2D[row][col]+" ");
            }
     //       System.out.println("");
        }
     //   System.out.println("");
    }

    public AtomicInteger[][] getData2D() {
        return data2D;
    }

    public void setData2D(AtomicInteger[][] data2D) {
        this.data2D = data2D;
    }

    public AtomicInteger[][] getEditado() {
        return editado;
    }

    public void setEditado(AtomicInteger[][] editado) {
        this.editado = editado;
    }

    public void crearArchivo() throws IOException
    {
        new File("archivo.pgm");
        FileWriter myWriter = new FileWriter("archivo.pgm");
        myWriter.write(infoArchivo);
        for (int i = 0; i < editado.length; i++) {
            for (int j = 0; j < editado[0].length; j++) {
                if (j+1!=editado[0].length)
                    myWriter.write(editado[i][j].toString()+" ");
                else
                    myWriter.write(editado[i][j].toString());
                
                
            //    System.out.print(editado[j][i]+" ");
            }
            myWriter.write("\n");
           // System.out.println("");
        }        
        myWriter.close();
    }
    
    
}
