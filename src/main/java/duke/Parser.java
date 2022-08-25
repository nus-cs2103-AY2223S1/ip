package duke;

import java.time.LocalDate;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSegments = fullCommand.split(" ", 2);
        String mainCommand = commandSegments[0].toLowerCase().trim();

        switch (mainCommand) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "todo":
            return new TodoCommand(commandSegments[1].trim());
        case "deadline":
            String[] deadlineSegments = commandSegments[1].split("/by", 2);
            return new DeadlineCommand(deadlineSegments[0], LocalDate.parse(deadlineSegments[1].trim()));
        case "event":
            String[] eventSegments = commandSegments[1].split("/at", 2);
            return new EventCommand(eventSegments[0], eventSegments[1]);
        case "mark":
            return new MarkCommand(Integer.parseInt(commandSegments[1].trim()));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(commandSegments[1].trim()));
        case "delete":
            return new DeleteCommand(Integer.parseInt(commandSegments[1].trim()));
        default:
            throw new DukeException(mainCommand + "? (´･_･`) I don't know what that means\n");
        }


        /*if (inputIsValid) {
            String[] segments = input.split(" ");

            if (input.equalsIgnoreCase("list")) {
                ui.printMessage(ui.listToString(list));
            } else if (input.equalsIgnoreCase("bye")) {
                storage.saveTasksInStorage(list);
                ui.printMessage("Gone so soon? Bye (._.)\n");
            } else if (input.toLowerCase().startsWith("unmark")) {
                duke.task.Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                curr.markAsUndone();
                ui.printMessage("[ ] I've marked this task as not done yet:\n" +curr + "\n");
            } else if (input.toLowerCase().startsWith("mark")) {
                duke.task.Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                curr.markAsDone();
                ui.printMessage("[X] You've completed a task!\n" + curr + "\n");
            } else if (input.toLowerCase().startsWith("delete")) {
                duke.task.Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                list.remove(Integer.parseInt(segments[1]) - 1);
                ui.printMessage(" - Removed this task:\n" +curr + "\nNow you have " + list.size()
                        + " tasks in the list\n");
            } else if (input.toLowerCase().startsWith("todo")) {
                String description = input.substring(5);
                duke.task.Todo todo = new duke.task.Todo(description);
                list.add(todo);
//                ui.printMessage("+ Added this todo:\n" + todo + "\nNow you have " + list.size()
//                        + " tasks in the list\n");
            } else if (input.toLowerCase().startsWith("deadline")) {
                String description = input.substring(9, input.indexOf(" /"));
                String by = input.substring(input.indexOf("/by") + 3);
                by = by.replaceAll("\\s+","");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
                try {
                    LocalDate date = LocalDate.parse(by);
                    duke.task.Deadline deadline = new duke.task.Deadline(description, date);
                    list.add(deadline);
                    ui.printMessage("+ Added this deadline:\n" + deadline + "\nNow you have " + list.size()
                            + " tasks in the list\n");
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }

            } else if (input.toLowerCase().startsWith("event")){
                String description = input.substring(6, input.indexOf(" /"));
                String at = input.substring(input.indexOf("/at") + 3);
                duke.task.Event event = new duke.task.Event(description, at);
                list.add(event);
                ui.printMessage("+ Added this event:\n" + event + "\nNow you have " + list.size()
                        + " tasks in the list\n");
            }
        }*/
    }
}
