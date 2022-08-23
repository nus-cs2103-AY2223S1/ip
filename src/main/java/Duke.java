import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Duke {
    public static ArrayList<Task> list;
    String filePath = "data/duke.txt";
    private static class DukeException extends Exception {
        String message;

        DukeException(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
    public static void saveData() throws DukeException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.text";
        File data = new File(filePath);
        // Write to data
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : list) {
                fileWriter.write(t.toFileDescription() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
        throw new DukeException("Hey! Are you in the wrong directory? You are currently at" +
                e.getMessage());
        }
    }
    public static void loadData() throws DukeException {
        list = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.text";
        File data = new File(filePath);
        // Load the file data into corresponding ArrayList
        try {
            if (data.exists()) {
                Scanner scanner = new Scanner(data);
                while (scanner.hasNext()) {
                    String currentlyAt = scanner.nextLine();
                    char first = currentlyAt.charAt(0);
                    Task task;
                    switch(first) {
                    case('T'): {
                        task = Todo.fromFileDescription(currentlyAt);
                        break;
                    }
                    case('D'): {
                        task = Deadline.fromFileDescription(currentlyAt);
                        break;
                    }
                    case('E'): {
                        task = Event.fromFileDescription(currentlyAt);
                        break;
                    }
                    default: {
                        throw new DukeException("What!? How did this happen... I'm pretty sure you" +
                                "have an itchy hand and modified the duke.txt file!!!");
                    }
                    }
                    list.add(task);
                }
            } else {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Are you a hacker? How on earth did you get to this stage!?");
        }
    }

    public static void line() {
        System.out.println("________________________________________");
    }

    public static void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    public static void echo(String message) {
        line();
        System.out.println(message);
        line();
    }

    private void store(String input) {
        Task t = new Task(input);
        list.add(t);
        line();
        System.out.println("added: " + t);
        line();
    }

    public static void enumerateArrayList() throws DukeException {
        int numOfTasks = list.size();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println(i + 1 + "." + t);
        }
        line();
    }

    public static Task getTask(int index) throws DukeException {
        int numOfTasks = list.size();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    "It definitely has to be at least 1!");
        }
        return list.get(index - 1);
    }

    public static void markDone(int index) throws DukeException {
        Task t = getTask(index);
        t.markDone();
        line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        line();
    }

    public static void markUndone(int index) throws DukeException {
        Task t = getTask(index);
        t.markUndone();
        line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        line();
    }

    public static void printArraySize() {
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static Todo handleTodo(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input);
    }

    public static Deadline handleDeadline(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your deadline for this is due by?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        LocalDate localDateBy = LocalDate.parse(dateBy);
        return new Deadline(description, localDateBy);
    }

    public static Event handleEvent(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your event is at?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        LocalDate localDateAt = LocalDate.parse(dateAt);
        return new Event(description, localDateAt);
    }

    public static void addTask(String input, Commands type) throws DukeException {
        Task t;
        switch (type) {
        case TODO: {
            t = handleTodo(input);
            break;
        }
        case DEADLINE: {
            t = handleDeadline(input);
            break;
        }
        case EVENT: {
            t = handleEvent(input);
            break;
        }
        default:
            throw new IllegalArgumentException("Invalid task type entered");
            // this should only be seen by developer
        }
        list.add(t);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public static void deleteTask(int index) throws DukeException {
        int numOfTasks = list.size();
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    " It definitely has to be at least 1!");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (numOfTasks == 0) {
            throw new DukeException("You cant delete anything yet! Try creating some tasks first!");
        }
        Task t = getTask(index);
        int indexInList = index - 1;
        list.remove(indexInList);
        line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public static void handleDelete(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify which task to delete?");
        }
        int index = Integer.parseInt(input);
        deleteTask(index);
    }

    public static void printTaskOnDate(LocalDate localDate) {
        List<Task> filteredList = list.stream().filter(task -> task.isHappeningOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        line();
        System.out.println("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")));
        for (Task t : filteredList) {
            System.out.println(i + 1 + "." + t);
            i++;
        }
        line();
    }

    public static void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public static void main(String[] args) {
        boolean isDone = false;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        try {
            loadData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in); // creating scanner for user input
        while (!isDone) {
            try {
                String input = scanner.nextLine();
                String[] strArray = input.split(" ", 2);
                String first = strArray[0];
                String second = "";
                if (strArray.length == 2) {
                    second = strArray[1];
                }
                switch (first) {
                case ("bye"): {
                    exit();
                    isDone = true;
                    saveData();
                    break;
                }
                case ("list"): {
                    enumerateArrayList();
                    break;
                }
                case ("mark"): {
                    markDone(Integer.parseInt(second));
                    break;
                }
                case ("unmark"): {
                    markUndone(Integer.parseInt(second));
                    break;
                }
                case ("todo"): {
                    addTask(second, Commands.TODO);
                    break;
                }
                case ("deadline"): {
                    addTask(second, Commands.DEADLINE);
                    break;
                }
                case ("event"): {
                    addTask(second, Commands.EVENT);
                    break;
                }
                case ("delete"): {
                    handleDelete(second);
                    break;
                }
                case ("on"): {
                    printTaskOnDate(LocalDate.parse(second));
                    break;
                }
                default: {
                    throw new DukeException("Invalid command entered. I don't recognize it. Sorry!");
                }
                }
            } catch (DukeException e) {
                line();
                System.out.println(e.toString());
                line();
            }
        }
    }
}
