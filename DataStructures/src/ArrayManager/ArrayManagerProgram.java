/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ArrayManager;
import java.util.Scanner;

/**
 *
 * @author leoli
 */

public class ArrayManagerProgram {
    private Scanner input;
    ArrayManager data;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayManagerProgram amp = new ArrayManagerProgram();
        amp.run();
        // TODO code application logic here
    }
    public ArrayManagerProgram(){
        Object[] numbers = {112,12,45,76,90,99,87,6,0};
        input = new Scanner(System.in);
    }
    public void run(){
        int choice =0;
        while(choice != 7){
            choice = displayActions();
            executeAction(choice);
        }
        System.out.println("Bye!");
    }
    
    public int displayActions(){
        System.out.println("1. Display number of items");
        System.out.println("2. Display all items");
        System.out.println("3. Add an item");
        System.out.println("4. Add an item at position");
        System.out.println("5. Remove an item");
        System.out.println("6. Get item at position");
        System.out.println("7. Exit");
        System.out.println("Please enter an option: ");
        int choice = input.nextInt();
        return choice;
    }
    
    public void executeAction(int choice){
        if (choice == 1){
            System.out.println("number of items: " + getSize());
        }
        else if(choice == 2){
            System.out.println("Items: ");
            data.printItems();
        }
        else if(choice == 3){
            addItem();
         
        }
        else if(choice == 4){
            addItemAtPos();
         
        }
        else if(choice == 5){
            remove();
         
        }
        else if(choice == 6){
            getElement();
         
        }
    }
    
    public void getElement(){
        System.out.println("enter position of the array");
        int pos = input.nextInt();
        
        try {
            int item = (int)data.;
        } catch (OutofBoundsException oobe) {
            System.out.println("Error: " oobe.getMessage);
        }
      
    }
    public void remove(){
        System.out.println("enter position to remove: ");
        int pos = input.nextInt();
        
        try {
            data.remove(pos);
        } catch (NoItemsException nie) {
            System.out.println("Error: " nie.getMessage);
        }
        catch (OutofBoundsException oobe) {
            System.out.println("Error: " oobe.getMessage);
        }
      
    }
    
    public void addItemAtPos(){
        System.out.println("Item to add: ");
        int item = input.nextInt();
        System.out.println("Position to add: ");
        int pos = input.nextInt();
        
        try{
            data.insertAt(item, pos);
        }
        catch (OutofBoundsException oobe) {
            System.out.println("Error: " oobe.getMessage);
        }
    }
    public void addItem(){
        System.out.println("Item to add: ");
        int item = input.nextInt();
        data.add(item);
        System.out.println("Position to add: ");
        int pos = input.nextInt();
        
        try{
            data.insertAt(item, pos);
        }
        catch (OutofBoundsException oobe) {
            System.out.println("Error: " oobe.getMessage);
        }
    }
    
    public int getSize(){
        return data.size();
    }
}
