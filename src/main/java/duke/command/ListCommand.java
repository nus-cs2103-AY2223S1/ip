package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class ListCommand extends Command {
    private boolean isDueListCommand;
    private LocalDate date;

    public ListCommand(boolean isDueListCommand, LocalDate date) {
        super(CommandType.LIST);
        this.isDueListCommand = isDueListCommand;
        this.date = date;
    }

    public ListCommand() {
        super(CommandType.LIST);
        this.isDueListCommand = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDueListCommand) {
            ui.printListTasksDueOnDateMessage(date);
            tasks.listTasks(date);
        } else {
            ui.printListTasksMessage();
            tasks.listTasks();
        }
    }
}
