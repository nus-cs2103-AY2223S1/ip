package pnkp.duke.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;
import static pnkp.duke.IOFormat.say;
import pnkp.duke.modules.todos.Task;

public class Todos {
    private final ArrayList<Task> todos;

    public Todos() {
        todos = new ArrayList<>();
    }

    public void cmdAdd(Scanner rest) {
        String name = rest.hasNextLine() ? rest.nextLine() : "";
        todos.add(new Task(name));
        say("Added: " + name);
    }

    public void cmdList() {
        ArrayList<String> output = new ArrayList<>(todos.size());
        output.add("Here are the tasks in your list:");
        for(int i=0; i<todos.size(); i++) {
            output.add(format("%d. %s", i, todos.get(i).toString()));
        }
        say(output);
    }

    public void cmdMark(Scanner rest) {
        if (!rest.hasNextInt()) {
            say("Please give me a task number to mark!");
            return;
        }

        int taskID = rest.nextInt();
        todos.get(taskID).setDone(true);
        say(List.of("Nice! I've marked this task as done:",
                    todos.get(taskID).toString()));
    }

    public void cmdUnmark(Scanner rest) {
        if (!rest.hasNextInt()) {
            say("Please give me a task number to unmark!");
            return;
        }

        int taskID = rest.nextInt();
        todos.get(taskID).setDone(false);
        say(List.of("Alright, I've marked this task as not done yet:",
                todos.get(taskID).toString()));
    }
}
