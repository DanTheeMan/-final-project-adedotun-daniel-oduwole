/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursion;
import LinkedLists.*;
/**
 *
 * @author leoli
 */
public class Recursion {

    public static void main(String[] args) {
        System.out.println("sum: " + sumAll(5));
        System.out.println("sum: " + factorial(5));
        System.out.println("sum: " + myPow(5, 3));
        System.out.println("Panlindrome: " + isPalindrome("racecar"));
        
//        GenericLinkedList<String> list = new GenericLinkedList<string>;
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.recursivePrint();

        System.out.println("fibonacci" + fibonacii(24));
        indentoTron(25);
    }

    public static int sumAll(int n) {
        // this metho sum all numbers from 1 to n

        if (n == 1) {
            return n;
        } else {
            return n + sumAll(n - 1);
        }
    }

    public static int factorial(int n) {
        // this metho sum all numbers from 1 to n

        if (n == 1) {
            return n;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int myPow(int a, int b) {
        // this metho sum all numbers from 1 to n

        if (b == 0) {
            return 1;
         
        }
        else if(b==1){
            return a;
        }
        else {
            return a * myPow(a, b - 1);

        }
    }
    public static boolean isPalindrome(String s){
        return isPalindrome(s,0,s.length()-1);
    }

    public static boolean isPalindrome(String s, int start, int end) {
        // this metho sum all numbers from 1 to n
        if(s.charAt(start)!=s.charAt(end)){
            return false;
        }
        else if(start > end){
            return true;
        }
        else{
             return isPalindrome(s, start+1 , end-1);
        }
       
    }
    
    public static int fibonacii(int n,int val_minus1,int val_minus2, int max_n, int value){
        if(n==max_n){
            return value;
        }
        else{
            value= val_minus1+val_minus2;
            System.out.println(value +", ");
            n++;
            return fibonacii(n, value, val_minus1, max_n, value);
        }
    }
    
    public static int fibonacii(int max_n){
       int n =1;
       int val_minus1=1;
       int val_minus2=0;
       if(max_n==0){
           System.out.println("0");
           return 0;
           
       }
       else{
           System.out.println("0,1");
           if(max_n==1){
               return 1;
           }
           return fibonacii(n, val_minus1, val_minus2, max_n,1);
       }
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
    
//    private void reversePrint(GNode<T> temp){
//        Gnode<T> temp = start;
//        reversePrint(temp);
//    }
    
    public static void indentoTron(int n){
        indentoTron(n,1);
    }
    
    public static void indentoTron(int n,int i){
        if(i>n){
            return;
            
        }
        else{
            printSpaces(i);
            System.out.println("this was written by call number " + i);
            indentoTron(n,i+1);
            printSpaces(i);
            System.out.println("this was also written by call number " + i);
        }
    }
    
    public static void printSpaces(int spaces){
        if(spaces==0){
            return;
        }
        else{
            System.out.print(" ");
            printSpaces(spaces-1);
        }
    }
}
