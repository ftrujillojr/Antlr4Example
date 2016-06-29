lexer grammar TTECommonLexer;

SEMICOLON
    : [\;][ \t]*;

LITERAL_STRING
    : '"' (ESC|.)*? '"' WS*;

BEGIN_SECTION
    : '$$_BEGIN_' ID WS* NL+;

END_SECTION
    : '$$_END_' ID WS* NL+;

KEY_VAL
    : [A-Z]([A-Z0-9\_\/]+)?[\:]([ \t]*~[ \t\r\n]+)+ WS*;

SLASHLOT
    : [A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+([\~][0-9]+)? WS*;

ID
    : [a-zA-Z](~[ \t\r\n]+)? WS*
    | [\-\?]+ WS*;

ATTRS
    : ID[\=]LITERAL_STRING;

REGISTER
    : ID AT NUMBER;

NUMBER
    : [0-9]+([\.][0-9]+)? WS*;

DEFINE
    : '#define'[ \t]+(.)+?[\r\n]+;

EQUALS
    : [\=] WS*;

AT
    : [\@] WS*;

SPLAT
    : [\*] WS*;

BANG
    : [\!] WS*;

PERCENT
    : [\%] WS*;

LEFT_CURLY
    : [\{] WS*;

RIGHT_CURLY
    : [\}] WS*;

COMMA
    : [\,] WS*;

NL
    : [\r\n]+;

WS 
    : [ \t]+;

COLON
    : [\:] WS*;

UNDERSCORE
    : [\_] WS*;

fragment ESC 
    : '\\"' | '\\\\' ;


