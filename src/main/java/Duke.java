import java.util.*;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String[] list = new String[100];
        int i = 0;
        boolean loop = true;
        System.out.println("Hello! Duke here, how can I help you?");
        Scanner sc = new Scanner(System.in);

        while (loop) {
            if (sc.hasNext("bye")) {
                loop = false;
                break;
            }

            if (sc.hasNext("list")) {
                for (int j = 0; list[j] != null; j++) {
                    System.out.println(j+1 + ": " + list[j]);
                }
                sc.nextLine();
                continue;
            }

            String str = sc.nextLine();
            list[i] = str;
            i++;
            System.out.println("added: " + str);
        }

        System.out.println("Thanks for having me!\nHave a nice day ahead!");
    }
}
