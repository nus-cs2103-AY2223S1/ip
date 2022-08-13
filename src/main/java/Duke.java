import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private final static String INTRODUCTION_MSG = "Hello I'm Duke\nWhat can I do for you?";
    private final static String CLOSING_MSG = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        System.out.println(INTRODUCTION_MSG);
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine()).equals("bye")) {
            System.out.println(input);
        }
        System.out.println(CLOSING_MSG);
        sc.close();
    }
}
