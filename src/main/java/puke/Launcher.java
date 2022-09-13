package puke;

import javafx.application.Application;

import java.lang.reflect.InvocationTargetException;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
            Application.launch(Main.class, args);
    }

}



