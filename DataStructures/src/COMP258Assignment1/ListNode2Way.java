/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP258Assignment1;

/**
 *
 * @author leoli
 */
public class ListNode2Way {
    private Object data;
    public ListNode2Way next;
    public ListNode2Way previous;

    public ListNode2Way() {
        this.data = null;
        this.next = null;
        this.previous = null;
    }
    public ListNode2Way(Object Data) {
        this.data = null;
        this.next = null;
        this.previous = null;
    }
    

   
    public Object getData() {
        return data;
    }

    public void setData(Object item) {
        this.data = item;
    }
}
