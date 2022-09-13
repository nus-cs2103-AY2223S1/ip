package Duke.commands;

import Duke.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FindCommands extends Command {

    private final ArrayList<String> Keywords;


    public FindCommands(String fullInput) throws DukeException {
        int firstSpace = fullInput.indexOf(" ");
        if (firstSpace == -1) {
            throw new DukeException("No input was detected");
        }
        String afterFirstSpace = fullInput.substring(firstSpace + 1);
        Keywords = new ArrayList<>();
        Keywords.addAll(Arrays.asList(afterFirstSpace.split(" ")));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        TaskList matches = tasks.filter(Keywords);

        ui.printLine(String.format("Here are the matching tasks \"%s\":",
                String.join("\", \"", Keywords)));

        for (int i = 1; matches.isValidTaskNumber(i); i++) {
            ui.printLine(String.format("%d. %s", i + 1, matches.getTaskToString(i)));
        }

    }

}
