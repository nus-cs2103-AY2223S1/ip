import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {

    private static List<Task> taskList;
    private static File saveFile;

    private enum validInput {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT

    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to UNC\n");

        taskList = new ArrayList<>();
        saveFile = new File("./data/save.txt");
        try {
            Scanner scanner1 = new Scanner(saveFile);
            while (scanner1.hasNext()) {
                String[] oldTask = scanner1.nextLine().split("///", 3);
                // Task type saved in the third parameter
                switch (oldTask[0]) {
                    case "T":
                        taskList.add(new Todo(oldTask[1]));
                        break;
                    case "D":
                        try {
                            taskList.add(new Deadline(oldTask[1], oldTask[2]));
                        } catch (UncException uncException) {
                            System.out.println("Error creating saved list.");
                        }
                        break;
                    case "E":
                        try {
                            taskList.add(new Event(oldTask[1], oldTask[2]));
                        } catch (UncException uncException) {
                            System.out.println("Error creating saved list.");
                        }
                        break;
                    default:

                }
            }
        } catch (FileNotFoundException e) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println(ioException);
            } catch (SecurityException securityException) {
                System.out.println(securityException);
            }
            System.out.println("Starting new file.");
        }


        String input;
        Scanner scanner = new Scanner(System.in);
        while(!Objects.equals(input = scanner.nextLine(), "bye")) {
            String[] words = input.split("\\s+", 2);
            try {
                validInput verb = validInput.valueOf(words[0].toUpperCase());
                switch (verb) {
                case LIST:
                    displayList();
                    break;
                case MARK:
                    mark(Integer.parseInt(words[1]));
                    break;
                case UNMARK:
                    unmark(Integer.parseInt(words[1]));
                    break;
                case DELETE:
                    delete(Integer.parseInt(words[1]));
                    break;
                case TODO:
                    try {
                        addToDo(words[1]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new UncException("You need something to do for Todo.");
                    }
                    break;
                case DEADLINE:
                    addDeadline(words[1]);
                    break;
                case EVENT:
                    addEvent(words[1]);
                    break;
                }
            } catch (UncException uncException) {
                System.out.println(uncException);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Invalid command.");
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        System.out.println("Bye");
        scanner.close();
        saveFile();
    }

    private static void displayList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /*
    private static void addToList(String input) {
        System.out.println("added: " + input);
        taskList.add(new Task(input));
    }

    */

    private static void addToDo(String input) throws UncException {
        Todo newTodo = new Todo(input);
        taskList.add(newTodo);
        System.out.println("added: \n " + newTodo + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void addDeadline(String input) throws UncException {
        String[] phrases = input.split(" /by ", 2);
        Deadline newDeadline = new Deadline(phrases[0], phrases[1]);
        taskList.add(newDeadline);
        System.out.println("added: \n " + newDeadline + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void addEvent(String input) throws UncException {
        String[] phrases = input.split(" /at ", 2);
        Event newEvent = new Event(phrases[0], phrases[1]);
        taskList.add(newEvent);
        System.out.println("added: \n " + newEvent + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void mark(int index) {
        taskList.get(index - 1).markAsDone();
        System.out.println("Marked as done: \n" + taskList.get(index - 1));
    }



    private static void unmark(int index) {
        taskList.get(index - 1).markAsNotDone();
        System.out.println("Marked as not done: \n" + taskList.get(index - 1));
    }

    private static void delete(int index) {
        System.out.println("Deleted: \n" + taskList.get(index -1) + "\nNow you have " + (taskList.size() - 1) +
                " tasks on the list.");
        taskList.remove(index -1);

    }

    private static void saveFile() {
        try {
            FileWriter writer = new FileWriter(saveFile);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toStorageString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }
}
