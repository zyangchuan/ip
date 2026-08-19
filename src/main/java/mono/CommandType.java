package mono;

/**
 * Represents the commands understood by Mono.
 */
public enum CommandType {
    BYE("bye", false),
    LIST("list", false),
    MARK("mark", true),
    UNMARK("unmark", true),
    TODO("todo", true),
    DEADLINE("deadline", true),
    EVENT("event", true),
    DELETE("delete", true);

    private final String keyword;
    private final boolean acceptsArguments;

    CommandType(String keyword, boolean acceptsArguments) {
        this.keyword = keyword;
        this.acceptsArguments = acceptsArguments;
    }

    /**
     * Finds the command represented by a complete user input.
     *
     * @param input trimmed user input
     * @return the matching command, or {@code null} if the input is unknown
     */
    public static CommandType fromInput(String input) {
        for (CommandType command : values()) {
            boolean matches = input.equals(command.keyword)
                    || (command.acceptsArguments && input.startsWith(command.keyword + " "));
            if (matches) {
                return command;
            }
        }
        return null;
    }

    /**
     * Returns the command keyword used when parsing its arguments.
     *
     * @return command keyword
     */
    public String getKeyword() {
        return this.keyword;
    }
}
