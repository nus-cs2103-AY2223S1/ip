import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String[] list = new String[500];
        int count = 0;

        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello I'm Duke\nWhat can I do for you");
        System.out.print("You: ");
        String item = scan.nextLine();

        while (!item.equals("bye")) {
            if (item.equals("list")) {
                for(int i = 0; i < list.length; i++){
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println(Integer.toString(i + 1) + ". " + list[i]);
                }
            }
            else{
                list[count] = item;
                count = count + 1;
                System.out.println("Duke: added: " + item);
            }
            System.out.print("You: ");
            item = scan.nextLine();
        }
        System.out.println("Duke: Bye. hope to see you again soon!");
    }
}
