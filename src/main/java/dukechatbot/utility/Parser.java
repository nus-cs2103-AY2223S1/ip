package dukechatbot.utility;
import dukechatbot.dukeexception.DukeException;

/**
 *  The Parser class implements the class that will parse input commands passed to the Duke program.
 *
 * @author A0233290M
 * @version Week4
 */
public class Parser {
    /**
     * Encapsulates the instance of TaskList that will be associated with this instance of the Parser.
     */
    private TaskList tasks;
    /**
     * Encapsulates the instance of Ui that will be associated
     */
    private Ui ui;

    /**
     * Constructs the instance of Parser with the Task instance and Ui instance associated with it.
     *
     * @param tasks the TaskList associated with the instance of Parser.
     * @param ui the Ui associated with the instance of Parser.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Categorises the client's inputs to decide the method to be called by the program next in response to it.
     *
     * @return a String response to the command.
     * @param str The input passed into the method to be processed.
     * @throws DukeException when the command input is unknown.
     */
    public String categorise(String str) throws DukeException {
        String response = null;
        String uncap = str.toLowerCase();
        assert(str.length() > 0);
        if (str.equals("bye")) {
            return this.ui.bye();
        }
        if (uncap.startsWith("mark")) {
            int i = Integer.parseInt(String.valueOf(uncap.charAt(5)));
            response = tasks.mark(i, ui);
            return response;
        }
        if (uncap.startsWith("unmark")) {
            int i = Integer.parseInt(String.valueOf(uncap.charAt(7)));
            response = tasks.unmark(i, ui);
            return response;
        }
        if (uncap.startsWith("find")) {
            response = tasks.find(uncap.substring(5), ui);
            return response;
        }
        if (uncap.startsWith("delete")) {
            Integer i = Integer.parseInt(String.valueOf(str.charAt(7)));
            response = tasks.delete(i, ui);
            return response;
        }
        if (uncap.equals("list")) {
            response = ui.listOut();
            return response;
        }
        String command = uncap.split(" ")[0].trim();
        Task t = null;
        int idOfSlash = -1;
        switch (command) {
            case "deadline":
                if (!uncap.contains("/")) {
                    this.ui.showTimeMissingError();
                }
                idOfSlash = str.indexOf("/");
                if (idOfSlash - 9 == 0) {
                    this.ui.showDescEmptyError("Deadline");
                }
                if (str.length() < idOfSlash + 4) {
                    this.ui.showTimeMissingError();
                }
                t = new Deadline(str.substring(9, idOfSlash), str.substring(idOfSlash + 4));
                break;
            case "event":
                if (!uncap.contains("/")) {
                    this.ui.showTimeMissingError();
                }
                idOfSlash = str.indexOf("/");
                if (idOfSlash - 6 == 0) {
                    this.ui.showDescEmptyError("Event");
                }
                t = new Event(str.substring(6, idOfSlash), str.substring(idOfSlash + 4));
                break;
            case "todo":
                if (str.length() < 6) {
                    this.ui.showDescEmptyError("To Do");
                }
                t = new Todo(str.substring(5));
                break;
            default:
                this.ui.showUnknownCommandError();
        }
        response = this.tasks.add(t);
        return response;
    }
}
