import java.util.Scanner;

public class Duke {
    private static String formatLine(String input) {
        return "\t " + input + "\n";
    }

    private static String formatParagraph(String paragraph) {
        String divider = "\t____________________________________________________________\n";
        return divider + paragraph + divider;
    }

    public static void main(String[] args) {
        String introParagraph = formatLine("Hello! I'm Duke") + formatLine("What can I do for you?");
        String formattedIntro = formatParagraph(introParagraph);

        System.out.println(formattedIntro);

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;

        while(!terminate) {
            String nextLine = sc.nextLine();
            switch (nextLine) {
                case "bye":
                    System.out.println(formatParagraph(formatLine("Bye. Hope to see you again soon!")));
                    terminate = true;
                    break;
                default:
                    System.out.println(formatParagraph(formatLine(nextLine)));
                    sc.close();
            }
        }
    }
}

