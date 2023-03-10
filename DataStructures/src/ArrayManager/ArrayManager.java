/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArrayManager;
import exceptions.NoItemsException;
import exceptions.OutOfBoundsException;
/**
 *
 * @author leoli
 */
public class ArrayManager {
    private Object[] items;
    private int count;
    
    public ArrayManager() {
        items = new Object[10];
        count = 0;
    }

    public ArrayManager(Object[] items) {
        this.items = items;
        count = items.length;
    }

    public ArrayManager(int size) {
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


    public void insertAT(Object o, int pos) throws OutOfBoundsException{
        if(pos < 0 || pos > count){
            throw new OutOfBoundsException("Cannot retrieve item outside of collection bounds");
        }


        //1. Resize if necessary
        if (count >= items.length){
            resize();
        }
        //2. Create a space in the pos {1,12,3,4,5} insert 66 at pos 2 {1,12,3,4,5}
        System.arraycopy(items,pos,items,pos+1,count- pos);
        //3. Add new item at pos
        items [pos] = o;
        //4. Count new item
        count++;
    }



    public void remove(int pos) throws NoItemsException,OutOfBoundsException{
        if (count == 0){
            throw new NoItemsException("Removed failed. The collection is empty.");
        }
        if (pos<0 || pos> count){
            throw new OutOfBoundsException("Cannot retrieve item outside of collection bounds");
        }
        //1 copy everything from position to count, back 1 space
        System.arraycopy(items,pos+1,items,pos,count - pos);
        //2 decrement count
        count--;
    }
}
