package duke.modules;

import duke.FallibleFunction;
import duke.MessagefulException;
import duke.modules.todos.Storage;
import duke.modules.todos.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.Ui.say;
import static duke.Ui.sayError;
import static java.lang.String.format;

/**
 * Module handling tracking tasks, optionally with dates or timeranges.
 */
public class Todos {
    private final ArrayList<Task> todos;

    private final Storage storage = new Storage();

    /**
     * constructor
     */
    public Todos() {
        ArrayList<Task> todos;
        try {
            todos = storage.loadList();
        } catch (MessagefulException e) {
            sayError(e);
            todos = new ArrayList<>();
        }
        this.todos = todos;
    }

    private String taskCountMessage() {
        return format("Now you have %d %s in the list.", todos.size(), todos.size() == 1 ? "task" : "tasks");
    }

    private int readTodoID(Scanner rest, String missingNumberPrompt) throws MessagefulException {
        if (!rest.hasNextInt()) {
            throw new MessagefulException("Missing task number", missingNumberPrompt);
        }

        int taskID = rest.nextInt() - 1;
        if (taskID < 0 || taskID >= todos.size()) {
            throw new MessagefulException(
                    "Task number out of bounds",
                    "Hmm... a task with that number doesn't seem to exist. " +
                        "You can see a list of all tasks by saying \"list\".");
        }

        return taskID;
    }

    /**
     * Command for adding a task.
     * @param rest The scanner with the remaining text in the message.
     * @param constructor The fromChat factory function of the given task.
     */
    public void cmdAdd(Scanner rest, FallibleFunction<Scanner, Task> constructor) throws MessagefulException {
        final Task task = constructor.apply(rest);

        todos.add(task);
        say(List.of(
                "Got it. I've added this task:",
                task.toString(),
                taskCountMessage()
        ));
        storage.saveList(todos);
    }

    /**
     * Command for listing all tasks.
     */
    public void cmdList() {
        ArrayList<String> output = new ArrayList<>(todos.size());
        output.add("Here are the tasks in your list:");
        for(int i=0; i<todos.size(); i++) {
            output.add(format("%d. %s", i+1, todos.get(i).toString()));
        }
        say(output);
    }

    /**
     * Command for marking a task as done.
     * @param rest The scanner with the remaining text in the message.
     */
    public void cmdMark(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to mark!");

        todos.get(taskID).setDone(true);
        say(List.of("Nice! I've marked this task as done:",
                    todos.get(taskID).toString()));
        storage.saveList(todos);
    }

    /**
     * Command for marking a task as not done.
     * @param rest The scanner with the remaining text in the message.
     */
    public void cmdUnmark(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to unmark!");

        todos.get(taskID).setDone(false);
        say(List.of("Alright, I've marked this task as not done yet:",
                todos.get(taskID).toString()));
        storage.saveList(todos);
    }

    /**
     * Command for deleting a task.
     * @param rest The scanner with the remaining text in the message.
     */
    public void cmdDelete(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to delete!");

        Task taskToDelete = todos.get(taskID);
        todos.remove(taskID);
        say(List.of(
                "OK, I've deleted this task:",
                taskToDelete.toString(),
                taskCountMessage()));
        storage.saveList(todos);
    }
}
