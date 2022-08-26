package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles creating commands through duke.CommandMatcher/duke.PrefixCommandMatcher.
 */
public class Parser {

    public static ArrayList<CommandMatcher> getCommands() {
        ArrayList<CommandMatcher> commands = new ArrayList<>();

        commands.add(new CommandMatcher((str) -> str.equals("list"), (str) -> {
            List<Task> list = TaskList.getTaskList();
            String[] output = new String[list.size() + 1];
            output[0] = "Here, your tasks:";
            for (int i = 0; i < list.size(); i++) {
                output[i + 1] = (i + 1) + "." + list.get(i).toString();
            }
            Ui.printStyledMessage(output);
        }));

        commands.add(new PrefixCommandMatcher("mark", (str, map) -> {
            TaskList.getTask(str).ifPresent((task) -> {
                task.markAsDone();
                Ui.printStyledMessage("Marked your task as done:",
                        task.toString());
            });
        }));

        commands.add(new PrefixCommandMatcher("unmark", (str, map) -> {
            TaskList.getTask(str).ifPresent((task) -> {
                task.markAsNotDone();
                Ui.printStyledMessage("Aw... it's not done yet:",
                        task.toString());
            });
        }));

        commands.add(new PrefixCommandMatcher("deadline", (str, map) -> {
            Task task = new Deadline(str, map.getOrDefault("by", "[unknown]"));
            TaskList.getTaskList().add(task);
            Ui.printStyledMessage("Good luck with the deadline, here's the task:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("todo", (str, map) -> {
            Task task = new ToDo(str);
            TaskList.getTaskList().add(task);
            Ui.printStyledMessage("I've recorded this thing you need to do:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("event", (str, map) -> {
            Task task = new Event(str, map.getOrDefault("at", "[unknown]"));
            TaskList.getTaskList().add(task);
            Ui.printStyledMessage("That's going to happen at some time later:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("delete", (str, map) -> {
            TaskList.getTask(str).ifPresent((task) -> {
                TaskList.getTaskList().remove(task);
                Ui.printStyledMessage("It seems you didn't need this task anymore, so I removed it:",
                        task.toString(),
                        String.format("You have %d tasks left.", TaskList.getTaskList().size()));
            });
        }));

        // default command matcher - add to list
        commands.add(new CommandMatcher((str) -> true, (str) -> {
            Ui.printStyledMessage("(>.<') I'm sorry, I don't really know what that means.");
        }));

        return commands;
    }
}
