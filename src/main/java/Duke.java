import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void addToList(Task task) {
        list.add(task);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String line = String.format("%s. %s", i + 1, list.get(i));
            System.out.println(line);
        }
    }

    private static void markTask(int num) {
        Task task = list.get(num - 1);
        task.mark();
    }

    private static void unmarkTask(int num) {
        Task task = list.get(num - 1);
        task.unmark();
    }

    public static void main(String[] args) {
        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");

        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("bye")) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            System.out.println("____________________________________________________");
            if (inputArr[0].equals("list")) {
                printList();
            } else if (inputArr[0].equals("mark")) {
                int taskNum = Integer.parseInt(inputArr[1]);
                try {
                    markTask(taskNum);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist!");
                }
                String output = String.format("Nice! I've marked this task as done:\n%s", list.get(taskNum - 1));
                System.out.println(output);
            } else if (inputArr[0].equals("unmark")) {
                int taskNum = Integer.parseInt(inputArr[1]);
                try {
                    unmarkTask(taskNum);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist!");
                }
                String output = String.format("OK, I've marked this task as not done yet:\n%s", list.get(taskNum - 1));
                System.out.println(output);
            } else {
                String taskName = String.join(" ", inputArr);
                addToList(new Task(taskName));
                System.out.println("added: " + input);
            }

            System.out.println("____________________________________________________");
        }

        System.out.println("____________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________");
        scanner.close();
    }
}
