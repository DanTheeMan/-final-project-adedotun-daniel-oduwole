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
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.print();
        System.out.println(list.size());
        System.out.println("----------------------------------------");
        
        list.start();
        System.out.println("Current: " + list.getCurrent());
        list.advance();
        System.out.println("Current: " + list.getCurrent());
        
        
        
    }
    
}
