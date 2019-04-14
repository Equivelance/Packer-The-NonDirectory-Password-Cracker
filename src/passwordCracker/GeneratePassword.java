package passwordCracker;

import java.util.ArrayList;

public class GeneratePassword {

    private int maxLength;
    private int minLength;
    private String[] knownChars;
    private char[] usedChars;
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
            usedChars = textBoxes.get(3).toCharArray();
        }
        if (checkBoxes.get(4)) {
            savePassList = true;
        }
    }

    public ArrayList<Passwords> generatePasswords() {
        ArrayList<Passwords> genPasswords = new ArrayList<>();

        if (usedChars == null) {
            usedChars = getAllChars();
        }

        if (knownChars != null) {

        }

        if (maxLength != 0) {

        }

        if (minLength != 0) {

        }

        if (usedChars != null) {

        }

        if (passwordFormat != null) {

        }

        return genPasswords;
    }

    private void initiateOptions() {

    }

    private char[] getAllChars() {

        String allCharsString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";

        char[] allChars = allCharsString.toCharArray();

        return allChars;
    }

    /*
    public String chooseNextLetter (String word) {
        
        
        return nextWord;
    }
     */
}
