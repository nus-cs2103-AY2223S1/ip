package org.Olivia.IO;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class handles all IO related tasks
 * including formatting output
 * read/write from/to console/file
 * having these encapsulated functions would make the core could a lot cleaner
 *
 * @author albertZhangTJ
 */
public class UiHandler {

    private Scanner sc;
    private PrintStream out;

    public UiHandler() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    public UiHandler(InputStream stream, PrintStream ostream) {
        this.sc = new Scanner(stream);
        this.out = ostream;
    }

    /**
     * Currently this logo is hard-coded, but I'm thinking of reading it from a config file in later versions
     * generated using figlet http://www.figlet.org/
     *
     * @return The logo for CLI
     */
    public static String getLogo() {
        String logo = "  ___  _ _       _       \n" +
                " / _ \\| (_)_   _(_) __ _\n" +
                "| | | | | \\ \\ / / |/ _` |\n" +
                "| |_| | | |\\ V /| | (_| |\n" +
                " \\___/|_|_| \\_/ |_|\\__,_|\n";

        return logo;
    }

    public static String generateSection(String content) {
        return "=====================================================\n" +
                content +
                "=====================================================\n\n";
    }

    public static String generateHelpMsg() {
        return "Currently the following commands are supported:\n" +
                "    todo {title} {tags}\n" +
                "    deadline {title} /by {time} {tags}\n" +
                "    event {title} /at {time start} - {time end} {tags}\n" +
                "    list\n" +
                "    ls\n" +
                "    mark {entry index}\n" +
                "    unmark {entry index}\n" +
                "    delete {entry index}\n" +
                "    find {keyword}\n" +
                "    help\n\n\n" +
                "Please use either of the following three formats for time:\n" +
                "    DD/MM/YYYY hh:mm\n" +
                "    DD/MM/YYYY\n" +
                "    hh:mm";
    }

    public String getGreeting() {
        String result = getLogo();

        result = result + "Hello, this is Olivia, your personal assistant\n";
        result = result + "Hallo, ich bin Olivia, Ihre persönliche Assistentin\n";
        result = result + "    For help message on how to communicate with me, type help\n";
        return generateSection(result);
    }

    /**
     * Just a simple encapsulation for the classic System.out.print function
     * seems a little bit redundant, but helps me type less LOL
     * function name comes from C++ std::cout
     *
     * @param content the thing to print
     */
    public void cout(String content) {
        this.out.print(content);
    }

    /**
     *
     */
    public String getCommand() {
        cout("> ");
        String input = sc.nextLine();
        return input;
    }

    @Deprecated
    public void printStatusMsg(int status_code) {
        if (status_code == 400 || status_code == 500) {
            cout(generateSection("Sorry, I don't seem to understand you.\nMaybe there is a syntax error or the command is unsupported?\n"));
        }
        else if (status_code == 200 || status_code == 208) {
            return;
        }
        else if (status_code == 501) {
            cout(generateSection("My apologies, this feature has yet to be implemented, please look out for updates!\n"));
        }
        else if (status_code == 0) {
            cout(generateSection("See you later! \nAuf Wiedersehen!\n"));
        }
        else {
            cout(generateSection("Sorry, some internal error has occured\n"));
        }
    }
}
