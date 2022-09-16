package mia;

import general.ui.ChatWindow;
import general.ui.Span;

/**
 * An instance of {@code Mia}, a virtual assistant.
 */
public class Mia {
    private static final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
                                     + "┃ You are talking to MIA... ┃\n"
                                     + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
    private final TaskManager tasksManager;
    private final ChatWindow window = new ChatWindow(50);
    private boolean flagShouldExitContext = false;

    public Mia(String dataPath) {
        tasksManager = new TaskManager(dataPath);
    }

    /**
     * Sends a message to the user.
     *
     * @param message the message to be sent
     */
    public void respond(String message) {
        window.printResponse(new Span(message));
    }

    /**
     * Gets the {@code TaskManager} task list manager for this {@code Mia} instance.
     *
     * @return The {@code TaskManager} associated with this {@code Mia} instance.
     */
    public TaskManager getTasks() {
        return tasksManager;
    }

    /**
     * Starts this {@code Mia} instance.
     */
    public void run() {
        System.out.println(logo);

        respond("Hello there!");

        while (true) {
            final String line = window.prompt("Enter a command: ");

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            window.printCommand(new Span(line));

            parseAndExecute(line);
            if (flagShouldExitContext) {
                break;
            }
        }
        window.dispose();
    }

    public void parseAndExecute(String line) {
        try {
            final Command command = Command.from(this, line);
            command.run();
            flagShouldExitContext = command.shouldExitContext();
        } catch (IllegalArgumentException e) {
            respond(e.getMessage());
        }
    }

}
