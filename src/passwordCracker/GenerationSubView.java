package passwordCracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author William Musick
 */
public class GenerationSubView extends JFrame {

    private JCheckBox show1, show2, show3, show4, show5;
    private JTextField minPassLenText, maxPassLenText, knownCharsText, usedCharsText;
    private JLabel errorLabel;
    private boolean isPress1, isPress2, isPress3, isPress4, isPress5;

    public GenerationSubView() {
        createWindow();
    }

    private void createWindow() {
        setTitle("Password Cracker");
        setSize(1000, 300);
        setLocationRelativeTo(null);  // center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 2));

        show1 = new JCheckBox("Miniumum Password Length");
        mainPanel.add(show1);
        minPassLenText = new JTextField(15);
        mainPanel.add(minPassLenText);

        show2 = new JCheckBox("Maxiumum Password Length");
        mainPanel.add(show2);
        maxPassLenText = new JTextField(15);
        mainPanel.add(maxPassLenText);

        show3 = new JCheckBox("Known Character Patterns");
        mainPanel.add(show3);

        knownCharsText = new JTextField("word1, w0Rd2", 50);
        mainPanel.add(knownCharsText);

        show4 = new JCheckBox("Used Characters");
        mainPanel.add(show4);
        usedCharsText = new JTextField("abcdefghijklmnopqrstuvwxyz", 200);
        mainPanel.add(usedCharsText);

        show5 = new JCheckBox("Save Library in .txt file");
        mainPanel.add(show5);

        errorLabel = new JLabel("");
        mainPanel.add(errorLabel);

        JButton saveSetButton = new JButton("Save Settings");
        saveSetButton.addActionListener(event -> saveSettings());
        mainPanel.add(saveSetButton);

        event e = new event();
        show1.addItemListener(e);
        show2.addItemListener(e);
        show3.addItemListener(e);
        show4.addItemListener(e);
        show5.addItemListener(e);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public class event implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            isPress1 = show1.isSelected();
            isPress2 = show2.isSelected();
            isPress3 = show3.isSelected();
            isPress4 = show4.isSelected();
            isPress5 = show5.isSelected();
        }
    }

    public void saveSettings() {
        generateErrorMessage();

        //Create Settings Save file for Deliverable 3
    }

    //FIX SO IT ONLY PRINTS WARNING IF ITEM IS SELECTED AND WILL NOT ACCEPT A BLANK BOX
    public void generateErrorMessage() {
        boolean hasError = false;
        String errorMessage = "Error with ";
        String blankMessage = "Checked blank boxes;";

        if (isPress1) {
            if (minPassLenText.getText().equals("")) {
                hasError = true;
                errorMessage += blankMessage;
            } else if (Pattern.matches("[a-zA-Z]+", minPassLenText.getText())) {
                hasError = true;
                errorMessage += "Minimum Password Length; ";
            }
        }

        if (isPress2) {
            if (maxPassLenText.getText().equals("")) {
                hasError = true;
                errorMessage += blankMessage;
            } else if (Pattern.matches("[a-zA-Z]+", maxPassLenText.getText())) {
                hasError = true;
                errorMessage += "Maxiumum Password Length; ";
            }
        }

        if (isPress3) {
            String[] tempKnown = knownCharsText.getText().split(",");
            for (int i = 0; i < tempKnown.length; i++) {
                if (tempKnown[i].length() > Integer.parseInt(maxPassLenText.getText())) {
                    hasError = true;
                    errorMessage += "Known Characters; ";
                }
            }

            if (isPress2) {
                if (tempKnown.length > Integer.valueOf(maxPassLenText.getText())) {
                    hasError = true;
                    errorMessage += "Max Password Length < Letter Pattern; ";
                }
            }
        }

        if (hasError) {
            errorLabel.setText(errorMessage);
            //System.out.println(errorMessage); //DEBUG
        } else {
            errorLabel.setText(" ");
            dispose();
        }
    }

    public ArrayList<Boolean> getCheckBoxes() {
        ArrayList<Boolean> checkBoxes = new ArrayList<>();
        checkBoxes.add(isPress1);
        checkBoxes.add(isPress2);
        checkBoxes.add(isPress3);
        checkBoxes.add(isPress4);
        checkBoxes.add(isPress5);
        return checkBoxes;
    }

    public ArrayList<String> getTextBoxes() {
        ArrayList<String> tb = new ArrayList<>();
        tb.add(minPassLenText.getText());
        tb.add(maxPassLenText.getText());
        tb.add(knownCharsText.getText());
        tb.add(usedCharsText.getText());
        return tb;
    }
}
