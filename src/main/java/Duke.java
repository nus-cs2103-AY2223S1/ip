import java.util.*;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        boolean loop = true;
        System.out.println("Hello! Duke here, how can I help you?");
        Scanner sc = new Scanner(System.in);

        while (loop) {
            if (sc.hasNext("bye")) {
                loop = false;
            } else {
                String str = sc.nextLine();
                System.out.println(str);
            }
        }

        System.out.println("Thanks for having me!\nHave a nice day ahead!");
    }
}
