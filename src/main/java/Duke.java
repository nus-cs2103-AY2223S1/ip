import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);

        String[] list = new String[100];
        int num = 0;

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < num; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
            } else {
                System.out.println("added: " + input);
                list[num] = input;
                num++;
            }
        }
    }
}
