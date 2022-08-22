import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) {
        //each worker is attached to a calendar table to work on and an IO_handler to handle IO
        //this design would allow the program to handle multi-user with multi-input-source case (with multi-thread)
        //the IO_handler can be binded to a specific InputStream and a specific PrintStream (default to be stdin & stdout)
        EventDispatcher worker_1=new EventDispatcher(new Calendar(), new UiHandler(), new FileHandler());
        worker_1.startWorking();


        // trying to locate where the working directory is, so that I can put a more reasonable file location
        // this is copied from a StackOverflow answer (since this is not part of the final solution
        // and there is no one-liner like system(cmd) in C++)
//        try {
//            Runtime r = Runtime.getRuntime();
//            Process p = r.exec("pwd");
//            p.waitFor();
//            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = "";
//
//            while ((line = b.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            b.close();
//        }
//        catch (Throwable e){
//            e.printStackTrace();
//        }
    }
}
