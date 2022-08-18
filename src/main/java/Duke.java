import java.util.HashSet;
import java.util.Scanner;

public class Duke {
    static HashSet<String> vocabList = new HashSet<String>();

    public static void echo(String msg) {
        String lineBlock = "     -----------------------------------------";
        System.out.println(lineBlock);
        System.out.println("     " + msg);
        System.out.println(lineBlock);
    }

    static void list(HashSet<String> vocabList) {
        String lineBlock = "     -----------------------------------------";
        int count = 1;

        System.out.println(String.format("%s\n",lineBlock));
        for (String text : vocabList) {
            System.out.println(String.format("     %d. %s\n", count, text));
            count++;
        }
        System.out.println(lineBlock);
    }

    public static void main(String[] args) {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcomeMessage = "> What can I do for you today? : )\n";
        String printText = String.format("> Hello from\n %s%s", logo, welcomeMessage);
        System.out.println(printText);

        while (true) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                String nextString = sc.nextLine();

                if (nextString.equals("bye")) {
                    echo("bye");
                    break;
                }

                if (nextString.equals("list")) {
                    list(vocabList);
                }

                else if (!vocabList.contains(nextString)) {
                    vocabList.add(nextString);
                    
                    printText = String.format("added: %s", nextString);
                    echo(printText);
                }

                else {
                    echo(nextString);
                }
            }
        }
    }
}
