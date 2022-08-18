import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";
    public Scanner sc = new Scanner(System.in);
    public String[] list = new String[100];
    public static int count = 0;

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
        String input = sc.nextLine();
        if (!input.equals("bye") && !input.equals("list")) {
            System.out.println(
                    line + "\n" +
                            "added: " + input + "\n" + line+ "\n");
            list[count++] = input;
            respond();
        }
        else if (!input.equals("list")) {
            bye();
        }

        else {
            System.out.println(
                    line);

            for (int i = 0, j = 1; i < count; i++, j++) {

                System.out.println(j + ". " + list[i] );
            }
            System.out.println(
                    line + "\n"
            );
            respond();
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
