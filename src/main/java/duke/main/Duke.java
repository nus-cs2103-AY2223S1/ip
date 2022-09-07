package duke.main;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the karen chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates instance of duke.
     *
     * @param filePath Location to create the storage
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the chatbot when called.
     */
    public String getResponse(String inputText) {
        try {
            Parser parser = new Parser(inputText);
            String firstWord = parser.getFirstText();

            switch (firstWord) {
            //terminate chat
            case "bye": {
                storage.saveData(tasks);
                return ui.printByeMessage();
            }
            //view list of tasks
            case "list": {
                return ui.printListMessage(tasks);
            }
            //mark a task
            case "mark": {
                int markValue = parser.getTaskNumber();
                //unsure if I should use SLAP here
                boolean isWithinTaskSize = markValue > tasks.getSize();
                boolean isGreaterThanZero = markValue <= 0;
                if (isWithinTaskSize || isGreaterThanZero) {
                    throw new DukeException("The mark value does not exist dummy!");
                }

                Task item = tasks.getTask(markValue);
                item.setAsDone();
                return ui.printMarkMessage(item);
            }
            //unmark a task
            case "unmark": {
                int markValue = parser.getTaskNumber();
                boolean isWithinTaskSize = markValue > tasks.getSize();
                boolean isGreaterThanZero = markValue <= 0;
                if (isWithinTaskSize || isGreaterThanZero) {
                    throw new DukeException("The unmark value does not exist dummy!");
                }

                Task item = tasks.getTask(markValue);
                item.setAsUndone();
                return ui.printUnmarkMessage(item);
            }
            //add a to-do task
            case "todo": {
                String desc = parser.getTodoDescription();
                Todo todo = new Todo(desc);
                tasks.addTask(todo);
                return ui.printAddTaskMessage(todo, tasks.getSize());
            }
            //add a deadline task
            case "deadline": {
                String desc = parser.getDeadlineDescription();
                LocalDate byDate = parser.getDeadlineDate();
                Deadline deadline = new Deadline(desc, byDate);
                tasks.addTask(deadline);
                return ui.printAddTaskMessage(deadline, tasks.getSize());
            }
            //add an event task
            case "event": {
                String desc = parser.getEventDescription();
                LocalDate atDate = parser.getEventDate();
                Event event = new Event(desc, atDate);
                tasks.addTask(event);
                return ui.printAddTaskMessage(event, tasks.getSize());
            }
            //delete a task from the task list
            case "delete": {
                int delValue = parser.getTaskNumber();
                boolean isWithinTaskSize = delValue > tasks.getSize();
                boolean isGreaterThanZero = delValue <= 0;
                if (isWithinTaskSize || isGreaterThanZero) {
                    throw new DukeException("The delete value does not exist dummy!");
                }

                Task item = tasks.getTask(delValue);
                tasks.removeTask(delValue);
                return ui.printDeleteMessage(item, tasks.getSize());
            }
            //find a related task from the task list
            case "find": {
                String keyword = parser.getKeyword();
                TaskList relatedTasks = tasks.findRelatedTask(keyword);
                return ui.printFindMessage(relatedTasks);
            }
            //update a task
            case "update": {
                int updateValue = parser.getTaskNumber();
                boolean isWithinTaskSize = updateValue > tasks.getSize();
                boolean isGreaterThanZero = updateValue <= 0;
                if (isWithinTaskSize || isGreaterThanZero) {
                    throw new DukeException("The update value does not exist dummy!");
                }

                Task task = tasks.getTask(updateValue);

                boolean hasUpdateDateClause = parser.hasUpdateDateClause();
                if (hasUpdateDateClause && task.getTaskType().equals("E")) {
                    LocalDate updatedDate = parser.getEventDate();
                    task.updateDate(updatedDate);
                } else if (hasUpdateDateClause && task.getTaskType().equals("D")) {
                    LocalDate updatedDate = parser.getDeadlineDate();
                    task.updateDate(updatedDate);
                }

                boolean hasUpdateDescClause = parser.hasUpdateDescClause();
                if (hasUpdateDescClause) {
                    String updatedDescription = parser.getUpdatedDescription();
                    task.updateTask(updatedDescription);
                }

                return ui.printUpdateMessage(task);
            }
            //unknown input
            default:
                return ui.printUnknownMessage();
            }
        } catch (DukeException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
