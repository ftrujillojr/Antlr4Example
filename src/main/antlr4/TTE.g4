grammar TTE;

top : 'dude';


SECTION_BEGIN 
    : [\$][\$]'_BEGIN_' IDENTIFIER;

SECTION_END 
    : [\$][\$]'_END_' IDENTIFIER;

STRING_LITERAL
    : '"' (ESC|.)*? '"';

IDENTIFIER
    : [a-zA-Z\_0-9\/\#\-\~\!\.\@\$\%\^\&\*\(\){}\[\]\|\:<>\?\+\=\;]+;

NUMERIC 
    : [0-9]+;

ALPHA
    : [a-zA-Z\_]+;

fragment ESC 
    : '\\"' | '\\\\' ;

COMMA
    : [\,]+;

WS 
    : [ \t\r\n]+;   // skip spaces, tabs, newlines

NEWLINE
    : [\n]+;

