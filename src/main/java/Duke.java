import java.util.Scanner;

public class Duke {
    private static String[] textList = new String[100];
    private static int count = 0;
    public static void printBot(String s) {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void printBot(String[] s) {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
        for (int i = 0; i < s.length; ++i) {
            if (s[i] != null) {
                System.out.println((i + 1) + ". " + s[i]);
            }
        }
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Nuke");
        System.out.println("What can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            if (s.equals("list")) {
                printBot(textList);
            } else if (s.equals("bye")) {
                printBot("Bye. Hope to see you again soon!");
                return;
            } else {
                textList[count] = s;
                ++count;
                printBot("added: " + s);
            }
        }
    }
}
