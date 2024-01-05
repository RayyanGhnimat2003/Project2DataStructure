package application;

//package com.example.structure;

public class CursorStackList {
	class CursorNode {

		  String data;

		  int next;

		  public CursorNode(String data, int next) {
		      this.data = data;
		      this.next = next;
		  }

			public String getData() {
				return data;
			}

			public void setData(String data) {
				this.data = data;
			}

			public int getNext() {
				return next;
			}

			public void setNext(int next) {
				this.next = next;
			}

			@Override
			public String toString() {
				return "CursorNode [data=" + data + ", next=" + next + "]";
			}
	}

  CursorNode[] cursorNodes;
  public static int freeList = 0;
  final static int SIZE = 1000;

  CursorStackList()
  {
      this(SIZE);
  }

  CursorStackList(int size)
  {
      cursorNodes = new CursorNode[size];
      for(int i = 0; i < size; i++) {
          cursorNodes[i] = new CursorNode(null, i + 1);
      }
      cursorNodes[size - 1].next = 0;
  }

  public int alloc()
  {
      int free = cursorNodes[0].next;
      cursorNodes[0].next = cursorNodes[free].next;
      cursorNodes[free].next = 0;
      return free;
  }


  public boolean pushElement(int list, String data)
  {
      int free = alloc();
      int temp = list;
      if(free == 0)
      {
          return false;
      }
      while(cursorNodes[temp].next != 0)
      {
          temp = cursorNodes[temp].next;
      }
      cursorNodes[temp].next = free;
      cursorNodes[free].data = data;

      return true;
  }

  public void deleteList(int list)
  {

  }

  public void popElement(int list)
  {
      int temp = list;
      while(cursorNodes[temp].next != 0)
      {
          temp = cursorNodes[temp].next;
      }
      int prev = list;
      while(cursorNodes[prev].next != temp)
      {
          prev = cursorNodes[prev].next;
      }

      free(temp);
      cursorNodes[prev].next = 0;
  }

  public void free(int pos)
  {
      cursorNodes[pos].data = null;
      cursorNodes[pos].next = cursorNodes[0].next;
      cursorNodes[0].next = pos;
  }

  public String peekElement(int list)
  {
      int temp = list;
      while(cursorNodes[temp].next != 0)
      {
          temp = cursorNodes[temp].next;
      }
      return cursorNodes[temp].data;
  }

  public boolean isEmpty(int list)
  {
      int temp = 0;
      if(cursorNodes[list].next == 0 )
      {
          return true;
      }
      while(cursorNodes[temp].next != 0)
      {
          temp = cursorNodes[temp].next;
          if(temp == list)
          {
              return true;
          }
      }
      return false;
  }

  public String itemAt(int list, int pos)
  {
      int counter = 0;
      int temp = list;
      while(cursorNodes[temp].next != 0)
      {
          counter++;
          temp = cursorNodes[temp].next;
          if(counter == pos)
              return cursorNodes[temp].data;
      }
      return null;
  }
}
