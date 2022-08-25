package dobby.commands;

import dobby.tasks.*;
import dobby.*;

import java.util.Objects;

public class TaskCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            String cmd = ui.getCmd();
            String desc = ui.getDesc();
            String date;
            if(desc.isBlank()) {
                DobbyChat.noTaskDesc();
            }
            if(cmd.equals("todo")) {
                Todo newTodo = new Todo(desc);
                dl.add(newTodo);
                DobbyChat.added(newTodo, dl);
            } else {
                date = ui.getDate();
                if(Objects.equals(date, "wrongDateFormat")) {
//                    //this is blanked out to prevent double wrongDateFormat messages
//                    DobbyChat.wrongDateFormat();
                } else if(Objects.equals(date, "noDate")) {
                    if (cmd.equals("event")) {
                        DobbyChat.noEventDate();
                    } else {
                        DobbyChat.noDeadlineDate();
                    }
                } else {
                    if(cmd.equals("deadline")) {
                        Deadline newDeadline = new Deadline(desc, date);
                        dl.add(newDeadline);
                        DobbyChat.added(newDeadline, dl);
                    } else {
                        Event newEvent = new Event(desc, date);
                        dl.add(newEvent);
                        DobbyChat.added(newEvent, dl);
                    }
                }
            }
        //only when there is no space behind "deadline", else is handled above
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskDesc();
        }
    }
}
