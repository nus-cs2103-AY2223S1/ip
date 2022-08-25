import java.util.Arrays;

public class AddFunction extends Function {

    public AddFunction(String function,boolean exit){
        super(function,exit);
    }

    public void run(TaskList tasks,UITextBox textBox) throws CleverNotBotException {
        String[] desc = getFunction().split(" ");
        if(desc[0].equals("todo")){
            if(desc.length != 1){
                String[] descOnly = Arrays.copyOfRange(desc,1,desc.length);
                String joinDesc = String.join(" ", descOnly);
                Task newTask = new ToDo(joinDesc,false);
                tasks.addTask(newTask);
                textBox.chat(String.format(
                         "Got it. I've added this task:" +
                         "\n  %s" +
                         "\nNow you have %d tasks in the list."
                        ,newTask.toString(),tasks.getSize()));
            } else{
                throw new CleverNotBotException("Please fill in the description of ToDo!",textBox);
            }
        } else if(desc[0].equals("deadline")){
            if(desc.length == 1){
                throw new CleverNotBotException("Please fill in the description of Deadline!",textBox);
            } else if (!getFunction().contains("/by")){
                throw new CleverNotBotException("Please include a /by in your description of Deadline! ",textBox);
            } else{
                String searchWord = " /by";
                int start = "deadline ".length();
                int mid = getFunction().indexOf(searchWord);
                String functionName = getFunction().substring(start,mid);
                String by = getFunction().substring(mid + searchWord.length() + 1); // to remove the space
                Task newTask = new Deadline(functionName,false,by);
                tasks.addTask(newTask);
                textBox.chat(String.format(
                         "Got it. I've added this task:" +
                         "\n  %s" +
                         "\nNow you have %d tasks in the list."
                        ,newTask.toString(),tasks.getSize()));
            }
        }
        else if(desc[0].equals("event")){
            if(desc.length == 1){
                throw new CleverNotBotException("Please fill in the description of Event!",textBox);
            } else if (!getFunction().contains("/at")){
                throw new CleverNotBotException("Please include a /at in your description of Deadline! ",textBox);
            } else{
                String searchWord = " /at";
                int start = "event ".length();
                int mid = getFunction().indexOf(searchWord);
                String functionName = getFunction().substring(start,mid);
                String at = getFunction().substring(mid + searchWord.length() + 1); // to remove the space;
                Task newTask = new Event(functionName,false,at);
                tasks.addTask(newTask);
                textBox.chat(String.format(
                         "Got it. I've added this task:" +
                         "\n  %s" +
                         "\nNow you have %d tasks in the list."
                        ,newTask.toString(),tasks.getSize()));
            }
        }else {
            throw new CleverNotBotException("An unexpected error has occurred (addf no results found)",textBox);
        }

    }

}
