package duke.command;

import duke.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a command to print all the relevant events and deadlines on a day.
 */
public class DateCommand extends Command {
    LocalDate date;


    /**
     * A constructor to create a DateCommand class
     *
     * @param date Date of the class if it has and event
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }


    /**
     * A function that executes the effect of printing all the relevant events and deadlines on a day
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        LocalDate date = this.date;
        List<String> onThisDate = new ArrayList<>();
        List<String> byThisDate = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if(task instanceof Event){
                Event event = (Event) task;
                if(event.onThisDate(date)){
                    onThisDate.add(event.toStringDate());
                }
            }
            if(task instanceof Deadline ){
                Deadline deadline = (Deadline) task;
                if( deadline.byThisDate(date)){
                    byThisDate.add( deadline.toStringDate());
                }
            }
        }
        returnString = "Things on this day :" ;
        for(String s : onThisDate) {
            returnString += (s + "\n");
        }
        System.out.println("Things to do by this day :" );
        for(String s : byThisDate) {
            returnString += (s + "\n");
        }
        return returnString;
    }


}
