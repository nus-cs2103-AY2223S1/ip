package duke.command;

import duke.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class DateCommand extends Command {
    LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
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
        System.out.println("Things on this day :" );
        for(String s : onThisDate) {
            System.out.println(s + "\n");
        }
        System.out.println("Things to do by this day :" );
        for(String s : byThisDate) {
            System.out.println(s + "\n");
        }
    }


}
