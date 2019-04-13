package passwordCracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasteSubView extends JFrame {

    private JCheckBox show1, show2, show3, show4, show5, show6;
    private JButton setPassButton, setUserNameButton;
    private JTextField pasteDelayText, knownUserName, choosePasswordsText;
    private JLabel label1, labelPassXY, label2, label3, labelUserNameXY, label4, label5;

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
        label1 = new JLabel(" ");
        mainPanel.add(label1);

        show2 = new JCheckBox("Password X,Y");
        mainPanel.add(show2);
        setPassButton = new JButton("Click to set position");
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
        setUserNameButton = new JButton("Click to Set Position");
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

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public void saveSettings() {

    }

}
