import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String BYE = "bye";

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equals(BYE)) {
                break;
            } else {
                Echos(userInput);
            }
        }

        Bye();
    }

    public static void Echos(String str) {
        System.out.println("--------------------------------");
        System.out.println(str);
        System.out.println("--------------------------------");

    }

    public static void Bye() {
        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}
