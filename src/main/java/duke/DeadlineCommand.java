package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineCommand extends Command{

    @Override
    void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage)
            throws IOException, DukeDeadlineEmptyException {
        if(taskName.length() == 8) {
            throw new DukeDeadlineEmptyException();
        }

        int index = taskName.indexOf("/");
        String subS = taskName.substring(9, index - 1);
        String subString = taskName.substring(index + 4);
        TaskList taskList = new TaskList(listOfTasks);

        try {
            LocalDate date = LocalDate.parse(subString);
            Task t = new Deadline(subS,false, date);
            taskList.addToList(t);
        } catch (DateTimeException e) {
            Task t = new Deadline(subS,false, subString);
            taskList.addToList(t);
        }
    }
}
