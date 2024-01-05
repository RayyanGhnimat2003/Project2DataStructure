package application;

///package com.example.structure;

public class EquationEngine {


	CursorStackList cursorList;

  public EquationEngine(CursorStackList cursorList) {
      this.cursorList = cursorList;
  }

  private static boolean isOperator(char ch) {
      return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
  }

  private static int getPrecedence(char operator) {
      switch (operator) {
          case '+':
          case '-':
              return 1;
          case '*':
          case '/':
              return 2;
          case '^':
              return 3;
          default:
              return -1;
      }
  }

  public  String infixToPostfix(String infix) {
      StringBuilder postfix = new StringBuilder();
      int stack = cursorList.alloc();
      String[] terms = infix.split(" ");

      for (String term : terms) {
          if ( !isOperator(term.charAt(0)) && (term.charAt(0) != '(') && (term.charAt(0) != ')') ) {
              postfix.append(term + " ");
          }
          else if (term.charAt(0) == '(') {
              cursorList.pushElement(stack, term);
          } else if (term.charAt(0) == ')') {
              while (!cursorList.isEmpty(stack) && cursorList.peekElement(stack).charAt(0) != '(') {
                  postfix.append(cursorList.peekElement(stack) + " ");
                  cursorList.popElement(stack);
              }
              cursorList.popElement(stack);
          }
          else if (isOperator(term.charAt(0))) {
               while (!cursorList.isEmpty(stack) && getPrecedence(term.charAt(0)) <=
                      getPrecedence(cursorList.peekElement(stack).charAt(0))) {
                  postfix.append(cursorList.peekElement(stack) + " ");
                  cursorList.popElement(stack);
               }
              cursorList.pushElement(stack, term);
          }
      }

      while (!cursorList.isEmpty(stack)) {
          if (cursorList.peekElement(stack).charAt(0) == '(')
              return "ERROR";
          postfix.append(cursorList.peekElement(stack) + " ");
          cursorList.popElement(stack);
      }

     String retVal = postfix.toString();
      return  retVal
              ;
  }

  public  String postfixToPrefix(String postfix) {
      int stack = cursorList.alloc();

      for (String term : postfix.split(" ")) {
          if (!isOperator(term.charAt(0))) {
              cursorList.pushElement(stack, term);
          } else {

              String operand2 = cursorList.peekElement(stack);
              cursorList.popElement(stack);
              String operand1 = cursorList.peekElement(stack);
              cursorList.popElement(stack);
              String toPush = term +  " " + operand1 + " " + operand2;

              cursorList.pushElement(stack, toPush );
          }
      }

      String retVal = cursorList.peekElement(stack);

      return cursorList.peekElement(stack);

  }

  public Double evaluatePrefix(String postfix) {
      postfix = postfix.trim();
      int stack = cursorList.alloc();
      String[] terms =  postfix.split(" ");

      for (int i = terms.length - 1; i >= 0; i-- ) {
          String term = terms[i];
          if (term.length() == 1 && isOperator(term.charAt(0))) {
              Double operand2 = Double.valueOf(cursorList.peekElement(stack));
              cursorList.popElement(stack);
              Double operand1 = Double.valueOf(cursorList.peekElement(stack));
              cursorList.popElement(stack);

              switch (term.charAt(0)) {
                  case '+':
                      String eval = String.valueOf(operand1 + operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '-':
                       eval = String.valueOf(operand1 - operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '*':
                       eval = String.valueOf(operand1 * operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '/':
                       eval = String.valueOf(operand1 / operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '^':
                      eval = String.valueOf(Math.pow(operand1 , operand2));
                      cursorList.pushElement(stack, eval);
                      break;
              }


          } else {
              cursorList.pushElement(stack,term);
          }
      }
      double result = Double.valueOf(cursorList.peekElement(stack));
      cursorList.popElement(stack);
      return result;
  }
  public Double evaluatePostfix(String prefix) {
      int stack = cursorList.alloc();
      prefix = prefix.trim();
      for (String term : prefix.split(" ")) {
          
          if (!isOperator(term.charAt(0))) {

              cursorList.pushElement(stack, term);
          } else {
               double operand1 = Double.valueOf(cursorList.peekElement(stack));
              cursorList.popElement(stack);
              double operand2 = Double.valueOf(cursorList.peekElement(stack));
              cursorList.popElement(stack);

              switch (term.charAt(0)) {
                  case '+':
                      String eval = String.valueOf(operand1 + operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '-':
                      eval = String.valueOf(operand1 - operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '*':
                      eval = String.valueOf(operand1 * operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '/':
                      eval = String.valueOf(operand1 / operand2);
                      cursorList.pushElement(stack, eval);
                      break;
                  case '^':
                      eval = String.valueOf(Math.pow(operand1 , operand2));
                      cursorList.pushElement(stack, eval);
                      break;
              }
          }
      }

      double retVal = Double.valueOf(cursorList.peekElement(stack));
      cursorList.popElement(stack);
      return retVal;
  }

  boolean isPostfix(String equation){
      if(equation.charAt(equation.length() -1) == ')')
          return false;
      if(!Character.isDigit(equation.charAt(equation.length() -1)))
      {
          return true;
      }
      return false;
  }


}



