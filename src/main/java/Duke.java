import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> userInputArray = new ArrayList<>();

        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?";
        String exitChat = "Bye. Hope to see you again soon!";

        System.out.println(formatText(startChat));

        while(true) {
            String userInput = sc.nextLine();
            if(userInput.equals("bye")) {
                break;
            }else if(userInput.equals("list")){
                String outputElement ="";
                for(int i=0;i< userInputArray.size(); i++) {
                    outputElement += i +". " + userInputArray.get(i) +"\n";
                }
                outputElement = formatText(outputElement.trim());
                System.out.println(outputElement);
            }else {
                String outputElement ="";
                userInputArray.add(userInput);
                outputElement += "added: " + userInput;
                outputElement = formatText(outputElement);
                System.out.println(outputElement);
            }
        }
        System.out.println(formatText(exitChat));
    }
    public static String formatText(String text) {
        String line = "____________________________________________________________";
        return line +"\n" + text+ "\n" + line;
    }
}
