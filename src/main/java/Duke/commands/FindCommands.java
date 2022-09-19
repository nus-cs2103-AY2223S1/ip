package Duke.commands;

import Duke.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommands extends Command {

    private final ArrayList<String> Keywords;


    public FindCommands(String input) throws DukeException {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            throw new DukeException("No input was detected");
        }
        String word = input.substring(firstSpace + 1);
        Keywords = new ArrayList<>();
        Keywords.addAll(Arrays.asList(word.split(" ")));
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        TaskList matches = tasks.filter(Keywords);

        String line1 = String.format("Here are the matching tasks \"%s\":",
                String.join("\", \"", Keywords));

        for (int i = 1; matches.isValidTaskNumber(i); i++) {
            String line2 = String.format("%d. %s", i + 1, matches.getTaskToString(i));
            line1 += line2;
        }
        return line1;

    }

}
