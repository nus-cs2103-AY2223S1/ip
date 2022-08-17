import java.util.Scanner;

public class Duke {

    private static String printSpacer() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    private static boolean isPositiveInt(String s) {
        try {
            int n = Integer.parseInt(s);
            return n > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Task[] tasks = new Task[100];
    private static int tasksLength = 0;

    private static boolean cont = true;

    public static void main(String[] args) {
        System.out.println(printSpacer());
        System.out.println("(｡･ω･｡)ﾉ♡ Hello! This is Duke! What can I do for you today?");
        System.out.println(printSpacer());
        Scanner sc = new Scanner(System.in);

        while (cont) {
            String s = sc.nextLine();

            switch (s) {

                case "list":
                    if (tasksLength == 0) {
                        System.out.println("No tasks yet~");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= tasksLength; i++) {
                            System.out.println(i + "." + tasks[i - 1].toString());
                        }
                    }
                    System.out.println(printSpacer());
                    break;

                case "bye":
                    System.out.println("(~‾▿‾)~ Bye. Hope to see you again soon!");
                    System.out.println(printSpacer());
                    cont = false;
                    break;

                default:
                    if (s.startsWith("mark ") && isPositiveInt(s.substring(5))) {

                        int n = Integer.parseInt(s.substring(5));
                        if (n > tasksLength) {
                            System.out.println("Oops, no such task number!");
                            System.out.println(printSpacer());
                        } else {
                            tasks[n - 1].setDone(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks[n - 1].toString());
                            System.out.println(printSpacer());
                        }

                    } else if (s.startsWith("unmark ")  && isPositiveInt(s.substring(7))) {

                        int n = Integer.parseInt(s.substring(7));
                        if (n > tasksLength) {
                            System.out.println("Oops, no such task number!");
                            System.out.println(printSpacer());
                        } else {
                            tasks[n - 1].setDone(false);
                            System.out.println("Okie, I've marked this task as not done yet:");
                            System.out.println(tasks[n - 1].toString());
                            System.out.println(printSpacer());
                        }

                    } else if (s.startsWith("todo ")) {

                        tasks[tasksLength] = new Todo(s.substring(5));
                        tasksLength++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[tasksLength - 1].toString());
                        System.out.println("Now you have " + tasksLength + " tasks in your list.");
                        System.out.println(printSpacer());

                    } else if (s.startsWith("deadline ")) {

                        String[] stuff = s.substring(9).split(" /by ");
                        tasks[tasksLength] = new Deadline(stuff[0], stuff[1]);
                        tasksLength++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[tasksLength - 1].toString());
                        System.out.println("Now you have " + tasksLength + " tasks in your list.");
                        System.out.println(printSpacer());

                    } else if (s.startsWith("event ")) {

                        String[] stuff = s.substring(6).split(" /at ");
                        tasks[tasksLength] = new Event(stuff[0], stuff[1]);
                        tasksLength++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[tasksLength - 1].toString());
                        System.out.println("Now you have " + tasksLength + " tasks in your list.");
                        System.out.println(printSpacer());

                    } else {
                        System.out.println("(╥﹏╥) Sorry I don't understand the command...");
                        System.out.println(printSpacer());
                    }
            }

        }
    }
}
