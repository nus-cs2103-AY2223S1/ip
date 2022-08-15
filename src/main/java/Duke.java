import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> stringArr = new ArrayList<>();

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }

    public void sayBye() {
        String message = "Bye! Hope to see you soon!";
        System.out.println(message);
    }

    public void pushString(String str) {
        this.stringArr.add(str);
    }

    public void listStringArr() {
        int len = this.stringArr.size();
        for (int i = 0; i < len; i++) {
            System.out.printf("%d. %s%n", i + 1, this.stringArr.get(i));
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        duke.greetUser();

        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                duke.sayBye();
                break;
            }

            if (input.equals("list")) {
                duke.listStringArr();
            } else {
                System.out.println("added: " + input);
                duke.pushString(input);
            }
        }
    }
}