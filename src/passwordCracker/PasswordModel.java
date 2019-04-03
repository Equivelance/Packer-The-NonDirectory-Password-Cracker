
package passwordCracker;

import java.util.ArrayList;

public class PasswordModel {
    
    private ArrayList<Passwords> passwordList; //named passwords as Password already existed
    private ReadFile readFile;
    private GeneratePassword genPass;
    
    public PasswordModel() {
        readFile = new ReadFile();
        genPass = new GeneratePassword();
    }
    
    
    public ReadFile getReadFile() {
        importPasswordList(); //Needs to import the list to generate a boolean
        recitePasswordList(); //DEBUG
        
        return readFile;
    }
    

    public void savePasswordList() {
        
    }
    
    public void importPasswordList() { //Put into a separate method for organization
        passwordList = readFile.addPasswordsToList();
    }
    
    
    //----------------------DEBUG-----------------------------
    public void recitePasswordList() {
        passwordList.forEach((password) -> {
            System.out.println(password.getPassword());
        });
    }

    public GeneratePassword getGenPass() {
        return genPass;
    }
}
