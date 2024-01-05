//package com.example.structure;
package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.*;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.InputMismatchException;

public class rayanController {

    static int sections;

    static int pos = 1;

    public static CursorStackList cursorList = new CursorStackList();

    static EquationEngine equationEngine = new EquationEngine(cursorList);

    static void loadFileContents(TextField pathField, TextArea equationTextArea) {
        try {
        	
        	Loadsfiles fileLoader = new Loadsfiles(new File(pathField.getText()), cursorList);
            if (!fileLoader.fileValidator()) {
                throw new InputMismatchException();
            } else {
                sections = fileLoader.loadFile();
            }
            int section ;

            section =  Integer.parseInt(cursorList.itemAt(sections, 1));

            StringBuilder infix = new StringBuilder("Infix: \n");
            StringBuilder postfix = new StringBuilder("Postfix: \n");

            while(cursorList.cursorNodes[section].next != 0)
            {
                section = cursorList.cursorNodes[section].next;


                String equation = cursorList.cursorNodes[section].data.trim();
                if(equationEngine.isPostfix(equation))
                {
                    postfix.append("-> ");
                    postfix.append(equation + " ===> ");
                    equation = equationEngine.postfixToPrefix(equation);
                    postfix.append(equation + " ====> ");
                    postfix.append(equationEngine.evaluatePrefix(equation));
                    postfix.append("\n");
                }
                else if(!equationEngine.isPostfix(equation))
                {
                    infix.append("-> ");
                    infix.append(equation + " ===> ");
                    equation = equationEngine.infixToPostfix(equation);
                    infix.append(equation + " ====> ");
                    infix.append(equationEngine.evaluatePostfix(equation));
                    infix.append("\n");
                }
            }
            equationTextArea.setText(postfix.toString() + "\n\n" + infix.toString());
        } catch (InputMismatchException ex) {
            equationTextArea.setText("Error encountered when reading file. Check formatting.");
        } catch (IOException ex) {
            equationTextArea.setText("File was not found");
        }
    }



    static void showNext(TextArea equationTextArea) {
        if (cursorList.itemAt(sections, pos + 1) == null) {
            return;
        }

        int section = Integer.parseInt(cursorList.itemAt(sections, ++pos));
        StringBuilder infix = new StringBuilder("Infix: \n");
        StringBuilder postfix = new StringBuilder("Postfix: \n");

        while (cursorList.cursorNodes[section].next != 0) {
            section = cursorList.cursorNodes[section].next;
            String equation = cursorList.cursorNodes[section].data.trim();

            if (equationEngine.isPostfix(equation)) {
                appendPostfixConversion(postfix, equation);
            } else {
                appendInfixConversion(infix, equation);
            }
        }

        equationTextArea.setText(postfix.toString() + "\n\n" + infix.toString());
    }

    static void showPrev(TextArea equationTextArea) {
        if (pos == 1) {
            return;
        }

        int section = Integer.parseInt(cursorList.itemAt(sections, --pos));
        StringBuilder infix = new StringBuilder("Infix: \n");
        StringBuilder postfix = new StringBuilder("Postfix: \n");

        while (cursorList.cursorNodes[section].next != 0) {
            section = cursorList.cursorNodes[section].next;
            String equation = cursorList.cursorNodes[section].data.trim();

            if (equationEngine.isPostfix(equation)) {
                appendPostfixConversion(postfix, equation);
            } else {
                appendInfixConversion(infix, equation);
            }
        }

        equationTextArea.setText(postfix.toString() + "\n\n" + infix.toString());
    }

    private static void appendPostfixConversion(StringBuilder postfix, String equation) {
        postfix.append("-> ");
        postfix.append(equation + " ===> ");
        equation = equationEngine.postfixToPrefix(equation);
        postfix.append(equation + " ====> ");
        postfix.append(equationEngine.evaluatePrefix(equation));
        postfix.append("\n");
    }

    private static void appendInfixConversion(StringBuilder infix, String equation) {
        infix.append("-> ");
        infix.append(equation + " ===> ");
        equation = equationEngine.infixToPostfix(equation);
        infix.append(equation + " ====> ");
        infix.append(equationEngine.evaluatePostfix(equation));
        infix.append("\n");
    

    }

}
