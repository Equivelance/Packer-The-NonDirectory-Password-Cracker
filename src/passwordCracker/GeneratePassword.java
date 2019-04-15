package passwordCracker;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratePassword {

    private ReadFile readFile;
    private int maxLength;
    private int minLength;
    private String[] knownChars; //known Chars = combination of chars
    private char[] usedChars; //Letters used for password generation
    private String passwordFormat;
    private boolean savePassList;

    public GeneratePassword(ReadFile readFile) {
        //savePassList = false;
        this.readFile = readFile;
        minLength = 0;  //Default Minimum Length
        maxLength = 15; //Default Maximum Length

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

        ArrayList<String> usedLetters = new ArrayList<>();

        if (usedChars == null) {
            usedChars = getAllChars();
        }

        for (char chars : usedChars) {
            String tempWord = "" + chars;
            usedLetters.add(tempWord);
        }

        if (knownChars != null) {
            usedLetters.addAll(Arrays.asList(knownChars));
        }

        String currentWord = "";

        computeWords(currentWord, usedLetters, genPasswords);

        /*
        while (currentWord.length() < maxLength) {
            computeWords(currentWord, usedLetters, genPasswords);
        }
         */
        if (minLength != 0) {

        }

        if (passwordFormat != null) {

        }

        if (savePassList) {
            readFile.writeToFile("passwords.txt", true, genPasswords);
        }

        return genPasswords;
    }

    private void computeWords(String currentWord, ArrayList<String> usedLetters, ArrayList<Passwords> genPasswords) {
        for (String letter : usedLetters) {
            String tempWord = currentWord + letter;
            genPasswords.add(new Passwords(tempWord));
            System.out.println(tempWord);
            if (continueIteration(tempWord)) {
                computeWords(tempWord, usedLetters, genPasswords);
            }
            System.out.println(tempWord);
        }
    }

    private boolean continueIteration(String currentWord) {
        boolean canContinue = false;

        if (currentWord.length() + 1 <= maxLength) {
            canContinue = true;
        }

        return canContinue;
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
