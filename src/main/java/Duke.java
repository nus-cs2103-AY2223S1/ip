import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean inNeed = true;
    private final ArrayList<Task> list = readFile();

    private final static String DEADLINE_SPLIT = " /by ";
    private final static String EVENT_SPLIT = " /at ";

    private void greet() {
        generateReply("Hello! I'm Sheep\n" +
                "What can I do for you?");
    }

    private void generateReply(String reply) {
        System.out.println("____________________________________________________________\n" +
                reply +
                "\n____________________________________________________________\n");
    }

    private static String[] splitString(String s, String regex) {
        return s.split(regex, 2);
    }

    private boolean checkCommandArgsLength(String[] command, int length) {
        if (command.length != length) {
            return false;
        } else {
            return true;
        }
    }

    private static ArrayList<Task> readFile() {
        File file = new File("./duke.txt");
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String storedTask = sc.nextLine();
                String[] splitTask = storedTask.split(" \\| ");
                Task task;
                if (splitTask[0].equals("T")) {
                    task = new Todo(splitTask[2]);
                } else if (splitTask[0].equals("D")) {
                    task = new Deadline(splitTask[2], splitTask[3]);
                } else {
                    task = new Event(splitTask[2], splitTask[3]);
                }
                if(splitTask[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch(FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void writeStorageFile(ArrayList<Task> tasks) {
        try {
            File file = new File("./duke.txt");
            FileWriter fw = new FileWriter(file);
            String storageString = "";
            for (int i = 0; i < tasks.size(); i++) {
                if (i < tasks.size() - 1) {
                    storageString += tasks.get(i).toMemoryString() + "\n";
                } else {
                    storageString += tasks.get(i).toMemoryString();
                }
            }
            fw.write(storageString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot store the tasks");
        }
    }

    private void parseMessage(String message) {

        String[] command = splitString(message, " ");
        String commandKey = command[0];
        switch (commandKey) {
            case "bye":
                this.generateReply("Bye. Hope to see you again soon!");
                this.inNeed = false;
                break;
            case "list":
                this.printList();
                break;
            case "mark":
                int index1 = Integer.parseInt(command[1].trim());
                this.markDone(index1);
                break;
            case "unmark":
                int index2 = Integer.parseInt(command[1].trim());
                this.unmarkDone(index2);
                break;
            case "todo":
                this.handleAddTask(command, "todo");
                break;
            case "deadline":
                this.handleAddTask(command, "deadline");
                break;
            case "event":
                this.handleAddTask(command, "event");
                break;
            case "delete":
                int index3 = Integer.parseInt(command[1]);
                this.deleteTask(index3);
                break;
            default:
                this.generateReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private void printList() {
        System.out.println("____________________________________________________________");
        int count = 1;
        for (Task t: this.list) {
            System.out.println(count++ + ". " + t);
        }
        System.out.println("____________________________________________________________\n");
    }

    private void addTask(Task t) {
        this.list.add(t);
        generateReply("Got it. I've added this task:\n" +
                t +
                "\nNow you have " + this.countTask() + " tasks in the list");
    }

    private Integer countTask() {
        return this.list.size();
    }

    private void markDone(int index) {
        this.list.get(index - 1).markDone();
        generateReply("Nice! I've marked this task as done:\n" +
                this.list.get(index - 1));
    }

    private void unmarkDone(int index) {
        this.list.get(index - 1).unmarkDone();
        generateReply("OK, I've marked this task as not done yet:\n" +
                this.list.get(index - 1));
    }

    public void handleAddTask(String[] commands, String type) {
        Task task;
        if (type.equals("todo")) {
            if (checkCommandArgsLength(commands, 2)) {
                if (commands[1].trim().length() != 0) {
                    task = new Todo(commands[1]);
                    this.addTask(task);
                } else {
                    generateReply("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else {
                generateReply("OOPS!!! The description of a todo cannot be empty.");
            }
        }
        else if (type.equals("deadline")) {
            if (checkCommandArgsLength(commands, 2)) {
                String[] argsDeadline = splitString(commands[1], DEADLINE_SPLIT);
                if (checkCommandArgsLength(argsDeadline, 2)) {
                    task = new Deadline(argsDeadline[0], argsDeadline[1]);
                    this.addTask(task);
                }
                else {
                    generateReply("OOPS!!! Invalid deadline command.");
                }
            } else {
                generateReply("OOPS!!! The description of a deadline cannot be empty.");
            }
        }
        else {
            if (checkCommandArgsLength(commands, 2)) {
                String[] argsDeadline = splitString(commands[1], EVENT_SPLIT);
                if (checkCommandArgsLength(argsDeadline, 2)) {
                    task = new Event(argsDeadline[0], argsDeadline[1]);
                    this.addTask(task);
                }
                else {
                    generateReply("OOPS!!! Invalid event command.");
                }
            } else {
                generateReply("OOPS!!! The description of a event cannot be empty.");
            }
        }
    }

    public void deleteTask(int index) {
        Task deletedTask = this.list.get(index - 1);
        this.list.remove(index - 1);
        generateReply("Noted. I've removed this task:\n" +
                deletedTask +
                "\nNow you have " + this.countTask() + " tasks in the list.\n");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (duke.inNeed) {
            String message = scanner.nextLine();
            duke.parseMessage(message);
        }
        writeStorageFile(duke.list);
    }
}
