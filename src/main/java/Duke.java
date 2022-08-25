import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    public static ToDo generateToDoFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        for (int i = 1; i < commands.length; ++i) {
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);
        return new ToDo(description);
    }

    public static Deadline generateDeadlineFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return  new Deadline(description, timeQualifier, timeDescription);
    }

    public static Event generateEventFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return new Event(description, timeQualifier, timeDescription);
    }

    public static String taskDataPath = "data/duke.txt";

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        UI UI = new UI();
        TaskList taskList = new TaskList(taskDataPath);

        boolean stopLish = false;
        UI.printResponse(UI.greeting);

        while (!stopLish) {
            String input = sc.nextLine();

            switch (input) {
                case "":
                    break;
                case "bye":
                    UI.printResponse("See you next time!");
                    stopLish = true;
                    break;
                case "list":
                    taskList.printTaskList();
                    break;
                default:
                    try {
                        String[] commands = input.split(" ");

                        if (commands.length < 2) {
                            throw new DukeException("Task description cannot be empty!");
                        }

                        if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                            int index = Integer.parseInt(commands[1]) - 1;

                            if (commands[0].equals("mark")) {
                                taskList.markTaskAsDone(index);
                            } else if (commands[0].equals("unmark")) {
                                taskList.markTaskAsNotDone(index);
                            } else if (commands[0].equals("delete")) {
                                taskList.deleteTask(index);
                            } else {
                                throw new DukeException("I do not understand that command :(");
                            }
                        } else {
                            Task newTask;
                            if (commands[0].equals("todo")) {
                                newTask = generateToDoFromInput(input);
                            } else if (commands[0].equals("deadline")) {
                                newTask = generateDeadlineFromInput(input);
                            } else if (commands[0].equals("event")) {
                                newTask = generateEventFromInput(input);
                            } else {
                                throw new DukeException("I do not understand that command :(");
                            }

                            taskList.add(newTask);
                        }
                    } catch (DukeException e) {
                        UI.printResponse(e.toString());
                    }

            }
        }

        taskList.updateTaskData();
    }
}
