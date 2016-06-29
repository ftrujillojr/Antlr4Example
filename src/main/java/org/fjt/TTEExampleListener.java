package org.fjt;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fjt.grammar.TTEBaseListener;
import org.fjt.grammar.TTEParser;

public class TTEExampleListener extends TTEBaseListener {

    private static boolean debug = true;
    

    private void displayList(String src, List<String> list) {
        System.out.println("============= " + src + "============\n");
        for (String str : list) {
            System.out.println("*** =>" + str + "<=");
        }
        System.out.println("\n=================================\n\n");
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
        List<String> trimList = new ArrayList<>();

        for (ParseTree pt : parseTreeList) {
            String line = pt.getText();
            line = line.replaceAll("[\r]+", "");
            line = line.replaceAll("[ \t]+$", "");
            line = line.replaceAll("^[ \t]+", "");
            line = line.replaceAll("[\n]+", "\n");
            if (line.isEmpty() == false) {
                trimList.add(line);
            }
        }
        return trimList;
    }

    
    @Override 
    public void visitErrorNode(ErrorNode node) { 
        for (int ii=0; ii < node.getChildCount(); ii++) {
            if(debug) {
                System.out.println("ERROR =>" + node.getChild(ii) + "<=");
            }
        }
    }
    
    @Override
    public void enterDefine(TTEParser.DefineContext ctx) {
        List<String> trimList = getTrimList(ctx.children);
        
        if (debug) {
            this.displayList("enterKey_val", trimList);
        }
        
    }

    @Override
    public void enterLiteral_string(TTEParser.Literal_stringContext ctx) {
        List<String> trimList = getTrimList(ctx.children);
        if (debug) {
            this.displayList("enterKey_val", trimList);
        }
        StringBuilder sb = new StringBuilder();
        for (String line : trimList) {
            sb.append(line);
        }
        System.out.println(sb.toString());
    }

    @Override
    public void enterKey_val(TTEParser.Key_valContext ctx) {
        List<String> trimList = getTrimList(ctx.children);
        if (debug) {
            this.displayList("enterKey_val", trimList);
        }
        StringBuilder sb = new StringBuilder();
        for (int ii = 0; ii < trimList.size(); ii++) {
            String line = trimList.get(ii);

            if (ii == 0) {
                sb.append(line);
            } else if (ii > 0) {
                sb.append("_").append(line);
            }
        }
        // TODO: need to fix this!!!!

        String output = sb.toString().replaceAll("_$", "");

        System.out.println(output);
    }

    @Override
    public void enterBegin_section(TTEParser.Begin_sectionContext ctx) {
        List<String> trimList = getTrimList(ctx.children);
        if (debug) {
            this.displayList("enterBegin_section", trimList);
        }

        StringBuilder sb = new StringBuilder();
        for (String line : trimList) {
            sb.append(line);
        }
        System.out.println(sb.toString());
    }

    @Override
    public void enterEnd_section(TTEParser.End_sectionContext ctx) {
        List<String> trimList = getTrimList(ctx.children);
        if (debug) {
            this.displayList("enterEnd_section", trimList);
        }
        StringBuilder sb = new StringBuilder();
        for (String line : trimList) {
            sb.append(line);
        }
        System.out.println(sb.toString());
    }

    @Override
    public void enterIndex_line(TTEParser.Index_lineContext ctx) {
        List<String> trimList = getTrimList(ctx.children);

        if (debug) {
            this.displayList("enterIndex_line", trimList);
        }

        StringBuilder sb = new StringBuilder();
        for (String line : trimList) {
            if (line.matches(".*[\n]$") == false) {
                sb.append(line).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

}
