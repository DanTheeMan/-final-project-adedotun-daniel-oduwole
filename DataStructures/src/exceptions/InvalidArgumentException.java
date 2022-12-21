/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author leoli
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String s){
        super(s);
    }
    
    public InvalidArgumentException(){
        super();
    }
    
    public InvalidArgumentException(String s, Throwable cause){
        super(s, cause);
    }
    public InvalidArgumentException(Throwable cause){
        super(cause);
    }
}
