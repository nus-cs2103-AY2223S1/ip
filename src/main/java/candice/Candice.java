package candice;

import candice.command.Command;
import candice.exception.EmptyCommandDescriptionException;
import candice.exception.EmptyTimingException;
import candice.exception.InvalidDateException;
import candice.exception.InvalidFormattingException;
import candice.exception.InvalidTimeException;
import candice.exception.UnknownCommandException;
import candice.task.TaskList;

import java.io.IOException;

import java.lang.IllegalArgumentException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Represents a program that helps to track tasks using a task list.
 * The program allows for different types of tasks to be added, tasks to be deleted or marked as finished/unfinished
 * as well as viewing the current list of tasks.
 *
 * Commands:
 * "todo {task name}" to add a task with no date associated to it.
 *
 * "deadline {task name} /by {deadline date} {optional: deadline time}" to add tasks that have a deadline associated to
 * it. Deadline dates are written in the form of DD/MM/YYYY (1/1/2022 or 31/12/2022). Deadline times are written in the
 * form of a 24-hour time (0000 to 2359).
 *
 * "event {task name} /at {event date} {optional: event start time - end time}" to add events with a date and
 * potentially a start plus end time associated to them. Deadline dates are written in the form of DD/MM/YYYY (1/1/2022
 * or 31/12/2022). Event start time - end time is written in the form of two 24-hour times (0000-0200 or 1800-2000).
 *
 * "list" to view the tasks added to the task list.
 *
 * "mark {task number}" to mark the task corresponding to the task number as finished.
 *
 * "unmark {task number}" to mark the task corresponding to the task number as unfinished.
 *
 * "delete {task number}" to remove the task corresponding to the task number from the task list.
 *
 * "find {key word}" to find any tasks with a task name containing the keyword.
 *
 * "bye" to exit the program.
 */
public class Candice {
    /** A task list to store all the tasks */
    TaskList taskList;

    /**
     * Constructor for a Candice object encapsulating a TaskList object by using a Path object to instantiate a Storage
     * object which will be used by the TaskList to parse any tasks added from previous sessions.
     *
     * @param storagePath The path to the storage file, specifically the path to the task list text file.
     * @throws IOException If there was an error in creating the storage file.
     */
    public Candice(Path storagePath) throws IOException {
        Storage storage = new Storage(storagePath);
        this.taskList = new TaskList(storage);
        this.taskList.parseTaskListText();
    }

    /**
     * Default constructor for a Candice object, using the default storage.
     *
     * @throws IOException If there was an error in creating the storage file.
     */
    public Candice() throws IOException {
        Path storagePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        Storage storage = new Storage(storagePath);
        this.taskList = new TaskList(storage);
        this.taskList.parseTaskListText();
    }

    /**
     * Runs the programme, allowing users to input a command to add, edit and delete tasks to and from a task list.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.resolve(taskList);
        } catch (EmptyCommandDescriptionException | EmptyTimingException | IllegalArgumentException
                | InvalidDateException | InvalidFormattingException | InvalidTimeException
                | UnknownCommandException e) {
            return e.getMessage();
        }
    }
}
