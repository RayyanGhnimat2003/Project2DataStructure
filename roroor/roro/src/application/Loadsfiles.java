package application;

//package com.example.structure;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loadsfiles {

  private File dest;
  private CursorStackList lists;

  public Loadsfiles(File dest, CursorStackList lists) {
      this.dest = dest;
      this.lists = lists;
  }

  public boolean fileValidator() throws InputMismatchException
  {

          String input;
          int check_stack = lists.alloc();
          String regexExp = "<[^>]+>";
          Pattern pattern = Pattern.compile(regexExp);
          StringBuilder content = new StringBuilder();
          String fileContent = fileToString();
          fileContent = fileContent.replaceAll("\\s+$", "");

          Matcher matcher = pattern.matcher(fileContent);
          int counter = 0;
          while(matcher.find())
          {

              input =  matcher.group();
              if(counter++ == 0 && input.trim().equals("<242>"))
                  continue;
              else if(counter == 0)
                  return false;

              switch (input.trim()) {
                  case rayanSections0.SECTION_START:
                  case rayanSections0.INFIX_START:
                  case rayanSections0.POSTFIX_START:
                  case rayanSections0.EQUATION_START:
                      lists.pushElement(check_stack, input);
                      break;
                  case rayanSections0.SECTION_END:
                      if (!lists.peekElement(check_stack).equals(rayanSections0.SECTION_START))
                          throw new InputMismatchException();
                      lists.popElement(check_stack);
                      break;
                  case rayanSections0.INFIX_END:
                      if (!lists.peekElement(check_stack).equals(rayanSections0.INFIX_START))
                          throw new InputMismatchException();
                      lists.popElement(check_stack);
                      break;
                  case rayanSections0.POSTFIX_END:
                      if (!lists.peekElement(check_stack).equals(rayanSections0.POSTFIX_START))
                          throw new InputMismatchException();
                      lists.popElement(check_stack);
                      break;
                  case rayanSections0.EQUATION_END:
                      if (!lists.peekElement(check_stack).equals(rayanSections0.EQUATION_START))
                          throw new InputMismatchException();
                      lists.popElement(check_stack);
                      break;
                  case "</242>":
                      matcher.find();
                      if (!matcher.hitEnd()) {
                          return false;
                      }
              }

      }

      return true;
  }

  public int loadFile() throws IOException
  {
      int sections = lists.alloc();
      String line= "";
      Scanner scanner = new Scanner(dest);
      while(scanner.hasNext()) {
          line = scanner.nextLine().trim();
          if (line.equals("<section>")) {
              int section = lists.alloc();
              lists.pushElement(sections, String.valueOf(section));
              while (!line.equals("</section>")) {
                  String regex = "<equation>(.*?)</equation>";
                  Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
                  Matcher matcher = pattern.matcher(line);
                  if (matcher.find()) {
                      String equation = matcher.group(1).trim();
                      lists.pushElement(section, equation);
                  }
                  line = scanner.nextLine().trim();
              }
          }
      }

      return sections;
  }

  String fileToString()
  {
      StringBuilder content = new StringBuilder();
      String contentToSave = "";
      try (BufferedReader reader = new BufferedReader(new FileReader(dest))) {
          String line;
          while ((line = reader.readLine()) != null) {
              content.append(line).append("\n");
          }
          contentToSave = content.toString();

      }
      catch (IOException ex)
      {

      }
      return contentToSave;
  }



}
