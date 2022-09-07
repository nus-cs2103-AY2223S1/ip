package hazell;

import hazell.exceptions.HazellException;
import hazell.exceptions.UnknownCommand;

public class Dispatcher {
    public Dispatcher() { }

    public void handle(Command command, Ui ui, TaskList taskList) throws HazellException {
        if (command.startsWith("bye")) {
            ui.reply("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (command.startsWith("list")) {
            ui.reply(String.format("Here are the tasks in your list:\n%s", taskList));
        } else if (command.startsWith("mark")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            String response = taskList.markTaskAsDone(index);
            ui.reply(response);
        } else if (command.startsWith("unmark")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            String response = taskList.markTaskAsUndone(index);
            ui.reply(response);
        } else if (command.startsWith("todo")) {
            String description = String.join(" ", command.getTrailingArgs());
            String response = taskList.addTask(ToDo.create(description));
            ui.reply(response);
        } else if (command.startsWith("deadline")) {
            String description = String.join(" ", command.getTrailingArgs());
            String time = command.getKwarg("by");
            String response = taskList.addTask(Deadline.create(description, time));
            ui.reply(response);
        } else if (command.startsWith("event")) {
            String description = String.join(" ", command.getTrailingArgs());
            String time = command.getKwarg("at");
            String response = taskList.addTask(Event.create(description, time));
            ui.reply(response);
        } else if (command.startsWith("delete")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            String response = taskList.deleteTask(index);
            ui.reply(response);
        } else if (command.startsWith("find")) {
            String keyword = command.getTrailingArgs().get(0);
            String response = taskList.findMatchingTasks(keyword);
            ui.reply(response);
        } else {
            throw new UnknownCommand();
        }
    }
}
