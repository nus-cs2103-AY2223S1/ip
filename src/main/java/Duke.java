import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //Chatbot intro segment
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hewwo! My name is\n" + logo + "\nWhat can I do for you?");

        //Initialising string array for list cmd
        String[] inputArray = new String[100];
        int numOfInputs = 0;

        Scanner in = new Scanner(System.in);
        String userInput = in.next();

        //Loop continues until bye cmd is given
        while (!userInput.equals("bye")) {
            //System.out.println(userInput + "\n");

            if (userInput.equals("list")) {
                for (int i = 0; i < numOfInputs; i++) {
                    System.out.println((i+1) + ". " + inputArray[i]);
                }
            }

            //Continue
            inputArray[numOfInputs] = userInput;
            numOfInputs += 1;
            userInput = in.next();
        }

        //when bye cmd is given
        System.out.println("UwU You Will Be Missed!");
        in.close();
    }
}
