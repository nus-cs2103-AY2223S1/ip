import java.util.Scanner;
import parser.Parser;
import printer.Printer;
import storage.Storage;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Printer.print(String.format("Hello from\n%s\nWait, I'm not Duke\n" +
                "I'm Yem, your personal assistant\nWhat can I do for you master?", logo));
        listenCommand();
    }

    private static void listenCommand() {
        Parser parser = new Parser(new Storage());
        String currentText;
        Scanner sc = new Scanner(System.in);

        while(parser.getIsListening()) {
            currentText = sc.nextLine();
            parser.parseText(currentText);
        }

        sc.close();
    }

}
