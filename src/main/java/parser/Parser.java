package parser;

import action.*;
import duke.DukeException;

import java.nio.file.Path;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskList;

import duke.Command;

public class Parser {


    protected Command command = Command.UNKNOWN;

    public Parser() {

    }

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


    public Boolean isBye() {
        return this.command == Command.BYE;
    }
}


