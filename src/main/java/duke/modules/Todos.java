package duke.modules;

import duke.FallibleFunction;
import duke.MessagefulException;
import duke.modules.todos.Storage;
import duke.modules.todos.Task;
import duke.modules.todos.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.Ui.printLines;
import static duke.Ui.say;
import static java.lang.String.format;

/**
 * Module handling tracking tasks, optionally with dates or timeranges.
 */
public class Todos {
    private final TaskList todos;

    private final Storage storage = new Storage();

    /**
     * constructor
     */
    public Todos() {
        this.todos = new TaskList(storage);
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
     *
     * @param rest        The scanner with the remaining text in the message.
     * @param constructor The fromChat factory function of the given task.
     * @throws MessagefulException if there is an issue.
     */
    public void cmdAdd(Scanner rest, FallibleFunction<Scanner, Task> constructor) throws MessagefulException {
        final Task task = constructor.apply(rest);

        todos.add(task);
        printLines(say(List.of(
                "Got it. I've added this task:",
                task.toString(),
                taskCountMessage())));
        storage.saveList(todos);
    }

    /**
     * Command for listing all tasks.
     */
    public void cmdList() {
        ArrayList<String> output = new ArrayList<>(todos.size());
        output.add("Here are the tasks in your list:");
        for (int i = 0; i < todos.size(); i++) {
            output.add(format("%d. %s", i + 1, todos.get(i).toString()));
        }
        printLines(say(output));
    }

    /**
     * Command for marking a task as done.
     *
     * @param rest The scanner with the remaining text in the message.
     * @throws MessagefulException if there is an issue.
     */
    public void cmdMark(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to mark!");

        todos.get(taskID).setDone(true);
        printLines(say(List.of(
                "Nice! I've marked this task as done:",
                todos.get(taskID).toString())));
        storage.saveList(todos);
    }

    /**
     * Command for marking a task as not done.
     *
     * @param rest The scanner with the remaining text in the message.
     * @throws MessagefulException if there is an issue.
     */
    public void cmdUnmark(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to unmark!");

        todos.get(taskID).setDone(false);
        printLines(say(List.of("Alright, I've marked this task as not done yet:",
                todos.get(taskID).toString())));
        storage.saveList(todos);
    }

    /**
     * Command for deleting a task.
     *
     * @param rest The scanner with the remaining text in the message.
     * @throws MessagefulException if there is an issue.
     */
    public void cmdDelete(Scanner rest) throws MessagefulException {
        final int taskID = readTodoID(rest, "Please give me a task number to delete!");

        Task taskToDelete = todos.get(taskID);
        todos.remove(taskID);
        printLines(say(List.of(
                "OK, I've deleted this task:",
                taskToDelete.toString(),
                taskCountMessage())));
        storage.saveList(todos);
    }

    /**
     * Command for showing tasks that match certain keywords.
     *
     * @param rest The scanner with the remaining text in the message.
     * @throws MessagefulException if there is an issue.
     */
    public void cmdFind(Scanner rest) throws MessagefulException {
        ArrayList<String> keywords = new ArrayList<>();
        while (rest.hasNext()) {
            keywords.add(rest.next());
        }

        if (keywords.size() == 0) {
            throw new MessagefulException("no keywords", "Please give me some words to find!");
        }

        TaskList matches = todos.filter(keywords);

        ArrayList<String> output = new ArrayList<>(matches.size());
        output.add(format("Here are the tasks that match \"%s\":", String.join("\", \"", keywords)));
        for (int i = 0; i < matches.size(); i++) {
            output.add(format("%d. %s", i + 1, matches.get(i).toString()));
        }
        printLines(say(output));
    }
}
