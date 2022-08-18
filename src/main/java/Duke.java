import java.util.Locale;
import java.util.Scanner;
public class Duke {
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static String[] userTexts = new String[100];
    private static int index = 0;
    private static void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    private static void appendArray(String inputString) {
        userTexts[index] = inputString;
        index++;
    }

    private static String generateList() {
        String listInString = "";
        for (int i = 0; i < index; i++) {
            listInString += String.valueOf(i) + ". " + userTexts[i];
            if(i != index - 1) {
                listInString += "\n";
            }
        }
        return listInString;
    }

    private static void handleUserInputs(Scanner scanner) {
        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();
            String[] inputs = inputString.split(" ");
            String input = inputs[0];
            if (input.equals("bye")) {
                wrapText(byeMessage);
                break;
            } else if (input.equals("list")) {
                wrapText(generateList());
            } else {
                appendArray(inputString);
                wrapText("added: " + inputString);
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        wrapText(greetMessage);
        Scanner scanner = new Scanner(System.in);
        handleUserInputs(scanner);
    }
}
