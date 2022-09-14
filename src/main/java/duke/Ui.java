package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.note.NoteList;

/**
 * Handles majority of user interaction and printing for CLI mode.
 */
public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return the command string
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints an error message to the user.
     *
     * @param e the error to print
     */
    public String showError(DukeException e) {
        return "☹ Oops. " + e.getLocalizedMessage();
    }

    /**
     * Returns a message indicating that a task has been added.
     * Takes in the full task list and returns info about the
     * newly added task, together with the number of tasks in
     * the task list.
     *
     * @param tasks the full task list
     * @return the message to print
     */
    public String printAddTaskSuccessfully(TaskList tasks) {
        String taskString = "tasks";
        if (tasks.size() == 1) {
            taskString = "task";
        }
        return "Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1).toString()
                + String.format("\nNow you have %d %s in the list.", tasks.size(), taskString);
    }

    /**
     * Returns a message indicating that a note has been added.
     * Takes in the full notes list and returns info about the
     * newly added note.
     *
     * @param notes the full note list
     * @return the message to print
     */
    public String printAddNotesSuccessfully(NoteList notes) {
        return "Got it. I've added this note:\n  " + notes.get(notes.size() - 1).toString();
    }

    /**
     * Prints a greeting to the user.
     */
    public String greet(boolean isFirstLoad, int numTasks, int numNotes) {
        String taskString = numTasks == 1 ? "task" : "tasks";
        String noteString = numNotes == 1 ? "note" : "notes";
        if (isFirstLoad) {
            return "Hello! I'm Caramel ☺\n"
                    + "Looks like I am unable to find a save file. "
                    + "I will create a new one for you.";
        } else {
            return "Hello! I'm Caramel ☺\n"
                    + String.format("I have sucessfully loaded %d %s and %d %s into your list.",
                    numTasks, taskString, numNotes, noteString);
        }
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Fancily prints a string by wrapping them around showLine
     * and left-padding them with spaces before printing.
     *
     * @param toPrint string to print
     */
    public void wrapPrint(String toPrint) {
        showLine();
        Scanner scanner = new Scanner(toPrint);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(leftPad(line));
        }
        scanner.close();
        showLine();
    }

    /**
     * @param toPrint
     * @return left-padded toPrint
     */
    private String leftPad(String toPrint) {
        return "     " + toPrint;
    }
}
