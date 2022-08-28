import java.io.IOException;
import java.util.Scanner;

public class Amanda {
    public static void main(String[] args) {
        try {
            String logo ="                                           _\n"
                        +"            __ _ _ __ ___   __ _ _ __   __| | __ _\n"
                        +"           / _' | '_ ` _ \\ / _` | '_ \\ / _` |/ _` |\n"
                        +"          | (_| | | | | | | (_| | | | | (_| | (_| |\n"
                        +"           \\__,_|_| |_| |_|\\__,_|_| |_|\\__,_|\\__,_|\n";
            System.out.println(logo);
            System.out.println("    ............................................................");
            System.out.println("     Urgh! It's you\n     What do you want?");
            System.out.println("    ............................................................\n");

            StoreManager manager = new StoreManager();
            QueryInterpreter interpreter = new QueryInterpreter();
            TaskMaker taskMaker = new TaskMaker();
            manager.load();

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            while (!interpreter.getType(input).equals("bye")) {
                System.out.println("    ............................................................");
                if (interpreter.getType(input).equals("list")) {
                    manager.list();
                    input = sc.nextLine();
                } else if (interpreter.getType(input).equals("mark")) {
                    manager.markTask(Integer.parseInt(interpreter.getIndex(input)));
                    manager.store();
                    input = sc.nextLine();
                } else if (interpreter.getType(input).equals("unmark")) {
                    manager.unmarkTask(Integer.parseInt(interpreter.getIndex(input)));
                    manager.store();
                    input = sc.nextLine();
                } else if(interpreter.getType(input).equals("delete")) {
                    manager.deleteTask(Integer.parseInt(interpreter.getIndex(input)));
                    manager.store();
                    input = sc.nextLine();
                } else {
                    if (interpreter.getType(input).equals("task")) {
                        Task currTask = taskMaker.make(input);
                        manager.add(currTask);
                        manager.store();
                        input = sc.nextLine();
                    }
                }
            }
            sc.close();

            System.out.println("    ............................................................");
            System.out.println("     Finally! I'll take a nap, please don't call me again.");
            System.out.println("    ............................................................\n");

        } catch (IOException | AmandaException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
