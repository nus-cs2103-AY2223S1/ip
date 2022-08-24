import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    public static void main(String[] args) {
        Storage storage = new Storage("src/filestorage/dummylist.txt");
        ArrayList<Task> list = storage.readData();
        UserInterface.copyList(list);
//        Storage storage = new Storage("src/filestorage/dummylist.txt");
//        ArrayList<Task> list = storage.readData();
//        UserInterface.copyList(list);
        boolean bye = false;
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println("HELLO!");
        while (!bye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye = true;
                //UserInterface.Bye(storage);
            } else if (input.equals("list")) {
                UserInterface.showList();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                UserInterface.markChild(index);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                UserInterface.unmarkChild(index);
            } else if (input.startsWith("delete ")) {
                UserInterface.deleteTodo(input);
            } else {
                UserInterface.addTodo(input);
            }
        }
    }
}
