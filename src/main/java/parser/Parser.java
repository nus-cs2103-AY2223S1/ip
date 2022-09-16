package parser;

import action.Bye;
import action.Delete;
import action.Find;
import action.ListOut;
import action.Mark;
import action.Unmark;

import duke.Command;
import duke.DukeException;

import java.nio.file.Path;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskList;

import ui.UI;


public class Parser {

    protected static UI ui = new UI();


    protected Command command = Command.UNKNOWN;


    /**
     * Constructor of Parser object.
     */
    public Parser() {

    }


    /**
     * Takes in the user input and check what type of input it is.
     * @param taskList The TaskList to act the Command on.
     * @param str The user input.
     * @return Returns the System reply to the user input.
     * @throws DukeException e
     */
    public String parse(TaskList taskList, String str) throws DukeException {
        String[] input = str.split(" ");
        Command c = Command.read(str);
        switch (c) {
            case BYE:
                command = Command.BYE;
                return Bye.bye(input, taskList, Path.of("data/duke.txt").toFile());
            case LIST:
                return ListOut.list(input, taskList);
            case FIND:
                return Find.findTasks(input, taskList);
            case MARK:
                try {
                    return Mark.mark(input, taskList);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case UNMARK:
                try {
                    return Unmark.unMark(input, taskList);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case TODO:
                return taskList.add(new Todo(str.substring(5)));
            case EVENT:
                int posE = str.indexOf("/") + 1;
                assert posE < str.length() : "String position cannot be longer than string";
                try {
                    Event event = new Event(str.substring(6, posE - 1), str.substring(posE + 3));
                    return taskList.add(event);
                } catch (DukeException e) {
                    return e.getMessage();
                }

            case DEADLINE:
                int posD = str.indexOf("/by") + 1;
                assert posD < str.length() : "String position cannot be longer than string";
                try {
                    Deadline dl = new Deadline(str.substring(9, posD - 1), str.substring(posD + 3));
                    return taskList.add(dl);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case DELETE:
                try {
                    return Delete.delete(input, taskList);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            default:
                throw new DukeException(ui.showInaccurateInput());
        }
    }


    /**
     * Check if command is Command.BYE.
     * @return True if command is Command.BYE.
     */
    public Boolean isBye() {
        return this.command == Command.BYE;
    }
}


