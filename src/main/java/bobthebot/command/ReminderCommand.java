package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.LanguageBank;
import bobthebot.utils.Ui;

/**
 * Reminder Command class representing deadline command executed by the user.
 */
public class ReminderCommand extends Command {
    private ToDoList list;

    /**
     * Constructs reminder command.
     * */
    public ReminderCommand(ToDoList list) {
        super("reminder");
        this.list = list;
    }

    /**
     * Executes the reminder command by searching the list for the tasks due roon.
     *      Gives the user information about the tasks due soon.
     *
     * @return String representing the tasks due soon.
     */
    @Override
    public String execute() {
        ToDoList tasksDueSoon = list.tasksDueSoon();
        String response = "";

        if (tasksDueSoon.getLength() == 0) {
            response = LanguageBank.REMINDER_NO_UPCOMING_TASKS_MESSAGE;
            return response;
        }

        response = LanguageBank.REMINDER_UPCOMING_TASKS_MESSAGE;
        response += tasksDueSoon.toString();
        return response;
    }
}
