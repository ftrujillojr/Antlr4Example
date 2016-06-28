package org.fjt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fjt.grammar.TTELexer;
import org.fjt.grammar.TTEParser;

public class TTEExample {

    public TTEExample() {
    }

    public void readStdin() throws IOException {
        System.out.println("Reading from STDIN . . .");
        // Your code here.
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        // create a lexer that feeds off of input CharStream
        TTELexer lexer = new TTELexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        TTEParser tteParser = new TTEParser(tokens);

        ParseTree tree = tteParser.tte_doc(); // begin parsing at r() rule
        System.out.println(tree.toStringTree(tteParser)); // print LISP-style tree  
    }

    public void parseFile(String fileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileName)) {
            System.out.println("Parsing file => " + fileName + "\n");

            // Your code here.
            // Create a CharStream that reads from standard input
            ANTLRInputStream input = new ANTLRInputStream(inputStream);

            // Create a lexer that feeds off of input CharStream
            TTELexer lexer = new TTELexer(input);

            // Create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create a parser that feeds off the tokens buffer
            TTEParser tteParser = new TTEParser(tokens);

            // Entry point
            TTEParser.Tte_docContext tteDocContext = tteParser.tte_doc();

            // Create a walker.
            ParseTreeWalker walker = new ParseTreeWalker();
            
            // attach our listener
            TTEExampleListener listener = new TTEExampleListener();
            
            // GO!
            walker.walk(listener, tteDocContext);
        }
    }

    public void showTokens(String fileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileName)) {
            System.out.println("Parsing TOKENS from file => " + fileName + "\n");

            // Your code here.
            // Create a CharStream that reads from standard input
            ANTLRInputStream input = new ANTLRInputStream(inputStream);

            // Create a lexer that feeds off of input CharStream
            TTELexer lexer = new TTELexer(input);

            for (Token token = lexer.nextToken();
                    token.getType() != Token.EOF;
                    token = lexer.nextToken()) {

                String symbolName = TTELexer.VOCABULARY.getSymbolicName(token.getType());
                String tokenString = token.getText();
//                if (symbolName.equals("STRING_LITERAL")) {
                   System.out.println(symbolName + " =>" + tokenString + "<=");
//                }
            }
        }
    }

}
