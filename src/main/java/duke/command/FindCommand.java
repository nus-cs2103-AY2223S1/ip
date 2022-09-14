package duke.command;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.inputoutput.DukeIo;
import duke.task.Task;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command that allows for search of data through out the list.
 */
public class FindCommand extends DataCommand {

    private static final String RED_TXT = "\u001B[31m$0\u001B[0m";
    private static final String FIND_TXT = "    The search results are below: \n";

    public FindCommand(ParsedData data) {
        super(data);
    }

    @Override
    public void execute(TaskList taskList, DukeIo io, Storage storage) throws DukeException, IOException {
        String searchString = data.description.trim();
        if (searchString.length() == 0) {
            throw new EmptyDescriptionException("find");
        }

        searchString = String.format("(?i)(%s)",
                Matcher.quoteReplacement(searchString));

        StringBuilder sb = new StringBuilder();
        List<Task> tasks = taskList.getTasks();

        io.printTask(FIND_TXT);
        for (int i = 0; i < tasks.size(); i++) {
            String sBefore = tasks.get(i).toString();
            String sAfter = sBefore.replaceAll(searchString, RED_TXT);
            if (sAfter.length() != sBefore.length()) {
                sb.append(String.format("%d. %s", i + 1, sAfter));
            }
        }
        io.printTask(sb.toString());
    }

}
