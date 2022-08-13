import java.util.*;
public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.print("Hi I'm catBot! How can I help you nya?\n");
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) break;
            System.out.print(input + "\n");
        }

        System.out.print("Bye nya! Hope to see you again nya.");

    }
}
