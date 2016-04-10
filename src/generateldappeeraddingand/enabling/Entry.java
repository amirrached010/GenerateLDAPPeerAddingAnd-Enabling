/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateldappeeraddingand.enabling;

/**
 *
 * @author Amir.Rashed
 */
public class Entry {
    
    String nodeID;
    String IP;
    public Entry(String nodeID, String IP){
        this.nodeID = nodeID;
        this.IP= IP;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    
    
}
