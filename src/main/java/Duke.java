import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "\n----------------------------------------------------------------\n";
        System.out.println(line + "Hello! I'm Duke\n" + "What can I do for you?" + line + "\n");

        while (true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again!" + line);
                break;
            }
            System.out.println(line + input + line);
        }
    }
}
