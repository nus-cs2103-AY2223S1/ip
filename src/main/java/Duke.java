import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> storeList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();

        Scanner scanner = new Scanner(System.in);
        String userInput = "";



        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if(userInput.equals("list")) {
                list();
                continue;
            }
            if(userInput.equals("bye")) {
                break;
            }
            add(userInput);
        }

        farewell();

    }

    public static void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(greet);
    }

    public static void echo(String userInput) {
        System.out.println(userInput);
    }

    public static void add(String userInput) {
        storeList.add(userInput);
        System.out.println("added: " + userInput);
    }

    public static void list() {
        for(int i=0; i < storeList.size(); i++){
            System.out.println(i + 1 + ". " + storeList.get(i));
        }
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
