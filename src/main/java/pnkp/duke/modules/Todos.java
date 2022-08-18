package pnkp.duke.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;
import static pnkp.duke.IOFormat.say;

import pnkp.duke.MessagefulException;
import pnkp.duke.modules.todos.Deadline;
import pnkp.duke.modules.todos.Event;
import pnkp.duke.modules.todos.Task;
import pnkp.duke.modules.todos.Todo;

public class Todos {
    private final ArrayList<Task> todos;

    public Todos() {
        todos = new ArrayList<>();
    }

    private void addWrapper(Task newTask) {
        todos.add(newTask);
        say(List.of(
            "Got it. I've added this task:",
            newTask.toString(),
            format("Now you have %d %s in the list.", todos.size(), todos.size() == 1 ? "task" : "tasks")
        ));
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

    public void cmdAddTodo(Scanner rest) {
        final Task task;
        try {
            task = Todo.fromChat(rest);
        } catch(MessagefulException e) {
            say(e.message());
            return;
        }

        addWrapper(task);
    }

    public void cmdAddDeadline(Scanner rest) {
        final Task task;
        try {
            task = Deadline.fromChat(rest);
        } catch(MessagefulException e) {
            say(e.message());
            return;
        }

        addWrapper(task);
    }

    public void cmdAddEvent(Scanner rest) {
        final Task task;
        try {
            task = Event.fromChat(rest);
        } catch(MessagefulException e) {
            say(e.message());
            return;
        }

        addWrapper(task);
    }

    public void cmdList() {
        ArrayList<String> output = new ArrayList<>(todos.size());
        output.add("Here are the tasks in your list:");
        for(int i=0; i<todos.size(); i++) {
            output.add(format("%d. %s", i+1, todos.get(i).toString()));
        }
        say(output);
    }

    public void cmdMark(Scanner rest) {
        final int taskID;
        try {
            taskID = readTodoID(rest, "Please give me a task number to mark!");
        } catch (MessagefulException e) {
            say(e.message());
            return;
        }

        todos.get(taskID).setDone(true);
        say(List.of("Nice! I've marked this task as done:",
                    todos.get(taskID).toString()));
    }

    public void cmdUnmark(Scanner rest) {
        final int taskID;
        try {
            taskID = readTodoID(rest, "Please give me a task number to unmark!");
        } catch (MessagefulException e) {
            say(e.message());
            return;
        }

        todos.get(taskID).setDone(false);
        say(List.of("Alright, I've marked this task as not done yet:",
                todos.get(taskID).toString()));
    }

    public void cmdDelete(Scanner rest) {
        final int taskID;
        try {
            taskID = readTodoID(rest, "Please give me a task number to delete!");
        } catch (MessagefulException e) {
            say(e.message());
            return;
        }

        Task taskToDelete = todos.get(taskID);
        todos.remove(taskID);
        say(List.of(
                "OK, I've deleted this task:",
                taskToDelete.toString(),
                format("Now you have %d %s in the list.",
                        todos.size(),
                        todos.size() == 1 ? "task" : "tasks")));
    }
}
