package tobtob;

import belly.Belly;
import brain.Brain;
import executor.Executor;
import processor.Parser;

/**
 * Represents a manager that manages the whole process (main logic) of the chatbot
 */
public class TobTob {
    private static final String TOB_TOB_GREETING = "Tob Tob! Who's there?\n"
        + "What do you want Tob Tob to do for you today?";

    private Belly belly;
    private Brain brain;
    private Parser parser;
    private Executor executor;
    private String initializationErrorMessage = "";

    /**
     * TobTob constructor.
     */
    public TobTob() {
        belly = new Belly();
        try {
            brain = new Brain(belly.puke());
        } catch (TobTobException e) {
            initializationErrorMessage = e.getMessage();
            brain = new Brain();
        }
        executor = new Executor(brain, belly);
        parser = new Parser(executor);
    }

    /**
     * Returns {@code TOB_TOB_GREETING}
     *
     * @return {@link String}
     */
    public static String getGreeting() {
        return TOB_TOB_GREETING;
    }

    /**
     * Returns the output to be shown to user
     *
     * @param input {@link String} input from user
     * @return {@link String} output
     */
    public String getResponse(String input) {
        if (initializationErrorMessage.startsWith("Oopsieee! Error loading file in")) {
            return initializationErrorMessage;
        }

        try {
            return parser.parse(input);
        } catch (TobTobException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns whether {@code input} is a bye command
     *
     * @param input {@link String} input from user
     * @return {@link boolean}
     */
    public boolean isByeCommand(String input) {
        if (input.toLowerCase().equals("bye")) {
            return true;
        }
        return false;
    }
}
