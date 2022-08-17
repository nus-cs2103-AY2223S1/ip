import java.util.*;

public class Duke {

    private static void listOut(Task[] taskList, int i) {
        int j = 0;
        System.out.println("_________________________");
        while (j < i) {
            System.out.println((j + 1) + ".[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDesc());
            j++;
        }
        System.out.println("_________________________");
    }

    private static void addTask(Task[] taskList, String str, int i) {
        Task task = new Task(str);
        taskList[i] = task;
    }

    private static boolean isInt(String str) {

        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }

    }

    private static int toInt(String str) {

        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return x; //String is an Integer
        } catch (NumberFormatException e) {
            return -1; //String is not an Integer
        }

    }

    public static void main(String[] args) {

        Task taskList[] = new Task[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;

        System.out.println("_________________________");
        System.out.println("    Hi, my name is Doke");
        System.out.println("    What can I do for you?");
        System.out.println("    Enter a String!!");
        System.out.println("_________________________");

        String str= sc.nextLine();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                listOut(taskList, i);
                str = sc.nextLine();
                continue;
            }

            if (str.contains(" ")) {
                String[] temp = str.split(" ");
                int num = toInt(temp[1]);
                boolean x = isInt(temp[1]);

                if (x && num > 0) {
                    if (temp[0].equals("mark")) {
                        Task current = taskList[num - 1];
                        try {
                            current.markDone();
                            System.out.println("_________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[" + current.getStatusIcon()
                                    + "] " + current.getDesc());
                            System.out.println("_________________________");
                            System.out.println();
                        } catch (Exception a) {
                            System.out.println("_________________________");
                            System.out.println("This task is already marked done:");
                            System.out.println("[" + current.getStatusIcon()
                                    + "] " + current.getDesc());
                            System.out.println("_________________________");
                            System.out.println();
                        }
                    }
                    if (temp[0].equals("unmark")) {
                        Task current = taskList[num - 1];
                        try {
                            current.markNotDone();
                            System.out.println("_________________________");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[" + current.getStatusIcon()
                                    + "] " + current.getDesc());
                            System.out.println("_________________________");
                            System.out.println();
                        } catch (Exception a) {
                            System.out.println("_________________________");
                            System.out.println("This task is already marked not done:");
                            System.out.println("[" + current.getStatusIcon()
                                    + "] " + current.getDesc());
                            System.out.println("_________________________");
                            System.out.println();
                        }
                    }
                    str = sc.nextLine();
                    continue;
                }
            }

            addTask(taskList, str, i);
            i++;
            System.out.println("_________________________");
            System.out.println("added: " + str);
            System.out.println("_________________________");
            System.out.println();

            str = sc.nextLine();
        }

        System.out.println("_________________________");
        System.out.println("    Bye bye!");
        System.out.println("_________________________");

    }
}