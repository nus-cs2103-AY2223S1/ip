import java.util.Scanner;

/**
 * Text UI of application that deals with interactions with the user.
 *
 * @author WR3nd3
 */
public class Ui {

    private static final String CAT_SYMBOL = "     /\\_____/\\\n"
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
    private static final String BORDER = "\n____________________________________________________________\n";
    private static final String SERVICE = "What can I do for mew?\n";
    private static final String GOODBYE = "Bye! See nya later!\n";

    private static final String TAB = "    ";


    public String readCommand() {
        String input = "did not wait";
        Scanner sc= new Scanner(System.in);
        //System.out.println(sc);
//        while (sc.hasNextLine()) {
//            input = sc.nextLine().trim();
//            break;
//        }
//        input = in.next
//        sc.close();
//        return input;
        if (sc.hasNextLine()) {
            input = sc.nextLine().trim();
            //System.out.println(sc.hasNextLine());
        }
        sc.close();
       
        return input;
    }

    public void showLine() {
        System.out.println(BORDER);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Meow from\n" + CAT_SYMBOL + "\n" + SERVICE);
        showLine();
    }

    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    public void showAdd(String id, Task task, int tasksLeft) {
        String message = "";
        switch(id) {
        case "T":
            message = "Meow! I'm a cat. I've added this task:\n";
            break;
        case "D":
            message = "Woof! I'm a cat. I've added this task:\n";
            break;
        case "E":
            message = "Moo! I'm a cat. I've added this task:\n";
            break;
        default:
            break;
        }
        System.out.println(message + task + "\n");
        showTasksLeft(tasksLeft);
    }

    public void showMark(Task t) {
        String msg = "Nyace! One step closer to nap!\n";
        System.out.println(msg + TAB + t);
    }

    public void showUnmark(Task t) {
        String msg = "You nyapped for too long!\n";
        System.out.println(msg + TAB + t);
    }

    public void showDelete(Task t, int tasksLeft) {
        String msg = "It's dead!! It's deadsss!\n";
        System.out.println(msg + TAB + t + "\n");
        showTasksLeft(tasksLeft);
    }

    public void showList(String[] list, int tasksLeft) {
        String message;
        if (tasksLeft == 0) {
            message = "NYAAA! 00 Tasks means nap time.\n";
            System.out.println(message);
        } else {
            message = "Here nya the tasks in your list:\n";
            StringBuilder builder = new StringBuilder(message);
            for (String s : list) {
                builder.append(s).append("\n");
            }
            System.out.println(builder);
        }


    }
    public void showEmpty() {
        System.out.println("There are NYA tasks hereeeee");
    }

    public void showTasksLeft(int tasksLeft) {
        String str = "Nyaw you have " + Math.max(tasksLeft, 0) + " ";
        if (tasksLeft > 1) {
            str += "tasks in the list";
        } else {
            str += "task in the list";
        }
        System.out.println(str);
    }

    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    public void showLoadingError() {
        System.out.println("File cannyat load. Please check saved list text");
    }

}
