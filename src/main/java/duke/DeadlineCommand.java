package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineCommand extends Command{

    @Override
    void execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException, DukeDeadlineEmptyException {
        if(fullCommand.length() == 8){
            throw new DukeDeadlineEmptyException();
        }
        int index = fullCommand.indexOf("/");
        String subS = fullCommand.substring(9, index - 1);
        String subString = fullCommand.substring(index + 4);
        TaskList taskList = new TaskList(listOfTasks);
        try {
            LocalDate date = LocalDate.parse(subString);
            Task t = new Deadlines(subS,false, date);
            taskList.addToList(t);
        } catch (DateTimeException e) {
            Task t = new Deadlines(subS,false, subString);
            taskList.addToList(t);
        }
    }
}
