import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> inputTaskArray = new ArrayList<>();
    public static int inputCount = 1;
    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
        getInput();

        }

    public static void getInput() {
        String inData;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();

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
                getInput();
                break;
            case("unmark"):
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                Task taskToUnmark = inputTaskArray.get(Integer.parseInt(input[1]) - 1);
                taskToUnmark.markAsNotDone();
                System.out.println(taskToUnmark.toString());
                printLine();
                getInput();
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
                getInput();
                break;
            case("todo"):
                printLine();
                Todo curTodo = new Todo(input[1]);
                addToTasklist(curTodo);
                printLine();
                getInput();
                break;
            case("deadline"):
                printLine();
                String[] deadlineString = input[1].split("/by", 2);
                Deadline curDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                addToTasklist(curDeadline);
                printLine();
                getInput();
                break;
            case("event"):
                printLine();
                String[]eventString = input[1].split("/at", 2);
                Event curEvent = new Event(eventString[0], eventString[1]);
                addToTasklist(curEvent);
                printLine();
                getInput();
                break;
            default:
//                printLine();
//                Task curTask = new Task(inData);
//                inputTaskArray.add(curTask);
//                System.out.println("added: " + curTask.description);
//                printLine();
                getInput();
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

}
