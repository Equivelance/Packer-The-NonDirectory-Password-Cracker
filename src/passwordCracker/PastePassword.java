package passwordCracker;

import java.awt.AWTException;
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
    int pasteDelayTime, passX, passY, userX, userY;
    String knownUserName, choosePasswordRange;

    Robot robot;

    public PastePassword() throws AWTException {
        initRobot();
    }

    private void initRobot() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("The robot controlling your mouse and keyboard is out of batteries!!");
        }

    }

    //FINISH THIS AND MAKE SUURE PASTE SETTINGS HAVE CORRECT INPUT BOUNDRIES
    public void siftSettings(boolean isWindows, ArrayList<Boolean> cb, ArrayList<String> tb) {

        this.isWindows = isWindows;

        if (cb.get(0)) { //Has paste delay
            pasteDelayTime = Integer.getInteger(tb.get(0));
        }
        if (cb.get(1)) { //Has chosen password location
            String[] coordsP = tb.get(1).split(",");
            passX = Integer.getInteger(coordsP[0]);
            passX = Integer.getInteger(coordsP[1]);
        }
        if (cb.get(2)) { //Has known username
            knownUserName = tb.get(2);
            String[] coordsU = tb.get(3).split(",");
            userX = Integer.getInteger(coordsU[0]);
            userY = Integer.getInteger(coordsU[1]);
        }
        if (cb.get(3)) { //Has selected specific passwords
            choosePasswordRange = tb.get(4);
        }

        this.isPasteDelay = cb.get(0);
        this.isPressPassXY = cb.get(1);
        this.isPressUser = cb.get(2);
        this.isPressSpecPass = cb.get(3);

    }

    public boolean crackPassword(ArrayList<Passwords> passwords) {
        int iteration = 0;

        for (Passwords pass : passwords) {
            if (isPressUser) {
                robot.mouseMove(userX, userY);
                leftClick();
                copyPaste(knownUserName);
            }
            if (isPressPassXY) {
                robot.mouseMove(passX, passY);
            }
            leftClick();

            if (isPasteDelay) {
                timeDelay((long) pasteDelayTime);
            } else {
                timeDelay((long) .5);
            }

            iteration++;

            if (isPressSpecPass) {
                if (Integer.parseInt(choosePasswordRange) <= iteration) {
                    break;
                }
            }
        }

        return true;
    }

    private void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void copyPaste(String word) {  //Uses the keyboard to paste the word
        StringSelection stringSelection = new StringSelection(word);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        if (isWindows) { //If OS is Windows
            robot.keyPress(KeyEvent.VK_CONTROL);

            timeDelay((long) .2);
            robot.keyPress(KeyEvent.VK_V);

            timeDelay((long) .1);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            timeDelay((long) .2);
            robot.keyRelease(KeyEvent.VK_V);
        } else { //If OS is Mac
            robot.keyPress(KeyEvent.VK_META);

            timeDelay((long) .2);
            robot.keyPress(KeyEvent.VK_V);

            timeDelay((long) .1);
            robot.keyRelease(KeyEvent.VK_META);

            timeDelay((long) .2);
            robot.keyRelease(KeyEvent.VK_V);
        }
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
    
    private static void selectDel() {
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
