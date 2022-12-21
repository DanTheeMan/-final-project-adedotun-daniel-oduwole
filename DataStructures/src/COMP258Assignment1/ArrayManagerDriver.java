/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP258Assignment1;
import exceptions.NoItemsException;
import exceptions.OutOfBoundsException;
import exceptions.InvalidArgumentException;
/**
 *
 * @author leoli
 */

import LinkedLists.LinkedList;
public class ArrayManagerDriver {
    public static void main(String[] args) {
        Object[] items = {"apple", "banana", "apple", "orange", "grape", "banana", "apple"};
    ArrayManager1 manager = new ArrayManager1(items);
    
    manager.print();
    LinkedList list = manager.convertToLinkedList();
    list.print();
    System.out.println(manager.countOccurrences("apple"));
    try {
      manager.removeRange(0, 1);
      manager.removeDuplicates();
      System.out.println("Successfully removed elements from index 1 to 3");
      manager.print();
        
    } catch (NoItemsException e) {
      System.out.println(e.getMessage());
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
      catch (InvalidArgumentException e) {
      System.out.println(e.getMessage());
      }
   
    }
    
    
    
    //if (count == 0) {
    //  throw new NoItemsException("There are no items in the array.");
    //}
    //if (start < 0 || end >= count) {
    //  throw new IndexOutOfBoundsException("Invalid index.");
    //}
    //if (start > end) {
    //  throw new InvalidArgumentException("Start index must be less than or equal to end index.");
    //}
}
