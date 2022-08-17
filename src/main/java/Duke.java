import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String[] tasks = new String[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while(true) {
            String input = in.nextLine();
            String exitCode = "bye";
            if (input.equals(exitCode)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < counter + 1; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    else {
                        System.out.println((i+1) + ". " + tasks[i]);
                    }
                }
            }
            else {
                tasks[counter] = input;
                counter++;
                System.out.println("added: " + input);
            }
        }

    }
}
