lexer grammar TTECommonLexer;

SEMICOLON
    : [\;][ \t]*;

STR
    : '"' (ESC|.)*? '"';

BEGIN_SECTION
    : [\$][\$]'_BEGIN_';

END_SECTION
    : [\$][\$]'_END_';

KEY_VAL
    : [A-Z]([A-Z0-9\_\/]+)?[\:][ \t]+(~[ \t\r\n]+)[ \t]*;

SLASHLOT
    : [A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+([\~][0-9]+)?[ \t]*;

/*
START_END_TAG
    : [\<]ID ATTRS*[ \t]*[\/][\>][ \t]*;

START_TAG
    : [\<]ID ATTRS*[\>][ \t]*;

END_TAG
    : [\<][\/]ID ATTRS*[\>][ \t]*;
*/


ID
    : [a-zA-Z](~[ \t\r\n]+)?[ \t]*
    | [\-\?]+[ \t]*;

ATTRS
    : ID[\=]STR;

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

NULL_CHAR
    : EOF;

