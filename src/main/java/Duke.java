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
        String[] tasks = new String[100];
        int taskCounter = 0;

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("bye")) {
                System.out.println("Goodbye! Have a pleasant day ʕ•́ᴥ•̀ʔっ");
                sc.close();
                return;
            }

            if (command.equals("list")) {
                System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "Your tasklist:");
                for (int i = 0; i < taskCounter; i++) {
                    int bulletPoint = i + 1;
                    System.out.println(bulletPoint + ". " + tasks[i]);
                }
                System.out.println("~\n");
                continue;
            }


            tasks[taskCounter] = command;
            taskCounter++;
            System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + "added to tasklist: " + command + "\n" + "~\n");
        }
    }
}
