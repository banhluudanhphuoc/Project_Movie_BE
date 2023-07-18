package edu.kits.movie.Common;

import org.apache.el.parser.AstGreaterThan;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH,
    CONTAINS,GREATER_THAN_OR_EQUAL_TO,LESS_THAN_OR_EQUAL_TO;

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~",">:","<:"};

    public static final String OR_PREDICATE_FLAG = "'";

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_OPERATOR = "OR";

    public static final String AND_OPERATOR = "AND";

    public static final String LEFT_PARANTHESIS = "(";

    public static final String RIGHT_PARANTHESIS = ")";

    public static SearchOperation getSimpleOperation(final String input) {
        return switch (input) {
            case ":" -> EQUALITY;
            case "!" -> NEGATION;
            case ">" -> GREATER_THAN;
            case "<" -> LESS_THAN;
            case "~" -> LIKE;
            case ">:" -> GREATER_THAN_OR_EQUAL_TO;
            case "<:" -> LESS_THAN_OR_EQUAL_TO;
            default -> null;
        };
    }
}
