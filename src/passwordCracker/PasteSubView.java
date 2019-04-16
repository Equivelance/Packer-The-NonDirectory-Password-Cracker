package passwordCracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasteSubView extends JFrame {

    private JCheckBox show1, show2, show3, show4;
    private JButton setPassButton, setUserNameButton;
    private JTextField pasteDelayText, knownUserName, choosePasswordsText;
    private JLabel label1, labelPassXY, label2, label3, labelUserNameXY, label4, label5, errorLabel;
    private boolean isPressDelay, isPressPassXY, isPressUser, isPressSpecPass;
    private int passX, passY, userX, userY;

    public PasteSubView() {
        createWindow();
    }

    private void createWindow() {
        setTitle("Password Cracker");
        setSize(600, 300);
        setLocationRelativeTo(null);  // center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 3));

        show1 = new JCheckBox("Paste Delay");
        mainPanel.add(show1);
        pasteDelayText = new JTextField(15);
        mainPanel.add(pasteDelayText);
        label1 = new JLabel(" "); //Just a blank label to fill space
        mainPanel.add(label1);

        show2 = new JCheckBox("Password X,Y");
        mainPanel.add(show2);
        setPassButton = new JButton("Click and Move Mouse");
        setPassButton.addActionListener(event -> setXY(labelPassXY, passX, passY));
        mainPanel.add(setPassButton);
        labelPassXY = new JLabel("X,Y");
        mainPanel.add(labelPassXY);

        show3 = new JCheckBox("Known Username");
        mainPanel.add(show3);
        knownUserName = new JTextField(50);
        mainPanel.add(knownUserName);
        label2 = new JLabel(" ");
        mainPanel.add(label2);

        //subSection
        label3 = new JLabel(" ");
        mainPanel.add(label3);
        setUserNameButton = new JButton("Click and Move Mouse");
        setUserNameButton.addActionListener(event -> setXY(labelUserNameXY, userX, userY));
        mainPanel.add(setUserNameButton);
        labelUserNameXY = new JLabel("X,Y");

        mainPanel.add(labelUserNameXY);

        show4 = new JCheckBox("Specific Passwords");
        mainPanel.add(show4);
        choosePasswordsText = new JTextField("0-100", 50);
        mainPanel.add(choosePasswordsText);
        label4 = new JLabel(" ");
        mainPanel.add(label4);

        label5 = new JLabel(" ");
        mainPanel.add(label5);
        JButton saveSetButton = new JButton("Save Settings");
        saveSetButton.addActionListener(event -> saveSettings());
        mainPanel.add(saveSetButton);

        errorLabel = new JLabel(" ");
        mainPanel.add(errorLabel);

        event e = new event();
        show1.addItemListener(e);
        show2.addItemListener(e);
        show3.addItemListener(e);
        show4.addItemListener(e);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        //System.out.println("Initialized PasteSettings");
    }

    public class event implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            isPressDelay = show1.isSelected();
            isPressPassXY = show2.isSelected();
            isPressUser = show3.isSelected();
            isPressSpecPass = show4.isSelected();
        }
    }

    private void setXY(JLabel label, int x, int y) {

        try {
            TimeUnit.SECONDS.sleep((long) 5);
        } catch (InterruptedException ex) {
            System.out.println("\n\nERROR: Problem with paste delay settings " + ex);
        }
        Point p = MouseInfo.getPointerInfo().getLocation();
        x = (int) p.x;
        y = (int) p.y;
        String coords = x + "," + y;

        System.out.println(coords);

        label.setText(coords);

    }

    public void saveSettings() {
        boolean hasError = false;
        String errorMessage = "ERROR! ";
        String blankMessage = "There areChecked blank boxes;";

        if (isPressDelay) {
            if (pasteDelayText.getText().equals("")) {
                hasError = true;
                errorMessage += blankMessage;
            }

            if (Pattern.matches("[a-zA-Z]+", pasteDelayText.getText())) {
                hasError = true;
                errorMessage += "Paste Delay; ";
            }
        }

        if (isPressPassXY) {
            if (labelPassXY.getText().equals("X,Y")) {
                hasError = true;
                errorMessage += "Set X,Y for Password; ";
            }
        }

        if (isPressUser) {
            if (labelUserNameXY.getText().equals("X,Y")) {
                hasError = true;
                errorMessage += "Set X,Y for Password; ";
            }
            if (knownUserName.getText().equals("")) {
                hasError = true;
                errorMessage += blankMessage;
            }
        }

        if (hasError) {
            errorLabel.setText(errorMessage);

        } else {
            errorLabel.setText(" ");
            dispose();
        }
    }

    public ArrayList<Boolean> getCheckBoxes() { //Check Boxes
        ArrayList<Boolean> checkBoxes = new ArrayList<>();
        checkBoxes.add(isPressDelay);
        checkBoxes.add(isPressPassXY);
        checkBoxes.add(isPressUser);
        checkBoxes.add(isPressSpecPass);
        return checkBoxes;
    }

    public ArrayList<String> getTextBoxesAndLabels() { //TextBoxes
        ArrayList<String> tb = new ArrayList<>();
        tb.add(pasteDelayText.getText());
        tb.add(labelPassXY.getText());
        tb.add(knownUserName.getText());
        tb.add(labelUserNameXY.getText());
        tb.add(choosePasswordsText.getText());
        return tb;
    }

}
