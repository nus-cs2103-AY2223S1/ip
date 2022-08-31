package org.Olivia;

import javafx.application.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
//        try {
//            Runtime r = Runtime.getRuntime();
//            Process p = r.exec("ls && cd images && ls   ");
//            p.waitFor();
//            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = "";
//
//            while ((line = b.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            b.close();
//        }
//        catch (Throwable e){
//            e.printStackTrace();
//        }
        Application.launch(Olivia.class, args);

    }
}
