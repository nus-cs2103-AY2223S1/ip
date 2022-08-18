import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String[] arr = new String[100];
    private static int arrPointer = 0;

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        boolean inProgress = true;

        while (inProgress) {
            String userInput = bufferedReader.readLine();
            switch (userInput) {
                case "list":
                    listArrItems();
                    break;
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    inProgress = false;
                    break;
                case "":
                    break;
                default:
                    addArrItem(userInput);
                    System.out.println("\tadded: " + userInput);
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    private static void listArrItems() {
        if (arrPointer == 0) {
            System.out.println("\t" + "list is empty");
        }
        for (int i = 0; i < arrPointer; i++) {
            System.out.println("\t" + (i + 1) + ". " + arr[i]);
        }
    }

    private static void addArrItem(String newString) {
        arr[arrPointer++] = newString;
    }
}
