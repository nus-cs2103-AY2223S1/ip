package org.Olivia;

import javafx.application.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Olivia.class, args);
    }
}
