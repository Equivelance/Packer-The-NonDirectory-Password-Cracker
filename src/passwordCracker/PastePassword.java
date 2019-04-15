package passwordCracker;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class PastePassword {

    public PastePassword() {

    }

    public void leftClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void doubleClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void singleClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void setMousePos(Robot robot, int xSet, int ySet) {

    }

    public static void copyPaste(Robot robot, String word) {
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

    public static void timeDelay(long time) {

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("\n\nERROR: Problem with paste delay settings " + ex);
        }
    }

    public static void selectDel(Robot robot) {
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

}
