package passwordCracker;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PastePassword {
    
    boolean isWindows, isPasteDelay, isPressPassXY, isPressUser, isPressSpecPass;
    int pasteDelayTime;
    
    
    public PastePassword() {
        isWindows = true;
    }
    
    //FINISH THIS AND MAKE SUURE PASTE SETTINGS HAVE CORRECT INPUT BOUNDRIES
    public void siftSettings(boolean isWindows, ArrayList<Boolean> cb, ArrayList<String> tb) {
        
        this.isWindows = isWindows;
        
        if (cb.get(0)) { //Has paste delay
            
        }
        this.isPasteDelay = cb.get(0);
        this.isPressPassXY = cb.get(1);
        this.isPressUser = cb.get(2);
        this.isPressSpecPass = cb.get(3);
        
    }
    
    public boolean crackPassword(ArrayList<Passwords> passwords) {
        for (Passwords pass : passwords) {
            
        }
        
        return true;
    }
    

    private void leftClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void setMousePos(Robot robot, int xSet, int ySet) {

    }

    private static void copyPaste(Robot robot, String word) {
        StringSelection stringSelection = new StringSelection(word);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_META);

        timeDelay((long) .2);
        robot.keyPress(KeyEvent.VK_V);

        timeDelay((long) .1);
        robot.keyRelease(KeyEvent.VK_META);

        timeDelay((long) .2);
        robot.keyRelease(KeyEvent.VK_V);
    }

    private static void timeDelay(long time) {

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("\n\nERROR: Problem with paste delay settings " + ex);
        }
    }

    /*
    
    //WILL BE USED IN FUTURE VERSIONS 
    
    private static void selectDel(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        robot.keyPress(KeyEvent.VK_META);

        timeDelay((long) .01);
        robot.keyPress(KeyEvent.VK_A);

        timeDelay((long) .01);
        robot.keyRelease(KeyEvent.VK_META);

        timeDelay((long) .01);
        robot.keyRelease(KeyEvent.VK_A);

        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
*/ 
}
