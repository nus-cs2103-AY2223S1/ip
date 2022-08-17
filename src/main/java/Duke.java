import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

    public static void tedResponse(String filler) {
        System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + filler + "~\n");
    }

    public static void main(String[] args) {
        String banner = "★★★★★★★★★★★★\n"
                + " TED ʕ•́ᴥ•̀ʔっ \n"
                + "★★★★★★★★★★★★\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks ʕ•́ᴥ•̀ʔっ\n"
                + "How can I assist you today?");

        Scanner sc = new Scanner(System.in).useDelimiter("\\n");

        while (sc.hasNext()) {
            String command = sc.next();

            if (!command.contains(" ")) {
                if (command.equals("bye")) {
                    System.out.println("Goodbye! Have a pleasant day ʕ•́ᴥ•̀ʔっ");
                    sc.close();
                    return;
                }

                if (command.equals("list")) {
                    String listOfTasks = "Your tasklist:\n";
                    for (int i = 0; i < Task.getTotalNumberOfTasks(); i++) {
                        int bulletPoint = i + 1;
                        listOfTasks = listOfTasks + bulletPoint + ". " + tasks[i] + "\n";
                    }
                    tedResponse(listOfTasks);
                    continue;
                }
                continue;
            }

            String[] temp = command.split(" ", 2);
            String action = temp[0];
            String elaboration = temp[1];

            if (action.equals("mark")) {
                int currTaskNumber = Integer.parseInt(elaboration);
                Task currTask = tasks[currTaskNumber - 1];
                currTask.markDone();
                tedResponse("Great! Task done:\n" + currTask + "\n");
                continue;
            }
            if (action.equals("unmark")) {
                int currTaskNumber = Integer.parseInt(elaboration);
                Task currTask = tasks[currTaskNumber - 1];
                currTask.unmarkDone();
                tedResponse("Aw :( Task undone:\n" + currTask + "\n");
                continue;
            }
            if (action.equals("todo")) {
                Task currTask = new Todo(elaboration);
                tasks[Task.getTotalNumberOfTasks() - 1]= currTask;
                tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                        + Task.getTotalNumberOfTasks() + "\n");
            }
            if (action.equals("deadline")) {
                String[] currTaskDesc = elaboration.split(" /by ", 2);
                Task currTask = new Deadline(currTaskDesc[0], currTaskDesc[1]);
                tasks[Task.getTotalNumberOfTasks() - 1]= currTask;
                tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                        + Task.getTotalNumberOfTasks() + "\n");
            }
            if (action.equals("event")) {
                String[] currTaskDesc = elaboration.split(" /at ", 2);
                Task currTask = new Event(currTaskDesc[0], currTaskDesc[1]);
                tasks[Task.getTotalNumberOfTasks() - 1]= currTask;
                tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                        + Task.getTotalNumberOfTasks() + "\n");
            }
        }
    }
}
