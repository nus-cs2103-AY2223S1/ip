import java.util.*;
public class Neo{
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String userText;

        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.println("Please enter items you wan to add to the list");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your command: ");
            userText = sc.nextLine();
            System.out.println("");
            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }
            if (userText.equals("list") || userText.equals("List")) {
                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    //System.out.println(j + ". " + arrayList.get(i));
                    Task temp = arrayL.get(i);
                    System.out.println(j + ". [" + temp.getIsDone() + "] " + temp.description);
                }
            }
            String arr[];
            arr = userText.split(" ");
            if (arr.length>1 && arr[0].equals("mark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi-1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done");
                System.out.println("[" + arrayL.get(tempi-1).getIsDone() + "] " + arrayL.get(tempi-1).description);
            }
            if (arr.length>1 && arr[0].equals("unmark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi-1).setIsDone(false);
                System.out.println("ok, I've marked this task as not done yet");
                System.out.println("[" + arrayL.get(tempi-1).getIsDone() + "] " + arrayL.get(tempi-1).description);
            }
            else {
                //System.out.println(userText);
                if (!userText.equals("list") && !userText.equals("List") && arr.length<2) {
                    arrayList.add(userText);
                    Task t = new Task(userText);
                    arrayL.add(t);
                    System.out.println("Added: " + userText);
                }
            }
        }
    }
}

