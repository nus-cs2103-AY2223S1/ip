import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

    public static void tedResponse(String filler) {
        System.out.println("~ |._.| ~\n" + filler + "~\n");
    }

    public static void main(String[] args) {
        String banner = "~~~~~~~~~~~\n"
                + " TED |._.|\n"
                + "~~~~~~~~~~~\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks |._.|\n"
                + "How can I assist you today?\n");

        Scanner sc = new Scanner(System.in).useDelimiter("\\n");

        while (sc.hasNext()) {
            String command = sc.next();

            String[] temp = command.split(" ", 2);
            String action = temp[0];

            if (temp.length == 1 && action.equals("bye")) {
                System.out.println("Goodbye! Have a pleasant day |._.|");
                sc.close();
                return;
            }
            if (temp.length == 1 && action.equals("list")) {
                String listOfTasks = "Your tasklist:\n";
                for (int i = 0; i < Task.getTotalNumberOfTasks(); i++) {
                    int bulletPoint = i + 1;
                    listOfTasks = listOfTasks + bulletPoint + ". " + tasks[i] + "\n";
                }
                tedResponse(listOfTasks);
                continue;
            }

            try {
                if (action.equals("mark")) {
                    if (temp.length == 1) {
                        throw new DukeException("Oh no, please indicate task to mark T_T\n");
                    }
                    String elaboration = temp[1];
                    int currTaskNumber = Integer.parseInt(elaboration);
                    Task currTask = tasks[currTaskNumber - 1];
                    currTask.markDone();
                    tedResponse("Great! Task done:\n" + currTask + "\n");
                    continue;
                } else if (action.equals("unmark")) {
                    if (temp.length == 1) {
                        throw new DukeException("Oh no, please indicate task to unmark T_T\n");
                    }
                    String elaboration = temp[1];
                    int currTaskNumber = Integer.parseInt(elaboration);
                    Task currTask = tasks[currTaskNumber - 1];
                    currTask.unmarkDone();
                    tedResponse("Aw :( Task undone:\n" + currTask + "\n");
                    continue;
                } else if (action.equals("todo")) {
                    if (temp.length == 1) {
                        throw new DukeException("Oh no, please include task description T_T\n");
                    }
                    String elaboration = temp[1];
                    Task currTask = new Todo(elaboration);
                    tasks[Task.getTotalNumberOfTasks() - 1] = currTask;
                    tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                            + Task.getTotalNumberOfTasks() + "\n");
                } else if (action.equals("deadline")) {
                    if (temp.length == 1) {
                        throw new DukeException("Oh no, please include task description T_T\n");
                    }
                    String elaboration = temp[1];
                    String[] currTaskDesc = elaboration.split(" /by ", 2);
                    if (currTaskDesc.length == 1) {
                        throw new DukeException("Oh no, please include both deadline description and time T_T\n");
                    }
                    Task currTask = new Deadline(currTaskDesc[0], currTaskDesc[1]);
                    tasks[Task.getTotalNumberOfTasks() - 1] = currTask;
                    tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                            + Task.getTotalNumberOfTasks() + "\n");
                } else if (action.equals("event")) {
                    if (temp.length == 1) {
                        throw new DukeException("Oh no, please include task description T_T\n");
                    }
                    String elaboration = temp[1];
                    String[] currTaskDesc = elaboration.split(" /at ", 2);
                    if (currTaskDesc.length == 1) {
                        throw new DukeException("Oh no, please include both event description and time T_T\n");
                    }
                    Task currTask = new Event(currTaskDesc[0], currTaskDesc[1]);
                    tasks[Task.getTotalNumberOfTasks() - 1] = currTask;
                    tedResponse("added to tasklist:\n" + currTask + "\ntask count: "
                            + Task.getTotalNumberOfTasks() + "\n");
                } else {
                    throw new DukeException("Oh no, I don't understand T_T\n");
                }
            } catch (DukeException e) {
                tedResponse(e.getMessage());
            } catch (NumberFormatException e) {
                tedResponse("Oh no, please indicate task to mark/unmark with a number T_T\n");
            }
        }
    }
}
