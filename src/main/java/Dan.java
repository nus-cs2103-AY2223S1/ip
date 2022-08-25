import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Dan {
    private static final int MAX_TASK_SIZE = 100;
    private static final String DATA_FILE = "TestData1";

    public static void main(String[] args) {
        //start up sequence
        Scanner sc = new Scanner(System.in);
        TaskListReader tlr = new TaskListReader(DATA_FILE);
        try {
            List<Task> tasks = tlr.readTaskListFromFile();
            greet();

            while (true) {
                String input = sc.nextLine().strip();
                String action = input.split(" ")[0];
                try {
                    switch (action) {
                    case "bye":
                        sayonara();
                        return;

                    case "list":
                        showTasks(tasks);
                        break;

                    case "mark":
                        markTask(tasks, Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "unmark":
                        unMarkTask(tasks, Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "delete":
                        deleteTask(tasks, Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "todo":
                        //fall through
                    case "deadline":
                        //fall through
                    case "event":
                        addTask(tasks, input);
                        break;

                    default:
                        throw new DanException("I don't really understand what do you mean by that...");
                    }

                } catch (DanException e) {
                    printBlock(e.getMessage());
                } catch (NumberFormatException nfe) {
                    printBlock("Please use an integer instead");
                } catch (DateTimeParseException dte) {
                    printBlock("Please use the format dd/MM/yyyy HHmm for dates");
                }

                try {
                    tlr.writeTaskListToFile(tasks);
                } catch (IOException ioe) {
                    printIndent(ioe.getMessage() + "Error when creating saving to data file");
                }
            }
        } catch (NoSuchFileException e) {
            try {
                printIndent("Task list data not found, creating new data file...");
                tlr.createFile();
                printIndent(String.format("Data file created at %s\n Please start me again.", tlr.getPath()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                printIndent("Error when creating new data file");
            }
        } catch (IOException ioe) {
           ioe.printStackTrace();
           printIndent("Error when reading current data file");
        }
        // write list every iteration

    }


    public static void printIndent(String s) {
        System.out.println("    " + s);
    }

    public static void printLine() {
        printIndent("____________________________________________________________");
    }

    public static void printBlock(String s) {
        printLine();
        printIndent(s);
        printLine();
    }

    public static void greet() {
        printLine();
        printIndent("Hello from\n");
        String logo = "_ .-') _     ('-.         .-') _  \n"
                + "( (  OO) )   ( OO ).-.    ( OO ) ) \n"
                + " \\     .'_   / . --. /,--./ ,--,'  \n"
                + " ,`'--..._)  | \\-.  \\ |   \\ |  |\\  \n"
                + " |  |  \\  '.-'-'  |  ||    \\|  | ) \n"
                + " |  |   ' | \\| |_.'  ||  .     |/  \n"
                + " |  |   / :  |  .-.  ||  |\\    |   \n"
                + " |  '--'  /  |  | |  ||  | \\   |   \n"
                + " `-------'   `--' `--'`--'  `--'   \n";

        printIndent(logo + "Ouuuuuuuuuhhhhhh Spo0ky");
        printIndent("What can I do for you?");
        printLine();
    }

    public static void sayonara() {
        printBlock("Boo! Bye bye... :(");
    }

    public static void addTask(List<Task> tasks, String input) throws DanException {
        String description;
        String dateString;
        if (input.startsWith("todo")) {
            description = input.replace("todo", "").strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your todo item");
            }
            tasks.add(new ToDo(description));

        } else if (input.startsWith("deadline")) {
            String[] temp = input.replace("deadline","").strip().split("/by");
            if (temp.length != 2) {
                throw new DanException("Please follow the following format:\n deadline <description> /by <due date>");
            }
            description = temp[0].strip();
            dateString = temp[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your deadline");
            }
            tasks.add(new Deadline(description, dateString));

        } else if (input.startsWith("event")) {
            String[] temp = input.replace("event", "").strip().split("/at");
            if (temp.length != 2) {
                throw new DanException("Please follow the following format:\n event <description> /at <time/date>");
            }
            description = temp[0].strip();
            dateString = temp[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your event");
            }
            tasks.add(new Event(description, dateString));
        }
        printLine();
        printIndent("Okay okay, I'll add this task then:");
        printIndent(tasks.get(tasks.size() -1).toString());
        printIndent(String.format("You now have %d many tasks in your list", tasks.size()));
        printLine();
    }

    public static void showTasks(List<Task> tasks) throws DanException {
        if (tasks.isEmpty()) {
            throw new DanException("Your list is empty!");
        }
        printLine();
        printIndent("Here are the tasks in your list:");
        for (int i =1; i <= tasks.size(); i++) {
            printIndent(i + "." + tasks.get(i - 1));
        }
        printLine();
    }

    public static void markTask(List<Task> tasks, int index) throws DanException {
        if (index > tasks.size()) {
            printIndent("tasks.size(): " + tasks.size());
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(true);
        printBlock(String.format("Hehe okay guess this is now done\n"
                + "  %s", task));
    }

    public static void unMarkTask(List<Task> tasks, int index) throws DanException {
        if (index > tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(false);
        printBlock(String.format("Ooops, you haven't done this yet? Here ya go:\n"
                + "  %s", task));
    }

    public static void deleteTask(List<Task> tasks, int index) throws DanException {
        if (index >= tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        printLine();
        printIndent("Alright then, I'll remove this task from your list:");
        printIndent(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        printIndent(String.format("You now have %d many tasks in your list", tasks.size()));
        printLine();
    }
}
