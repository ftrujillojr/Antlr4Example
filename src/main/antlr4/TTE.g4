grammar TTE;

import TTECommonLexer;

tte_doc 
    : index_line section+ WS* NL*
    | section+ WS* NL*;

section
    : begin_section section_contents+ end_section;

section_contents
    : ( key_val | literal_string | register | define );

index_line
    :  WS* SLASHLOT (ID | NUMBER)+ WS* NL+;

begin_section
    : WS* BEGIN_SECTION ID WS* NL+;

key_val
    : WS* KEY_VAL WS* NL+;

literal_string
    : WS* LITERAL_STRING WS* NL+;

register
    : WS* REGISTER WS* NL+;

define
    : WS* DEFINE WS* NL+;

end_section
    : WS* END_SECTION ID WS* NL+;
