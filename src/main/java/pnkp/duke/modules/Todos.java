package pnkp.duke.modules;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;
import static pnkp.duke.IOFormat.say;

public class Todos {
    private final ArrayList<String> todos;

    public Todos() {
        todos = new ArrayList<>();
    }

    public void cmdAdd(Scanner rest) {
        String name = rest.nextLine();
        todos.add(name);
        say("Added: " + name);
    }

    public void cmdList(Scanner rest) {
        String[] output = new String[todos.size()];
        for(int i=0; i<todos.size(); i++) {
            output[i] = format("%d. %s", i, todos.get(i));
        }
        say(output);
    }
}
