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

    boolean isWindows, isPasteDelay, isPressPassXY, isPressUser, isPressSpecPass, isPressEnter, isPressDelete;
    int pasteDelayTime, passX, passY, userX, userY;
    String knownUserName, choosePasswordRange;

    Robot robot;

    public PastePassword() throws AWTException {
        isWindows = false;
        isPasteDelay = false;
        isPressPassXY = false;
        isPressUser = false;
        isPressSpecPass = false;
        initRobot();
    }

    private void initRobot() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("The robot controlling your mouse and keyboard is out of batteries!!");
        }

    }

    public void siftSettings(boolean isWindows, ArrayList<Boolean> cb, ArrayList<String> tb) {

        this.isWindows = isWindows;

        if (cb.get(0)) { //Has paste delay
            this.pasteDelayTime = Integer.parseInt(tb.get(0));
        }
        if (cb.get(1)) { //Has chosen password location
            String[] coordsP = tb.get(1).split(",");
            this.passX = Integer.parseInt(coordsP[0]);
            this.passY = Integer.parseInt(coordsP[1]);
        }
        if (cb.get(2)) { //Has known username
            this.knownUserName = tb.get(2);
            String[] coordsU = tb.get(3).split(",");
            this.userX = Integer.parseInt(coordsU[0]);
            this.userY = Integer.parseInt(coordsU[1]);
        }
        if (cb.get(3)) { //Has selected specific passwords
            this.choosePasswordRange = tb.get(4);
        }

        this.isPasteDelay = cb.get(0);
        this.isPressPassXY = cb.get(1);
        this.isPressUser = cb.get(2);
        this.isPressSpecPass = cb.get(3);
        this.isPressEnter = cb.get(4);
        this.isPressDelete = cb.get(5);
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

            copyPaste(pass.getPassword());
            if (isPressEnter) {
                pressEnter();
            }

            if (isPasteDelay) {
                timeDelay((long) pasteDelayTime);
            } else {
                timeDelay((long) 2);
            }

            iteration++;

            if (isPressSpecPass) {
                if (Integer.parseInt(choosePasswordRange) <= iteration) {
                    break;
                }
            }

            if (isPressDelete) {
                selectDel();
            }

        }

        return true;
    }

    private void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
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

    private void selectDel() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        if (isWindows) {
            robot.keyPress(KeyEvent.VK_CONTROL);

            timeDelay((long) .01);
            robot.keyPress(KeyEvent.VK_A);

            timeDelay((long) .01);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            timeDelay((long) .01);
            robot.keyRelease(KeyEvent.VK_A);
        } else {
            robot.keyPress(KeyEvent.VK_META);

            timeDelay((long) .01);
            robot.keyPress(KeyEvent.VK_A);

            timeDelay((long) .01);
            robot.keyRelease(KeyEvent.VK_META);

            timeDelay((long) .01);
            robot.keyRelease(KeyEvent.VK_A);
        }

        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

}
