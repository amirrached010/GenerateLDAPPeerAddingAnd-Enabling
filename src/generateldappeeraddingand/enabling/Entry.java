/*
This is the entry object in the Project
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
