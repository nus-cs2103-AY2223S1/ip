import java.util.ArrayList;
import java.util.Scanner;

public class Lish {

    public static void printResponse(String response) {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    " + response);
        System.out.println("    ____________________________________________________________\n");
    }

    public static ArrayList<String> tasks = new ArrayList<String>();

    public static void printTasks() {
        System.out.println("    ____________________________________________________________\n");
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.println("    " + i + ". " + tasks.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean stopLish = false;
        String greeting = "Hello! I'm Lish\n" + "    What can I do for you?\n";
        printResponse(greeting);

        while (!stopLish) {
            String input = sc.nextLine();

            switch (input) {
                case "":
                    break;
                case "bye":
                    printResponse(input);
                    stopLish = true;
                    break;
                case "list":
                    printTasks();
                    break;
                default:
                    tasks.add(input);
                    printResponse("added: " + input);
            }
        }
    }
}
