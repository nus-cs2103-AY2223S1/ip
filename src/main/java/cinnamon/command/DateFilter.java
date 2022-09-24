package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DateFilter extends Command{

    String date;
    String input;

    public DateFilter(String input) {
        this.input = input;
    }

    /**
     * Lists all tasks with the same date
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return respone of cinnamon
     * @throws DukeException specific exceptions
     */

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        String s = "";
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            LocalDate d1 = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String a = d1.format((DateTimeFormatter.ofPattern("MMM dd yyyy")));
            if (str.contains(a)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            assert (ui != null);
            s = ui.dateNotFound();
            return s;
        } else {
            assert (ui != null);
            return ui.printMatchedTasks(matched);
        }
    }
}
