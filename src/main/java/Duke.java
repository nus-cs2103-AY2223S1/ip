import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";
    public Scanner sc = new Scanner(System.in);
    public static Task[] list = new Task[100];
    public static int count = 0;

    public Duke() {
    }

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

        String[] arr = input.split(" ");
        String command = arr[0];
        if (command.equals("mark") || command.equals("unmark")) {
            int index = Integer.parseInt(arr[1]);
            if (command.equals("mark")) {
                list[index - 1].mark(index);
            } else if (command.equals("unmark")) {
                list[index - 1].unmark(index);
            }
            respond();
        } else if (input.equals(("bye"))) {
            bye();
        } else if (input.equals("list")) {
            list();
            respond();
        } else {
            System.out.println(
                    line + "\n" +
                            "added: " + input + "\n" + line + "\n");
            list[count++] = new Task(input);
            respond();
        }

    }

    public void list() {
        System.out.println(
                line + "\n" +
                        "Here are the tasks in your list:");

        for (int i = 0, j = 1; i < count; i++, j++) {

            System.out.println(j + ". " + list[i].getStatus() + list[i].getName());
        }
        System.out.println(
                line + "\n"
        );

    }

    public void bye() {
        System.out.println(
                line + "\n" +
                        "Bye. Hope to see you again soon!" + "\n" + line
        );

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.respond();
    }

}