import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors

public class Duke {
    private static TaskList itemList = new TaskList();

    public static void runDuke() {
        Scanner sc= new Scanner(System.in);
        int index;
        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            try {
                String[] words = command.split(" ", 2);
                String[] taskName = command.split("/");

                switch (words[0]) {
                    // Exit
                    case "bye":
                        carryOn = false;
                        System.out.print("Bye. Hope to see you again soon!");
                        break;
                    // List out items
                    case "list":
                        System.out.println(itemList);
                        command = sc.nextLine();
                        break;
                    case "delete":
                        itemList.deleteTask(words[1]);
                        command = sc.nextLine();
                        break;
                    // mark items
                    case "mark":
                        index = Integer.parseInt(words[1]) - 1;
                        itemList.markTask(index);
                        command = sc.nextLine();
                        break;
                    // unmark items
                    case "unmark":
                        index = Integer.parseInt(words[1]) - 1;
                        itemList.unmarkTask(index);
                        command = sc.nextLine();
                        break;
                    case "todo":
                        if (words.length > 1) {
                            Task toAdd = new ToDo(taskName[0].substring(5));
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        } else {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        if (words.length == 1) {
                            throw new DukeException("OOPS!!! The description of a Deadline cannot be empty.");
                        } else if (taskName.length == 1 || taskName[1].length() < 3) {
                            throw new DukeException("OOPS!!! The time and date of the Deadline cannot be empty.");
                        } else {
                            Task toAdd = new Deadline(taskName[0].substring(9), taskName[1].substring(3));
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        }
                        break;
                    case "event":
                        if (words.length == 1) {
                            throw new DukeException("OOPS!!! The description of a Event cannot be empty.");
                        } else if (taskName.length == 1 || taskName[1].length() < 3) {
                            throw new DukeException("OOPS!!! The time and date of the Event cannot be empty.");
                        } else {
                            Task toAdd = new Event(taskName[0].substring(6), taskName[1].substring(3));
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        }
                        break;
                    // unrecognised commands
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                command = sc.nextLine();
            }
        }
    }

    public static void readFile(String fileLocation) throws FileNotFoundException {
            File dukeHistory = new File(fileLocation);
            Scanner myReader = new Scanner(dukeHistory);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                addToList(data);
            }
            myReader.close();
    }

    public static void addToList(String taskItem) {
        String[] details = taskItem.split("[|]");
        Task toAdd = new ToDo("");
        switch (details[0]) {
            case "T":
                toAdd = new ToDo(details[2]);
                break;
            case "D":
                toAdd = new Deadline(details[2], details[3]);
                break;
            case "E":
                toAdd = new Event(details[2], details[3]);
                break;
        }

        switch (details[1]) {
            case "0":
                toAdd.setStatusIcon(false);
                break;
            case "1":
                toAdd.setStatusIcon(true);
        }
        itemList.silentAdd(toAdd);
    }

    public static void makeFile() {
        try {
            File blankFile = new File("dukeHistory.txt");
            if (blankFile.createNewFile()) {
                System.out.println("File created: " + blankFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            readFile("dukeHistory.txt");
        } catch (FileNotFoundException e) {
            //make file
            System.out.println("An error occurred.");
            makeFile();
        } finally {
            runDuke();
        }

    }
}
