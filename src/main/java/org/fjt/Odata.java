package org.fjt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fjt.grammar.OdataLexer;
import org.fjt.grammar.OdataParser;

public class Odata {

    private MyIOUtils myIOUtils;

    public Odata() {
        this.myIOUtils = new MyIOUtils();
    }


    private InputStream getInputStream(String myString) {
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(myString.getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stream;
    }

    public void parseFileUsingListeners(String fileName) throws IOException {
        this.myIOUtils.redirectStderr("stderr.log", Boolean.FALSE);
        
        CharStream inputStream = CharStreams.fromFileName(fileName);

        // Create a lexer that feeds off of input CharStream
        OdataLexer lexer = new OdataLexer(inputStream);

        for (Token token = lexer.nextToken();
                token.getType() != Token.EOF;
                token = lexer.nextToken()) {

            String symbolName = OdataLexer.VOCABULARY.getSymbolicName(token.getType());
            String tokenString = token.getText();
            int lineNumber = token.getLine();
            System.out.println("Line " + lineNumber + " " + symbolName + " =>" + tokenString + "<=");
        }

        System.out.println("==================================================================================");

        System.out.println("\nParsing file => " + fileName + "\n");

        lexer.reset();

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        OdataParser odataParser = new OdataParser(tokens);
        odataParser.removeErrorListeners();  // remove default error listener.
        odataParser.addErrorListener(new OdataErrorListener()); // add our own error listener.

        // Entry point
        OdataParser.FilterTopContext filterTopContext = odataParser.filterTop();

        // Create a walker.
        ParseTreeWalker walker = new ParseTreeWalker();

        // attach our implemented listener
        OdataListenerImpl listener = new OdataListenerImpl();

        // GO!
        walker.walk(listener, filterTopContext);

        // List out all errors at the end.
        int numberErrors = listener.getNumErrors();
        System.out.println("Num errors: " + numberErrors);
        if (numberErrors > 0) {
            for (String str : listener.getErrorMessages()) {
                System.out.println(str);
            }
        }
        this.myIOUtils.restoreStderr();
    }
}
