/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accdatosactivids;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arfon
 */
public class AccDatosActivids {

    public static void listaFich(File fDirec) {

        String[] direc = fDirec.list();
        File[] listF = fDirec.listFiles();
        ArrayList<String> direccion = new ArrayList<String>();

        System.out.println("Archivos: ");
        for (File f : listF) {
            if (f.isFile()) {
                System.out.println(f.getName());
            } else if (f.isDirectory()) {
                direccion.add(f.getName());
            }
        }
        System.out.println("\nDirecciones: ");
        for (int i = 0; i < direccion.size(); i++) {
            System.out.println(direccion.get(i));
        }

    }

    public void getF(File dir) {
        File[] dirs = dir.listFiles();
        for (File f : dirs) {
            if (f.isDirectory()) {
                System.out.println(f.getName());
            }
        }
    }

    public void getData(File dir, boolean soloDir) {
        File[] dirs = dir.listFiles();
        for (File f : dirs) {
            //el cambio lo hago al llamar al getFs o al getDir
            if ((f.isDirectory() && soloDir)
                    || (f.isFile() && !soloDir)) {
                System.out.println(f.getAbsolutePath());
            }

        }
    }
    //para ficheros

    public void getFs(File dir) {
        getData(dir, true);
    }
    //para directorios

    public void getDir(File dir) {
        getData(dir, false);
    }

    public static void pruebaBuffer(File f, int n, int l) throws IOException {

        char[] buffer = new char[n];
        // FileWriter fw;
        try (FileReader fr = new FileReader(f)) {
            int i;
            while ((i = fr.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, i));
            }
        }

    }

    //Función recursiva!!!
    public static ArrayList<File> ejer2(File f, ArrayList<File> files) {

        File[] fList = f.listFiles();
        if (fList != null) {
            for (File archi : fList) {
                if (archi.isFile()) {
                    files.add(archi);
                } else if (archi.isDirectory()) {

                    ejer2(archi, files);
                }
            }
        }
        return files;
    }

    public static void ejer5(File f, String cad) throws IOException {

        Scanner sc = null;
        int cont = 0;
        try {
            sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                cont++;
                if (line.contains(cad)) {
                    System.out.println(cont + ": " + line);
                }
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void ejer6Div(File f, int n, int line) throws IOException {
        FileWriter fichOut = null;
        FileReader fr = null;
        Scanner sc = null;
        String cadAFich = "";
        String fullFich = "";
        File fnew;

        int cont = 1;
        int lineas = line;
        char[] buffer;

        try {
            fr = new FileReader(f);
            int i;
            buffer = new char[n];
            while ((i = fr.read(buffer)) != -1) {

                cadAFich = new String(buffer, 0, i);
                fnew = new File(f.getParent() + "\\arEj6-" + cont + ".txt");
                fichOut = new FileWriter(fnew);
                fichOut.write(cadAFich);

                System.out.println(cadAFich);
                cont++;
                fichOut.close();
            }
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (fichOut != null) {
                fichOut.close();
            }
        }

        System.out.println("Cadena Fich: " + cadAFich);
        System.out.println(fullFich);
        System.out.println(f.getParent());
    }

    public static void ejer7(String nomArch, String oper) {

        switch (oper) {
            case "n":

        }

    }
    public static void ejer8(){
        
    }
    
    

    public static void main(String[] args) throws IOException {

        char i = 'E';

        Ejer3y4 ejer3 = new Ejer3y4();

        ArrayList<File> fs = new ArrayList<File>();
        //arch.direcDado("C:/Users/arfon/Desktop/archivosPrueba");
        File fCasa = new File("D:/DocusPrueba/DocuUno.txt");

        File f = new File("C:/Users/arfon/Desktop/archivosPrueba");
        File f2 = new File("C:/Users/arfon/Desktop/archivosPrueba/Ar1.txt");
        //listaFich(f);
        fs = ejer2(f, fs);
        System.out.println("Ejercicio 2: ");

        for (File direc : fs) {
            System.out.println(direc);
        }

        System.out.println("\nEjercicio 3:");

//        try {
//            ejer3.leeFichero(f2, i);
//            System.out.println(" ");
//            System.out.println("Ejer 4: ");
//            ejer3.findChar(f2);
//        } catch (IOException ex) {
//
//        }
        System.out.println("\nEjercicio 6: ");
        ejer6Div(fCasa, 4, 2);

        System.out.println("\nEjercicio 7: ");

    }

}
