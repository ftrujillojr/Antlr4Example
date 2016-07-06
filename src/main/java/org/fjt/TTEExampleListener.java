package org.fjt;

import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.fjt.grammar.TTEParser;

public class TTEExampleListener extends TTEExampleHelperListener {

    @Override
    public void visitErrorNode(ErrorNode node) {
        for (int ii = 0; ii < node.getChildCount(); ii++) {
            if (debug) {
                System.out.println("ERROR =>" + node.getChild(ii) + "<=");
            }
        }
    }

    @Override
    public void enterDefine(TTEParser.DefineContext ctx) {
        List<String> list = getList(ctx.children);
        
        if (debug) {
            this.displayList("enterKey_val", list, ctx.start.getLine());
        }
    }

    @Override
    public void enterLiteral_string(TTEParser.Literal_stringContext ctx) {
        List<String> list = getList(ctx.children);
        if (debug) {
            this.displayList("enterKey_val", list, ctx.start.getLine());
        }
    }

    @Override
    public void enterKey_val(TTEParser.Key_valContext ctx) {
        List<String> list = getList(ctx.children);
        if (debug) {
            this.displayList("enterKey_val", list, ctx.start.getLine());
        }

    }
    
    @Override public void enterBegin_tte_log_section(TTEParser.Begin_tte_log_sectionContext ctx) { 
        List<String> list = getList(ctx.children);
        
        if (debug) {
            this.displayList("enterBegin_tte_log_section", list, ctx.start.getLine());
        }
        StringBuilder sb = new StringBuilder();

        for (String str : list) {
            sb.append(str);
        }

        sectionStack.push(sb.toString().trim());
    }
    
    @Override public void enterEnd_tte_log_section(TTEParser.End_tte_log_sectionContext ctx) {
        List<String> list = getList(ctx.children);
        
        if (debug) {
            this.displayList("enterEnd_tte_log_section", list, ctx.start.getLine());
        }
        StringBuilder sb = new StringBuilder();

        for (String str : list) {
            sb.append(str);
        }

        String compareStr = sb.toString().trim().replaceAll("_END_", "_BEGIN_"); // for comparason
        String expect = sectionStack.pop();

        if (compareStr.equals(expect) == false) {
            int lineNumber = ctx.start.getLine();
            String msg = "\nERROR: END section does NOT match last BEGIN section. => " + expect + "\n";
            msg += "\nERROR: Line " + lineNumber + " " + sb.toString() + "\n";
            errorMessages.add(msg);
        }
    }

    @Override
    public void enterBegin_section(TTEParser.Begin_sectionContext ctx) {
        List<String> list = getList(ctx.children);
        
        if (debug) {
            this.displayList("enterBegin_section", list, ctx.start.getLine());
        }
        StringBuilder sb = new StringBuilder();

        for (String str : list) {
            sb.append(str);
        }

        sectionStack.push(sb.toString().trim());
    }

    @Override
    public void enterEnd_section(TTEParser.End_sectionContext ctx) {
        List<String> list = getList(ctx.children);
        
        if (debug) {
            this.displayList("enterEnd_section", list, ctx.start.getLine());
        }
        StringBuilder sb = new StringBuilder();

        for (String str : list) {
            sb.append(str);
        }

        String compareStr = sb.toString().trim().replaceAll("_END_", "_BEGIN_"); // for comparason
        String expect = sectionStack.pop();

        if (compareStr.equals(expect) == false) {
            int lineNumber = ctx.start.getLine();
            String msg = "\nERROR: END section does NOT match last BEGIN section. => " + expect + "\n";
            msg += "\nERROR: Line " + lineNumber + " " + sb.toString() + "\n";
            errorMessages.add(msg);
        }
    }

    @Override
    public void exitTte_doc(TTEParser.Tte_docContext ctx) {
        
        if (sectionStack.size() > 0) {
            String msg = "\nERROR: There were unclosed sections left on the stack.";
            for (String str : sectionStack) {
                msg += "\nERROR: " + str;
            }
            errorMessages.add(msg);
        }
    }

    @Override
    public void enterIndex_line(TTEParser.Index_lineContext ctx) {
        List<String> list = getUNModifiedList(ctx.children);

        if (debug) {
            this.displayList("enterIndex_line", list, ctx.start.getLine());
        }
    }

}
