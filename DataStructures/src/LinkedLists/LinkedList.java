/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedLists;
import exceptions.NoItemsException;
import exceptions.OutOfBoundsException;
/**
 *
 * @author leoli
 */
public class LinkedList {
    private ListNode start;
    private ListNode current;
    private int count;
    
    
    
    public LinkedList(){
        
    }
    private void recursivePrint(ListNode temp){
        if(temp==null){
            return;
        }
        else{
            System.out.println(temp.getData());
            recursivePrint(temp.next);
        }
    }

    public Object getCurrent(){
        return current.getData();

    }

    public void setCurrent(ListNode current) {
        this.current = current;
    }

    public void add(Object item){
        ListNode node = new ListNode(item);
        if(start == null){
            start = node;
            current = start;
        }
        else{
            current.next = node;
            current = current.next;
        }
        count++;
    }

    public void print(){
        ListNode temp = start;
        while(temp!=null){
            System.out.println("Data: " + temp.getData());
            temp = temp.next;
        }
    }

    public int size(){
        return count;
    }

    public void start(){
        current = start;
    }

//    public void advance(){
//        current = current.next;
//    }


    public void advance() throws OutOfBoundsException {
        if (current.next == null) {
            throw new OutOfBoundsException("You have gone outside the bounds of the list.");
        }
        current = current.next;
    }

    public void addAfter(Object item) {
        //add after current
        //1. Create the ListNode for item
        ListNode temp = new ListNode(item);
        //2. Handle empty list edge case
        if (start == null) {
            start = temp;
            current = temp;
        }
        //3. Add new node after current
        else {
            temp.next = current.next;
            current.next = temp;
            current = current.next; // or current = temp
        }
        count++;
    }
        public void removeCurrent() throws NoItemsException{

            if(start == null){
                throw new NoItemsException("No items in collection to delete");
            }

            else if(current == start){
                start = start.next;
                count--; // removing
            }
            else {
                //1. Declare a temp node
                ListNode temp = start;

                //2. Advance till its next is current
                while (temp.next != current) {
                    temp = temp.next;
                }

                //3. Point it to what comes after current
                temp.next = temp.next.next;


                //4. Point current to temp
                current = temp;

                count--;
            }

            //think about any edge cases

    }

            public void addBefore(Object item){
                ListNode node = new ListNode(item);
                if(start == null){
                    start = node;
                    current = start;
                }
                else if(current == start){
                    node.next = current;
                    start = node;
                    current = node;
                }
                else{
                    //1. get a reference to the node before current
                    ListNode temp = start;
                    while(temp.next != current){
                        temp = temp.next;

                    }
                    //2. use it to insert the new node before current
                    node.next = current;
                    temp.next = node;
                    current = node.next;

                    //current = current.next;

                    //edge cases

                }

                count++;

            }

            public Object getItemAt(int pos) throws OutOfBoundsException{
                if (pos < 0 || pos > count){
                    throw new OutOfBoundsException("Outtabounds!!!");
                }
                ListNode temp = start;
                for (int i = 0; i < pos; i++){
                    temp = temp.next;
                }
                return temp.getData();
            }
        
}
