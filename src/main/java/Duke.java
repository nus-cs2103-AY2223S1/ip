import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    private static final String initialMessage = messageWithIndentedLines("\n      Hello! I'm Duke\n      What can I do for you?\n");
    private static final String byeMessage = messageWithIndentedLines("\n      Bye. This doesn't have to be the end!\n");
    private static final ArrayList<String> userText = new ArrayList<>();

    public static String messageWithIndentedLines(String message) {
        return indentedLine + message + indentedLine;
    }

    public static void main(String[] args) {
        System.out.println(initialMessage);
        Scanner scanner = new Scanner(System.in);
        // the user has to at least input bye to exit
        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (userInput.equals("list")) {
                // Idea below of iterating with indices in streams adapted from https://stackoverflow.com/a/42616742
                HashMap<Integer, String> mappedIndexToUserText = userText.stream()
                        .collect(HashMap::new, (hashMap, streamElement) -> hashMap.put(hashMap.size(), streamElement), HashMap::putAll);
                StringBuilder listOfUserText = mappedIndexToUserText.entrySet().stream()
                        .reduce(new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ").append(currentEntry.getKey()).append(". ").append(currentEntry.getValue()), StringBuilder::append);
                System.out.println(messageWithIndentedLines(listOfUserText.toString() + "\n"));
            } else {
                System.out.println(messageWithIndentedLines("\n      added: " + userInput + "\n"));
                userText.add(userInput);
            }
            userInput = scanner.nextLine();
        }
    }
}
