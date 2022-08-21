public class TaskCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            String cmd = ui.getCmd();
            String desc = ui.getDesc();
            String date = "";
            if(desc.isBlank()) {
                DobbyChat.noTaskDesc();
            }
            if(cmd == "todo") {
                Todo newTodo = new Todo(desc);
                dl.add(newTodo);
                DobbyChat.added(newTodo, dl);
            } else {
                date = ui.getDate();
                if(date == "wrongDateFormat") {
//                    //this is blanked out to prevent double wrongDateFormat messages
//                    DobbyChat.wrongDateFormat();
                } else if(date == "noDate") {
                    DobbyChat.noEventDate();
                } else {
                    if(cmd == "deadline") {
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
