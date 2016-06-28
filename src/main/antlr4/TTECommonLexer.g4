lexer grammar TTECommonLexer;

SEMICOLON
    : [\;][ \t]*;

LITERAL_STRING
    : '"' (ESC|.)*? '"' [ \t]*;

BEGIN_SECTION
    : '$$_BEGIN_' ID;

END_SECTION
    : '$$_END_' ID;

KEY_VAL
    : [A-Z]([A-Z0-9\_\/]+)?[\:][ \t]+(~[ \t\r\n]+)[ \t]*;

SLASHLOT
    : [A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+([\~][0-9]+)?[ \t]*;

ID
    : [a-zA-Z](~[ \t\r\n]+)?[ \t]*
    | [\-\?]+[ \t]*;

ATTRS
    : ID[\=]LITERAL_STRING;

REGISTER
    : ID AT NUMBER;

NUMBER
    : [0-9]+([\.][0-9]+)?[ \t]*;

DEFINE
    : '#define'[ \t]+(.)+?[\r\n]+;

EQUALS
    : [\=][ \t*];

AT
    : [\@][ \t]*;

SPLAT
    : [\*][ \t]*;

BANG
    : [\!][ \t]*;

PERCENT
    : [\%][ \t]*;

LEFT_CURLY
    : [\{][ \t]*;

RIGHT_CURLY
    : [\}][ \t]*;

COMMA
    : [\,][ \t]*;

NL
    : [\r\n]+;

WS 
    : [ \t]+;

COLON
    : [\:][ \t]*;

UNDERSCORE
    : [\_][ \t]*;

fragment ESC 
    : '\\"' | '\\\\' ;


