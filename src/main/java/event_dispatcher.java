import jdk.jshell.spi.ExecutionControl;

import java.security.InvalidParameterException;
import java.util.Locale;

public class event_dispatcher {

    private calendar table;
    private IO_handler ui;

    public event_dispatcher(calendar table, IO_handler ui){
        this.table=table;
        this.ui=ui;
    }

    private int help(){
        ui.cout(IO_handler.generate_section(IO_handler.generate_help_msg()));
        return 200;
    }
    private int list(){
        ui.cout(IO_handler.generate_section(this.table.toString()));
        return 200;
    }

    @Deprecated
    private int add(String[] entry_info){
        calendar_entry entry=new calendar_entry(entry_info[0]);
        this.table.add_entry(entry);
        ui.cout(IO_handler.generate_section("Added: "+entry.toString()+"\n"));
        return 200;
    }

    private int mark_as_done_undone(String input){
        String[] args=input.toLowerCase().split(" ");
        if (args.length!=2){
            throw new InvalidParameterException("Sorry, which entry do you want me to mark/unmark?");
            //return 400;
        }
        if (args[0].equals("mark")){
            int status=this.table.mark_as_done(Integer.parseInt(args[1]));
            if (status==200 || status==208) {
                ui.cout(IO_handler.generate_section("Jawohl, I have marked the following event as completed:\n     " +
                        this.table.get_entry(Integer.parseInt(args[1]))+"\n"));
            }
            return status;
        }
        if (args[0].equals("unmark")){
            int status=this.table.mark_as_undone(Integer.parseInt(args[1]));
            if (status==200 || status==208) {
                ui.cout(IO_handler.generate_section("Jawohl, I have marked the following event as incomplete:\n     " +
                        this.table.get_entry(Integer.parseInt(args[1]))+"\n"));
            }
            return status;
        }
        else{
            throw new RuntimeException("Sorry, there seems to be some difficulties processing your command, could you check the syntax and try again later?");
            //return 500;
        }
    }

    private int add_entry_to_calendar(String input){
        String[] args=input.toLowerCase().split(" ");
        if (args[0].equals("todo") && args.length>=2){
            calendar_entry_todo entry=new calendar_entry_todo(input.substring(5));
            int status=this.table.add_entry(entry);
            if (status==200){
                ui.cout(IO_handler.generate_section("Verstehe, added: "+entry.toString()+"\n"));
            }
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
            calendar_entry_deadline entry=new calendar_entry_deadline(title, time);
            int status=this.table.add_entry(entry);
            if (status==200){
                ui.cout(IO_handler.generate_section("Verstehe, added: "+entry.toString()+"\n"));
            }
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
            calendar_entry_event entry=new calendar_entry_event(title, time.split(" - ")[0], time.split(" - ")[1]);
            int status=this.table.add_entry(entry);
            if (status==200){
                ui.cout(IO_handler.generate_section("Verstehe, added: "+entry.toString()+"\n"));
            }
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
    public int dispatch_command(String input) throws Exception{
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
            return mark_as_done_undone(input);
        }
        if (splited_input[0].equals("todo") || splited_input[0].equals("event") || splited_input[0].equals("deadline")){
            return add_entry_to_calendar(input);
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
    public int start_working(){
        ui.cout(ui.get_greeting());
        while (true){
            try {
                int status=this.dispatch_command(ui.get_command());
                ui.print_status_msg(status);
                if (status==0){
                    break;
                }
            }
            catch (Throwable e){
                ui.cout(IO_handler.generate_section(e.getMessage()+"\n"));
            }
        }
        return 0;
    }

}
