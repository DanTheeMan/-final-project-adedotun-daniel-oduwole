/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP258Assignment1;
import LinkedLists.LinkedList;
import exceptions.*;
/**
 *
 * @author leoli
 */
public class DoublyLinkedList {
  private ListNode2Way start;
  private ListNode2Way current;
  private ListNode2Way end;
  private int count;

  public DoublyLinkedList() {
    this.start = null;
    this.current = null;
    this.end = null;
    this.count = 0;
  }

  public DoublyLinkedList(Object[] items) {
        // initialize the list with the provided items
        this.start = null;
        this.current = null;
        this.end = null;
        this.count = 0;
        

        // loop through the rest of the items in the array and add them to the end of the list
        for (int i = 0; i < items.length; i++) {
            addAfter(items[i]);
            
        }
       
        current=start;
    }


  public void addBefore(Object item) {
        // create a new node
        ListNode2Way newNode = new ListNode2Way();
        newNode.setData(item);

        // if the list is empty, set the new node as the start and end
        if (count == 0) {
            start = newNode;
            end = newNode;
            current = newNode;
        }
        //if the current node is the start, add the new node as the start
        else if (current == start) {
            newNode.next = start;
            start.previous = newNode;
            start = newNode;
            current = newNode;
        }
        // otherwise, add the new node before the current node
        else {
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous = newNode;
            newNode.previous.next = newNode;
            current = newNode;
        }

        // update the count
        count++;
        
    }

  //Method to add an item after the tail of the list
  public void addAfter(Object item) {
        // create a new node
        ListNode2Way newNode = new ListNode2Way();
        newNode.setData(item);

        //if the list is empty, set the new node as the start and end
        if (count == 0) {
            start = newNode;
            end = newNode;
            current = newNode;
        }
        // if the current node is the end, add the new node as the end
        else if (current == end) {
            newNode.previous = end;
            end.next = newNode;
            end = newNode;
            current = newNode;
        }
        // otherwise, add the new node after the current node
        else {
            newNode.previous = current;
            newNode.next = current.next;
            current.next = newNode;
            newNode.next.previous = newNode;
            current = newNode;
        }

        // update the count
        count++;
        
    }

  public Object getCurrent() {
    if (current == null) {
      return null;
    }
    return current.getData();
  }
  public void remove() {
        // Implementation for removing the current element from the list
        if (current == null) {
            return;
        }

        if (current.previous != null) {
            current.previous.next = current.next;
        } else {
            // The current element is the start of the list
            start = current.next;
        }

        if (current.next != null) {
            current.next.previous = current.previous;
        } else {
            // The current element is the end of the list
            end = current.previous;
        }

        count--;
        current = current.next;
    }
  public int size(){
        return count;
    }

  public void removeRange(int start, int end) {
    if (start < 0 || end >= count || start > end) {
      throw new IndexOutOfBoundsException();
    }

    // find the first element to remove
    ListNode2Way current = this.start;
    for (int i = 0; i < start; i++) {
      current = current.next;
    }

    // remove all elements in the specified range
    for (int i = start; i <= end; i++) {
      current.previous.next = current.next;
      current.next.previous = current.previous;
      current = current.next;
      count--;
    }
  }


  public void advance() {
      if (current == null || current.next == null) {
      return;
    }
    current = current.next;
  }

  // Method to move the current node one position backward
  public void previous() {
    if (current == null || current.previous == null) {
      return;
    }
    current = current.previous;
  }

  // Method to print the list forward
  public void printforward() {
    ListNode2Way current = start;
    while (current != null) {
      System.out.println(current.getData());
      current = current.next;
    }
  }

  public void printBackwards() {
    ListNode2Way current = end;
    while (current != null) {
      System.out.print(current.getData() + " ");
      current = current.previous;
    }
    System.out.println();
  }
}
