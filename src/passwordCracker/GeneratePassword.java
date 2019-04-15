package passwordCracker;

import java.util.ArrayList;

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

        /*
        if (knownChars != null) {
            usedLetters.addAll(Arrays.asList(knownChars));
        }
         */
        String currentWord = "";

        computeWords(currentWord, usedLetters, genPasswords);

        if (savePassList) {
            readFile.writeToFile("passwords.txt", true, genPasswords);
        }
        
        return genPasswords;
    }

   
    private void computeWords(String currentWord, ArrayList<String> usedLetters, ArrayList<Passwords> genPasswords) { //Uses recursion to find every possible value between the max and min length

        usedLetters.stream().map((letter) -> currentWord + letter).map((tempWord) -> {
            if (knownChars != null) {
                boolean isContained = false;    //Despite knowing the word, it still takes a large amount of time as it checks every possibilty that contains the desired char patterns
                for (String word : knownChars) {
                    if (tempWord.contains(word)) {
                        isContained = true;
                    }
                }
                if (isContained) {
                    genPasswords.add(new Passwords(tempWord));
                    System.out.println(tempWord);
                }
            } else if (tempWord.length() >= minLength) {
                genPasswords.add(new Passwords(tempWord));
            }
            //System.out.println(tempWord);
            return tempWord;
        }).filter((tempWord) -> (continueIteration(tempWord))).forEachOrdered((tempWord) -> {
            computeWords(tempWord, usedLetters, genPasswords); // tempWord replaces currentWord when the method recurs from the second to the nth time
        });
    }

    private boolean continueIteration(String currentWord) {
        boolean canContinue = false;

        if (currentWord.length() + 1 <= maxLength) {
            canContinue = true;
        }

        return canContinue;
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
