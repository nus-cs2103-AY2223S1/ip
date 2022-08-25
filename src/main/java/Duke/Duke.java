package Duke;

import java.util.Scanner;

public class Duke {
    public static String taskDataPath = "data/duke.txt";

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        UI UI = new UI();
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskDataPath);

        boolean stopLish = false;
        UI.printResponse(UI.greeting);
        storage.readTaskData(taskList);

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
                        throw new DukeException("Duke.Task description cannot be empty!");
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
                            newTask = parser.generateToDoFromInput(input);
                        } else if (commands[0].equals("deadline")) {
                            newTask = parser.generateDeadlineFromInput(input);
                        } else if (commands[0].equals("event")) {
                            newTask = parser.generateEventFromInput(input);
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

        storage.updateTaskData(taskList);
    }
}
