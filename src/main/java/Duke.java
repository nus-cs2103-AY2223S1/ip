import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private static ArrayList list = new ArrayList<>();

    private static void addToList(String task){
        list.add(task);
    }

    private static void printList(){
        for (int i=0; i < list.size(); i++){
            String line = String.format("%s. %s", i + 1, list.get(i));
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");
//        boolean closeFlag = false;

        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("bye")) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________");
            if (input.equals("list")){
                printList();
            }
            else {
                addToList(input);
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________");
        }

        System.out.println("____________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________");
        scanner.close();
    }
}
