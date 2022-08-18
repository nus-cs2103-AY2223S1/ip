import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> inputArray = new ArrayList<>();
    public static ArrayList<Task> inputTaskArray = new ArrayList<>();
    public static int inputCount = 1;
    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        getInput();


//        System.out.println(String.join(" ", args));

        }

    public static void getInput() {
        String inData;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();

        String[] input = inData.split(" ", 2);
        switch (input[0]) {
            case("bye"):
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            case("mark"):
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                Task taskToMark = inputTaskArray.get(Integer.parseInt(input[1]) - 1);
                taskToMark.markAsDone();
                System.out.println("[" + taskToMark.getStatusIcon() + "] " + taskToMark.description);
                System.out.println("____________________________________________________________");
                getInput();
                break;
            case("unmark"):
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                Task taskToUnmark = inputTaskArray.get(Integer.parseInt(input[1]) - 1);
                taskToUnmark.markAsNotDone();
                System.out.println("[" + taskToUnmark.getStatusIcon() + "] " + taskToUnmark.description);
                System.out.println("____________________________________________________________");
                getInput();
                break;
            case("list"):
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for(Task t : inputTaskArray) {
                    System.out.println(t.index + ".[" + t.getStatusIcon() + "] " + t.description);
                }
                System.out.println("____________________________________________________________");
                getInput();
                break;
            default:
                System.out.println("____________________________________________________________");
                Task curTask = new Task(inData);
                inputTaskArray.add(curTask);
                System.out.println("added: " + curTask.description);
                System.out.println("____________________________________________________________");
                getInput();
        }




    }

}
