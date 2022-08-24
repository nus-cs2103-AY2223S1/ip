import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File saved = new File("data/duke.txt");
        if (!saved.exists()) {
            saved.createNewFile();
        }

        Duke duke = new Duke();
        duke.runBot();
    }

    public void runBot() {

        try {
            load();
        } catch (DukeException e) {
            printMessage(e.getMessage());
        } catch (FileNotFoundException fe) {
            printMessage(fe.getMessage());
        }

        greetingMessage();

        boolean exitBot = false;
        while (!exitBot) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    exitBot = true;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(input.replace("mark ", ""));
                    validateMark(taskNum);
                    markAsDone(taskNum);
                } else if (input.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(input.replace("unmark ", ""));
                    validateMark(taskNum);
                    markAsUndone(taskNum);
                } else if (input.startsWith("deadline ")) {
                    String[] deadline = input.replace("deadline ", "").split(" /by ");
                    addDeadline(deadline[0], deadline[1]);
                } else if (input.startsWith("todo ")) {
                    String todo = input.replace("todo ", "");
                    validateTodo(todo);
                    addTodo(todo);
                } else if (input.startsWith("event ")) {
                    String[] event = input.replace("event ", "").split(" /at ");
                    addEvent(event[0], event[1]);
                } else if (input.startsWith("delete ")) {
                    int taskNum = Integer.parseInt(input.replace("delete ", ""));
                    validateDelete(taskNum);
                    deleteTask(taskNum);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                save();
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }

        exitMessage();
    }

    public void load() throws DukeException, FileNotFoundException {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" \\| ");

                switch (splitInput[0]) {
                    case "T":
                        Todo todo = new Todo(splitInput[2]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw new DukeException("Task is of unknown type :(");
                }
            }
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        } catch (NumberFormatException e) {
            throw new DukeException("An error occurred during file parsing, unexpected done value encountered.");
        }
    }

    public void save() throws DukeException {
        try {
            File file = new File("data/duke.txt");

            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                fw.write(task.getOutput() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        }
    }

    public static void markAsDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
        linePrint();
        System.out.println("\tNice! I've marked this task as done:\n\t" +
                tasks.get(taskNum - 1).toString());
        linePrint();
    }

    public static void markAsUndone(int taskNum) {
        tasks.get(taskNum - 1).markAsUndone();
        linePrint();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" +
                tasks.get(taskNum - 1).toString());
        linePrint();
    }

    public static void validateTodo(String todo) throws DukeException {
        if(todo.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void validateMark(int taskNum) throws DukeException {
        if(taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }
    }

    public static void validateDelete(int taskNum) throws DukeException {
        if(taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("OOPS!!! The index of the task to delete is not in the list.");
        }
    }

    public static void deleteTask(int taskNum) {
        Task toDelete = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        linePrint();
        System.out.println("\tNoted. I've removed this task:\n\t" +
                toDelete.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public static void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newDeadline.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newTodo.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public static void addEvent(String description, String time) {
        Event newEvent = new Event(description, time);
        tasks.add(newEvent);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newEvent.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public void addTask(String input) {
        tasks.add(new Task(input));
        printMessage("added: " + input);
    }

    public static void listTasks() {
        linePrint();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        linePrint();
    }

    public static void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t" +
                "What can I do for you?";
        printMessage(greeting);
    }

    public static void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    public static void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    public static void linePrint() {
        System.out.println("\t____________________________________________________________");
    }
}
