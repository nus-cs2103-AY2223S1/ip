import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    int num = 1;

    ArrayList<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        Duke duke = new Duke();
        while (true) {
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } // Say goodbye.
                duke.PrintCommand(command);
                String list = duke.getList();
                OutputStream out = new FileOutputStream(new File("D:\\cs2103t\\duke.txt"));
                out.write(list.getBytes());
                out.close();
                command = sc.nextLine();
            } catch (DukeException e) {
                System.out.println(e.toString());
                command = sc.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * PrintCommand function to print out the current command.
    *
    * @param command.
    */
    public void PrintCommand(String command) {
        if (command.equals("list")) {
            String list = "";
            for (int k = 1; k < arrayList.size() + 1; k++) {
                list += k + "." + arrayList.get(k - 1) + "\n";
            }
            System.out.println(list);
        }
        else if (command.split(" ")[0].equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            num--;
            Delete task = new Delete(arrayList.get(number), num);
            arrayList.remove(number);
            System.out.println(task.toString());
        }
        else {
            Task task = Task.of(command, arrayList, num);
            System.out.println(task.toString());
            if (task.AddToList()) {
                num++;
            }
        }
    }

    public String getList() {
        String list = "";
        for (int k = 1; k < arrayList.size() + 1; k++) {
            list += k + "." + arrayList.get(k - 1) + "\n";
        }
        return list;
    }
}