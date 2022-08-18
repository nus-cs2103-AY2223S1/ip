import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] arr = new String[100];
        int i = 0;
        String line = "\n----------------------------------------------------------------\n";
        System.out.println(line + "Hello! I'm Duke\n" + "What can I do for you?" + line + "\n");


        while (true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again!" + line);
                break;
            }
            else if (input.equals("list")) {
                for (int j = 1; j < i + 1; j++) {
                    System.out.println("" + j + ". " + arr[j - 1]);
                }
            }

            else {
                arr[i] = input;
                System.out.println(line + "added: " + input + line);
                i++;
            }

        }
    }
}
