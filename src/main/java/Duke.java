import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?";
        String exitChat = "Bye. Hope to see you again soon!";

        System.out.println(formatText(startChat));

        while(true) {
            String userInput = sc.nextLine();
            if(userInput.equals("bye")) {
                break;
            }else {
                System.out.println(formatText(userInput));
            }
        }
        System.out.println(formatText(exitChat));
    }
    public static String formatText(String text) {
        String line = "____________________________________________________________";
        return line +"\n" + text+ "\n" + line;
    }
}
