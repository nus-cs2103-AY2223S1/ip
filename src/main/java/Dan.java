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
            TaskList tasks = new TaskList(tlr.readTaskListFromFile());
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
                        printLine();
                        printIndent("Here are the tasks in your list:");
                        tasks.showTasks();
                        printLine();
                        break;

                    case "mark":
                        tasks.markTask(Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "unmark":
                        tasks.unMarkTask(Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "delete":
                        tasks.deleteTask(Integer.parseInt(input.split(" ")[1]));
                        break;

                    case "todo":
                        //fall through
                    case "deadline":
                        //fall through
                    case "event":
                        tasks.addTask(input);
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




}
