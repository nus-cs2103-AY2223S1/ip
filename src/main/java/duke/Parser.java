package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /** Returns String date in a nicer format.
     *
     * @param date
     * @return String date in nicer format
     */
    public String getDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public boolean parse(String fullCommand, Ui ui, TaskList taskList, Storage storage) {
        String[] commandBreakdown = fullCommand.split(" ");
        String command = commandBreakdown[0];

        switch (command) {
        case "list":
            ui.printTasks(taskList);
            break;
        case "mark":
            int taskNo = Integer.valueOf(commandBreakdown[1])-1;
            Task task = taskList.getTask(taskNo);
            taskList.getTask(taskNo).markDone();
            ui.printMarked(task);
            break;
        case "unmark":
            taskNo = Integer.valueOf(commandBreakdown[1])-1;
            task = taskList.getTask(taskNo);
            taskList.getTask(taskNo).markUndone();
            ui.printUnmarked(task);
            break;
        case "bye":
            ui.printBye();
            storage.write(taskList);
            return true;
        case "todo":
            String todoName = "";
            if (commandBreakdown.length == 1) {
                ui.printDukeException(new EmptyTodoException());
                break;
            }
            for (int i = 1; i < commandBreakdown.length; i++) {
                todoName = todoName + commandBreakdown[i] + " ";
            }
            ToDo todo = new ToDo(todoName);
            taskList.addTask(todo);
            ui.printTaskAdded(todo, taskList);
            break;
        case "deadline":
            String[] deadlineSplit = fullCommand.split(" /by ");
            String formattedDate = this.getDate(deadlineSplit[1]);
            Deadline deadline = new Deadline(deadlineSplit[0].substring(9, deadlineSplit[0].length()), formattedDate);
            taskList.addTask(deadline);
            ui.printTaskAdded(deadline, taskList);
            break;
        case "event":
            String[] eventSplit = fullCommand.split(" /at ");
            formattedDate = this.getDate(eventSplit[1]);
            Event event = new Event(eventSplit[0].substring(6, eventSplit[0].length()), formattedDate);
            taskList.addTask(event);
            ui.printTaskAdded(event, taskList);
            break;
        case "delete":
            int indToDelete = Integer.valueOf(commandBreakdown[1]) - 1;
            Task toDelete = taskList.getTask(indToDelete);

            taskList.remove(indToDelete);
            ui.printDelete(toDelete, taskList);
            break;
        default:
            ui.printDukeException(new UnknownCommandException());
        }

        return false;
    }
}
