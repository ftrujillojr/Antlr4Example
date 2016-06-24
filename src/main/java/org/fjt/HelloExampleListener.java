package org.fjt;

import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fjt.grammar.HelloBaseListener;
import org.fjt.grammar.HelloParser.RContext;

public class HelloExampleListener  extends HelloBaseListener {
    
    @Override
    public void enterR(RContext ctx) {
        List<ParseTree> parseTreeList = ctx.children;
        
        for(ParseTree pt: parseTreeList) {
            System.out.println("CHILD =>" + pt.getText() + "<=");
        }
        
    }
    
}
