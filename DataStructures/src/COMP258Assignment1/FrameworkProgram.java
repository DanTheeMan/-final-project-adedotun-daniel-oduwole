/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP258Assignment1;

import java.util.Scanner;

/**
 *
 * @author leoli
 */
public class FrameworkProgram extends DoublyLinkedList{
    private Scanner input;
    public Object[] items = {"start", "banana", "apple", "orange", "grape", "banana", "end"};
    public DoublyLinkedList manager = new DoublyLinkedList(items);

    public FrameworkProgram() {
        input = new Scanner(System.in);
       
    }

    
    public void run() {
        int choice = 0;
        while (choice != 11) {
            choice = displayActions();
            executeAction(choice);
        }
    }

    public int displayActions() {
        System.out.println("1. getCurrent");
        System.out.println("2. Advance");
        System.out.println("3. Previous");
        System.out.println("4. Print Forward");
        System.out.println("5. Print Backwards");
        System.out.println("6. Add Before");
        System.out.println("7. Add After");
        System.out.println("8. Get Size of Linked List");
        System.out.println("9. Remove");
        System.out.println("10.Remove Range");
        System.out.println("11. Quit");
        System.out.print("-->");
        int choice = input.nextInt();
        return choice;
    }

    public void executeAction(int choice) {
        if (choice == 1) {
            actionStub1();
        } else if (choice == 2) {
            actionStub2();
        } else if (choice == 3) {
            actionStub3();
        } else if (choice == 4) {
            actionStub4();
        } else if (choice == 5) {
            actionStub5();
        } else if (choice == 6) {
            actionStub6();
        }else if (choice == 7) {
            actionStub7();
        } else if (choice == 8) {
            actionStub8();
        } else if (choice == 9) {
            actionStub9();
        } else if (choice == 10) {
            actionStub10();
        } else if (choice == 11) {
            quit();
        }
    }

    public void actionStub1() {
        System.out.println("Get Current");
        System.out.println(manager.getCurrent());;
    }

    public void actionStub2() {
        System.out.println("Stub 2");
        manager.advance();
    }

    public void actionStub3() {
        System.out.println("Stub 3");
        manager.previous();
    }

    public void actionStub4() {     
        System.out.println("Stub 4");
        manager.printforward();
    }
    public void actionStub5() {
        System.out.println("Stub 5");
        manager.printBackwards();
    }
    public void actionStub6() {
        System.out.println("Add Before ");
        System.out.println("Enter New Object: ");
        Object newItem = input.next();
        manager.addBefore(newItem);
    }

    public void actionStub7() {
        System.out.println("Add After ");
         System.out.println("Enter New Object: ");
        Object newItem = input.next();
        manager.addAfter(newItem);
    }

    public void actionStub8() {
        System.out.println("Get size of Linked List");
        System.out.println(manager.size());;
    }

    public void actionStub9() {     
        System.out.println("Stub 9");
        manager.remove();
    }
    public void actionStub10() {
        System.out.println("Stub 10");
        System.out.println("Enter start of range: ");
        int starts = input.nextInt();
        System.out.println("Enter end of range(up to but not including): ");
        int ends = input.nextInt();
        manager.removeRange(starts, ends);
    }

    public void quit() {
        System.out.println("Optional exit message");
    }
    public static void main(String[] args) {
        FrameworkProgram prog = new FrameworkProgram();
        
        
       
        prog.run();
           
    
    }
    
        }

        


