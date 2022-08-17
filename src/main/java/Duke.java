import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Yale\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        Task[] toDoList = new Task[100];
        int count = 0;

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) {
                if (count == 0) {
                    System.out.println("There is no item in list yet");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.printf("%d.%s\n", i + 1, toDoList[i]);
                    }
                }
            } else if (input.contains("unmark")) {
                String[] splitStr = input.trim().split("\\s+");
                int markItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                toDoList[markItem].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(toDoList[markItem]);
            } else if (input.contains("mark")) {
                String[] splitStr = input.trim().split("\\s+");
                int markItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                toDoList[markItem].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(toDoList[markItem]);
            } else {
                Task newTask = new Task(input);
                toDoList[count] = newTask;
                count++;
                String message = "\t" + "_".repeat(20) + "\n"
                        + String.format("\t  added: %s\n", input)
                        + "\t" + "_".repeat(20) + "\n";
                System.out.println(message);
            }
        }
    }
}
