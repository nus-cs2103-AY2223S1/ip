import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introParagraph = TextFormatter.formatLine("Hello! I'm Duke") +
                TextFormatter.formatLine("What can I do for you?");
        String formattedIntro = TextFormatter.formatParagraph(introParagraph);

        System.out.println(formattedIntro);

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        TaskList dukelist = new TaskList();

        while(!terminate) {
            String nextLine = sc.nextLine();
            String[] splitted = nextLine.split("\\s+");
            String command = splitted[0];
            switch (command) {
                case "bye":
                    System.out.println(TextFormatter.formatParagraph(
                            TextFormatter.formatLine("Bye. Hope to see you again soon!")
                    ));
                    terminate = true;
                    sc.close();
                    break;
                case "list":
                    System.out.println(TextFormatter.formatParagraph(
                            dukelist.toString()
                    ));
                    break;
                case "mark":
                    int index = Integer.parseInt(splitted[1]);
                    String markedItem = "  " + dukelist.markItem(index);
                    String markedText = "Nice! I've marked this task as done:";
                    System.out.println(TextFormatter.formatParagraph(
                            TextFormatter.formatLine(markedText) +
                            TextFormatter.formatLine(markedItem)
                    ));
                    break;
                case "unmark":
                    index = Integer.parseInt(splitted[1]);
                    String unmarkedItem = "  " + dukelist.unmarkItem(index);
                    String unmarkedText = "OK, I've marked this task as not done yet:";
                    System.out.println(TextFormatter.formatParagraph(
                            TextFormatter.formatLine(unmarkedText) +
                            TextFormatter.formatLine(unmarkedItem)
                    ));
                    break;
                default:
                    dukelist.add(nextLine);
                    String outputString = "added: " + command;
                    System.out.println(TextFormatter.formatParagraph(
                            TextFormatter.formatLine(outputString)
                    ));
            }
        }
    }
}
