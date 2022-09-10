package drake.commands;

import drake.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FindCommand extends Command {

    private final ArrayList<String> searchKeywords;
    public FindCommand(String fullInput) {
        searchKeywords = new ArrayList<>();
        searchKeywords.addAll(Arrays.asList(fullInput.split(" ")));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        //Inspired by parnikkapore's PR
        if (searchKeywords.size() == 0) {
            throw new IncompatibleCommandException("Where are the wordssss :weary_face:");
        }

        TaskList matches = tasks.filter(searchKeywords);

        ui.printLine(String.format("Here are the tasks that match \"%s\":",
                String.join("\", \"", searchKeywords)));

        for (int i = 0; matches.isValidTaskNumber(i); i++) {
            ui.printLine(String.format("%d. %s", i + 1, matches.getTaskToString(i)));
        }

    }
}
