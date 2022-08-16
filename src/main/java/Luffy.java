import java.util.Scanner;


public class Luffy {
    public static void main(String[] args) {
        String logo = "██╗░░░░░██╗░░░██╗███████╗███████╗██╗░░░██╗\n"
                + "██║░░░░░██║░░░██║██╔════╝██╔════╝╚██╗░██╔╝\n"
                + "██║░░░░░██║░░░██║█████╗░░█████╗░░░╚████╔╝░\n"
                + "██║░░░░░██║░░░██║██╔══╝░░██╔══╝░░░░╚██╔╝░░\n"
                + "███████╗╚██████╔╝██║░░░░░██║░░░░░░░░██║░░░\n"
                + "╚══════╝░╚═════╝░╚═╝░░░░░╚═╝░░░░░░░░╚═╝░░░";
        System.out.println("------------------------------------------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------");

        //Actual Luffy Logic:
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            String s = in.nextLine();

            if (s.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon :)");
                break;
            } else if (s.equals("list")) {
                System.out.println("------------------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("------------------------------------------------------");
            } else if (s.length() >= 6 && s.substring(0, 4).equals("mark")) {
                System.out.println("------------------------------------------------------");
                int taskIndex = Integer.parseInt(s.substring(5, 6)) - 1;
                if (taskIndex >= 0 && taskIndex < counter) {
                    tasks[taskIndex].markCompleted();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex]);
                } else {
                    System.out.println("Task index " + (taskIndex + 1) + " is not valid!");
                }
                System.out.println("------------------------------------------------------");
            } else if (s.length() >= 8 && s.substring(0, 6).equals("unmark")) {
                System.out.println("------------------------------------------------------");
                int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                if (taskIndex >= 0 && taskIndex < counter) {
                    tasks[taskIndex].markUncompleted();
                    System.out.println("Alright! I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskIndex]);
                } else {
                    System.out.println("Task index " + (taskIndex + 1) + " is not valid!");
                }
                System.out.println("------------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------------");
                tasks[counter] = new Task(s);
                counter++;
                System.out.println("added: " + s);
                System.out.println("------------------------------------------------------");
            }
        }
    }
}
