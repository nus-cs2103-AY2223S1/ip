import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "-------------------------";
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println("Greetings, human! I am Duke.\n" + "How may I serve you today?" + "\n" + horizontalLine);

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();

        while(!echo.equals("bye")) {
            System.out.println(horizontalLine + "\n" + echo + "\n" + horizontalLine);
            echo = sc.nextLine();
        }
        System.out.println(horizontalLine + "\n" +"Goodbye human. See you again!" + "\n" + horizontalLine);
    }
}
