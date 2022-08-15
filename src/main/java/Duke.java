import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        boolean bye = false;
        ArrayList<String> toDoList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("HELLO!");

        while (!bye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println("Bye Bye!");
            } else if (input.equals("list")) {
                for (int i = 1; i <= toDoList.size(); i++) {
                    System.out.println(i +  ". " +toDoList.get(i-1));
                }
            }

            else {
                toDoList.add(input);
                System.out.println("added: " + input);
            }
        }

    }
}
