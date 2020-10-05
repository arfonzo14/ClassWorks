/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accdatosactivids;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 *
 * @author arfon
 */
public class Ejer1 {

    public void direcDado(String direc) {

        File fich = new File(direc);
        

//Cod Internet a entender
        String[] directories = fich.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                
                return new File(current, name).isDirectory();
            }
        });

        File[] f = fich.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isFile();
            }
        });

        System.out.println("Directorios: ");

        System.out.println(Arrays.toString(directories));

        System.out.println("\nArchivos: ");
        for (File fs : f) {
            System.out.println(fs.getName());
        }

    }

}
