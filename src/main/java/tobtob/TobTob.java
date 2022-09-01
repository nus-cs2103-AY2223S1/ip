package tobtob;

import belly.Belly;
import brain.Brain;
import executor.Executor;
import processor.Parser;

/**
 * Represents a manager that manages the whole process (main logic) of the chatbot
 */
public class TobTob {
    private final static String TOB_TOB_GREETING = "Tob Tob! Who's there?\nWhat do you want Tob Tob to do for you today?";

    private Belly belly;
    private Brain brain;
    private Parser parser;
    private Executor executor;

    public TobTob() {
        belly = new Belly();
        try {
            brain = new Brain(belly.puke());
        } catch (TobTobException e) {
            brain = new Brain();
        }
        executor = new Executor(brain, belly);
        parser = new Parser(executor);
    }

    public static String getGreeting() {
        return TOB_TOB_GREETING;
    }

    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (TobTobException e) {
            return e.getMessage();
        }
    }

    public boolean isByeCommand(String input) {
        if (input.toLowerCase().equals("bye")) {
            return true;
        }
        return false;
    }
}
