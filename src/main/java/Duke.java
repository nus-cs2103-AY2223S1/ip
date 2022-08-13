import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = "\n" +
                "██████╗░░█████╗░░█████╗░██╗░░░░░░██████╗██╗░░██╗███████╗███████╗███╗░░██╗\n" +
                "██╔══██╗██╔══██╗██╔══██╗██║░░░░░██╔════╝██║░░██║██╔════╝██╔════╝████╗░██║\n" +
                "██████╔╝██║░░██║██║░░██║██║░░░░░╚█████╗░███████║█████╗░░█████╗░░██╔██╗██║\n" +
                "██╔═══╝░██║░░██║██║░░██║██║░░░░░░╚═══██╗██╔══██║██╔══╝░░██╔══╝░░██║╚████║\n" +
                "██║░░░░░╚█████╔╝╚█████╔╝███████╗██████╔╝██║░░██║███████╗███████╗██║░╚███║\n" +
                "╚═╝░░░░░░╚════╝░░╚════╝░╚══════╝╚═════╝░╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝";

        String welcomeMessage = "Hello from" + logo + "\n" +
                "Type something in for Poolsheen to respond to you:";

        System.out.println(welcomeMessage);

        String poolsheenLastReply = "meow *_*";

        String horizontalLine = "---------------";

        String poolshenStartReply = "      ";

        String exitCommand = "bye";

        String exitMessage = "MeoAww... See you next time :(";

        boolean hasExited = false;

        String userInput = "";
        Scanner myScanner = new Scanner(System.in);

        while (!hasExited) {
            userInput = myScanner.nextLine();
            if (userInput.equals(exitCommand)) {
                hasExited = true;
            } else {
                System.out.println(horizontalLine + "\n" + poolshenStartReply + userInput + " " +
                        poolsheenLastReply + "\n" + horizontalLine);
            }
        }

        System.out.println(horizontalLine + "\n" + poolshenStartReply + exitMessage + "\n" + horizontalLine);

    }
}
