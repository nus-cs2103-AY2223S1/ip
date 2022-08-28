package duke;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a UI class that is responsible for user interactions.
 * 
 * @author Ramanathan Kumarappan
 */
public class UI {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "____________________________________________________________";
    
    /**
     * Constructor for UI.
     */
    private final Scanner IN;
    private final PrintStream OUT;
    
    UI() {
        this.IN = new Scanner(System.in);
        this.OUT = System.out;
    }
    
    private boolean shouldIgnore(String rawInput) {
        return rawInput.trim().isEmpty();
    }

    /**
     * Gets the user input.
     * 
     * @return - The user input in String form.
     */
    public String getUserCommand() {
        String rawInput = this.IN.nextLine();
        
        while (shouldIgnore(rawInput)) {
            rawInput = this.IN.nextLine();
        }
        
        return rawInput;
    }

    /**
     * Shows the given messages to user in proper format.
     * 
     * @param message - The messages to be shown.
     */
    public void showToUser(String... message) {
        this.OUT.println(DIVIDER);
        for (String m : message) {
            this.OUT.println("\t" + m.replace("\n", LS + "\t"));
        }
        this.OUT.println(DIVIDER);
    }

    /**
     * Shows the intro message to user upon staring up Duke.
     */
    public void showIntroMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello I am duke";
        
        showToUser(logo, intro);
    }

    /**
     * Shows the exit message to user upon exiting Duke.
     */
    public void showOutroMsg() {
        String outro = "See ya";
        showToUser(outro);
    }
    
}
