import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //initialise the array to store texts
        List<String> list = new ArrayList<>();


        //print welcome message
        String welcomeMsg = "Hello! I'm Duke\n     What can I do for you?";
        printEchosWithFormat(welcomeMsg);


        //interaction with user
        while(true) {

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            //special inputs
            if(userInput.equals("bye")) {
                break;
            }
            if(userInput.equals("list")) {
                listStoredTexts(list);
                continue;
            }

            list.add(userInput);
            printEchosWithFormat("added: " + userInput);
        }


        //print ending message
        String endingMsg = "Bye. Hope to see you again soon!";
        printEchosWithFormat(endingMsg);
    }

    private static void printEchosWithFormat(String text) {

        String startHorizontalLines = "     ________________________________________\n";
        String endHorizontalLines = "\n     ________________________________________\n";

        System.out.println(startHorizontalLines + "     " + text + endHorizontalLines);
    }

    private static void listStoredTexts(List<String> list) {

        String text = "";
        for(int i = 0; i < list.size(); i++) {
            if(i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }
        printEchosWithFormat(text);
    }
}
