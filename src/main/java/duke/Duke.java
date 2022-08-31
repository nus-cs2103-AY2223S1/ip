package duke;

import duke.ui.Ui;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

/**
 * Represents Duke - an interactive virtual assistant to organize tasks.
 */
public class Duke {

    private Ui ui;

    /**
     * Constructs a Duke object and creates a new Ui object for user interaction
     */
    public Duke(){
        this.ui = new Ui(System.in, System.out);
    }

    /**
     * Introduces Duke and initiates interactive conversation with user
     */
    public void interact(){
        this.ui.introduceDuke();

        this.ui.readAndRespond();
    }


    /**
     * Creates a new Duke object and begins interaction 
     */
    public static void main(String[] args) {
       Duke AemonT = new Duke();
       AemonT.interact();
    }
}