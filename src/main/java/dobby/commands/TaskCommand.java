package dobby.commands;

import java.util.Objects;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;
import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Todo;

/**
 * Class that adds new tasks to the list
 */
public class TaskCommand extends Command {
    /**
     * Adds new tasks to the list
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            String cmd = ui.getTaskType();
            String desc = ui.getDesc();
            String date;
            if (desc.isBlank()) {
                DobbyChat.noTaskDesc();
            }
            if (cmd.equals("todo")) {
                Todo newTodo = new Todo(desc);
                dl.add(newTodo);
                DobbyChat.added(newTodo, dl);
            } else {
                date = ui.getDate();
                if (Objects.equals(date, "wrongDateFormat")) {

                } else if (Objects.equals(date, "noDate")) {
                    if (cmd.equals("event")) {
                        DobbyChat.noEventDate();
                    } else {
                        DobbyChat.noDeadlineDate();
                    }
                } else {
                    if (cmd.equals("deadline")) {
                        Deadline newDeadline = new Deadline(desc, date);
                        dl.add(newDeadline);
                        DobbyChat.added(newDeadline, dl);
                    } else {
                        Event newEvent = new Event(desc, date);
                        dl.add(newEvent);
                        DobbyChat.added(newEvent, dl);
                    }
                }
            }
            //only when there is no space behind "deadline", else is handled above
        } catch (StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskDesc();
        }
    }
}
