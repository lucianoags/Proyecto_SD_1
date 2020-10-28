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
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Luciano
 */
public class Archivo {
    String filePath = "minidilatacion.pgm";
    int[][] data2D;
    int[][] editado;

    public Archivo() throws FileNotFoundException, IOException {
    }
    
    
    
    public void leer() throws FileNotFoundException, IOException
    {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner scan = new Scanner(fileInputStream);
        // Discard the magic number
        scan.nextLine();
        // Discard the comment line
        scan.nextLine();
        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();
        int maxvalue = scan.nextInt();
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
                System.out.print(data2D[row][col]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
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

    
    
    
}
