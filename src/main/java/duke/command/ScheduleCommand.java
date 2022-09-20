package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/*
Shows the user a list of events/deadlines for the given date.
 */
public class ScheduleCommand extends Command {

    String date;

    public ScheduleCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the schedule command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        assert(ui != null);
        try {
            LocalDate.parse(this.date);
        } catch (DateTimeParseException e) {
            throw new DukeException("please input a valid date in the format YYYY-MM-DD");
        }
        return ui.showSchedule(this.date, taskList.getTaskList());
    };

}