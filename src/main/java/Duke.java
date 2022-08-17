import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println("Hello from\n" + intro);


        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            System.out.println(input);
        }
    }
}
