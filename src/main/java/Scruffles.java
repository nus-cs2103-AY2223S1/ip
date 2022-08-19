import java.util.Scanner;
import java.util.ArrayList;

public class Scruffles {
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        ArrayList<Task> list = new ArrayList<Task>(100);
        int taskCount = 0;

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("woof see you again woof!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String output = (i + 1) + "." + list.get(i).toString();
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                input = input.replaceFirst("mark ", "");
                int listRef = Integer.parseInt(input) - 1;
                list.get(listRef).setDone();

                System.out.println("woof! the task is now marked as done woof!\n" + list.get(listRef).toString());
            } else if (input.startsWith("todo")) {
                input = input.replaceFirst("todo ", "");
                list.add(new Todo(input));

                System.out.println("woof! the task is added woof!");
                System.out.println(list.get(taskCount).toString());
                System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                taskCount++;
            } else if (input.startsWith("deadline")) {
                input = input.replaceFirst("deadline ", "");
                String[] inputArray = input.split(" /by ");
                list.add(new Deadline(inputArray[0], inputArray[1]));

                System.out.println("woof! the task is added woof!");
                System.out.println(list.get(taskCount).toString());
                System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                taskCount++;
            } else if (input.startsWith("event")) {
                input = input.replaceFirst("event ", "");
                String[] inputArray = input.split(" /at ");
                list.add(new Event(inputArray[0], inputArray[1]));

                System.out.println("woof! the task is added woof!");
                System.out.println(list.get(taskCount).toString());
                System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                taskCount++;
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
                taskCount++;
            }
        }
    }
}