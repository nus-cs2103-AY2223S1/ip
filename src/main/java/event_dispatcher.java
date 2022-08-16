import java.security.InvalidParameterException;
import java.util.Locale;

public class event_dispatcher {
    /**
     * calls corresponding functions based on the input command
     * @param input the array of string that contains the command and parameters
     * @return status code (adapted from http, with exceptions such as status 0 represents exit)
     */
    public static int dispatch_command(String[] input){
        if (input==null){
            throw new InvalidParameterException("command string array is not expected to be null, internal error");
        }
        if (input.length==0){
            return 400; //Bad request
        }
        if (input[0].toLowerCase().equals("exit")){
            return 0; //NOT http status code, exit
        }
        return 501; //not implemented
    }
}
