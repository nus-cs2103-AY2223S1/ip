package mia;

import general.ui.ChatWindow;
import general.ui.Span;
import mia.tasks.TaskManager;

public class Mia {
    private static final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                                       "┃ You are talking to MIA... ┃\n" +
                                       "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
    private final TaskManager tasksManager;
    private final ChatWindow window = new ChatWindow(50);

    public Mia(String dataPath) {
        tasksManager = new TaskManager(dataPath);
    }

    public void respond(String message) {
        window.printResponse(new Span(message));
    }

    public TaskManager getTasks() {
        return tasksManager;
    }

    public void run() {
        System.out.println(logo);

        respond("Hello there!");

        while (true) {
            final String line = window.prompt("Enter a command: ");

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            window.printCommand(new Span(line));

            try {
                final Command command = Command.from(this, line);
                command.run();
                if (command.shouldExitContext()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                respond(e.getMessage());
            }
        }
        window.dispose();
    }

}
