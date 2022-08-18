import java.util.List;
import java.util.ArrayList;
public class ChatBot {
    private final String name;
    private boolean runningState;
    private final List<String> taskList;
    ChatBot(String name) {
        this.name =  name;
        this.runningState = false;
        this.taskList = new ArrayList<>();
    }

    public void initialize() {
        this.runningState = true;
        System.out.println(wrapMessage("Greetings, " + this.name + " at your service.\n" +
                "How may I help you today?"));
    }

    public void terminate() {
        this.runningState = false;
        System.out.println(wrapMessage("Goodbye! It was nice seeing you."));
    }

    public boolean isRunning() {
        return this.runningState;
    }

    public void processCommand(String command) {
        switch (command) {
            case "bye":
                this.runningState = false;
                break;
            case "list":
                System.out.println(wrapMessage(this.taskList));
                break;
            default:
                this.taskList.add(command);
                System.out.println(wrapMessage("\t> Added: " + command));
                break;
        }
    }

    private String wrapMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------------------------------------\n");
        stringBuilder.append(str);
        stringBuilder.append("\n--------------------------------------------------");
        return stringBuilder.toString();
    }

    private String wrapMessage(List<String> strList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------------------------------------\n");
        for (int i = 0; i < strList.size(); i++) {
            stringBuilder.append("\t" + (i + 1) + ") " + strList.get(i) + "\n");
        }
        stringBuilder.append("--------------------------------------------------");
        return stringBuilder.toString();
    }

}