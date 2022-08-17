package duke.core;

import duke.commands.*;
import duke.core.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    private HashMap<String, Command> commands = new HashMap<>();

    public Parser(ArrayList<Command> commands) {
        commands.forEach(x -> this.commands.put(x.getInvoker(), x));
    }

    public String parseInput(String input) throws DukeException {
        String[] command = input.split(" ", 2);
        String parameters = command.length > 1
            ? command[1]
            : "";
        Command toExecute = commands.get(command[0]);
        if (toExecute != null) {
            return toExecute.execute(parameters);
        }
        else {
            throw new DukeException("Invalid Command");
        }
    }
}
