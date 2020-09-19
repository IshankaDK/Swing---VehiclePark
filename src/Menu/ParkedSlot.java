/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author Ishanka
 */
public class ParkedSlot {
    private String type;
    private int slot;

    public ParkedSlot() {
    }
    
    public ParkedSlot(String type) {
        this.type = type;
        
    }
    
    public ParkedSlot(String type, int slot) {
        this.type = type;
        this.slot = slot;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }
    
    
}
