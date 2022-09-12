package dobby.commands;

import java.io.IOException;

import dobby.Dobby;
import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.DobbyStorage;
import dobby.UserInput;
import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Todo;


/**
 * Class that adds new tasks to the list.
 */
public class TaskCommand extends Command {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String WRONG_DATE_FORMAT = "wrongDateFormat";
    private static final String NO_DATE = "noDate";
    private static final String NO_DESCRIPTION = "noDescription";
    private static final String WRONG_DATETYPE_DEADLINE = "wrongDeadline";
    private static final String WRONG_DATETYPE_EVENT = "wrongEvent";

    /**
     * Adds new tasks to the list.
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
            if (desc.equals("")) {
                DobbyChat.noTaskDesc();
            } else {
                if (cmd.equals(COMMAND_TODO)) {
                    Todo newTodo = new Todo(desc);
                    dl.add(newTodo);
                    DobbyChat.added(newTodo, dl);
                    DobbyStorage.save(dl, Dobby.getFilePath());
                } else {
                    date = ui.getDate();
                    //if date has the wrong format
                    if (date.equals(WRONG_DATE_FORMAT)) {
                        DobbyChat.wrongDateFormat();
                        //if user didn't use the /by command for a deadline
                    } else if (date.equals(WRONG_DATETYPE_DEADLINE)) {
                        DobbyChat.noDeadlineDate();
                        //if user didn't use the /at command for an event
                    } else if (date.equals(WRONG_DATETYPE_EVENT)) {
                        DobbyChat.noEventDate();
                        //if user didn't include the date
                    } else if (date.equals(NO_DATE)) {
                        if (cmd.equals(COMMAND_EVENT)) {
                            DobbyChat.noEventDate();
                        } else {
                            DobbyChat.noDeadlineDate();
                        }
                        //user entered everything correctly
                    } else if (desc.equals(NO_DESCRIPTION)) {
                        DobbyChat.noTaskDesc();
                    } else {
                        if (cmd.equals(COMMAND_DEADLINE)) {
                            Deadline newDeadline = new Deadline(desc, date);
                            dl.add(newDeadline);
                            DobbyChat.added(newDeadline, dl);
                            DobbyStorage.save(dl, Dobby.getFilePath());
                        } else {
                            Event newEvent = new Event(desc, date);
                            dl.add(newEvent);
                            DobbyChat.added(newEvent, dl);
                            DobbyStorage.save(dl, Dobby.getFilePath());
                        }
                    }
                }
            }
            //only when there is no space behind "deadline", else is handled above
        } catch (StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskDesc();
        } catch (NullPointerException e) {
            DobbyChat.noTaskDesc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
