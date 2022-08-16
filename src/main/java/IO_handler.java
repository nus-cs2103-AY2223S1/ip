import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class handles all IO related tasks
 * including formatting output
 * read/write from/to console/file
 * having these encapsulated functions would make the core could a lot cleaner
 * @author albertZhangTJ
 */
public class IO_handler {

    private static Scanner sc=new Scanner(System.in);

    /**
     * Currently this logo is hard-coded, but I'm thinking of reading it from a config file in later versions
     * generated using figlet http://www.figlet.org/
     * @return The logo for CLI
     */
    public static String get_logo(){
        String logo ="  ___  _ _       _       \n"+
                " / _ \\| (_)_   _(_) __ _\n"+
                "| | | | | \\ \\ / / |/ _` |\n"+
                "| |_| | | |\\ V /| | (_| |\n"+
                " \\___/|_|_| \\_/ |_|\\__,_|\n";

        return logo;
    }

    public static String generate_section(String content){
        return "=====================================================\n"+
                content+
                "=====================================================\n";
    }

    public static String get_greeting(){
        String result=get_logo();

        result=result+"Hello, this is Olivia, your personal assistant\n";
        result=result+"Hallo, ich bin Olivia, Ihre persönliche Assistentin\n";

        return generate_section(result);
    }

    /**
     * Just a simple encapsulation for the classic System.out.print function
     * seems a little bit redundant, but helps me type less LOL
     * function name comes from C++ std::cout
     * @param content the thing to print
     */
    public static void cout(String content){
        System.out.print(content);
    }

    /**
     *
     */
    public static String[] get_command(){
        cout("> ");
        String input=sc.nextLine();
        return input.split(" ");
    }

    public static void print_status_msg(int status_code){
        if (status_code==400){
            cout(generate_section("Sorry, I don't seem to understand you.\n"));
        }
        else if (status_code==200){
            return;
        }
        else if (status_code==501){
            cout(generate_section("My apologies, this feature has yet to be implemented, please look out for updates!\n"));
        }
        else if (status_code==0){
            cout(generate_section("See you later! \nAuf Wiedersehen!\n"));
        }
        else {
            cout(generate_section("Sorry, some internal error has occured\n"));
        }
    }
}
