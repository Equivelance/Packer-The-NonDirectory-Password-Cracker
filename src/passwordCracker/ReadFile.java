
package passwordCracker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    private boolean fileRead;
    
    public ReadFile() {
    
    }
    
    public ArrayList<Passwords> addPasswordsToList() {
        ArrayList<Passwords> passwordList = new ArrayList<>();
        java.io.File file = new java.io.File("passwords.txt");
        
        try {
            try (Scanner scan = new Scanner(file)) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    
                    if (line.length() > 0) {
                        Passwords tempPass = new Passwords(line);
                        //System.out.println(tempPass.getPassword()); //Debug
                        passwordList.add(tempPass);
                        //System.out.println("\n\nSUCCESS\n\n"); //Debug
                        
                    }
                }
                fileRead = true;
                return passwordList;
            }
        }               
        
        catch (FileNotFoundException ex) {
            System.out.println("\n\n--------------------------------------------------------------------\nAn error occcurred "
                    + "while importing your Password Database\nError: " + ex);
            fileRead = false;
            return null;
        }
    }
    
    public boolean getFileReadStatus() {
        
        return fileRead;
    }
    
    /*
    public ArrayList<Passwords> getPasswordList(){
        return ;
    }
    */
}
