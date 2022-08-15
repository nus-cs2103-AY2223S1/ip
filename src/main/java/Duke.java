import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                String message = "";
                int length = arrayList.size();
                for(int i = 0; i < length; i++) {
                    message += String.format("%d. " + arrayList.get(i) + "\n", i + 1);
                }
                System.out.println(message);
            } else {
                arrayList.add(input);
                System.out.println("added: " + input + "\n");
            }
        }
        sc.close();
    }
}
