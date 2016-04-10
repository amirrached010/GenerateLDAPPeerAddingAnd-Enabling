/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateldappeeraddingand.enabling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amir.Rashed
 */
public class GenerateLDAPPeerAddingAndEnabling {

    /**
     * @param args the command line arguments
     */
    public static StringBuilder ldapAdd;
    public static StringBuilder ldapEnable;
    public static ArrayList<Entry> allEntries;
    public static String outputFileAdd;
    public static String outputFileEnable;
    
    public static StringBuilder resultAdd;
    public static StringBuilder resultEnable;
    public static void main(String[] args) {
        
        // TODO code application logic here
        ldapAdd = new StringBuilder();
        ldapEnable = new StringBuilder();
        resultAdd = new StringBuilder();
        resultEnable = new StringBuilder();
        allEntries = new ArrayList<Entry>();
        
        outputFileAdd = System.getProperty("user.dir")+"\\OutputAdd.ldif";
        outputFileEnable = System.getProperty("user.dir")+"\\OutputEnable.ldif";
        readRequest(System.getProperty("user.dir")+"\\Resources\\LdapAdd.ldif",true);
        readRequest(System.getProperty("user.dir")+"\\Resources\\LdapEnable.ldif",false);
        readNodeIdsAndIPs(System.getProperty("user.dir")+"\\Resources\\Input.txt");
        
        for(int i=0; i<allEntries.size();i++){
            
                String m = ldapAdd.toString().replace("<nodeid>", allEntries.get(i).getNodeID());
                 m = m.replace("<IP>", allEntries.get(i).getIP());
                 resultAdd.append(m);
                 resultAdd.append(System.lineSeparator());
                 resultAdd.append(System.lineSeparator());
            
                String m1 = ldapEnable.toString().replace("<nodeid>", allEntries.get(i).getNodeID());
                 resultEnable.append(m1);
                 resultEnable.append(System.lineSeparator());
                 resultEnable.append(System.lineSeparator());
            
        }
        
        writeInFile(resultAdd, outputFileAdd);
        
        writeInFile(resultEnable, outputFileEnable);
        
        
    }
    
    public static void readRequest(String FileName, boolean isAdd){
       
        File dialsFile = new File(FileName);
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(dialsFile));
        } catch(FileNotFoundException e){
            
        }
        
        try {
            
            String line = br.readLine();

            while (line != null) {
                if(isAdd){
                    ldapAdd.append(line);
                    ldapAdd.append(System.lineSeparator());
                }else{
                    ldapEnable.append(line);
                    ldapEnable.append(System.lineSeparator());
                }
                line = br.readLine();
            }
            
            br.close();
        } catch (IOException ex) {
            
        } 
    }
    
    public static void readNodeIdsAndIPs(String FileName){
        File dialsFile = new File(FileName);
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(dialsFile));
        } catch(FileNotFoundException e){
            
        }
        
        try {
            
            String line = br.readLine();

            while (line != null) {
                Entry newEntry = new Entry(line.split(",")[0],line.split(",")[1]);
                allEntries.add(newEntry);
                line = br.readLine();
            }
            
            br.close();
        } catch (IOException ex) {
            
        } 
    }

    public static void writeInFile(StringBuilder result, String fileName){
        Writer writer = null;
        File resultFile = new File(fileName);
        if(!resultFile.exists())
            try {
                resultFile.createNewFile();
        } catch (IOException ex) {
            
        }
        try {
            FileWriter fw = new FileWriter(resultFile,false);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(result.toString());
            bw.close();
        } catch (UnsupportedEncodingException ex) {
           
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }
}
