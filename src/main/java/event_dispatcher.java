import java.security.InvalidParameterException;
import java.util.Locale;

public class event_dispatcher {

    private calendar table;

    public event_dispatcher(calendar table){
        this.table=table;
    }

    private int list(){
        IO_handler.cout(IO_handler.generate_section(this.table.toString()));
        return 200;
    }

    private int add(String entry_info){
        this.table.add_entry(new calendar_entry(entry_info));
        IO_handler.cout(IO_handler.generate_section("Added: "+entry_info+"\n"));
        return 200;
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
        while (true){
            int status=this.dispatch_command(IO_handler.get_command());
            IO_handler.print_status_msg(status);
            if (status==0){
                break;
            }
        }
        return 0;
    }

}
