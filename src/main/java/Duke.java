import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        boolean hasnext = true;
        List list = new List();

        while (hasnext) {
            String message = sc.nextLine();

            String[] tempMsg = message.split(" ");

            switch (tempMsg[0]) {
                case "mark" :
                    list.dir.get(Integer.parseInt(tempMsg[1]) - 1).mark();
                    break;
                case "unmark" :
                    list.dir.get(Integer.parseInt(tempMsg[1]) - 1).unmark();
                    break;
                case "bye" :
                    hasnext = false;
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                case "list" :
                    System.out.println(list);
                    break;
                default :
                    list.addDir(message);
                    System.out.println(String.format("added: %s\n", message));
            }
        }
    }
}
