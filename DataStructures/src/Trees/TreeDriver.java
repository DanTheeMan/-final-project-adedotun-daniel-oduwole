/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Trees;
import java.util.Scanner;
/**
 *
 * @author leoli
 */
public class TreeDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tree t = new Tree();
        Scanner input = new Scanner(System.in);
        
        for (int i = 0; i < 10; i++) {
            int num=(int)(Math.random()*200);
            System.out.println(num+ " ");
            t.add(num);
        }
        System.out.println("max: " + t.findMax());
        
        System.out.println("\nPrintTree: ");
        t.printTree();
        
        
        System.out.println("Search for: ");
        int n = input.nextInt();
        Object o = t.searchTree(n);
        
        if(o!=null){
            System.out.println("Found: " + o + " After" + t.searchCount + " calls");
        }
        else{
            System.out.println("not found after " + t.searchCount + " calls");
        }
        
        
    }
    
}
