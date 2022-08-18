import java.util.*;
public class Neo {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<String>();
        String userText;

        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.println("Please enter items you want to add to the list");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your task: ");
            userText = sc.next();
            System.out.println("");
            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }
            if (userText.equals("list") || userText.equals("List")) {
                for (int i = 0; i < arrayList.size(); i++) {
                    int j=i+1;
                    System.out.println(j + ". " + arrayList.get(i));
                }
            } else {
                arrayList.add(userText);
                System.out.println("Added: " + userText);
            }
        }
    }
}

