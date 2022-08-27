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
    
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for UI.
     */
    UI() {
        this.in = new Scanner(System.in);
        this.out = System.out;
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
        String rawInput = in.nextLine();
        
        while (shouldIgnore(rawInput)) {
            rawInput = in.nextLine();
        }
        
        return rawInput;
    }

    /**
     * Shows the given messages to user in proper format.
     * 
     * @param message - The messages to be shown.
     */
    public void showToUser(String... message) {
        out.println(DIVIDER);
        for (String m : message) {
            out.println("\t" + m.replace("\n", LS + "\t"));
        }
        out.println(DIVIDER);
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
