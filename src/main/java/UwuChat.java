import java.util.ArrayList;

public class UwuChat {
    private ArrayList<Task> userToDoArray = new ArrayList<Task>();

    private void chatify(String uwuChat) {
        String horizontalLine ="-----------------------------------------------------";
        System.out.println(horizontalLine + uwuChat + "\n" + horizontalLine);
    }

    public void greetingUsers() {
        String greetings = "\n\tこんにちわ! " +
                           "\n\t hello" +
                           "\n\t私はううです <:" +
                           "\n\t i am oo woo" +
                           "\n\tよろしくお願します！" +
                           "\n\t how can i be of service today?";

        chatify(greetings);
    }

    public void leavingChat() {
        String farewellWords = "\n\tgood work today!" +
                                "\n\thope to see you again soon~" +
                                "\n\tじゃ、しつれいします~";

        chatify(farewellWords);
    }

    public void addTask(String userCmd) {
        userToDoArray.add(new Task(userCmd));

        String addToList = "\n\too new task! ^^" +
                            "\n\t\tadded:\t" + userCmd;
        chatify(addToList);
    }

    public void listTasks() {
        chatify(listify());
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

            String unmarked = "\n\talmost done! keep going~";
            chatify(unmarked + "\n\t\t" + task.toString());
        }
    }
}
