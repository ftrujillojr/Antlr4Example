package org.fjt;

import org.fjt.grammar.HelloBaseListener;
import org.fjt.grammar.HelloParser.RContext;

public class HelloExampleListener  extends HelloBaseListener {
    
    @Override
    public void enterR(RContext ctx) {
        System.out.println("Match rule r() => " + ctx.getText());
    }
    
}
