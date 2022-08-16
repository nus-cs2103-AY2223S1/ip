import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String reply = "";
        String exit = "bye";
        List<String> todoList = new ArrayList<>();
        System.out.println("hi im chompers what do u need!!!\n");

        while(true) {
            Scanner scanIn = new Scanner(System.in);
            reply = scanIn.nextLine();
            if(reply.equals(exit)) {
                System.out.println("bye see u");
                scanIn.close();
                break;
            } else if (reply.equals("list")) {
                printList(todoList);
            }else {
                todoList.add(reply);
                System.out.println("added: " + reply);
            }

        }
    }
    public static void printList(List<String> list) {
        for(int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }
}
