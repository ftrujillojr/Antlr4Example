lexer grammar TTECommonLexer;

STR
    : '"' (ESC|.)*? '"';

BEGIN_SECTION
    : [\$][\$]'_BEGIN_';

END_SECTION
    : [\$][\$]'_END_';

KEY_VAL
    : [A-Z]([A-Z0-9\_]+)?[\:][ \t]*(~[ \t\r\n]+)[ \t]*;

SLASHLOT
    : [A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+[\/][A-Z0-9]+([\~][0-9]+)?[ \t]*;

ID
    : [a-zA-Z](~[ \t\r\n]+)?[ \t]*
    | [\-\?]+[ \t]*;

NUMBER
    : [0-9]+[ \t]*;

NL
    : [\r\n]+;

WS 
    : [ \t]+;

fragment ESC 
    : '\\"' | '\\\\' ;


