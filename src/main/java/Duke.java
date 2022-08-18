import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String exitCode = "bye";
            String arr[] = input.split(" ", 2);
            if (input.equals(exitCode)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < Task.getCount() + 1; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    else {
                        System.out.println((i+1) + ". " + tasks[i].toString());
                    }
                }
            }
            else if (arr[0].equals("mark")){
                int i = Integer.parseInt(arr[1]);
                if (i  <= Task.getCount()) {
                    tasks[i - 1].complete();
                    System.out.println("Nice! I have marked this task as done: ");
                    System.out.println(tasks[i - 1]);
                } else {
                    System.out.println("Index does not exist");
                }
            } else if (arr[0].equals("unmark")) {
                int i = Integer.parseInt(arr[1]);
                if (i <= Task.getCount()) {
                    tasks[i - 1].incomplete();
                    System.out.println("OK, I have marked this task as not done yet: ");
                    System.out.println(tasks[i - 1]);
                } else {
                    System.out.println("Index does not exist");
                }
            } else {
                tasks[Task.getCount()] = new Task(input);
                System.out.println("added: " + input);
            }
        }

    }
}
