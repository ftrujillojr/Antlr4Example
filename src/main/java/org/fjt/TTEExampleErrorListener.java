package org.fjt;

import edu.emory.mathcs.backport.java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class TTEExampleErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(
            Recognizer<?,?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException ex
    ) {
        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        System.err.println("Rule Stack: " + stack);
        System.err.println("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
    }
}
