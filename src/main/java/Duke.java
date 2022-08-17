import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<String>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = myObj.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String[] inputArray = inputList.toArray(new String[inputList.size()]);
                for (int i = 1; i <= inputArray.length; i++) {
                    String item = String.format("%s. %s", i, inputArray[i - 1]);
                    System.out.println(item);
                }
            } else {
                System.out.println("added: " + input);
                inputList.add(input);
            }
            input = myObj.nextLine();
        }

        myObj.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
