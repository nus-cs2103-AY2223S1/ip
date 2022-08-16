import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! I'm Duke\nWhat can I do for you?\n";

    private static void echo(String str) {
        System.out.println("added: " + str);
    }

    private static void printArrAsNumberedList(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i+1 + ". " + arr.get(i).toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.println(GREETINGS);

        ArrayList<Task> taskArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printArrAsNumberedList(taskArr);
            } else if (input.startsWith("mark")){
                taskArr.get(Integer.parseInt(input.substring(5))).mark();
            } else if (input.startsWith("unmark")){
                taskArr.get(Integer.parseInt(input.substring(7))).unmark();
            } else {
                taskArr.add(new Task(input));
                echo(input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");




    }

}
