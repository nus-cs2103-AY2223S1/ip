import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    static boolean inProgress = true;

    public static void main(String[] args) throws IOException, DukeException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        loadTaskFromFile();

        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");

        while (inProgress) {
            String input = bufferedReader.readLine();
            inputProcessor(input);
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    private static void inputProcessor(String input) {
        String[] taskDetailsArr;
        String[] userInputArr = input.split(" ", 2);
        try {
            switch (userInputArr[0]) {
            case "list":
                listArrItems();
                break;
            case "bye":
                System.out.println("\tBye. Hope to see you again soon!");
                inProgress = false;
                break;
            case "mark":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! Please specify a task to mark.");
                }
                try {
                    markItem(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException e) {
                    throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                }
                break;
            case "unmark":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! Please specify a task to unmark.");
                }
                try {
                    unmarkItem(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException e) {
                    throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                }
                break;
            case "delete":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! Please specify a task to delete.");
                }
                try {
                    deleteTask(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException e) {
                    throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                }
                break;
            case "todo":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
                }
                addToTasks(new ToDo(userInputArr[1]));
                break;
            case "deadline":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! The date/time of a deadline cannot be empty.");
                }
                taskDetailsArr = userInputArr[1].split(" /by ");
                if (taskDetailsArr.length < 2) {
                    throw new DukeException("\tOOPS!!! The date/time of a deadline cannot be empty.");
                }
                addToTasks(new Deadline(taskDetailsArr[0], taskDetailsArr[1]));
                break;
            case "event":
                if (userInputArr.length < 2) {
                    throw new DukeException("\tOOPS!!! The date/time of a event cannot be empty.");
                }
                taskDetailsArr = userInputArr[1].split(" /at ");
                if (taskDetailsArr.length < 2) {
                    throw new DukeException("\tOOPS!!! The date/time of a event cannot be empty.");
                }
                addToTasks(new Event(taskDetailsArr[0], taskDetailsArr[1]));
                break;
            default:
                throw new DukeException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listArrItems() {
        // Corner case with empty list
        if (tasks.size() == 0) {
            System.out.println("\t" + "list is empty");
            return;
        }

        // Usual Path
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void addToTasks(Task task) throws DukeException {
        tasks.add(task);
        saveOneTaskToFile(task);
        System.out.println("\tGot it. I've added this task:\n" +
                "\t\t"+ task + "\n" +
                "\tNow you have " + tasks.size() + " tasks in your list.");
    }

    private static void deleteTask(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        System.out.println("\tNoted. I've removed this task:\n" +
                "\t\t"+ tasks.remove(index - 1) + "\n" +
                "\tNow you have " + tasks.size() + " tasks in your list.");
    }

    private static void markItem(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        tasks.get(index - 1).isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1));
    }

    private static void unmarkItem(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        tasks.get(index - 1).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1));
    }

    private static void loadTaskFromFile() throws DukeException, FileNotFoundException {
        File dataFile = new File(DATA_FILE_PATH);
        dataFile.getParentFile().mkdirs();
        try {
            if(dataFile.createNewFile()) {
                System.out.println("\tOOPS!!! I can't find the data file. I'll create a new one.");
            }
        } catch (IOException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I can't create the file.");
        }

        Scanner s = new Scanner(dataFile); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
//            String[] lineArr = line.split("\\[[a-zA-Z]]\\[[a-zA-Z]] [a-zA-Z]+ \\([^)]*\\)");
            System.out.println(line);
            char eventType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'D';
            String description;
            if (line.indexOf('(') == -1) {
                description = line.substring(7, line.length());
            } else {
                description = line.substring(7, line.indexOf('(') - 1);
            }
            switch (eventType) {
            case 'T':
                tasks.add(new ToDo(description, isDone));
                break;
            case 'D':
                String dateTime = line.substring(line.indexOf(':') + 2, line.indexOf(')'));
                tasks.add(new Deadline(description, dateTime, isDone));
                break;
            case 'E':
                dateTime = line.substring(line.indexOf(':') + 2, line.indexOf(')'));
                tasks.add(new Event(description, dateTime, isDone));
                break;
            default:
                throw new DukeException("\tOOPS!!! I'm sorry, there was an error reading saved file.");
            }
        }
    }

    private static void saveOneTaskToFile(Task newTask) throws DukeException {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true); // create a FileWriter in append mode
            fw.write(newTask.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I can't write to the file.");
        }
    }

}
