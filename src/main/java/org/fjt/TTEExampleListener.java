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

/**
 *
 * @author ftrujillo
 */
public class TTEExampleListener extends TTEBaseListener {
    @Override
    public void enterIndex_line(TTEParser.Index_lineContext ctx) {
        
        this.displayParseTreeList("enterIndex_line ", ctx.children);
    }
            
//    @Override
//    public void enterSection_begin_header(TTEParser.Section_begin_headerContext ctx) {
//        this.displayParseTreeList(ctx.children);
//    }
//    
//    @Override
//    public void enterSection_begin(TTEParser.Section_beginContext ctx) {
//        this.displayParseTreeList(ctx.children);
//    }
    
    private void displayParseTreeList(String src, List<ParseTree> parseTreeList) {
        System.out.println("============= " + src + "============\n");
        for(ParseTree pt: parseTreeList) {
            System.out.println("CHILD =>" + pt.getText() + "<=");
        }
        System.out.println("=================================\n");
    }
}
