import java.util.*;
public class Neo {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<String>();
        String userText;

        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.println("Please enter items you want to add to the list and enter bye to exit");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your task: ");
            userText = sc.next();
            System.out.println("");
            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            } else {
                System.out.println(userText);
            }
        }
    }
}

