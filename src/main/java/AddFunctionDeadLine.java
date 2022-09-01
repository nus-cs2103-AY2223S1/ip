import java.util.Arrays;

public class AddFunctionDeadLine extends Function {

    public AddFunctionDeadLine(String function, boolean exit) {
        super(function, exit);
    }

    public void run(TaskList tasks, UITextBox textBox, Storage storage) throws CleverNotBotException {
        String[] desc = getFunction().split(" ");
        try {
            if (desc.length == 1) {
                throw new CleverNotBotException("Please fill in the description of Deadline!", textBox);
            } else if (!getFunction().contains("/by")) {
                throw new CleverNotBotException("Please include a /by in your description of Deadline! ", textBox);
            } else {
                String searchWord = " /by";
                int start = "deadline ".length();
                int mid = getFunction().indexOf(searchWord);
                String functionName = getFunction().substring(start, mid);
                String dateTime = getFunction().substring(mid + searchWord.length() + 1); // to remove the space
                Task newTask = new Deadline(functionName, false, dateTime);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                textBox.chat(String.format(
                        "Got it. I've added this task:" +
                                "\n  %s" +
                                "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize()));
            }
        } catch (CleverNotBotException e){
            throw new CleverNotBotException("Deadline description must not be empty or must contain /by!", textBox);
        }
    }


}
