import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(dukeGreeting);

        Scanner sc = new Scanner(System.in);
        String userResponse = null;
        ArrayList<Task> userTasks = new ArrayList<>();
        while (userResponse == null || !userResponse.equals("bye")) {
            userResponse = sc.nextLine();
            String dukeOutput = "";
            String[] parsedUserResponse = userResponse.split(" ");
            String mainCommand = parsedUserResponse[0];
            switch(mainCommand) {
                case "":
                    break;
                case "bye":
                    if (parsedUserResponse.length > 1) {
                        System.out.println("Invalid number of arguments");
                    } else {
                        dukeOutput = "    " + "Bye. Hope to see you again soon!";
                    }
                    break;
                case "list":
                    if (parsedUserResponse.length > 1) {
                        System.out.println("Invalid number of arguments");
                    } else {
                        dukeOutput = "    " + "Here are the tasks in your list:\n";
                        for (int i = 0; i < userTasks.size(); i++) {
                            Task userTask = userTasks.get(i);
                            dukeOutput += "        " + (i + 1) + ". " + userTask + "\n";
                        }
                    }
                    break;
                case "mark":
                    if (parsedUserResponse.length != 2) {
                        System.out.println("Invalid number of arguments");
                    } else {
                        Integer taskNumber = Integer.parseInt(userResponse.replaceAll("[^0-9]", "")) - 1;
                        Task userTask = userTasks.get(taskNumber);
                        if (userTask.isCompleted()) {
                            dukeOutput = "    " + "This task is already marked as done.\n";
                        } else {
                            userTask.setCompleted();
                            dukeOutput = "    " + "Nice! I've marked this task as done:\n"
                                    + "      " + userTask + "\n";
                        }
                    }
                    break;
                case "unmark":
                    if (parsedUserResponse.length != 2) {
                        System.out.println("Invalid number of arguments");
                    } else {
                        Integer taskNumber = Integer.parseInt(userResponse.replaceAll("[^0-9]", "")) - 1;
                        Task userTask = userTasks.get(taskNumber);
                        if (!userTask.isCompleted()) {
                            dukeOutput = "    " + "This task is already marked as not done yet.\n";
                        } else {
                            userTask.setUncompleted();
                            dukeOutput = "    " + "OK, I've marked this task as not done yet:\n"
                                    + "      " + userTask + "\n";
                        }

                    }
                    break;
                default:
                    Task newUserTask = new Task(userResponse);
                    userTasks.add(newUserTask);
                    dukeOutput = "    " + "added: " + userResponse;
            }
            System.out.println(dukeOutput);
        }

    }
}
