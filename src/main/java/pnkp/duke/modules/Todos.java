package pnkp.duke.modules;

import java.util.ArrayList;
import static java.lang.String.format;
import static pnkp.duke.IOFormat.say;
import pnkp.duke.modules.todos.Task;

public class Todos {
    private final ArrayList<Task> todos;

    public Todos() {
        todos = new ArrayList<>();
    }

    public void cmdAdd(String name) {
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
}
