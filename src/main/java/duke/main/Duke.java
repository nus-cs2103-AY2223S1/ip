package duke.main;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;

import java.time.LocalDate;

/**
 * Represents the karen chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Runs the chatbot when called.
     */
    public String getResponse(String inputText) {
        try {
            Parser parser = new Parser(inputText);
            String firstWord = parser.getFirstText();
            //terminate chat
            if (firstWord.equals("bye")) {
                storage.saveData(this.tasks);
                return ui.printByeMessage();
                //view list of items
            } else if (firstWord.equals("list")) {
                return ui.printListMessage(tasks);
                //mark an item as done
            } else if (firstWord.equals("mark")) {
                int markValue = parser.getTaskNumber();
                if (markValue > tasks.getSize() || markValue <= 0) {
                    throw new DukeException("The mark value does not exist dummy!");
                }
                Task item = tasks.getTask(markValue - 1);
                item.setAsDone();
                return ui.printMarkMessage(item);
                //mark an item as not done
            } else if (firstWord.equals("unmark")) {
                int markValue = parser.getTaskNumber();
                if (markValue > tasks.getSize() || markValue <= 0) {
                    throw new DukeException("The unmark value does not exist dummy!");
                }
                Task item = tasks.getTask(markValue - 1);
                item.setAsUndone();
                return ui.printUnmarkMessage(item);
                //add a to-do item
            } else if (firstWord.equals("todo")) {
                String desc = parser.getTodoDescription();
                Todo t = new Todo(desc);
                tasks.addTask(t);
                return ui.printAddTaskMessage(t, tasks.getSize());
                // add a deadline item
            } else if (firstWord.equals("deadline")) {
                String desc = parser.getDeadlineDescription();
                LocalDate byDate = parser.getDeadlineDate();
                Deadline d = new Deadline(desc, byDate);
                tasks.addTask(d);
                return ui.printAddTaskMessage(d, tasks.getSize());
                //add an event item
            } else if (firstWord.equals("event")) {
                String desc = parser.getEventDescription();
                LocalDate atDate = parser.getEventDate();
                Event e = new Event(desc, atDate);
                tasks.addTask(e);
                return ui.printAddTaskMessage(e, tasks.getSize());
                //delete a duke.task from the list
            } else if (firstWord.equals("delete")) {
                int delValue = parser.getTaskNumber();
                if (delValue > tasks.getSize() || delValue <= 0) {
                    throw new DukeException("The delete value does not exist dummy!");
                }
                Task item = tasks.getTask(delValue - 1);
                tasks.removeTask(delValue - 1);
                return ui.printDeleteMessage(item, tasks.getSize());
                //find a task from the task list
            } else if (firstWord.equals("find")) {
                String keyword = parser.getKeyword();
                TaskList relatedTasks = tasks.findRelatedTask(keyword);
                return ui.printFindMessage(relatedTasks);
                //firstWord is not a keyword
            } else {
                return ui.printUnknownMessage();
            }
        } catch (DukeException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
