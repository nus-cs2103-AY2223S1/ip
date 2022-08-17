import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("               __\n" +
                "              / _)\n" +
                "     _.----._/ /\n" +
                "    /         /\n" +
                " __/ (  | (  |\n" +
                "/__.-'|_|--|_|\n");

        String line = "Dino:\n";
        Task[] ls = new Task[100];

        System.out.println(line
                + "     Hello! I'm Dino\n"
                + "     What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String curr = "";

        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            if (!Objects.equals(curr, "bye")) {
                if (Objects.equals(curr, "list")) {
                    System.out.println("Dino:");
                    for (int i = 0; i < Task.lsSize(); i++) {
                        System.out.printf("     %d. %s%n", i + 1, ls[i].myTask());
                    }
                    System.out.print("\n");
                } else if (curr.startsWith("mark")) {
                    int index = Integer.parseInt(curr.split("\\s")[1]);
                    Task myTask = ls[index - 1];
                    myTask.markAsDone();
                    System.out.printf("%s     Hooray! You have completed this task:%n     %s%n%n", line, myTask.myTask());
                } else if (curr.startsWith("unmark")) {
                    int index = Integer.parseInt(curr.split("\\s")[1]);
                    Task myTask = ls[index - 1];
                    myTask.markAsUndone();
                    System.out.printf("%s     Oh no! You have more things to complete:%n     %s%n%n", line, myTask.myTask());
                } else {
                    ls[Task.lsSize()] = new Task(curr);
                    System.out.printf("%s     added: %s%n%n", line, curr);
                }
            }
        }

        System.out.println(line
                + "     Bye bye. Hope to see you again soon!");
    }
}
