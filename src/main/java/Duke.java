import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Variables
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Introduction message
        greetUser();

        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again!");
                break;
            }

            else {
                System.out.println(input);
                input = sc.nextLine();
            }
        }
    }
    private static void greetUser() {
        System.out.println("Hello! I'm Jukebox\n" + "What can I do for you?");
    }
}

