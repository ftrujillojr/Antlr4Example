grammar TTE;

import TTECommonLexer;

tte_doc 
    : index_line* section+ WS* NL*;

section
    : begin_section section_contents* end_section;

section_contents
    : ( key_val | literal_string | register | define | section) WS* NL*;

index_line
    :  (WS | NL)* SLASHLOT (ID | NUMBER)+ WS* NL*;

begin_section
    : (WS | NL)* BEGIN_SECTION;

key_val
    : (WS | NL)* KEY_VAL;

literal_string
    : (WS | NL)* LITERAL_STRING;

register
    : (WS | NL)* REGISTER;

define
    : (WS | NL)* DEFINE;

end_section
    : (WS | NL)* END_SECTION;
