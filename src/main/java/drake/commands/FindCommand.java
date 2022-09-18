package drake.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import drake.DrakeException;
import drake.IncompatibleCommandException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {

    private final ArrayList<String> searchKeywords;

    /**
     * Constructor.
     * @param fullInput The user's input
     * @throws IncompatibleCommandException When the user does not enter the search keywords.
     */
    public FindCommand(String fullInput) throws IncompatibleCommandException {
        int firstSpace = fullInput.indexOf(" ");
        if (firstSpace == -1) {
            throw new IncompatibleCommandException("Where are the wordssss :weary_face:");
        }
        String afterFirstSpace = fullInput.substring(firstSpace + 1);
        searchKeywords = new ArrayList<>();
        searchKeywords.addAll(Arrays.asList(afterFirstSpace.split(" ")));
    }

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        //Inspired by parnikkapore's PR
        TaskList matches = tasks.filter(searchKeywords);
        ArrayList<String> reply = new ArrayList<>();
        reply.add(String.format("Here are the tasks that match \"%s\":",
                String.join("\", \"", searchKeywords)));

        for (int i = 1; matches.isValidTaskNumber(i); i++) {
            reply.add(String.format("%d. %s", i + 1, matches.getTaskToString(i)));
        }
        return reply;
    }
}
