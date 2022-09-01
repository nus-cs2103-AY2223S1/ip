package clevernotbot;

public class AddCommandEvent extends Command {

    public AddCommandEvent(String commandName, boolean exit) {
        super(commandName, exit);
    }

    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ");
        try{
            if (desc.length == 1) {
                throw new CleverNotBotException("Please fill in the description of Task.Event!", textBox);
            } else if (!getCommandName().contains("/at")) {
                throw new CleverNotBotException("Please include a /at in your description of Task.Deadline! ", textBox);
            } else {
                String searchWord = " /at";
                int start = "event ".length();
                int mid = getCommandName().indexOf(searchWord);
                String commandName = getCommandName().substring(start, mid);
                String at = getCommandName().substring(mid + searchWord.length() + 1); // to remove the space;
                Task newTask = new Event(commandName, false, at);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                textBox.chat(String.format(
                        "Got it. I've added this task:" +
                                "\n  %s" +
                                "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize()));
            }
        } catch(CleverNotBotException e){
            throw new CleverNotBotException("Task.Event description must not be empty or must contain /at!", textBox);
        }

    }

}
