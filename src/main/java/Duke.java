import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Duke {
    private static ArrayList<Task> data = new ArrayList<Task>();
    private static List<String> UNKNOWN_COMMANDS = Arrays.asList("todo", "deadline", "event");

    private static void addTodo(String[] inputs) {
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        data.add(new Todo(task));
    }
    private static void addDeadLine(String[] inputs) {
        int limit = findElem(inputs, "/by");
        if (limit == -1) {
            System.out.println("Oops! Remember to include /by and the deadline after your task description");
            return;
        }
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        String by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
        data.add(new Deadline(task, by));
    }

    private static void addEvent(String[] inputs) {
        int limit = findElem(inputs, "/at");
        if (limit == -1) {
            System.out.println("Oops! Remember to include /at and the event time after your task description");
            return;
        }
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        String at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
        data.add(new Event(task, at));
    }

    private static void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + data.get(i));
        }
    }

    private static void markItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        data.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + data.get(index - 1));
    }

    private static void unMarkItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        data.get(index - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + data.get(index - 1));

    }

    private static void deleteItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        Task taskRemoved = data.remove(index - 1);
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n",
                taskRemoved, data.size());
    }

    private static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    private static void printTaskAddedMessage() {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }
    private static void loadFileContents(String folderPath, String filePath) {
        try {
            File directory = new File(folderPath);
            directory.mkdir();
            File file = new File(filePath);
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                int index = 0;
                while (scanner.hasNext()) {
                    String[] input = scanner.nextLine().split("\\|");
                    switch (input[0]) {
                    case "T":
                        addTodo(Arrays.copyOfRange(input, 1, input.length));
                        break;
                    case "E":
                        addEvent(Arrays.copyOfRange(input, 1, input.length));
                        break;
                    case "D":
                        addDeadLine(Arrays.copyOfRange(input, 1, input.length));
                        break;
                    }
                    if (input[1].equals("X")) {
                        data.get(index).markAsDone();
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveFileContents(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : data) {
                switch (task.getTaskType()) {
                case "T":
                    Todo todo = (Todo) task;
                    fw.write(todo.getTaskType() + "|" + todo.getStatusIcon() + "|" + todo.getDescription()
                            + System.lineSeparator());
                    break;
                case "E":
                    Event event = (Event) task;
                    fw.write(event.getTaskType() + "|" + event.getStatusIcon() + "|" + event.getDescription()
                            + "|/at|" + event.getAt() + System.lineSeparator());
                    break;
                case "D":
                    Deadline deadline = (Deadline) task;
                    fw.write(deadline.getTaskType() + "|" + deadline.getStatusIcon() + "|"
                            + deadline.getDescription() + "|/by|" + deadline.getBy() + System.lineSeparator());
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadFileContents("./data", "./data/duke.txt");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String command = sc.nextLine();
                String[] inputs = command.split(" ");
                if (inputs.length == 1) {
                    if (command.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (command.equals("list")) {
                        listItems();
                    } else if (UNKNOWN_COMMANDS.contains(command)) {
                        throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else if (inputs.length == 2) {
                    if (inputs[0].equals("mark")) {
                        markItem(inputs);
                    } else if (inputs[0].equals("unmark")) {
                        unMarkItem(inputs);
                    } else if (inputs[0].equals("delete")) {
                        deleteItem(inputs);
                    }
                } else {
                    switch (inputs[0]) {
                        case "todo": {
                            addTodo(inputs);
                            printTaskAddedMessage();
                            break;
                        }
                        case "deadline": {
                            addDeadLine(inputs);
                            printTaskAddedMessage();
                            break;
                        }
                        case "event": {
                            addEvent(inputs);
                            printTaskAddedMessage();
                            break;
                        }
                    }
                }
                saveFileContents("./data/duke.txt");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
