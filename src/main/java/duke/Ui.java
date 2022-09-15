package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The Ui class interacts with the user and is responsible for the formatting of the printed texts. The Ui class also 
 * greets the user and asks for inputs until terminated.
 */
public class Ui {
    
    /**
     * Constant for the spacing to be printed out for line separation. 
     */
    private static String SPACING = "-----------------------------------------";
    
    /**
     * BufferedReader to read in inputs from the user.
     */
    private BufferedReader br;

    /**
     * Constructor of Ui class.
     */
    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Greets the user when the program is started.
     */
    public void greet() {
        System.out.println(SPACING);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(SPACING + "\n");
    }

    /**
     * Prints a spacing before Duke gives an output.
     */
    public void printFrontSpacing() {
        System.out.println("\n  " + SPACING);
    }

    /**
     * Prints a spacing after Duke gives an output.
     */
    public void printBackSpacing() {
        System.out.println("  " + SPACING + "\n");
    }

    /**
     * Says bye to the user when the program is terminated.
     */
    public void sayBye() {
        System.out.println("    Goobye, see you again!\n");
    } 

    /**
     * Trigger the BufferedReader to ask for inputs from the user.
     * @return Returns a the user input in the form of a String literal.
     * @throws Exception
     */
    public String getInput() throws Exception {
        return br.readLine();
    }
}
