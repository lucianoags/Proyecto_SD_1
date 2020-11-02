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

/**
 *
 * @author Luciano
 */
public class Archivo {
    String filePath = "imgEdit2.pgm";
    int[][] data2D;
    int[][] editado;
    String infoArchivo;
    
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
        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();
        int maxvalue = scan.nextInt();
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
        data2D = new int[picHeight][picWidth];
        editado = new int[picHeight][picWidth];

        for (int row = 0; row < picHeight; row++) {
            for (int col = 0; col < picWidth; col++) {
                int valByte = dis.readUnsignedByte();
                data2D[row][col] = valByte;
                editado[row][col] = valByte;
     //           System.out.print(data2D[row][col]+" ");
            }
     //       System.out.println("");
        }
     //   System.out.println("");
    }

    public int[][] getData2D() {
        return data2D;
    }

    public void setData2D(int[][] data2D) {
        this.data2D = data2D;
    }

    public int[][] getEditado() {
        return editado;
    }

    public void setEditado(int[][] editado) {
        this.editado = editado;
    }

    public void crearArchivo() throws IOException
    {
        File nuevoFile = new File("archivo.pgm");
        FileWriter myWriter = new FileWriter("archivo.pgm");
        myWriter.write(infoArchivo);
        for (int i = 0; i < editado.length; i++) {
            for (int j = 0; j < editado[0].length; j++) {
                if (j+1!=editado[0].length)
                    myWriter.write(Integer.toString(editado[i][j])+" ");
                else
                    myWriter.write(Integer.toString(editado[i][j]));
                
                
            //    System.out.print(editado[j][i]+" ");
            }
            myWriter.write("\n");
           // System.out.println("");
        }        
        myWriter.close();
    }
    
    
}
