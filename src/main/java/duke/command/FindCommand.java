package duke.command;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

public class FindCommand extends DataCommand {

    private static final String RED_TXT = "\u001B[31m$0\u001B[0m";
    private static final String FIND_TXT = "    The search results are below: ";

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

        List<Task> tasks = taskList.getTasks();

        io.printLine();
        io.printTask(FIND_TXT, 0);
        String sBefore, sAfter;
        for (int i = 0; i < tasks.size(); i++) {
            sBefore = tasks.get(i).toString();
            sAfter = sBefore.replaceAll(searchString, RED_TXT);
            if (sAfter.length() != sBefore.length()) {
                io.printTask(String.format("%d. %s", i + 1, sAfter), 2);
            }
        }
        io.printLine();

    }

}
