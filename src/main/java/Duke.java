import java.util.Scanner;
import static java.lang.System.exit;

public class Duke {
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

        /*
        Scanner sc= new Scanner(System.in);
        Recorder echo = new Recorder();
        String input = sc.nextLine();
        while(!input.equals("bye")){
            System.out.println(border);
            echo.echo(input);
            System.out.println(border);
            input = sc.nextLine();
        }
        */


        Scanner sc= new Scanner(System.in);
        ToDoList list = new ToDoList();

        String input = sc.nextLine();
        while(!input.equals("bye")){
            System.out.println(border);
            if (!input.equals("list")) {
                list.addTask(input);
            } else {
                list.printList();
            }
            System.out.println(border);
            input = sc.nextLine();
        }

        System.out.println(border + goodbye + border);
        exit(0);

    }
}
