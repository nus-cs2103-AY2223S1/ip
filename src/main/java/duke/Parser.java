package duke;

import java.util.Scanner;

/**
 * Parser Class to represent a class that will hold all the Parse methods
 * @author amresh A0235398R
 */
public class Parser {

    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructor for Parser Class to create Parse object
     * @param tasks TaskList object
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Method that runs to parse userInput when using CLI
     */
    public void parseFunc() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String printStatement;

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();

            if (userInput.equals("list")) {
                printStatement = tasks.list();
                ui.print(printStatement);
                continue;
            }
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.split(" ", 2)[0].equals("mark")) {
                int inputTaskIndex = getTaskIndex(userInput);
                printStatement = tasks.mark(inputTaskIndex);
                ui.print(printStatement);
                continue;
            }
            if (userInput.split(" ", 2)[0].equals("unmark")) {
                int inputTaskIndex = getTaskIndex(userInput);
                printStatement = tasks.unmark(inputTaskIndex);
                ui.print(printStatement);
                continue;
            }
            if (userInput.split(" ", 2)[0].equals("delete")) {
                int inputTaskIndex = getTaskIndex(userInput);
                printStatement = tasks.deleteTask(inputTaskIndex);
                ui.print(printStatement);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("find")) {
                String search = userInput.split(" ", 2)[1];
                printStatement = tasks.find(search);
                ui.print(printStatement);
                continue;
            }

            try {
                tasks.userInputCheck(userInput);

                if (userInput.split(" ", 2)[0].equals("todo")) {
                    String taskInput = userInput.split(" ", 2)[1];
                    printStatement = tasks.todo(taskInput);
                    ui.print(printStatement);
                    continue;
                }
                if (userInput.split(" ", 2)[0].equals("deadline")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String by = userInput.split("/", 2)[1].split(" ", 2)[1];
                    printStatement = tasks.deadline(taskInput, by);
                    ui.print(printStatement);
                    continue;
                }
                if (userInput.split(" ", 2)[0].equals("event")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
                    printStatement = tasks.event(taskInput, duration);
                    ui.print(printStatement);
                    continue;
                }

            } catch (DukeException err) {
                System.out.println(err.getMessage());
                continue;
            }
            printStatement = tasks.addTask(userInput);
            ui.print(printStatement);
        }
    }

    /**
     * Method that runs to parse UserInput when GUI is being used
     * @param userInput userInput that is parsed
     * @return String that will be printed on the GUI
     */
    public String guiParseFunc(String userInput) {
        if (userInput.equals("list")) {
            return tasks.list();
        }
        if (userInput.equals("bye")) {
            return ui.farewell();
        }
        if (userInput.split(" ", 2)[0].equals("mark")) {
            int inputTaskIndex = getTaskIndex(userInput);
            return tasks.mark(inputTaskIndex);

        }
        if (userInput.split(" ", 2)[0].equals("unmark")) {
            int inputTaskIndex = getTaskIndex(userInput);
            return tasks.unmark(inputTaskIndex);
        }
        if (userInput.split(" ", 2)[0].equals("delete")) {
            int inputTaskIndex = getTaskIndex(userInput);
            return tasks.deleteTask(inputTaskIndex);
        }
        if(userInput.split(" ", 2)[0].equals("find")) {
            String search = userInput.split(" ", 2)[1];
            return tasks.find(search);
        }

        try {
            tasks.userInputCheck(userInput);

            if (userInput.split(" ", 2)[0].equals("todo")) {
                String taskInput = userInput.split(" ", 2)[1];
                return tasks.todo(taskInput);

            }
            if (userInput.split(" ", 2)[0].equals("deadline")) {
                String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                String by = userInput.split("/", 2)[1].split(" ", 2)[1];
                return tasks.deadline(taskInput, by);
            }
            if (userInput.split(" ", 2)[0].equals("event")) {
                String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
                return tasks.event(taskInput, duration);
            }

        } catch (DukeException err) {
            return err.getMessage();
        }
        return tasks.addTask(userInput);
    }

    private static int getTaskIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
    }


}
