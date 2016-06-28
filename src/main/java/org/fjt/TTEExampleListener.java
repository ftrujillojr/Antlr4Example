/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fjt;

import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fjt.grammar.TTEBaseListener;
import org.fjt.grammar.TTEParser;

public class TTEExampleListener extends TTEBaseListener {

    @Override
    public void enterSection_contents(TTEParser.Section_contentsContext ctx) {
        this.displayParseTreeList("enterSection_contents", ctx.children);
    }

    @Override
    public void enterBegin_section(TTEParser.Begin_sectionContext ctx) {
        this.displayParseTreeList("enterBegin_section", ctx.children);
    }

    @Override
    public void enterEnd_section(TTEParser.End_sectionContext ctx) {
        this.displayParseTreeList("enterEnd_section", ctx.children);
    }

    @Override
    public void enterIndex_line(TTEParser.Index_lineContext ctx) {
        this.displayParseTreeList("enterIndex_line", ctx.children);
    }

    private void displayParseTreeList(String src, List<ParseTree> parseTreeList) {
        System.out.println("============= " + src + "============\n");
        for (ParseTree pt : parseTreeList) {
            System.out.println("CHILD =>" + pt.getText() + "<=");
        }
        System.out.println("=================================\n");
    }
}
