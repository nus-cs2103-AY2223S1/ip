import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");

        String strInput = sc.nextLine();
        speak(strInput);
    }

    /**
     * Prints out a greeting after user input.
     *
     * @param s Input from user.
     **/
    public static void speak(String s) {
        if (s.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________\n" +
                    "Bye. Hope to see you again soon!" +
                    "\n____________________________________________________________");

        } else {
            System.out.println("____________________________________________________________\n" +
                    s + "\n____________________________________________________________");
        }
    }
}
