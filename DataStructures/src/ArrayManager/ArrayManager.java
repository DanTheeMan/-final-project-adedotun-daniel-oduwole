/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArrayManager;

/**
 *
 * @author leoli
 */
public class ArrayManager {
    private Object[] items;
    private int count;
    
    public ArrayManager(){
        items = new Object[10];
        count = 0;
    }
    
    public ArrayManager(Object[] items){
        this.items=items;
        count = items.length;
    }
    public ArrayManager(int size){
        items = new Object[size];
        count = 0;
    }
    public void add(Object o){
        if(count < items.length){
            items[count]=o;
            count++;
        }
        else{
            resize();
            items[count]=o;
            count++;
        }
    }
    
    public void resize(){
        Object[] temp = new Object[items.length+10];
        System.arraycopy(items, 0, temp, 0, count);
        items=temp;
    }
    
    public void print(){
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
            
        }
    }
    
    public void printItems(){
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
            
        }
    }
    public int size(){
        return count;
    }
    
    
    
    public boolean isEmpty(){
        if(count == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void insertAt(Object o, int pos){
       
        //1. Resize if necessary
        
        if(count>=items.length){
            resize();
        }
        //2. Create a space in the position [1,12,3,4,5] insert 66 at pos 2 [1,12,(blank),3,4,5]
        System.arraycopy(items, pos, items, pos+1, count-pos);
        //3. Add new item at blank position
        items[pos]=o;
        //4. make sure to count new item
        count++;
    }
}
