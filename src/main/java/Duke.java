import java.util.Scanner;

public class Duke {
    private static String printSpacer() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    public static void main(String[] args) {
        System.out.println(printSpacer());
        System.out.println("(｡･ω･｡)ﾉ♡ Hello! This is Duke! What can I do for you today?");
        System.out.println(printSpacer());
        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.nextLine();

            if (s.equals("bye")) {
                System.out.println("(~‾▿‾)~ Bye. Hope to see you again soon!");
                System.out.println(printSpacer());
                break;
            } else {
                System.out.println(s);
                System.out.println(printSpacer());
            }
        }
    }
}
