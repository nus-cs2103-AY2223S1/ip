import java.io.File;
import java.security.InvalidParameterException;

public class EventDispatcher {

    private Calendar table;
    private UiHandler ui;
    private FileHandler disk;

    public EventDispatcher(Calendar table, UiHandler ui, FileHandler disk){
        this.table=table;
        this.ui=ui;
        this.disk=disk;
    }

    private int help(){
        ui.cout(UiHandler.generateSection(UiHandler.generateHelpMsg()));
        return 200;
    }
    private int list(){
        ui.cout(UiHandler.generateSection(this.table.toString()));
        return 200;
    }

    @Deprecated
    private int add(String[] entry_info) throws Exception{
        CalendarEntry entry=new CalendarEntry(entry_info[0]);
        this.table.addEntry(entry);
        ui.cout(UiHandler.generateSection("Added: "+entry.toString()+"\n"));
        disk.syncToFile(this.table);
        return 200;
    }

    private int markAsDoneUndone(String input) throws Exception{
        String[] args=input.toLowerCase().split(" ");
        if (args.length!=2){
            throw new InvalidParameterException("Sorry, which entry do you want me to mark/unmark?");
            //return 400;
        }
        if (args[0].equals("mark")){
            int status=this.table.markAsDone(Integer.parseInt(args[1]));
            if (status==200 || status==208) {
                ui.cout(UiHandler.generateSection("Jawohl, I have marked the following event as completed:\n     " +
                        this.table.getEntry(Integer.parseInt(args[1]))+"\n"));
            }
            disk.syncToFile(this.table);
            return status;
        }
        if (args[0].equals("unmark")){
            int status=this.table.markAsUndone(Integer.parseInt(args[1]));
            if (status==200 || status==208) {
                ui.cout(UiHandler.generateSection("Jawohl, I have marked the following event as incomplete:\n     " +
                        this.table.getEntry(Integer.parseInt(args[1]))+"\n"));
            }
            disk.syncToFile(this.table);
            return status;
        }
        else{
            throw new RuntimeException("Sorry, there seems to be some difficulties processing your command, could you check the syntax and try again later?");
            //return 500;
        }
    }

    private int delete(String input) throws Exception{
        String[] args=input.toLowerCase().split(" ");
        if (args.length!=2){
            throw new InvalidParameterException("Sorry, which entry do you want me to delete?");
            //return 400;
        }
        ui.cout(UiHandler.generateSection("Jawohl, I have removed the following entry from your calendar:\n     " +
                this.table.deleteEntry(Integer.parseInt(args[1]))+"\n"));
        disk.syncToFile(this.table);
        return 200;
    }

    private int addEntryToCalendar(String input) throws Exception {
        String[] args=input.toLowerCase().split(" ");
        if (args[0].equals("todo") && args.length>=2){
            CalendarEntryTodo entry=new CalendarEntryTodo(input.substring(5));
            int status=this.table.addEntry(entry);
            if (status==200){
                ui.cout(UiHandler.generateSection("Verstehe, added: "+entry.toString()+"\n"));
            }
            disk.syncToFile(this.table);
            return status;
        }
        else if (args[0].equals("deadline") && args.length>=4){
            if (input.indexOf("/by")==-1){
                throw new InvalidParameterException("Sorry, what is the exact time of the deadline?\nCheck the help message for information on command syntax");
                //return 500;
            }
            input=input.substring(9);
            String time=input.substring(input.indexOf("/by")+4);
            String title=input.substring(0, input.indexOf("/by")-1);
            CalendarEntryDeadline entry=new CalendarEntryDeadline(title, time);
            int status=this.table.addEntry(entry);
            if (status==200){
                ui.cout(UiHandler.generateSection("Verstehe, added: "+entry.toString()+"\n"));
            }
            disk.syncToFile(this.table);
            return status;
        }
        else if (args[0].equals("event") && args.length>=4){
            if (input.indexOf("/at")==-1 || input.indexOf(" - ")==-1){
                throw new InvalidParameterException("Sorry, what is the exact time of the event?\nCheck the help message for information on command syntax");
                //return 500;
            }
            input=input.substring(6);
            String time=input.substring(input.indexOf("/at")+4);
            String title=input.substring(0, input.indexOf("/at")-1);
            CalendarEntryEvent entry=new CalendarEntryEvent(title, time.split(" - ")[0], time.split(" - ")[1]);
            int status=this.table.addEntry(entry);
            if (status==200){
                ui.cout(UiHandler.generateSection("Verstehe, added: "+entry.toString()+"\n"));
            }
            disk.syncToFile(this.table);
            return status;
        }
        throw new InvalidParameterException("Sorry, I don't seem to understand you");
        //return 500;
    }

    /**
     * calls corresponding functions based on the input command
     * @param input the array of string that contains the command and parameters
     * @return status code (adapted from http, with exceptions such as status 0 represents exit)
     */
    public int dispatchCommand(String input) throws Exception{
        if (input==null){
            throw new InvalidParameterException("command string array is not expected to be null, internal error");
        }
        if (input.length()==0){
            throw new IllegalArgumentException("Sorry, command length cannot be zero");
            //return 400; //Bad request
        }
        if (input.toLowerCase().equals("exit") || input.toLowerCase().equals("bye")){
            return 0; //NOT http status code, exit
        }
        if (input.toLowerCase().equals("list") || input.toLowerCase().equals("ls")){
            return list();
        }

        String[] splited_input=input.toLowerCase().split(" ");
        if (splited_input[0].equals("help")){
            return help();
        }
        if (splited_input[0].equals("mark") || splited_input[0].equals("unmark")){
            return markAsDoneUndone(input);
        }
        if (splited_input[0].equals("todo") || splited_input[0].equals("event") || splited_input[0].equals("deadline")){
            return addEntryToCalendar(input);
        }
        if (splited_input[0].equals("delete")){
            return delete(input);
        }
        throw new IllegalArgumentException("Sorry, I don't seem to understand you");
        //return 500; //not implemented
    }

    /**
     * the infinite loop that listens to whatever place that the command is coming from (currently only support console)
     * when the command is read, it will be handed over to the dispatch_command function to parse and execute
     * after which the dispatch_command function will return a status code (adapted from http, details in respective comments)
     * this function will print error msg (if any)
     * @return status code
     */
    public int startWorking(){
        ui.cout(ui.getGreeting());
        try {
            this.disk.syncFromFile(this.table);
        }
        catch (Exception e){
            ui.cout(e.getMessage()+"\n");
            ui.cout("Error syncing with disk, starting with a fresh new table"+"\n");
        }
        while (true){
            try {
                int status=this.dispatchCommand(ui.getCommand());
                ui.printStatusMsg(status);
                if (status==0){
                    break;
                }
            }
            catch (Throwable e){
                ui.cout(UiHandler.generateSection(e.getMessage()+"\n"));
            }
        }
        return 0;
    }

}
