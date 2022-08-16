import java.security.InvalidParameterException;
import java.util.Locale;

public class event_dispatcher {

    private calendar table;
    private IO_handler ui;

    public event_dispatcher(calendar table, IO_handler ui){
        this.table=table;
        this.ui=ui;
    }

    private int list(){
        ui.cout(IO_handler.generate_section(this.table.toString()));
        return 200;
    }

    private int add(String entry_info){
        calendar_entry entry=new calendar_entry(entry_info);
        this.table.add_entry(entry);
        ui.cout(IO_handler.generate_section("Added: "+entry.toString()+"\n"));
        return 200;
    }

    private int mark_as_done_undone(String input){
        String[] args=input.toLowerCase().split(" ");
        if (args.length!=2){
            return 400;
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
            return 500;
        }
    }

    /**
     * calls corresponding functions based on the input command
     * @param input the array of string that contains the command and parameters
     * @return status code (adapted from http, with exceptions such as status 0 represents exit)
     */
    public int dispatch_command(String input){
        if (input==null){
            throw new InvalidParameterException("command string array is not expected to be null, internal error");
        }
        if (input.length()==0){
            return 400; //Bad request
        }
        if (input.toLowerCase().equals("exit") || input.toLowerCase().equals("bye")){
            return 0; //NOT http status code, exit
        }
        if (input.toLowerCase().equals("list") || input.toLowerCase().equals("ls")){
            return list();
        }
        if (input.toLowerCase().split(" ")[0].equals("mark") || input.toLowerCase().split(" ")[0].equals("unmark")){
            return mark_as_done_undone(input);
        }
        else {
            return add(input);
        }

        //return 501; //not implemented
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
            int status=this.dispatch_command(ui.get_command());
            ui.print_status_msg(status);
            if (status==0){
                break;
            }
        }
        return 0;
    }

}
