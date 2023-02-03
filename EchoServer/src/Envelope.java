
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author leoli
 */
public class Envelope implements Serializable{
    private String id;
    private String arg;
    private Object contents;
    
    
    public Envelope(){
        
    }

    public Envelope(String id, String arg, Object contents) {
        this.id = id;
        this.arg = arg;
        this.contents = contents;
    }

    public String getId() {
        return id;
    }

    public String getArg() {
        return arg;
    }

    public Object getContents() {
        return contents;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }
    
    
}
