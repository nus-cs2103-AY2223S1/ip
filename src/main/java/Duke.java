import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String greetings = "_________________________________________________\nHello! I'm Duke" +
                "\nWhat can I do for you?\n_________________________________________________";
        System.out.println(greetings);

        ArrayList<Task> tasks = new ArrayList<>();
        //int count = 1;

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            if (Objects.equals(echo, "list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available!");
                } else {
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                }
                // checks if it starts with mark, has a space after mark, and are all numbers after the space
            } else if (echo.length() >= 5 && (echo.startsWith("mark") &&
                    (Character.isWhitespace(echo.charAt(4))) &&
                    echo.substring(5).chars().allMatch(Character::isDigit))) {
                int number = parseInt(echo.substring(5));
                if (number > tasks.size() || number <= 0) {
                    System.out.println("Task number does not exist.");
                } else {
                    tasks.get(number - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(number - 1).toString());
                }
                //int number = Character.getNumericValue(echo.charAt(5));
                //similar logic as above
            } else if (echo.length() >= 7 && (echo.startsWith("unmark") &&
                    (Character.isWhitespace(echo.charAt(6))) &&
                    echo.substring(7).chars().allMatch(Character::isDigit))) {
                int number = parseInt(echo.substring(7));
                if (number > tasks.size() || number <= 0) {
                    System.out.println("Task number does not exist.");
                } else {
                    tasks.get(number - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            tasks.get(number - 1).toString());
                }
                //int number = Character.getNumericValue(echo.charAt(5));
            } else {
                System.out.println("Got it. I've added this task:");
                //System.out.println("_________________________________________________\nadded: " + echo + "\n" +
                //        "_________________________________________________\n");
                if (echo.startsWith("todo") && Character.isWhitespace(echo.charAt(4))) {
                    tasks.add(new ToDo(echo));
                    System.out.println(tasks.get(tasks.size() - 1));
                }

                if (echo.startsWith("deadline") && Character.isWhitespace(echo.charAt(8))) {
                    tasks.add(new Deadline(echo));
                    System.out.println(tasks.get(tasks.size() - 1));
                }

                if (echo.startsWith("event") && Character.isWhitespace(echo.charAt(5))) {
                    tasks.add(new Event(echo));
                    System.out.println(tasks.get(tasks.size() - 1));
                }

                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                //count++;
            }
            echo = sc.nextLine();
        }
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n" +
                "_________________________________________________\n");
        /*
        String greetings = "_________________________________________________\nHello! I'm Duke" +
                "\nWhat can I do for you?\n_________________________________________________";
        System.out.println(greetings);

        String[] tasks = new String[100];
        int count = 1;

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            if (Objects.equals(echo, "list")) {
                for (int i = 1; i < tasks.length; i++) {
                    System.out.println(i + ". " + tasks[i]);
                }
            }
            System.out.println("_________________________________________________\nadded: " + echo + "\n" +
                    "_________________________________________________\n");
            tasks[count] = echo;
            count++;
            echo = sc.nextLine();
        }
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n" +
                "_________________________________________________\n");
        */
    }
}
