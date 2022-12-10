/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package LinkedLists;

/**
 *
 * @author leoli
 */
public class LinkedListDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListNode a = new ListNode("Im a");
        ListNode b = new ListNode("Im b");
        ListNode c = new ListNode("Im c");
        ListNode d = new ListNode("Im d");
        ListNode e = new ListNode("Im e");

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        ListNode temp = a;
        while (temp != null) {
            System.out.println("Data: " + temp.getData());
            temp = temp.next;
        }

        ListNode f = new ListNode("Im an intruder");
        f.next = d;
        c.next = f;
        System.out.println("---------------------------");
        temp = a;
        while (temp != null) {
            System.out.println("Data: " + temp.getData());
            temp = temp.next;
        }

    }
}
