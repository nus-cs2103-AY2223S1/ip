package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles creating commands through duke.CommandMatcher/duke.PrefixCommandMatcher.
 */
public class Parser {

    private static String[] listTasks(String response, List<Task> tasks) {
        String[] output = new String[tasks.size() + 1];
        output[0] = response;
        for (int i = 0; i < tasks.size(); i++) {
            output[i + 1] = (i + 1) + "." + tasks.get(i).toString();
        }
        return output;
    }

    /**
     * Gets the chatbot commands in an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> getCommands() {
        ArrayList<CommandMatcher> commands = new ArrayList<>();

        commands = addTaskCommands(commands);
        commands = addTaskModificationCommands(commands);
        commands = addTaskViewingCommands(commands);

        commands = addCatchAllCommand(commands);

        return commands;
    }

    /**
     * Adds adding task commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskCommands(ArrayList<CommandMatcher> commands) {
        commands.add(new PrefixCommandMatcher("deadline", (str, map) -> {
            Task task = new Deadline(str, map.getOrDefault("by", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "Good luck with the deadline, here's the task:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("todo", (str, map) -> {
            Task task = new ToDo(str);
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "I've recorded this thing you need to do:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("event", (str, map) -> {
            Task task = new Event(str, map.getOrDefault("at", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "That's going to happen at some time later:",
                    task.toString());
        }));

        return commands;
    }

    /**
     * Adds task modification commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskModificationCommands(ArrayList<CommandMatcher> commands) {
        commands.add(PrefixCommandMatcher.of("mark", (str, map) -> {
            Task task = TaskList.getTask(str);
            task.markAsDone();
            return new DukeResponse(
                    "Marked your task as done:",
                    task.toString());
        }));

        commands.add(PrefixCommandMatcher.of("unmark", (str, map) -> {
            Task task = TaskList.getTask(str);
            task.markAsNotDone();
            return new DukeResponse(
                    "Aw... it's not done yet:",
                    task.toString());
        }));

        commands.add(PrefixCommandMatcher.of("delete", (str, map) -> {
            Task task = TaskList.getTask(str);
            TaskList.getTaskList().remove(task);
            return new DukeResponse(
                    "It seems you didn't need this task anymore, so I removed it:",
                    task.toString(),
                    String.format("You have %d tasks left.", TaskList.getTaskList().size()));
        }));

        return commands;
    }

    /**
     * Adds task viewing commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskViewingCommands(ArrayList<CommandMatcher> commands) {
        commands.add(new CommandMatcher((str) -> str.equals("list"), (str) -> {
            List<Task> tasks = TaskList.getTaskList();
            return new DukeResponse(
                    listTasks("Here, your tasks:", tasks));
        }));

        commands.add(new PrefixCommandMatcher("find", (str, map) -> {
            List<Task> tasks = TaskList.filterTasks(str);
            return new DukeResponse(
                    listTasks("Here are the tasks that you might be looking for:", tasks));
        }));

        return commands;
    }

    /**
     * Adds the command that runs otherwise
     * to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addCatchAllCommand(ArrayList<CommandMatcher> commands) {
        commands.add(
                new CommandMatcher((str) -> true, (str) -> new DukeResponse(
                "(>.<') I'm sorry, I don't really know what that means.")));
        return commands;
    }
}
