import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList  = new ArrayList<Task>();
    private static final File FILE_PATH = new File("./data/duke.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        welcome();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                processCommand(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                input = sc.nextLine();
            }
        }
        storeData();
        terminate();
    }

    private static void welcome() {
        System.out.println("Hello!\n" +
                "How may i help you today?");
    }

    private static void ReadData() {
        try {
            Scanner fileScanner = new Scanner(FILE_PATH);
            while (fileScanner.hasNextLine()) {
                String[] storedInfo = fileScanner.nextLine().split(" | ");
                String type = storedInfo[0];
                boolean isDone = storedInfo[1].equals("O") ? true : false;
                String description = storedInfo[2];
                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, storedInfo[3]);
                    break;
                case "E":
                    task = new Event(description, isDone, storedInfo[3]);
                    break;
                default:
                    throw new DukeException("No save data found");
                }
                taskList.add(task);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void terminate() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void processCommand(String input) throws DukeException {
        String[] commandAndDescription = input.split(" ", 2);
        String command = commandAndDescription[0];

        switch (command) {
        case "list" :
            listDisplay(taskList);
            break;
        case "mark" :
            markAsDone(commandAndDescription);
            break;
        case "unmark" :
            markAsUndone(commandAndDescription);
            break;
        case "delete" :
            deleteTask(commandAndDescription);
            break;
        case "deadline" :
            createDeadline(commandAndDescription);
            break;
        case "event" :
            createEvent(commandAndDescription);
            break;
        case "todo" :
            createToDo(commandAndDescription);
            break;
        default:
            throw new DukeException("☹ OOPS!!! " +
                    "I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void listDisplay(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, list.get(i)));
            }
        }
    }

    private static void markAsDone(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of mark cannot be empty.");
        }
        int indexToMark = Integer.parseInt(commandAndDescription[1]) - 1;
        taskList.get(indexToMark).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                String.format("%s", taskList.get(indexToMark)));
    }

    private static void markAsUndone(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of unmark cannot be empty.");
        }
        int indexToUnmark = Integer.parseInt(commandAndDescription[1]) - 1;
        taskList.get(indexToUnmark).markAsUndone();
        System.out.println("Ok! I've marked this task as not done yet:\n" +
                String.format("%s", taskList.get(indexToUnmark)));
    }

    private static void deleteTask(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of delete cannot be empty.");
        }
        int indexToDelete = Integer.parseInt(commandAndDescription[1]) - 1;
        System.out.println("Noted. I've removed this task:\n" +
                String.format("\t%s", taskList.get(indexToDelete)));
        taskList.remove(indexToDelete);
        listStatus(taskList);
    }

    private static void createDeadline(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of deadline cannot be empty.");
        }
        String[] descAndDue = commandAndDescription[1].split("/by ");
        if (descAndDue.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "There is no specified date/time for the deadline.");
        }
        System.out.println("Got it. I've added this task:");
        taskList.add(new Deadline(descAndDue[0], false, descAndDue[1]));
        System.out.println(String.format("\t%s",
                taskList.get(taskList.size() - 1)));
        listStatus(taskList);
    }

    private static void createEvent(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of event cannot be empty.");
        }
        String[] descAndDuration = commandAndDescription[1].split("/at ");
        if (descAndDuration.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "There is no specified time duration for the event.");
        }
        System.out.println("Got it. I've added this task:");
        taskList.add(new Event(descAndDuration[0], false, descAndDuration[1]));
        System.out.println(String.format("\t%s",
                taskList.get(taskList.size() - 1)));
        listStatus(taskList);
    }

    private static void createToDo(String[] commandAndDescription) throws DukeException {
        if (commandAndDescription.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of todo cannot be empty.");
        }
        System.out.println("Got it. I've added this task:");
        taskList.add(new ToDo(commandAndDescription[1], false));
        System.out.println(String.format("\t%s",
                taskList.get(taskList.size() - 1)));
        listStatus(taskList);
    }
    
    private static void listStatus(ArrayList<Task> list) {
        System.out.println(String.format("Now you have %d tasks in the list.",
                list.size()));
    }

    private static void storeData() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                String dataToSave = task.saveData();
                fw.write(dataToSave);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Data cannot be stored");
        }
    }
}
