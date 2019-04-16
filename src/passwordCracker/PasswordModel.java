package passwordCracker;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordModel {

    private ArrayList<Passwords> passwordList; //named passwords as Password already existed
    private ReadFile readFile;
    private GeneratePassword genPass;
    private PastePassword pastePass;

    private boolean isWindowsBool;

    public PasswordModel() {
        readFile = new ReadFile();
        genPass = new GeneratePassword(readFile);
        try {
            pastePass = new PastePassword();
        } catch (AWTException ex) {
            Logger.getLogger(PasswordModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        passwordList = new ArrayList<>();
    }

    public ReadFile getReadFile() {
        importPasswordList(); //Needs to import the list to generate a boolean
        recitePasswordList(); //DEBUG

        return readFile;
    }

    public void crackPassword(boolean genPassBool, boolean isWindowsBool, ArrayList<Boolean> pasteChoices, ArrayList<String> pasteFields) {
        if (genPassBool) {

            if (!passwordList.isEmpty()) {
                passwordList.clear();
            }

            passwordList.addAll(genPass.generatePasswords());
        }

        System.out.println("\n-----------------( Finished Skimming Passwords )-----------------\n\n");  //DEBUG
        pastePass.siftSettings(isWindowsBool, pasteChoices, pasteFields);
        pastePass.crackPassword(passwordList);

    }

    private void importPasswordList() { //Put into a separate method for organization
        passwordList = readFile.addPasswordsToList();
    }

    //----------------------DEBUG-----------------------------
    private void recitePasswordList() {
        passwordList.forEach((password) -> {
            System.out.println(password.getPassword());
        });
    }

    public GeneratePassword getGenPass() {
        return genPass;
    }

}
