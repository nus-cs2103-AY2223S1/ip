import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("_____________________________________________________________");
        System.out.printf("Hey there! I'm Bob\nWhat can I do for you?\n");
        System.out.println("_____________________________________________________________");

        String[] toDoList = new String[100];
        int toDoListCount  = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println("_____________________________________________________________");
                for (int i = 0; i < toDoListCount; i ++) {
                    System.out.println(i+1 + ". " + toDoList[i]);
                }
                System.out.println("_____________________________________________________________");
            } else {
                toDoList[toDoListCount] = input;
                System.out.println("_____________________________________________________________");
                System.out.println("added : " + input);
                System.out.println("_____________________________________________________________");
                toDoListCount++;
            }
        }

        sc.close();
    }
}
