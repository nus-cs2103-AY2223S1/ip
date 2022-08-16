import java.util.Scanner;

public class Duke {
    static String[] storage = new String[100];
    static int index = 0;

    public static String printList() {
        String list = "";
        for(int i = 0; i < 100; i++) {
            if(storage[i] != null) {
                list += ((i + 1) + ". " + storage[i] + "\n");
            }
        }
        return list;
    }
    public static void main(String[] args) {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println(printList());
            } else {
                System.out.println("added: " + input);
                storage[index] = input;
                index += 1;
            }
        }
    }
}
