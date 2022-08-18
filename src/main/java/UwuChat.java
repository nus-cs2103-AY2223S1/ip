import java.util.ArrayList;

public class UwuChat {
    private String horizontalLine ="-----------------------------------------------------";
    private ArrayList<String> userToDoArray = new ArrayList<String>();

    private void chatify(String uwuChat) {
        System.out.println(horizontalLine + uwuChat + "\n" + horizontalLine);
    }

    private String listify() {
        int count = userToDoArray.size();
        String userToDoStr = "";

        for (int i = 0; i<count; i++) {
            String listItem = String.valueOf(i + 1) + ".\t" + userToDoArray.get(i);

            userToDoStr = userToDoStr + "\n" + listItem;
        }

        return userToDoStr;
    }

    public void greetingUsers() {
        String greetings = "\n\tこんにちわ! " +
                           "\n\tわたしはううです <:" +
                           "\n\tよろしくおねがいします！" +
                           "\n\thello there, how can i be of service today? <3";

        chatify(greetings);
    }

    public void leavingChat() {
        String farewellWords = "\n\tgood work today! hope to see you again soon~";

        chatify(farewellWords);
    }

    public void userCommands(String userCmd) {

        if (userCmd.equals("list")) {
            chatify(listify());
        } else {
            userToDoArray.add(userCmd);

            String addToList = "\nadded:\t" + userCmd;
            chatify(addToList);
        }
    }
}
