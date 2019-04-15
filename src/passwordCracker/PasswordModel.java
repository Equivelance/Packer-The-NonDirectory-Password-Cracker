package passwordCracker;

import java.util.ArrayList;

public class PasswordModel {

    private ArrayList<Passwords> passwordList; //named passwords as Password already existed
    private ReadFile readFile;
    private GeneratePassword genPass;
    private PastePassword pastePass;

    private boolean isWindowsBool;

    public PasswordModel() {
        readFile = new ReadFile();
        genPass = new GeneratePassword(readFile);
        pastePass = new PastePassword();
    }

    public ReadFile getReadFile() {
        importPasswordList(); //Needs to import the list to generate a boolean
        recitePasswordList(); //DEBUG

        return readFile;
    }

    public void crackPassword(boolean genPassBool, boolean isWindowsBool) {
        if (genPassBool) {
            if (!passwordList.isEmpty()) {
                passwordList.clear();
            }

            passwordList.addAll(genPass.generatePasswords());
        }
        
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
