package duke;

import java.util.Scanner;

/**
 * The main class of the Duke application.
 *
 * @author Elbert Benedict
 */
public class Duke {
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the main logic of the Duke application.
     *
     * @param args not important, ignore.
     */
    public static void main(String[] args) {
        TaskList taskList;
        try {
            taskList = Storage.getSavedTasks();
        } catch (DukeException e) {
            taskList = new TaskList();
            Ui.printDukeError(e);
        }

        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            try {
                String input = scanner.nextLine();
                String[] parsed = Parser.parseUserInput(input);
                //command is first word of the input
                String command = parsed[0];
                int index;
                Task task;

                switch (command) {
                case "todo":
                    String taskString = parsed[1];
                    task = new ToDo(taskString);
                    taskList.add(task);
                    Storage.saveTasks(taskList);
                    Ui.printTaskAdded(task, taskList.getSize());
                    break;

                case "deadline":
                    String deadlineTask = parsed[1];
                    String deadlineDate = parsed[2];
                    task = new Deadline(deadlineTask, deadlineDate);
                    taskList.add(task);
                    Storage.saveTasks(taskList);
                    Ui.printTaskAdded(task, taskList.getSize());
                    break;

                case "event":
                    String eventString = parsed[1];
                    String eventDate = parsed[2];
                    task = new Event(eventString, eventDate);
                    taskList.add(task);
                    Storage.saveTasks(taskList);
                    Ui.printTaskAdded(task, taskList.getSize());
                    break;

                case "mark":
                    index = Integer.parseInt(parsed[1]);
                    taskList.mark(index);
                    break;

                case "unmark":
                    index = Integer.parseInt(parsed[1]);
                    taskList.unmark(index);
                    break;

                case "delete":
                    index = Integer.parseInt(parsed[1]);
                    taskList.delete(index);
                    break;

                case "find":
                    String keyword = parsed[1];
                    TaskList filtered = taskList.filter(keyword);
                    Ui.printFilteredTasks(filtered);
                    break;

                case "bye":
                    Ui.printGoodbyeMessage();
                    shouldContinue = false;
                    break;

                case "list":
                    Ui.printTaskList(taskList);
                    break;

                default:
                    throw new DukeException("Command Not Found!");
                }
            } catch (DukeException e) {
                Ui.printDukeError(e);
            }
        }

    }
}
