import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);*/
        ArrayList<String> strList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String line = "---------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println(line);

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(line);
            if (input.equals("list")) {
                int counter = 1;
                for (String s : strList) {
                    System.out.println(counter + ". " + s);
                    counter++;
                }
            } else {
                strList.add(input);
                System.out.println("added: " + input);
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }
}
