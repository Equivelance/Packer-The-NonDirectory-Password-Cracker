package passwordCracker;

import java.util.ArrayList;


public class GeneratePassword {
    private int maxLength;
    private int minLength;
    private String[] knownChars;
    private String[] ignoreChars;
    private String passwordFormat;
    private boolean savePassList;
    
    public GeneratePassword() {
        savePassList = false;
    }

    public void siftGenInfo(ArrayList<Boolean> checkBoxes, ArrayList<String> textBoxes) {
        if (checkBoxes.get(0)) {
            minLength = Integer.valueOf(textBoxes.get(0));
        }
        if (checkBoxes.get(1)) {
            maxLength = Integer.valueOf(textBoxes.get(1));
        }
        if (checkBoxes.get(2)) {
            knownChars = textBoxes.get(2).split(",");
        }
        if (checkBoxes.get(3)) {
            ignoreChars = textBoxes.get(3).split(",");
        }
        if (checkBoxes.get(4)) {
            savePassList = true;
        }
    }
    
    public ArrayList<Passwords> generatePasswords() {
        ArrayList<Passwords> genPasswords = new ArrayList<>();
        
        if (maxLength  != 0) {
            
        }
        
        if (minLength != 0) {
            
        }
        
        if (knownChars != null) {
            
        }
        
        if (ignoreChars != null) {
            
        }
        
        if (passwordFormat != null) {
            
        }
        
        return genPasswords;
    }
    
    /*
    public String chooseNextLetter (String word) {
        
        
        return nextWord;
    }
    */
}
