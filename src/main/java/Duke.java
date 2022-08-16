import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<String> list;
    private void line() {
        System.out.println("________________________________________");
    }
    private void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    private void echo(String message) {
        line();
        System.out.println(message);
        line();
    }

    private void store(String input) {
        list.add(input);
        line();
        System.out.println("added: " + input);
        line();
    }

    private void enumerateArrayList(){
        line();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        line();
    }

    private void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        boolean isDone = false;
        duke.list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke.greet();
        while (!isDone) {
            Scanner scanner = new Scanner(System.in); // creating scanner for user input
            String input = scanner.nextLine();
            switch (input) {
                case ("bye"): {
                    duke.exit();
                    isDone = true;
                    break;
                }
                case("list"): {
                    duke.enumerateArrayList();
                    break;
                }
                default: {
                    duke.store(input);
                    break;
                }
            }
        }
    }
}
