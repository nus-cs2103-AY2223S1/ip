import java.util.Scanner;

public class Duke {
    private static Task[] store = new Task[100];
    private static int count = 0;
    public static void main(String[] args) {

        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\tBye! Hope that I helped!");
                break;
            } else if (input.equals("list")) {
                int number = 1;
                for (Task task : store) {
                    if (task == null) continue;
                    System.out.println("\t" + number + "." + task);
                    number++;
                }
                continue;
            } else if (input.split(" ")[0].equals("mark")) {
                Integer index = Integer.valueOf(input.split(" ")[1]);
                store[index].markAsDone();
                System.out.println("\tnice! I've marked this task as done:");
                System.out.println("\t\t" + store[index]);
                continue;
            } else if (input.split(" ")[0].equals("unmark")) {
                Integer index = Integer.valueOf(input.split(" ")[1]);
                store[index].markAsNotDone();
                System.out.println("\tOk! I've marked this task as not done yet:");
                System.out.println("\t\t" + store[index]);
                continue;
            }
            store[++count] = new Task(input);
            System.out.println("\tadded: " + input);
        }
    }
}
