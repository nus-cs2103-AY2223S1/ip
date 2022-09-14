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


public class Parser {


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
     * @throws DukeException
     */
    public String parse(TaskList taskList, String str) throws DukeException {
        String[] input = str.split(" ");
        Command c = Command.read(input[0]);
        switch (c) {
            case BYE:
                command = Command.BYE;
                return Bye.bye(input, taskList, Path.of("data/duke.txt").toFile());
            case LIST:
                return ListOut.list(input, taskList);
            case FIND:
                return Find.findTasks(input, taskList);
            case MARK:
                return Mark.mark(input, taskList);
            case UNMARK:
                return Unmark.unMark(input, taskList);
            case TODO:
                return taskList.add(new Todo(str.substring(5)));
            case EVENT:
                int posE = str.indexOf("/") + 1;
                assert posE < str.length() : "String position cannot be longer than string";
                return taskList.add(new Event(str.substring(6, posE - 1), str.substring(posE + 3)));
            case DEADLINE:
                int posD = str.indexOf("/by") + 1;
                assert posD < str.length() : "String position cannot be longer than string";
                return taskList.add(new Deadline(str.substring(9, posD - 1), str.substring(posD + 3)));
            case DELETE:
                return Delete.delete(input, taskList);
            default:
                throw new DukeException("???? What are you saying\n");
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


