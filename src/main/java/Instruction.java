public class Instruction {
    private InstructionType instructionType;
    private String[] instructionArgs;

    public Instruction(String instruction) {
        instructionArgs = instruction.trim().split("\\s+");
        if (instructionArgs.length == 0 || instructionArgs[0].isBlank()) {
            instructionType = InstructionType.NONE;
            return;
        }

        switch (instructionArgs[0]) {
            case "bye":
                instructionType = InstructionType.EXIT;
                break;

            case "list":
                instructionType = InstructionType.PRINT_LIST;
                break;

            case "mark":
                instructionType = InstructionType.MARK_ITEM;
                break;

            case "unmark":
                instructionType = InstructionType.UNMARK_ITEM;
                break;

            case "todo":
                instructionType = InstructionType.ADD_TODO;
                break;

            case "event":
                instructionType = InstructionType.ADD_EVENT;
                break;

            case "deadline":
                instructionType = InstructionType.ADD_DEADLINE;
                break;

            case "delete":
                instructionType = InstructionType.DELETE_ITEM;
                break;

            default:
                instructionType = InstructionType.UNKNOWN;
                break;
        }
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public String[] getInstructionArgs() {
        return instructionArgs;
    }
}
