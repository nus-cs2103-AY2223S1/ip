package pikachu.command;
import java.time.LocalDate;

import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Deadline;
import pikachu.task.Event;
import pikachu.task.Todo;

public class AddCommand extends Command {
    String input;

    public AddCommand(String fullCommand) {
        input = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        String tempStr = "";
        if (input.startsWith("deadline ") && input.contains(" /by ")) {
            String temp1 = input.split(" ",2)[1];
            String[] temp2 = temp1.split(" /by ",2);
            try {
                LocalDate date = LocalDate.parse(temp2[1]);
                Deadline newDDL = new Deadline(temp2[0],date);
                tasks.taskList.add(newDDL);
                tempStr = "Pikapi(added): " + newDDL + '\n';
                tempStr += "Pikaaaaa: " + tasks.taskList.size() + (tasks.taskList.size() > 1 ? " tasks" : " task");
            } catch(Exception e) {
                throw new PikachuException("Need valid date format(yyyy-mm-dd) Pikaaaa");
            }
        } else if (input.startsWith("todo ")){ //add as tasks
            Todo newTODO = new Todo(input.substring(5));
            if (newTODO.description.equals("")) {
                throw new PikachuException("Pi-cannot be empty-pi");
            } else {
                tasks.taskList.add(newTODO);
                tempStr = "Pikapi(added): " + newTODO + '\n';
                tempStr += "Pikaaaaa: " + tasks.taskList.size() + (tasks.taskList.size() > 1 ? " tasks" : " task");
            }
        } else if (input.startsWith("event ") && input.contains(" /at ")){
            String temp1 = input.split(" ",2)[1];
            String[] temp2 = temp1.split(" /at ",2);
            Event newEvent = new Event(temp2[0],temp2[1]);
            tasks.taskList.add(newEvent);
            tempStr = "Pikapi(added): " + newEvent + '\n';
            tempStr += "Pikaaaaa: " + tasks.taskList.size() + (tasks.taskList.size() > 1 ? " tasks" : " task");
        }  

        storage.save(tasks.taskList);
        System.out.println(tempStr);
        
    }

    public boolean isExit() {
        return false;
    }
    
}
