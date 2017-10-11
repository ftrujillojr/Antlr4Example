grammar Odata;

filterTop
    : (filterStatement)+ EOF                                            
    ;

filterStatement
    : TEXT RELATIONAL_OPERATOR (STRING_LITERAL | TEXT | BOOLEAN_VALUE)  
    | filterStatement LOGICAL_OPERATOR filterStatement                  
    ;

LOGICAL_OPERATOR
    : 'or' | 'and'
    | 'OR' | 'AND'
    ;

RELATIONAL_OPERATOR
    : 'eq' | 'lt' | 'gt' | 'ne' | 'ge' | 'le' | 'like'
    | 'EQ' | 'LT' | 'GT' | 'NE' | 'GE' | 'LE' | 'LIKE'
    ;

BOOLEAN_VALUE
    : 'true' | 'false'
    | 'TRUE' | 'FALSE'
    ;

STRING_LITERAL
    : '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    |'"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

TEXT
    : ([a-zA-Z0-9_]+)
    ;
 
WS
    : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 
    ;

fragment ESC_SEQ 
    : '\\"' | '\\\\' | EOF
    ;

ANY : . ;
