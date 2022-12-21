/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP258Assignment1;

import LinkedLists.LinkedList;
import exceptions.NoItemsException;
import exceptions.OutOfBoundsException;
import exceptions.InvalidArgumentException;

/**
 *
 * @author leoli
 */
public class ArrayManager1 {

    private Object[] items;
    private int count;

    public ArrayManager1() {
        items = new Object[10];
        count = 0;
    }

    public ArrayManager1(Object[] items) {
        this.items = items;
        this.count = items.length;
    }

    public ArrayManager1(int size) {
        items = new Object[size];
        count = 0;
    }

    public void add(Object o) {
        if (count < items.length) {
            items[count] = o;
            count++; // adding things to the array
        } else {
            Object[] temp = new Object[items.length + 10];
            System.arraycopy(items, 0, temp, 0, count);
            items = temp;
            items[count] = o;
            count++;
        }
    }

    public void resize() {
        Object[] temp = new Object[items.length + 10];
        System.arraycopy(items, 0, temp, 0, count);
        items = temp;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    public void printItems() {
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    public int size() { //utility method to count size of array
        return count;
    }

    public boolean isEmpty() { // checking if array has anything in it
        return count == 0;
    }

    public void insertAt(Object o, int pos) {
        //1. Resize if necessary
        if (count >= items.length) {
            resize();
        }
        //2. Create a space in the pos ie. (1,12,3,4,5) insert 66 at pos 2 {1,12,3,4,5}
        System.arraycopy(items, pos, items, pos + 1, count - pos);
        //3. Add new item at pos
        items[pos] = o;
        //4. Count new item
        count++;
    }

    public Object getItemAt(int pos) throws OutOfBoundsException {
        if (pos < 0 || pos > count) {
            throw new OutOfBoundsException(("Cannot retreive item outside of collection bounds"));
        }
        return items[pos];
    }

    public void insertAT(Object o, int pos) throws OutOfBoundsException {
        if (pos < 0 || pos > count) {
            throw new OutOfBoundsException("Cannot retrieve item outside of collection bounds");
        }

        //1. Resize if necessary
        if (count >= items.length) {
            resize();
        }
        //2. Create a space in the pos {1,12,3,4,5} insert 66 at pos 2 {1,12,3,4,5}
        System.arraycopy(items, pos, items, pos + 1, count - pos);
        //3. Add new item at pos
        items[pos] = o;
        //4. Count new item
        count++;
    }

    public void remove(int pos) throws NoItemsException, OutOfBoundsException {
        if (count == 0) {
            throw new NoItemsException("Removed failed. The collection is empty.");
        }
        if (pos < 0 || pos > count) {
            throw new OutOfBoundsException("Cannot retrieve item outside of collection bounds");
        }
        //1 copy everything from position to count, back 1 space
        System.arraycopy(items, pos + 1, items, pos, count - pos);
        //2 decrement count
        count--;
    }

    public void removeRange(int start, int end) throws NoItemsException, IndexOutOfBoundsException, InvalidArgumentException {
        if (count == 0) {
            throw new NoItemsException("Array is empty");
        }

        if (start < 0 || end >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (start > end) {
            throw new InvalidArgumentException("Start index cannot be greater than end index");
        }

        int newCount = count - (end - start + 1);
        Object[] newItems = new Object[newCount];

        System.arraycopy(items, 0, newItems, 0, start);
        System.arraycopy(items, end + 1, newItems, start, items.length - end - 1);

        items = newItems;
        count = newCount;
    }

    public int countOccurrences(Object item) {
    int occurrences = 0;
    for (int i = 0; i < count; i++) {
      if (items[i] == item) {
        occurrences++;
      }
    }
    return occurrences;
  }

    public LinkedList convertToLinkedList() {
    LinkedList list = new LinkedList();
    for (int i = 0; i < count; i++) {
      list.add(items[i]);
    }
    return list;
  }

    public void removeDuplicates() {
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (items[i].equals(items[j])) {
                    items[j] = null;
                }
            }
        }
        Object[] newItems = new Object[count];
        int newIndex = 0;
        for (int i = 0; i < count; i++) {
            if (items[i] != null) {
                newItems[newIndex] = items[i];
                newIndex++;
            }
        }
        items = newItems;
        count = newIndex;
    }
}

