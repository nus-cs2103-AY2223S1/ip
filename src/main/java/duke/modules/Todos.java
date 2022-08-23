package duke.modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;
import static duke.IOFormat.say;

import duke.FallibleFunction;
import duke.MessagefulException;
import duke.modules.todos.Task;
import duke.modules.todos.Todo;

/**
 * Module handling tracking tasks, optionally with dates or timeranges.
 */
public class Todos {
    private final ArrayList<Task> todos;

    /**
     * constructor
     */
    public Todos() {
        todos = new ArrayList<>();
        try {
            loadList();
        } catch (MessagefulException e) {
            say(e.message());
        }
    }

    private String taskCountMessage() {
        return format("Now you have %d %s in the list.", todos.size(), todos.size() == 1 ? "task" : "tasks");
    }

    private static final String FILE_DIR = "data";
    private static final String FILE_PATH = FILE_DIR + "/tasks.csv";

    private void saveList() throws MessagefulException {
        try {
            File filedir = new File(FILE_DIR);
            if (!filedir.isDirectory() && !filedir.mkdirs()) {
                throw new MessagefulException("cannot create task save dir", "Uh oh! I cannot save the task list.");
            }

            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : todos) {
                fw.write(String.join(",", task.flatpack()) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            throw new MessagefulException(
                    "file writing error",
                    "Uh oh! I cannot save the task list. This might help: " + e);
        }
    }

    private void loadList() throws MessagefulException {
        try {
            Scanner file = new Scanner(new File(FILE_PATH));
            while (file.hasNextLine()) {
                List<String> line = Arrays.asList(file.nextLine().split(",", -1));
                final Task newTask;
                switch (line.get(0)) {
                case Todo.typeCode:
                    newTask = new Todo(line);
                    break;
                default:
                    throw new MessagefulException(
                            "unknown task type",
                            "Uh oh! I cannot load the task list.");
                }
                todos.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new MessagefulException(
                    "tasks file missing",
                    "I have gotton you started with an empty task list. Welcome!");
        }
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
        saveList();
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
    }
}
