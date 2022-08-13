import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "_________________________________________________\nHello! I'm Duke" +
                "\nWhat can I do for you?\n_________________________________________________";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            System.out.println("_________________________________________________\n" + echo + "\n" +
                    "_________________________________________________\n");
            echo = sc.nextLine();
        }
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n" +
                "_________________________________________________\n");

    }
}
