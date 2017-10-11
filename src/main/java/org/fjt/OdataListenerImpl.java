package org.fjt;

import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.fjt.grammar.OdataParser;

/**
 * Goto your generated BaseListener and grab the method signatures to override
 * in this implementation if desired.
 *
 * OdataListenerImpl extends OdataAbstractHelperListener which extends
 * OdataBaseListener (generated) which implements OdataLister (generated)
 *
 * Listeners are attached to ParseTreeWalkers.
 *
 * // Create a walker. ParseTreeWalker walker = new ParseTreeWalker();
 *
 * // attach our implemented listener OdataListenerImpl listener = new
 * OdataListenerImpl();
 *
 * // GO! walker.walk(listener, filterTopContext);
 *
 * // List out all errors at the end. int numberErrors =
 * listener.getNumErrors();
 *
 * System.out.println("Num errors: " + numberErrors); if (numberErrors > 0) {
 * for (String str : listener.getErrorMessages()) { System.out.println(str); } }
 *
 */
public class OdataListenerImpl extends OdataAbstractHelperListener {

    @Override
    public void visitErrorNode(ErrorNode node) {
        for (int ii = 0; ii < node.getChildCount(); ii++) {
            if (debug) {
                System.out.println("ERROR =>" + node.getChild(ii) + "<=");
            }
        }
    }

    /**
     * Enter a parse tree produced by {@link OdataParser#filterTop}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterFilterTop(OdataParser.FilterTopContext ctx) {
        List<String> list = this.getList(ctx.children);

        if (debug) {
            this.displayList("enterFilterTop", list, ctx.start.getLine());
        }
    }

    /**
     * Exit a parse tree produced by {@link OdataParser#filterTop}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFilterTop(OdataParser.FilterTopContext ctx) {
        List<String> list = this.getList(ctx.children);

        if (debug) {
            this.displayList("exitFilterTop", list, ctx.start.getLine());
        }
    }

    /**
     * Enter a parse tree produced by {@link OdataParser#filterStatement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterFilterStatement(OdataParser.FilterStatementContext ctx) {
        List<String> list = this.getList(ctx.children);

        if (debug) {
            this.displayList("enterFilterStatement", list, ctx.start.getLine());
        }
    }

    /**
     * Exit a parse tree produced by {@link OdataParser#filterStatement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFilterStatement(OdataParser.FilterStatementContext ctx) {
        List<String> list = this.getList(ctx.children);

        if (debug) {
            this.displayList("exitFilterStatement", list, ctx.start.getLine());
        }
    }

}
