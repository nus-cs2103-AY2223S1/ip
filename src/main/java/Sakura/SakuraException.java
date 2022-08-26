package Sakura;

public class SakuraException {
    protected static void invalidMark() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Mark Command written incorrectly!! ✿\n");
    }

    protected static void genericTask() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR What on earth does this mean!! ✿\n");
    }

    protected static void noSuchTask() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR There is no such thing!! ✿\n");
    }

    protected static void invalidDeadline() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR That is NOT a valid deadline!! ✿\n");
    }

    protected static void invalidEvent() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR That is NOT a valid event!! ✿\n");
    }

    protected static void invalidCommand() {
        System.out.println("\t(ﾐ`⋏´ﾐ) BOOOOO Can you even write a proper command?? ✿\n");
    }

    protected static void invalidTodo() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Todo Command written incorrectly!! ✿\n");
    }

    protected static void databaseError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR There is something wrong with the database!! ✿\n");
    }

    protected static void saveError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR I cannot save this data!! ✿\n");
    }

    protected static void dateError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Please enter date in the format yyyy-MM-dd HHmm!! ✿\\n\"");
    }
}
