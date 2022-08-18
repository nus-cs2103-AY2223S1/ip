import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> inputTaskArray = new ArrayList<>();
    public static int inputCount = 1;
    public static Scanner scan = new Scanner( System.in );
    public static void main(String[] args) throws DukeException {

        printLine();
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
        printLine();
//        getInput(scan);
        try {
            getInput(scan);
        } catch (DukeException de) {
            printLine();
            System.out.println(de.getMessage());
            printLine();
        }
    }

    public static void getInput(Scanner scan) throws DukeException {
        String inData;
        try {
            inData = scan.nextLine();
        } catch (Exception NoSuchElementException) {
            return;
        }

        String[] input = inData.split(" ", 2);
        switch (input[0]) {
            case("bye"):
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            case("mark"):
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                Task taskToMark = inputTaskArray.get(Integer.parseInt(input[1]) - 1);
                taskToMark.markAsDone();
                System.out.println(taskToMark.toString());
                printLine();
                getInput(scan);
                break;
            case("unmark"):
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                Task taskToUnmark = inputTaskArray.get(Integer.parseInt(input[1]) - 1);
                taskToUnmark.markAsNotDone();
                System.out.println(taskToUnmark.toString());
                printLine();
                getInput(scan);
                break;
            case("list"):
                int count = 1;
                printLine();
                System.out.println("Here are the tasks in your list:");
                for(Task t : inputTaskArray) {
                    System.out.println(count++ + "." + t.toString());
                }
                System.out.println("Now you have " + (inputCount - 1) + " tasks in the list.");

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
                String[] deadlineString = input[1].split("/by", 2);
                Deadline curDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                addToTasklist(curDeadline);
                printLine();
                getInput(scan);
                break;
            case("event"):
                printLine();
                String[]eventString = input[1].split("/at", 2);
                Event curEvent = new Event(eventString[0], eventString[1]);
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

    public static void addToTasklist(Task taskToAdd) {
        inputTaskArray.add(taskToAdd);
        System.out.println("Got it. I've added this task:");
        inputCount++;
        System.out.println(taskToAdd.toString());
        System.out.println("Now you have " + (inputCount - 1) + " tasks in the list.");
    }

    public static void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(inputTaskArray.get(index).toString());
        inputTaskArray.remove(index);
        inputCount--;
        System.out.println("Now you have " + (inputCount - 1) + " tasks in the list.");
    }

}
