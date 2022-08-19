import java.util.*;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Duke newDuke = new Duke();
        newDuke.chatDuke();


        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }

    public void chatDuke(){
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        if (str.equals("bye")){
            System.out.println("Bye. Hope to see you again soon");
        } else {
            System.out.println(str);
            chatDuke();
        }


    }


}
