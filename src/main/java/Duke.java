import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

public class Duke {

    private Ui ui;

    public Duke(){
        this.ui = new Ui(System.in, System.out);
    }

    public void interact(){
        this.ui.introduceDuke();
        this.ui.readAndRespond();
    }


    /**
     * Main method run to converse with Duke Aemon of Old.
     */
    public static void main(String[] args) {
       Duke AemonT = new Duke();
       AemonT.interact();
    }
}