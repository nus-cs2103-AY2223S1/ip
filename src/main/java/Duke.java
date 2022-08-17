import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = myObj.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = myObj.nextLine();
        }

        myObj.close();
        System.out.println("Bye. Hope to see you again soon!");

    }
}
