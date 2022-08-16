import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    private static String printSpacer() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
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
                        for (int i = 1; i <= tasksLength; i++) {
                            System.out.println(i + "." + tasks[i - 1].getStatusIcon() + " " + tasks[i - 1].getDescription());
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
                    if (s.startsWith("mark ")) {

                        int n = parseInt(s.substring(5));
                        if (n > tasksLength) {
                            System.out.println("Oops, no such task number!");
                            System.out.println(printSpacer());
                        } else {
                            tasks[n - 1].setDone(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks[n - 1].getStatusIcon() + " " + tasks[n - 1].getDescription());
                            System.out.println(printSpacer());
                        }

                    } else if (s.startsWith("unmark ")) {

                        int n = parseInt(s.substring(7));
                        if (n > tasksLength) {
                            System.out.println("Oops, no such task number!");
                            System.out.println(printSpacer());
                        } else {
                            tasks[n - 1].setDone(false);
                            System.out.println("Okie, I've marked this task as not done yet:");
                            System.out.println(tasks[n - 1].getStatusIcon() + " " + tasks[n - 1].getDescription());
                            System.out.println(printSpacer());
                        }

                    } else {
                        tasks[tasksLength] = new Task(s);
                        tasksLength++;
                        System.out.println("Added task: " + s);
                        System.out.println(printSpacer());
                    }
            }

        }
    }
}
