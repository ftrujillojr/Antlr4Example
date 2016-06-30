lexer grammar TTECommonLexer;

LITERAL_STRING
    : '"' (ESC|.)*? '"' WS*;

BEGIN_TTE_LOG_SECTION
    : '$$_BEGIN_TTE_LOG' WS* NL+;

END_TTE_LOG_SECTION
    : '$$_END_TTE_LOG' WS* NL+;

BEGIN_SECTION
    : '$$_BEGIN_' ID WS* NL+;

END_SECTION
    : '$$_END_' ID WS* NL+;

SEMICOLON
    : [\;][ \t]*;

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

FUSEID
    : [0-9][0-9][0-9][0-9][0-9][0-9][0-9]':'[0-9][0-9]':'[NP][0-9][0-9]':'[0-9][0-9];

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

