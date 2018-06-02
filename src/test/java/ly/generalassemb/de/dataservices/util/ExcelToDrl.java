package ly.generalassemb.de.dataservices.util;


import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ExcelToDrl {
    public static void main(String args[]) {
        // create an input stream
        InputStream is = null;
        try {
            // assign the excel to the input stream
            // mention the local directory path where your excel is kept
            // you can take any decision table (excel sheet) for testing
            is = new FileInputStream("/Users/davidashirov/Source/GA/de-ds-docusign-template-library/src/main/resources/DroolsDecisionTable.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            // create compiler class instance
        SpreadsheetCompiler sc = new SpreadsheetCompiler();
            // compile the excel to generate the (.drl) file
        String drl = sc.compile(is, InputType.XLS);
           // check the generated (.drl) file
        System.out.println("Generate DRL file is –: ");
        System.out.println(drl);
    }
}