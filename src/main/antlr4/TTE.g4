grammar TTE;

import TTECommonLexer;

top 
    : index_line;

/*
    | section_begin_header
    | section_end_header
    | section_begin
    | section_end;
*/

index_line
    : slashlot ID+ NL;

slashlot
    : '212345';

/*
section_begin_header
    : DD '_BEGIN_HEADER' NEW_LINE;

section_end_header
    : DD '_END_HEADER' NEW_LINE;

section_begin
    : DD '_BEGIN_' IDENTIFIER NEW_LINE;

section_end 
    : DD '_END_' IDENTIFIER NEW_LINE;
*/
