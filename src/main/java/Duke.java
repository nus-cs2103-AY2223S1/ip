import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String logo  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> list;

    public static void main(String[] args) {
        list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today, Master?");

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        boolean open = true;
        while (open) {
            // Bye
            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                open = false;
                System.out.println("Goodbye, Master! Thank you for visiting\n" + logo);
            // List
            } else if (text.equalsIgnoreCase("list")) {
                if (list.isEmpty()) {
                    System.out.println("There is nothing in your list yet!");

                } else {
                    System.out.println("Here is your to-do list, Master:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1).getName() + list.get(i - 1).getStatus());
                    }
                }
                text = scanner.nextLine();

            // Mark
            } else if (text.startsWith("mark") && text.length() <= 7) {
                String taskNumber = String.valueOf(text.charAt(5));
                if (text.length() == 7) {
                    taskNumber += String.valueOf(text.charAt(6));
                }
                int number = Integer.parseInt(taskNumber) - 1;
                if (number >= list.size() || number <= 0) {
                    System.out.println("There is no such task just yet, Master.");
                } else if (list.get(number).isDone) {
                    System.out.println("This task was already marked done, Master.");
                } else {
                    list.get(number).markDone();
                    System.out.println("Well done! I have marked " + list.get(number).getName() + " as done, Master.");
                }
                text = scanner.nextLine();
            } else if (text.startsWith("unmark")) {
                String taskNumber = String.valueOf(text.charAt(7));
                if (text.length() == 9) {
                    taskNumber += String.valueOf(text.charAt(8));
                }
                int number = Integer.parseInt(taskNumber) - 1;
                if (number >= list.size() || number <= 0) {
                    System.out.println("There is no such task just yet, Master.");
                } else if (!list.get(number).isDone) {
                    System.out.println("This task was already marked undone, Master.");
                } else {
                    list.get(number).markUndone();
                    System.out.println("Oh no :( I have marked " + list.get(number).getName() + " as undone, Master.");
                }
                text = scanner.nextLine();
            } else {
                list.add(new Task(text));
                System.out.println("I have added " + text + " to your to-do list, Master.");
                text = scanner.nextLine();
            }
        }
    }
}
