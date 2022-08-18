import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String startMsg = "Hello!!! My name is Wanya! \n WWaku WWaku! \n How can I help you? \n";
        String closeMsg = "I am so sad that you have to go :( Bye bye :(";
        Scanner sc = new Scanner(System.in);
        System.out.println(startMsg);

        while (sc.hasNext()) {
            String commandInput = sc.nextLine();
            if (commandInput.equals("bye")) {
                System.out.println(closeMsg);
                break;
            } else {
                System.out.println(commandInput);
            }
        }
    }
}
