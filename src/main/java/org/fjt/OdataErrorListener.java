package org.fjt;

import edu.emory.mathcs.backport.java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * This class will allow g4 grammar bugs to be displayed to STDERR.
 * 
 * If you define your own ErrorListener, then only change the class name.
 * 
 * This should then be attached to your Parser.
 * 
 * Example:
 * 
 * OdataParser odataParser = new OdataParser(tokens);
 * odataParser.removeErrorListeners();
 * odataParser.addErrorListener(new OdataErrorListener());
 */
public class OdataErrorListener extends BaseErrorListener {
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
