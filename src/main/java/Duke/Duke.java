package Duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * Main class that calls the parsing of the user input.
 */
public class Duke {
    public static void main(String[] args) {
        Parser p = new Parser();
        p.startParse();
    }
}
