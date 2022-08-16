import java.util.Scanner;
import java.util.ArrayList;
public class Kirby {
    public static void main(String[] args) {
        ArrayList<String> previousInputs = new ArrayList<>();
        System.out.println("Kirby: Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" +
                "What amazing plans do you have today?");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                System.out.println("I loved talking to you :> \n" +
                        "Hope to see you again!");
                break;
            }
            if (inputString.equals("list")) {
                System.out.println("Here is what you wanted: ");
                for (int i = 0; i < previousInputs.size(); i++) {
                    System.out.println(i + 1 + ": " + previousInputs.get(i));
                }
            } else {
                previousInputs.add(inputString);
                System.out.println("Added into Kirby's bag of items: " + inputString);
            }
        }
    }
}