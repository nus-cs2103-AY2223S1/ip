import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introParagraph = TextFormatter.formatLine("Hello! I'm Duke") +
                TextFormatter.formatLine("What can I do for you?");
        String formattedIntro = TextFormatter.formatParagraph(introParagraph);

        System.out.println(formattedIntro);

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        DukeList dukelist = new DukeList();

        while(!terminate) {
            String nextLine = sc.nextLine();
            switch (nextLine) {
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
                default:
                    dukelist.add(nextLine);
                    String outputString = "added: " + nextLine;
                    System.out.println(TextFormatter.formatParagraph(
                            TextFormatter.formatLine(outputString)
                    ));
            }
        }
    }
}
