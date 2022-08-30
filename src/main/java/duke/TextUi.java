package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The user interface of the chatbot
 */
public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }


    /**
     * Outputs a string using the chosen output stream
     * @param s string to output
     */
    public void display(String s) {
        out.println(s);
    }

    /**
     * Obtains user input form input stream
     * @return a line of user input
     */
    public String getInput() {
        return in.nextLine();
    }



}
