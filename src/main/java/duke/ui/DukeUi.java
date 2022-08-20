package duke.ui;

import java.util.Scanner;

public class DukeUi {
    private static Scanner sc;

    public DukeUi() {
        sc = new Scanner(System.in);
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public String getNextLine() {
        return sc.nextLine();
    }

    public void endService() {
        sc.close();
    }

    public static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
    }
}
