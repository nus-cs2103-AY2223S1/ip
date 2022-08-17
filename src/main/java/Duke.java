import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasksToDo = new String[100];
        int i = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi\n" + logo + "\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye"))
        {
            if (input.equals("list"))
            {
                for (int j = 0; j < i; j ++)
                {
                    System.out.println((j + 1) + ". " + tasksToDo[j]);
                }
            }
            else
            {
                tasksToDo[i] = input;
                System.out.println("Added: " + input);
                i++;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }
}
