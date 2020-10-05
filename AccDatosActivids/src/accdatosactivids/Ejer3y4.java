/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accdatosactivids;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author arfon
 */
public class Ejer3y4 {

    public void leeFichero(File fichero, char e) throws IOException {

        //FileReader fichIn= null;
        try (FileReader fichIn = new FileReader(fichero)) {
            int i;
            int cont = 0;
            while ((i = fichIn.read()) != -1) {
                if ((char) i == e) {
                    cont++;
                }
            }
            System.out.println(cont);
        }
    }

    public void findChar(File f) throws IOException {

        HashMap<Character, Integer> charMap = new HashMap<>();

        char ch = ' ';
        try (FileReader fichIn = new FileReader(f)) {
            int i;

            while ((i = fichIn.read()) != -1) {
                ch = (char) i;
                if (charMap.containsKey(ch)) {

                    charMap.put(ch, charMap.get(ch) + 1);

                } else {
                    charMap.put(ch, 1);
                }

            }

            int maxVal = (Collections.max(charMap.values()));

//            for (HashMap.Entry<Character, Integer> entry : charMap.entrySet()) {
//
//                if (entry.getValue() == maxVal) {
//                    System.out.println(entry.getKey());
//                }
//            }
            int max = 0;
            char c = ' ';
            for (char cha : charMap.keySet()) {
                // System.out.println(cha+" "+charMap.get(cha));
                if (charMap.get(cha) > max) {
                    max = charMap.get(cha);
                    c = cha;

                }
               

            }
             System.out.println(c +": "+max);

        }
    }
}
