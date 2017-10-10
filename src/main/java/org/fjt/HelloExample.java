package org.fjt;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.fjt.grammar.HelloLexer;
import org.fjt.grammar.HelloParser;

public class HelloExample {

    public HelloExample() {
    }

    public void readStdin() throws IOException {
        System.out.println("Reading from STDIN . . .");
        // Your code here.
        // create a CharStream that reads from standard input
        CharStream inputStream = CharStreams.fromStream(System.in);

        // create a lexer that feeds off of input CharStream
        HelloLexer lexer = new HelloLexer(inputStream);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        HelloParser helloParser = new HelloParser(tokens);

        ParseTree tree = helloParser.r(); // begin parsing at r() rule
        System.out.println(tree.toStringTree(helloParser)); // print LISP-style tree  
    }

    public void parseFile(String fileName) throws IOException {
        System.out.println("Parsing file => " + fileName + "\n");

        // Your code here.
        // Create a CharStream that reads from standard input
        CharStream inputStream = CharStreams.fromFileName(fileName);

        // Create a lexer that feeds off of input CharStream
        HelloLexer lexer = new HelloLexer(inputStream);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        HelloParser helloParser = new HelloParser(tokens);

        // Entry point
        HelloParser.TopContext topContext = helloParser.top();

        // Create a walker.
        ParseTreeWalker walker = new ParseTreeWalker();

        // attach our listener
        HelloExampleListener listener = new HelloExampleListener();

        // GO!
        walker.walk(listener, topContext);

    }

    public void showTokens(String fileName) throws IOException {
        System.out.println("Parsing TOKENS from file => " + fileName + "\n");

        // Your code here.
        // Create a CharStream that reads from standard input
        CharStream inputStream = CharStreams.fromFileName(fileName);

        // Create a lexer that feeds off of input CharStream
        HelloLexer lexer = new HelloLexer(inputStream);

        for (Token token = lexer.nextToken();
                token.getType() != Token.EOF;
                token = lexer.nextToken()) {

            String symbolName = HelloLexer.VOCABULARY.getSymbolicName(token.getType());
            String tokenString = token.getText();
            System.out.println(symbolName + " => " + tokenString);
        }
    }
}
