package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.parser.Parser;
import duke.storage.TaskRecords;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.BotUI;

public class AddCommand extends Command {

    private final String details;

    public AddCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            String taskCommand = super.getCommand();
            switch (taskCommand) {
            case "todo":
                Task taskToDo = new ToDos(details);
                taskList.addProcess(taskToDo);
                System.out.print((ui.addStatus(taskList, taskToDo)));
                break;
            case "deadline":
                String deadlineDetail = Parser.extractDetail(details, " /by ");
                LocalDateTime deadlineDateTime = Parser.extractDateTime(details, " /by ");
                Task taskDeadline = new Deadlines(deadlineDetail, deadlineDateTime);
                taskList.addProcess(taskDeadline);
                System.out.print((ui.addStatus(taskList, taskDeadline)));
                break;
            case "event":
                String eventDetail = Parser.extractDetail(details, " /at ");
                LocalDateTime eventDateTime = Parser.extractDateTime(details, " /at ");
                Task taskEvent = new Events(eventDetail, eventDateTime);
                taskList.addProcess(taskEvent);
                System.out.print(ui.addStatus(taskList, taskEvent));
                break;
            default:
                System.out.print("Adding process fail!");
                break;
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.invalidDateFormat());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
