            Netbeans 8.1 with Maven controlled project using Antlr4.
            ========================================================

Credit => https://ljelonek.wordpress.com/2014/01/03/generate-parsers-with-antlr4-via-maven/
          https://maven.apache.org/guides/mini/guide-generating-sources.html


Create a Maven controlled Java Application project.

        https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

        
Place this in your pom.xml.. Make sure the VERSIONS match.

    <dependencies>
    . . .
        <dependency>
            <groupId>org.antlr</groupId>
            <artifactId>antlr4</artifactId>
            <version>4.5.3</version>
        </dependency>
    . . .
    </dependencies>


        <plugins>
            . . .
            <plugin>
                <groupId>org.antlr</groupId>
                <artifactId>antlr4-maven-plugin</artifactId>
                <version>4.5.3</version>
                <configuration>
                    <arguments>
                        <argument>-package</argument>
                        <argument>org.fjt.grammar</argument>
                    </arguments>
                    <sourceDirectory>${basedir}/src/main/antlr4</sourceDirectory>
                    <outputDirectory>${project.build.directory}/generated-sources/antlr4</outputDirectory>
                </configuration>
                <executions>
                    <execution>
                        <id>antlr</id>
                        <goals>
                            <goal>antlr4</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            . . .
        </plugins>



Create a your grammars in a package that matches pom file.

    Create a package of your new project called "antlr4". (It has to be that name exactly.) 

    Example package:    org.fjt.antlr4

    Hello.g4
    ==========
    // Defined a grammar called Hello
    grammar Hello;
    r  : 'hello' ID ;         // match keyword hello followed by an identifier
    ID : [a-z]+ ;             // match lower-case identifiers
    WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines


Create a test run.   (Main.java)

        import org.fjt.grammar.HelloLexer;
        import org.fjt.grammar.HelloParser;
. . .
. . .

            // Your code here.
            // create a CharStream that reads from standard input
            ANTLRInputStream input = new ANTLRInputStream(System.in);

            // create a lexer that feeds off of input CharStream
            HelloLexer lexer = new HelloLexer(input);

            // create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // create a parser that feeds off the tokens buffer
            HelloParser helloParser = new HelloParser(tokens);

            ParseTree tree = helloParser.r(); // begin parsing at r() rule
            System.out.println(tree.toStringTree(helloParser)); // print LISP-style tree  


Clean and Build

    Shift + F11


Run

$ java -jar ./target/Antler4Example.jar --file hello.txt
hello world
(r hello world)




If you want command line tools, then do this in .cshrc or equivilant shell.

    setenv CLASSPATH ".:$HOME/.m2/repository/org/antlr/antlr4/4.5.3"
    alias antlr4 'java -Xmx500M -cp "$HOME/.m2/repository/org/antlr/antlr4/4.5.3/antlr4-4.5.3.jar:$CLASSPATH" org.antlr.v4.Tool'
    alias grun 'java -cp "$HOME/.m2/repository/org/antlr/antlr4/4.5.3/antlr4-4.5.3.jar:$CLASSPATH" org.antlr.v4.runtime.misc.TestRig'



http://stackoverflow.com/questions/29060496/allow-whitespace-sections-antlr4/29115489#29115489

    templateBody : {enableWs();} templateChunk* {disableWs();};


    public void enableWs() {
        if (_input instanceof MultiChannelTokenStream) {
            ((MultiChannelTokenStream) _input).enable(HIDDEN);
        }
    }

    public void disableWs() {
        if (_input instanceof MultiChannelTokenStream) {
            ((MultiChannelTokenStream) _input).disable(HIDDEN);
        }
    }

http://www.theendian.com/blog/antlr-4-lexer-parser-and-listener-with-example-grammar/


