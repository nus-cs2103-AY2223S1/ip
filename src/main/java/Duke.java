import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {

    private static List<String> dukeInputs = new ArrayList<>();
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("Hello! I'm Duke"); //Can use newline character
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            // 1) Read the input
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < dukeInputs.size(); i++) {
                    System.out.println(i+1 + ". " + dukeInputs.get(i));
                }
            } else { //will only reach this line if it is not bye / list
                dukeInputs.add(input);
                System.out.println("added: " + input);
            }
        }
        //always remember to greet!
        System.out.println(ENDING_MESSAGE);
    }
}

