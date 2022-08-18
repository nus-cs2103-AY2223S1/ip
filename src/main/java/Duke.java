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
        System.out.println("Got it. I've added this task:\n" + str);
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
            try {
                if (input.equals("list")) {
                    printArrAsNumberedList(taskArr);
                } else if (input.startsWith("mark")) {
                    taskArr.get(Integer.parseInt(input.substring(5)) - 1).mark();
                } else if (input.startsWith("unmark")) {
                    taskArr.get(Integer.parseInt(input.substring(7)) - 1).unmark();
                } else {
                    // this is under task creation
                    if (input.startsWith("event")) {
                        String[] inputArr = input.split("/");
                        taskArr.add(new Event(inputArr[0], inputArr[1].substring(3)));
                        echo(taskArr.get(taskArr.size() - 1).toString());
                    } else if (input.startsWith("todo")) {

                        if (input.substring(4).equals("")){
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            taskArr.add(new ToDo(input));
                            echo(taskArr.get(taskArr.size() - 1).toString());
                        }
                    } else if (input.startsWith("deadline")) {
                        String[] inputArr = input.split("/");
                        taskArr.add(new Deadline(inputArr[0], inputArr[1].substring(3)));
                        echo(taskArr.get(taskArr.size() - 1).toString());
                    } else if (input.startsWith("delete")) {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                                taskArr.get(index).toString(), (taskArr.size() - 1));
                        taskArr.remove(index);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
//                    echo(taskArr.get(taskArr.size() - 1).toString());
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");




    }

}
