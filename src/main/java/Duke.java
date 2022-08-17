import java.util.Scanner;
import static java.lang.System.exit;

public class Duke {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE
    }
    public static void main(String[] args) {

        String cat = "     /\\_____/\\\n"
                + "    /  o   o  \\\n"
                + "   ( ==  ^  == )\n"
                + "    )         (\n"
                + "   (           )\n"
                + "  ( (  )   (  ) )\n"
                + " (__(__)___(__)__)\n"
                + "           _\n"
                + "  ___ __ _| |_ ___\n"
                + " / __/ _` | __/ __|\n"
                + "| (_| (_| | |_\\__ \\\n"
                + " \\___\\__,_|\\__|___/\n";
        String border = "\n____________________________________________________________\n";
        String service = "\nWhat can I do for mew?";
        String goodbye = "Bye! See nya later!\n";

        System.out.println(border + "Meow from\n" + cat + service + border);

        Scanner sc= new Scanner(System.in);
        TaskList list = new TaskList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ", 2);
            String cmd = inputArray[0];
            String target = null;

            if (inputArray.length == 2) {
                target = inputArray[1];
            }

            try {
                System.out.println(border);
                Command c = Command.valueOf(cmd.toUpperCase());
                switch(c) {
                    case BYE:
                        System.out.println(goodbye + border);
                        exit(0);
                    case LIST:
                        list.printList();
                        break;
                    case TODO:
                        if (target == null) {
                            throw new IllegalArgumentException();
                        }
                        list.addTodo(target);
                        break;
                    case EVENT:
                        break;
                    case DEADLINE:
                        break;
                    case MARK:
                        if (target == null) {
                            throw new IllegalArgumentException();
                        }
                        int position = Integer.parseInt(target);
                        list.mark(position);
                        break;
                    case UNMARK:
                        if (target == null) {
                            throw new IllegalArgumentException();
                        }
                        position = Integer.parseInt(target);
                        list.unMark(position);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nyat a valid instruction! Rub my belly instead!\n"
                                    + "    'bye' to exit.\n"
                                    + "    'list' for overview\n"
                                    + "    'add ABC' to add task ABC\n"
                                    + "    'mark x' to mark task x as complete\n"
                                    + "    'unmark x' to mark task x as incomplete\n"
                                    + "    NYAAAAAA!\n");
            } finally {
                System.out.println(border);
            }
        }




    }

}
