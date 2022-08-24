import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
//    private static ArrayList<Task> inputTaskArray = new ArrayList<>();
//    private static TaskList taskList = new TaskList();
    private static int inputCount = 1;
    private static Scanner scan = new Scanner( System.in );
    private static Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadInTasks());
            inputCount = tasks.getTasks().size();
        } catch (IOException ioe) {
            System.exit(0);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        printLine();
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
        printLine();
//        getInput(scan);
        try {
            getInput(scan);
        } catch (DukeException | IOException de) {
            printLine();
            System.out.println(de.getMessage());
            printLine();
        }
    }

    public static void getInput(Scanner scan) throws DukeException, IOException {
        String inData;
        try {
            inData = scan.nextLine();
        } catch (Exception NoSuchElementException) {
            return;
        }

        String[] input = inData.split(" ", 2);
        switch (input[0]) {
            case("bye"):
                // store existing tasks into save file
                storage.update(tasks);
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            case("mark"):
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                Task taskToMark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
                taskToMark.markAsDone();
                System.out.println(taskToMark.toString());
                printLine();
                storage.update(tasks);
                getInput(scan);
                break;
            case("unmark"):
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                Task taskToUnmark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
                taskToUnmark.markAsNotDone();
                System.out.println(taskToUnmark.toString());
                printLine();
                storage.update(tasks);
                getInput(scan);
                break;
            case("list"):
                int count = 1;
                printLine();
                System.out.println("Here are the tasks in your list:");
                for(Task t : tasks.getTasks()) {
                    System.out.println(count++ + "." + t.toString());
                }
//                System.out.println("Now you have " + (inputCount - 1) + " tasks in the list.");
                System.out.println("Now you have " + (count - 1) + " tasks in the list.");

                printLine();
                getInput(scan);
                break;
            case("todo"):
                    if (input.length == 1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    printLine();
                    Todo curTodo = new Todo(input[1]);
                    addToTasklist(curTodo);
                    printLine();
                    getInput(scan);
                    break;

            case("deadline"):
                printLine();
                String[] deadlineString = input[1].split("/by ", 2);
                Deadline curDeadline = new Deadline(deadlineString[0], LocalDate.parse(deadlineString[1]));
                addToTasklist(curDeadline);
                printLine();
                getInput(scan);
                break;
            case("event"):
                printLine();
                String[]eventString = input[1].split("/at ", 2);
                Event curEvent = new Event(eventString[0], LocalDate.parse(eventString[1]));
                addToTasklist(curEvent);
                printLine();
                getInput(scan);
                break;
            case("delete"):
                printLine();
                deleteTask(Integer.parseInt(input[1]) - 1);
                printLine();
                getInput(scan);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

//    public static int getCount(TaskList taskList) {
//        return
//
//    }

    public static void addToTasklist(Task taskToAdd) throws IOException {
        tasks.addTask(taskToAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd.toString());
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        storage.update(tasks);
    }

    public static void deleteTask(int index) throws IOException {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.getTasks().get(index).toString());
        tasks.removeTask(index);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        storage.update(tasks);
    }

}
