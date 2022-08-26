package Duke;

import Duke.task.Task;
import Duke.task.Todo;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.TaskList;
import Duke.task.TaskStorage;

import Duke.util.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the response handler for Duke chat-bot. Stores a list of tasks,
 * and perform CRUD functions on both the list of tasks and the text file that
 * saves it.
 */
public class DukeHandler {
    private TaskList tasks;
    private TaskStorage storage;
    private Ui ui;

    public DukeHandler(TaskStorage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Makes sense of user input and perform operations
     * on task list, and shows output to user
     */
    public void handleResponse(String input) throws DukeException {
        if (input.equals("bye")) {
            ui.sayGoodbye();
            System.exit(0);
        }
        String[] temp = input.split(" ", 2);
        List<String> inputParts = new ArrayList<String>(Arrays.asList(temp));
        try {
            if (inputParts.size() < 2) {
                inputParts.add("");
            }
            if (input.equals("list")) {
                ArrayList<Task> list = tasks.listTasks();
                ui.listTasks(list);
            }
            else if (input.matches("mark +\\d+") || input.matches("unmark +\\d+")) {
                if (inputParts.get(0).equals("mark")) {
                    Task task = tasks.mark(Integer.parseInt(inputParts.get(1)));
                    ui.respond("Nice! I've marked this Duke.Duke.task as done: \n" + task.toString());
                    storage.saveTask(tasks);
                }
                if (inputParts.get(0).equals("unmark")) {
                    Task task = tasks.unmark(Integer.parseInt(inputParts.get(1)));
                    ui.respond("OK, I've marked this Duke.Duke.task as not done yet: \n" + task.toString());
                    storage.saveTask(tasks);
                }
            }
            else if (input.matches("delete +\\d+")) {
                Task task = tasks.delete(Integer.parseInt(inputParts.get(1)));
                ui.deleteTask(task, tasks);
                storage.saveTask(tasks);
            }
            else if (inputParts.get(0).equals("todo")) {
                Todo newTodo = new Todo(inputParts.get(1), false);
                tasks.addTask(newTodo);
                ui.addTask(newTodo, tasks);
                storage.saveTask(tasks);
            }
            else if (inputParts.get(0).equals("deadline")) {
                String[] deadlineParts = inputParts.get(1).split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    new Deadline(deadlineParts[0], deadlineParts[0], false);
                }

                String[] timeParts = deadlineParts[1].split(" ", 2);
                String deadlineDate =(timeParts.length) < 2 ? deadlineParts[1]
                        + " 00:00" : deadlineParts[1];
                Deadline deadlineTask = new Deadline(deadlineParts[0], deadlineDate, false);
                tasks.addTask(deadlineTask);
                ui.addTask(deadlineTask, tasks);
                storage.saveTask(tasks);
            } else if (inputParts.get(0).equals("event")) {
                String[] eventParts = inputParts.get(1).split(" /at ", 2);
                if (eventParts.length < 2) {
                    new Event(eventParts[0], eventParts[0], false);
                }
                String[] timeParts = eventParts[1].split(" ", 2);
                String eventDate =(timeParts.length) < 2 ? eventParts[1]
                        + " 00:00" : eventParts[1];
                Event newEvent = new Event(eventParts[0], eventDate, false);
                tasks.addTask(newEvent);
                ui.addTask(newEvent, tasks);
                storage.saveTask(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException e) {
           ui.showError(e.getMessage());
        }
    }

}
