import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.util.stream.Stream;

public class Luna {
    private static String sep = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";
    private static Scanner sc;

    public static void bye() {
        System.out.println("\n . ❍  ❃ ☆  ✶ ❅  🌙 Goodbye from Luna 🌙  ❅ ✶  ☆ ❃  ❍  .\n");
        sc.close();
    }

    public static void mark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = Task.tasks.get(num - 1);
        curr.mark();
    }

    public static void unmark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = Task.tasks.get(num - 1);
        curr.unmark();
    }

    public static void delete(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task.delete(num);
    }

    public static void loadTasks() {
        String fileName = "./data/luna.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Stream<String> content = reader.lines();

            content.forEach(s -> System.out.println(s));
            System.out.println("\n" + sep);
        } catch (FileNotFoundException e1) {
            try {
                File file = new File(fileName);
                file.getParentFile().mkdirs();
                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                writer.write("  Luna finds the following items saved in your list 🍃");
                writer.close();

                System.out.println("  You do not have anything to do yet!\n  Tell Luna your tasks for the day ☀️\n" + sep);
            } catch (IOException e1a) {
                System.out.println("⚡️Luna has encountered an error while loading tasks⚡️" +
                                    "\n️Please exit and try again ️⛈");
                e1a.printStackTrace();
            }
        } catch (IOException e2) {
            System.out.println("⚡️Luna has encountered an error while loading tasks⚡️" +
                                "\n️Please exit and try again ⛈");
        }
    }

    public static void printCommands() {
        System.out.println("  Luna commands" +
                            "\n    🌸 list                             | View all tasks on your agenda" +
                            "\n    🌷 todo \"task\"                      | Add a task to your agenda" +
                            "\n    🌺 deadline \"task\" /by \"yyyy-mm-dd\" | Add a task to complete by the specified deadline" +
                            "\n    🌹 event \"event\" /at \"yyyy-mm-dd\"    | Add an event on the specified date" +
                            "\n    🪷 mark \"num\"                       | Mark the (num)th item in your list as completed"+
                            "\n    🌻 unmark \"num\"                     | Mark the (num)th item in your list as uncompleted" +
                            "\n    🥀 bye                              | Quit Luna\n");
    }

    public static void handleCommands(Scanner sc) {
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            try {
                if (cmd.equals("list")) {
                    Task.list();
                } else if (cmd.startsWith("mark")) {
                    mark(cmd);
                } else if (cmd.startsWith("unmark")) {
                    unmark(cmd);
                } else if (cmd.startsWith("todo")) {
                    if (cmd.length() <= 5) {
                        throw new LunaException("Please enter a task to do 🌷");
                    }
                    Task curr = new Todo(cmd.substring(5));
                } else if (cmd.startsWith("deadline")) {
                    if (cmd.length() <= 9) {
                        throw new LunaException("Please enter a task and deadline 🌷");
                    }
                    String[] cmds = cmd.split(" /by ");
                    Task curr = new Deadline(cmds[0].substring(9), cmds[1]);
                } else if (cmd.startsWith("event")) {
                    if (cmd.length() <= 6) {
                        throw new LunaException("Please enter an event and date 🌷");
                    }
                    String[] cmds = cmd.split(" /at ");
                    Task curr = new Event(cmds[0].substring(6), cmds[1]);
                } else if (cmd.startsWith("delete")) {
                    delete(cmd);
                }
                else if (!cmd.equals("bye")) {
                    throw new LunaException("I'm not sure what that means 🥀");
                }
            } catch (LunaException e) {
                System.out.println(e);
            }
            cmd = sc.nextLine();
        }

        bye();
    }

    public static void main(String[] args) {
        String logo =
                  "    _\n"
                + "   | |    _   _ _____   ___ _\n"
                + "   | |   | | | |  __ \\ /     |\n"
                + "   | |__ | |_| | |  | |    | |\n"
                + "   |____| \\__,_|_|  |_|\\__/|_|\n";
        System.out.println(sep + "\nHello. ⛅️\n   This is\n" + logo);
        printCommands();
        loadTasks();
        sc = new Scanner(System.in);
        Task.tasks = new ArrayList<Task>();
        handleCommands(sc);
    }
}
