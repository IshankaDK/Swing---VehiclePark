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
public class Van extends Vehicle{
    private String vNum;
    private String owner;
    private String type;

    public Van() {
    }

    public Van(String vNum, String owner, String type) {
        this.vNum = vNum;
        this.owner = owner;
        this.type = type;
    }
    public Van(String vNum, String owner) {
        this.vNum = vNum;
        this.owner = owner;
    }
    
    /**
     * @return the vNum
     */
    public String getvNum() {
        return vNum;
    }

    /**
     * @param vNum the vNum to set
     */
    public void setvNum(String vNum) {
        this.vNum = vNum;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
    
}
