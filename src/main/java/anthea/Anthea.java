package anthea;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import anthea.note.NoteList;
import anthea.task.TaskList;

/**
 * The main method of the chatbot, as well as its startup and teardown.
 */
public class Anthea {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;
    private static UiInterface ui = new ConsoleUi();

    /**
     * Sets the current UI.
     *
     * @param ui The current UI to use.
     */
    public static void setUi(UiInterface ui) {
        assert ui != null;
        Anthea.ui = ui;
    }

    /**
     * Gets the current UI to interact with.
     *
     * @return UiInterface that helps display text to screen.
     */
    public static UiInterface getUi() {
        assert ui != null;
        return ui;
    }

    private static ChatbotResponse handleCommand(String command) {
        Optional<ChatbotResponse> response = Optional.empty();
        for (CommandMatcher matcher : commands) {
            response = response.or(() -> matcher.run(command));
        }
        return response.orElse(new ChatbotResponse("I cannot figure out this command..."));
    }

    /**
     * Runs the chatbot execution.
     *
     * @param args Command line args which are not used.
     */
    public static void main(String[] args) {
        // initialization
        ui.greet();
        TaskList.initializeTaskList();
        NoteList.initializeNoteList();
        commands = Parser.getCommands();
        BufferedReader input = new BufferedReader(ui.getReader());

        // main application logic
        boolean isStillRunning = true;
        while (isStillRunning) {
            String command;
            try {
                command = input.readLine();
            } catch (IOException ex) {
                System.out.println("IOException in application logic - terminating");
                throw new RuntimeException(ex);
            }
            if (command.equals("bye")) {
                isStillRunning = false;
            } else {
                ChatbotResponse response = handleCommand(command);
                response.print(ui); // ensure response is printed
            }
        }

        // finalization
        TaskList.finalizeTaskList();
        NoteList.finalizeNoteList();
        ui.leave();
    }
}
