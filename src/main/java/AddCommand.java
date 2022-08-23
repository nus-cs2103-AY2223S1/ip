import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddCommand extends Command {

    private final String details;

    AddCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            if (super.getCommand().equals("todo")) {
                Task task = new ToDos(details);
                taskList.addProcess(task);
                System.out.print((ui.addStatus(taskList, task)));
            } else if (super.getCommand().equals("deadline")) {
                String deadlineDetail = Parser.extractDetail(details, "/by ");
                LocalDateTime dateTime = Parser.extractDateTime(details, "/by ");
                Task task = new Deadlines(deadlineDetail ,dateTime);
                taskList.addProcess(task);
                System.out.print((ui.addStatus(taskList, task)));
            } else if (super.getCommand().equals("event")) {
                String eventDetail = Parser.extractDetail(details, "/at ");
                LocalDateTime dateTime = Parser.extractDateTime(details, "/at ");
                Task task = new Events(eventDetail, dateTime);
                taskList.addProcess(task);
                System.out.print(ui.addStatus(taskList, task));
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
