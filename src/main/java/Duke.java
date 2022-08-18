import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";
    public Scanner sc = new Scanner(System.in);

    public Duke() {};

    public void greet() {
        System.out.println(
                line + "\n" +
                        "Hello! I'm Duke" + "\n" +
                        "What can I do for you?" + "\n" +
                        line + "\n"
        );
    }

    public void respond() {
        String input = sc.next();
        if (!input.equals("bye")) {
            System.out.println(
                    line + "\n" +
                            input + "\n" + line+ "\n");
            respond();
        }
        else {
            bye();
        }

    }

    public void bye() {
        System.out.println(
                line + "\n" +
                        "Bye. Hope to see you again soon!" + "\n" + line
        );

    }

    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.respond();
    }

}
