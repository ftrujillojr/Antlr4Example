package org.fjt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fjt.grammar.TTEBaseListener;
import org.fjt.grammar.TTEParser;

@SuppressWarnings("FieldMayBeFinal")
public class TTEExampleListener extends TTEBaseListener {

    private static boolean debug = true;
    private static boolean preSet = false;

    private static Stack<String> sectionStack = new Stack<>();
    private static List<String> errorMessages = new ArrayList<>();

    private void displayList(String src, List<String> list, int lineNumber) {
        StringBuilder sb = new StringBuilder();

        System.out.println("============= " + src + "============\n");
        for (String str : list) {
            System.out.println("***" + lineNumber + "*** =>" + str + "<=");
            sb.append(str);
        }
        System.out.println("\n=================================\n\n");

        System.out.println(sb.toString());
    }

    public int getNumErrors() {
        return errorMessages.size();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
    
    private List<String> getList(List<ParseTree> parseTreeList) {
        List<String> returnList;

        if (preSet) {
            returnList = this.getUNModifiedList(parseTreeList);
        } else {
            returnList = this.getTrimList(parseTreeList);
        }
        return returnList;
    }

    private List<String> getUNModifiedList(List<ParseTree> parseTreeList) {
        List<String> modifiedList = new ArrayList<>();

        for (ParseTree pt : parseTreeList) {
            String line = pt.getText();
            if (line.isEmpty() == false) {
                modifiedList.add(line);
            }
        }
        return modifiedList;
    }

    private List<String> getTrimList(List<ParseTree> parseTreeList) {
        List<String> list = new ArrayList<>();

        for (ParseTree pt : parseTreeList) {
            String line = pt.getText();
            line = line.replaceAll("[\r]+", "");
            line = line.replaceAll("[ \t]+$", "");
            line = line.replaceAll("^[ \t]+", "");
            line = line.replaceAll("[\n]+", "\n");
            if (line.isEmpty() == false) {
                list.add(line);
            }
        }
        return list;
    }

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
        //System.out.println("SECTION_STACK: " + sectionStack);
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
            msg += "ERROR: Line " + lineNumber + " " + sb.toString() + "\n";
            errorMessages.add(msg);
        }
    }

    @Override
    public void exitTte_doc(TTEParser.Tte_docContext ctx) {
        
        if (sectionStack.size() > 0) {
            String msg = "\nERROR: There were unclosed sections left on the stack.";
            for (String str : sectionStack) {
                msg += "ERROR: " + str;
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
