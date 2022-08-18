import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("\tHello! I'm Duke\t\nWhat can I do for you?");
        while (true) {
            String userInput = bufferedReader.readLine();
            if (userInput.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            System.out.println("\t" + userInput);
        }
        bufferedReader.close();
        inputStreamReader.close();
    }
}
