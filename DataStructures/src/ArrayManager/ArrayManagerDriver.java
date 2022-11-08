/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ArrayManager;

/**
 *
 * @author leoli
 */
public class ArrayManagerDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayManager am = new ArrayManager(5);
        
        am.add(12);
        am.add(7);
        am.add(3);
        am.add(12);
        am.add(11);
        am.add(18);
        am.print();
        System.out.println("----");
        am.printItems();
        
//        int [] items = new int[5];
//        
//        items[0] = 11;
//        items[1] = 61;
//        items[2] = 161;
//        items[3] = 15;
//        items[4] = 91;
//        
//        for (int i = 0; i < items.length; i++) {
//            System.out.println(">>" + items[i]);
//        }
//        
//        //1. Make a bigger array
//        int[] temp = new int [items.length+1];
//        
//        
//        //2. Copy everything from the original to the new array
//        
//        System.arraycopy(items, 0, temp, 0, items.length);
//        
//        //3. point the old array to the new array
//        items = temp;
//        
//        //4. add the new item
//        items[5] = 99;
       
    }
    
}
