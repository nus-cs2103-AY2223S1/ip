import java.util.ArrayList;

public class UwuChat {
    private ArrayList<Task> userToDoArray = new ArrayList<Task>();

    private void chatify(String uwuChat) {
        String horizontalLine ="-----------------------------------------------------";
        System.out.println(horizontalLine + uwuChat + "\n" + horizontalLine);
    }

    public void greetingUsers() {
        String greetings = "\n\thellu!" +
                           "\n\ti am oo woo <:" +
                           "\n\thow can i be of service today?";

        chatify(greetings);
    }

    public void leavingChat() {
        String farewellWords = "\n\tgood work today!" +
                                "\n\thope to see you again soon~";

        chatify(farewellWords);
    }

    public void addTask(String userCmd) throws UwuException {
        if (userCmd.replaceFirst("todo", "").isBlank() ||
            userCmd.replaceFirst("deadline", "").isBlank() ||
            userCmd.replaceFirst("event", "").isBlank()) {
            throw new EmptyInputException("No task given.");
        }

        Task task = null;

        if (userCmd.startsWith("todo")) {
            task = new ToDos(userCmd);
            userToDoArray.add(task);
        }

        if (userCmd.startsWith("deadline")) {
            if (!userCmd.contains("/by ")) {
                throw new IncorrectFormatException("Incorrect Format given for deadline task.");
            }

            int deadlineStart = userCmd.indexOf("/by ");
            int userCmdLen = userCmd.length();
            String description = userCmd.substring(8, deadlineStart);
            String by = userCmd.substring(deadlineStart + 3, userCmdLen);

            task = new Deadline(description, by);
            userToDoArray.add(task);
        }

        if (userCmd.startsWith("event")) {
            if (!userCmd.contains("/at ")) {
                throw new IncorrectFormatException("Incorrect Format given for event task.");
            }

            int eventStart = userCmd.indexOf("/at");
            int userCmdLen = userCmd.length();
            String description = userCmd.substring(8, eventStart);
            String at = userCmd.substring(eventStart + 3, userCmdLen);

            task = new Event(description, at);
            userToDoArray.add(task);
        }

        if (task != null) {
            String addToList = "\n\too new task! ^^" +
                    "\n\t\tadded:\t" + task.toString() +
                    "\n\tyou have " + String.valueOf(userToDoArray.size()) + " task(s) <:";
            chatify(addToList);
        }
    }
    public void listTasks() {
        chatify("\n\there are your tasks~ you've got this!" + listify());
    }

    private String listify() {
        int count = userToDoArray.size();
        String userToDoStr = "";

        for (int i = 0; i<count; i++) {
            String listItem = "\t" + String.valueOf(i + 1) + ".\t" + userToDoArray.get(i).toString();

            userToDoStr = userToDoStr + "\n" + listItem;
        }

        return userToDoStr;
    }

    public void markTasks(String userCommand) {
        if (userCommand.startsWith("mark")) {
            userCommand = userCommand.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(userCommand) - 1;

            Task task = userToDoArray.get(index);
            task.markAsDone();

            String markedAsDone = "\n\tyey! good job~ keep it up <3";
            chatify(markedAsDone + "\n\t\t" + task.toString());
        } else {
            userCommand = userCommand.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(userCommand) - 1;

            Task task = userToDoArray.get(index);
            task.unmark();

            String unmarked = "\n\tkeep going~";
            chatify(unmarked + "\n\t\t" + task.toString());
        }
    }

    public void unknownCommand() {
        chatify("\n\tsorry >< i don't know what that means TT");
    }

    public void emptyInput() {
        chatify("\n\tlooks like there isn't a task for me to add uwu" +
                "\n\tfeed me a task to get started~");
    }

    public void incorrectFormat() {
        chatify("\n\tplease enter the correct format for your task uwu");
    }

}
