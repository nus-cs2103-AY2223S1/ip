import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");
        boolean closeFlag = false;

        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("bye")) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________");
        }

        System.out.println("____________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________");
        scanner.close();
    }
}
