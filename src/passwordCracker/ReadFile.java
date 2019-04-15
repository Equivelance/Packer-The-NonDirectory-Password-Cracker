package passwordCracker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        } catch (FileNotFoundException ex) {
            System.out.println("\n\n--------------------------------------------------------------------\nAn error occcurred "
                    + "while importing your Password Database\nError: " + ex);
            fileRead = false;
            return null;
        }
    }

    public boolean getFileReadStatus() {

        return fileRead;
    }

    public void writeToFile(String fileName, boolean clearFile, ArrayList<Passwords> passwords) {
        if (clearFile) {
            PrintWriter write;

            try {
                write = new PrintWriter(fileName);
                write.close();
                //System.out.println("Cleared File");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error with clearing a file!!!!");
            }
        }

        try {
            Formatter formatter = new Formatter(fileName);
            passwords.forEach((password) -> {
                formatter.format("%s%n", password.getPassword()); //Adds each word from the list to a new line in a text document
                //System.out.println("Wrote to file");
            });
            formatter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with writing to file!!!!!");
        }
    }

    /*
    public ArrayList<Passwords> getPasswordList(){
        return ;
    }
     */
}
