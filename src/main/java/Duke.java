import java.util.Scanner;

public class Duke {

    private static String printSpacer() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    private static String[] tasks = new String[100];
    private static int tasksLength = 0;

    private static boolean cont = true;

    public static void main(String[] args) {
        System.out.println(printSpacer());
        System.out.println("(｡･ω･｡)ﾉ♡ Hello! This is Duke! What can I do for you today?");
        System.out.println(printSpacer());
        Scanner sc = new Scanner(System.in);

        while (cont) {
            String s = sc.nextLine();

            switch (s) {

                case "list":
                    if (tasksLength == 0) {
                        System.out.println("No tasks yet~");
                    } else {
                        for (int i = 1; i <= tasksLength; i++) {
                            System.out.println(i + ". " + tasks[i - 1]);
                        }
                    }
                    System.out.println(printSpacer());
                    break;

                case "bye":
                    System.out.println("(~‾▿‾)~ Bye. Hope to see you again soon!");
                    System.out.println(printSpacer());
                    cont = false;
                    break;

                default:
                    tasks[tasksLength] = s;
                    tasksLength++;
                    System.out.println("Added task: " + s);
                    System.out.println(printSpacer());
            }

        }
    }
}
