grammar Odata;

filterTop
    : filterStatement
    | filterStatement LOGICAL_OPERATOR filterStatement;

filterStatement: TEXT RELATIONAL_OPERATOR (STRING_LITERAL | TEXT | BOOLEAN_VALUE);

LOGICAL_OPERATOR
    : 'and' | 'or' | 'not'
    | 'AND' | 'OR' | 'NOT';

RELATIONAL_OPERATOR
    : 'eq' | 'lt' | 'gt' | 'ne' | 'ge' | 'le' | 'like'
    | 'EQ' | 'LT' | 'GT' | 'NE' | 'GE' | 'LE' | 'LIKE';

BOOLEAN_VALUE
    : 'true' | 'false';

STRING_LITERAL
    : '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    |'"' ( ESC_SEQ | ~('\\'|'"') )* '"';

TEXT
    : ([a-zA-Z0-9_]+);
 
WHITESPACE
    : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip;

fragment ESC_SEQ 
    : '\\"' | '\\\\' | EOF;
