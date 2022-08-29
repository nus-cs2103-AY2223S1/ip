package duke.ui;

import duke.parser.ParserDuke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private static final String END_CMD = "bye";

    public Ui(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Method to print Duke's self-introduction and to customise its personality.
     */
    public void introduceDuke(){
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself..." +
                "\nBut you are young blood. What brings you to these ancient halls?"
                +"\n***********************************************************************";

        System.out.println("Welcome, my friend!\n" + intro + quote1);
    }

    /**
     * Method to exit chat if user types "bye" and print user input otherwise.
     */
    public static void echoAndExit(){
        String exitCmd = "bye";
        while(true){
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();
            if(!userMsg.equals(exitCmd)) {
                System.out.println(userMsg + "\n" + "***********************************************************************\n");
            } else {
                System.out.println("Ah! And so we part here today.\n We may yet meet again...Farewell, my friend!");

                break;
            }
        }
    }

    public void bidFarewell(){
        System.out.println("Ah! And so we part here today.\n We may yet meet again...Farewell, my friend!");
        System.out.println("\n***********************************************************************");
    }

    public void askForClarification(String userMsg){
        System.out.print("Did you say..." + userMsg + "?\n");
        System.out.println("The shadow of my memory is long...State what you would ask clearly.");
        System.out.println("\n***********************************************************************");
    }

    public void readAndRespond(){

        Scanner readInput = this.in;

        while(true){
            String userMsg = readInput.nextLine();
            if(userMsg.equals(END_CMD)){
                bidFarewell();
                break;
            } else {
                ParserDuke parseCmd = new ParserDuke(userMsg);
                parseCmd.parseCommand();
            }
        }

    }





}