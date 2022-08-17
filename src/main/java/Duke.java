import java.util.*;

public class Duke {

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }
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
                        dukeOutput = ("    " + "Invalid number of arguments, only one required\n");
                    } else {
                        dukeOutput = "    " + "Bye. Hope to see you again soon!";
                    }
                    break;
                case "list":
                    if (parsedUserResponse.length > 1) {
                        dukeOutput = ("    " + "Invalid number of arguments, only one required\n");
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
                        dukeOutput = ("    " + "Invalid number of arguments, two required\n");
                    } else {
                        Integer taskNumber = Integer.parseInt(userResponse.replaceAll("[^0-9]", "")) - 1;
                        try {
                            Task userTask = userTasks.get(taskNumber);
                            if (userTask.isCompleted()) {
                                dukeOutput = "    " + "This task is already marked as done.\n";
                            } else {
                                userTask.setCompleted();
                                dukeOutput = "    " + "Nice! I've marked this task as done:\n"
                                        + "      " + userTask + "\n";
                            }
                        } catch (IndexOutOfBoundsException e) {
                            dukeOutput = "    " + "No such task exists.\n";
                        }
                    }

                    break;
                case "unmark":
                    if (parsedUserResponse.length != 2) {
                        dukeOutput = ("    " + "Invalid number of arguments, two required\n");
                    } else {
                        Integer taskNumber = Integer.parseInt(userResponse.replaceAll("[^0-9]", "")) - 1;
                        try {
                            Task userTask = userTasks.get(taskNumber);
                            if (!userTask.isCompleted()) {
                                dukeOutput = "    " + "This task is already marked as not done yet.\n";
                            } else {
                                userTask.setUncompleted();
                                dukeOutput = "    " + "OK, I've marked this task as not done yet:\n"
                                        + "      " + userTask + "\n";
                            }
                        } catch (IndexOutOfBoundsException e) {
                            dukeOutput = "    " + "No such task exists.\n";
                        }
                    }
                    break;
                case "todo":
                    if (parsedUserResponse.length < 2) {
                        dukeOutput = "    " + "☹ OOPS!!! The description of a todo cannot be empty.\n";
                    } else {
                        String newTaskDescription = String.join(" ",
                                                                Arrays.copyOfRange(parsedUserResponse,
                                                                              1,
                                                                                   parsedUserResponse.length));
                        Task newUserTask = new Todo(newTaskDescription);
                        userTasks.add(newUserTask);
                        dukeOutput = "    " + "Got it. I've added this task:\n"
                                   + "        " + newUserTask + "\n"
                                   + "    " + "Now you have " + userTasks.size() + " tasks in the list.\n";
                    }
                    break;
                case "deadline":
                    int bySeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/by");
                    if (bySeparationIndex == -1) {
                        dukeOutput = "    " + "Error: No date added for the deadline.\n";
                    } else {
                        String newTaskDescription = String.join(" ",
                                                                Arrays.copyOfRange(parsedUserResponse,
                                                                              1,
                                                                                bySeparationIndex));
                        String newTaskDeadline = String.join(" ",
                                                            Arrays.copyOfRange(parsedUserResponse,
                                                                    bySeparationIndex + 1,
                                                                    parsedUserResponse.length));
                        Task newUserTask = new Deadline(newTaskDescription, newTaskDeadline);
                        userTasks.add(newUserTask);
                        dukeOutput = "    " + "Got it. I've added this task:\n"
                                + "        " + newUserTask + "\n"
                                + "    " + "Now you have " + userTasks.size() + " tasks in the list.\n";
                    }

                    break;
                case "event":
                    int atSeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/at");
                    if (atSeparationIndex == -1) {
                        dukeOutput = "    " + "Error: No date added for the event.\n";
                    } else {
                        String newTaskDescription = String.join(" ",
                                Arrays.copyOfRange(parsedUserResponse,
                                        1,
                                        atSeparationIndex));
                        String newEventTime = String.join(" ",
                                Arrays.copyOfRange(parsedUserResponse,
                                        atSeparationIndex + 1,
                                        parsedUserResponse.length));
                        Task newUserTask = new Event(newTaskDescription, newEventTime);
                        userTasks.add(newUserTask);
                        dukeOutput = "    " + "Got it. I've added this task:\n"
                                + "        " + newUserTask + "\n"
                                + "    " + "Now you have " + userTasks.size() + " tasks in the list.\n";
                    }

                    break;
                case "delete":
                    if (parsedUserResponse.length != 2) {
                        dukeOutput = "    " + "Invalid number of arguments, two required\n";
                    } else {
                        int taskNumber = Integer.parseInt(userResponse.replaceAll("[^0-9]", "")) - 1;
                        try {
                            Task userTask = userTasks.get(taskNumber);
                            userTasks.remove(taskNumber);
                            dukeOutput = ("    " + "Noted. I've removed this task:\n" +
                                    "    " + userTask + "\n" +
                                    "    " + "Now you have " + userTasks.size() +  " tasks in the list.\n");
                        } catch (IndexOutOfBoundsException e) {
                            dukeOutput = "    " + "No such task exists.\n";
                        }
                    }

                    break;
                default:
                    dukeOutput = "    " + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
            System.out.println(dukeOutput);
        }

    }
}
