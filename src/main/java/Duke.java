import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String banner = "★★★★★★★★★★★★\n"
                + " TED ʕ•́ᴥ•̀ʔっ \n"
                + "★★★★★★★★★★★★\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks ʕ•́ᴥ•̀ʔっ\n"
                + "How can I assist you today?");

        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        Task[] tasks = new Task[100];

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("bye")) {
                System.out.println("Goodbye! Have a pleasant day ʕ•́ᴥ•̀ʔっ");
                sc.close();
                return;
            }

            if (command.equals("list")) {
                System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "Your tasklist:");
                for (int i = 0; i < Task.getTotalNumberOfTasks(); i++) {
                    int bulletPoint = i + 1;
                    System.out.println(bulletPoint + ". " + tasks[i]);
                }
                System.out.println("~\n");
                continue;
            }

            if (command.length() > 4 && command.substring(0, 4).equals("mark")) {
                int currTaskNumber = Integer.parseInt(command.substring(5));
                Task currTask = tasks[currTaskNumber - 1];
                currTask.markDone();

                System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "Great! Task done:\n" + currTask + "\n~\n");
                continue;
            }

            if (command.length() > 6 && command.substring(0, 6).equals("unmark")) {
                int currTaskNumber = Integer.parseInt(command.substring(7));
                Task currTask = tasks[currTaskNumber - 1];
                currTask.unmarkDone();

                System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "Aw :( Task undone:\n" + currTask + "\n~\n");
                continue;
            }

            tasks[Task.getTotalNumberOfTasks()] = new Task(command);
            System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "added to tasklist: " + command + "\n" + "~\n");
        }
    }
}
