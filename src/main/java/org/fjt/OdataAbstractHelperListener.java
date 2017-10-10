package org.fjt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fjt.grammar.OdataBaseListener;

/**
 * Only change the class name and the extends Base Listener class that is GENERATED
 * from g4 grammar.  (OdataBaseListener in this case)
 * 
 * All methods should not change.
 * 
 * This ABSTRACT class will be implemented in your Listener class.
 * 
 * Example:
 * 
 * public class OdataListener extends OdataHelperListener
 * 
 */
public abstract class OdataAbstractHelperListener extends OdataBaseListener {
    protected static boolean debug = true; // set this to false for production.
    protected static boolean preSet = true;

    protected static Stack<String> sectionStack = new Stack<>();
    protected static List<String> errorMessages = new ArrayList<>();

    protected void displayList(String src, List<String> list, int lineNumber) {
        StringBuilder sb = new StringBuilder();

        System.out.println("============= " + src + " ============\n");
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
    
    protected List<String> getList(List<ParseTree> parseTreeList) {
        List<String> returnList;

        if (preSet) {
            returnList = this.getUNModifiedList(parseTreeList);
        } else {
            returnList = this.getTrimList(parseTreeList);
        }
        return returnList;
    }

    protected List<String> getUNModifiedList(List<ParseTree> parseTreeList) {
        List<String> modifiedList = new ArrayList<>();

        for (ParseTree pt : parseTreeList) {
            String line = pt.getText();
            if (line.isEmpty() == false) {
                modifiedList.add(line);
            }
        }
        return modifiedList;
    }

    protected List<String> getTrimList(List<ParseTree> parseTreeList) {
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
}
