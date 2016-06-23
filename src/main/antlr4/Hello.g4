grammar Hello;

top : r;
r  : 'hello' LCID;            // match keyword hello followed by an identifier
LCID : [a-z]+ ;               // match lower-case identifiers

IDENTIFIER : [a-zA-Z0-9_\.\\\/]+;

WS : [ \t\r\n]+ -> skip ;   // skip spaces, tabs, newlines

