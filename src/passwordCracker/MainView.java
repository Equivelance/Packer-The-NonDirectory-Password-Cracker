package passwordCracker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MainView extends JFrame {
    
    private Controller controller;
    private JPanel mainPanel;
    private GenerationSubView genSubView;
    private PasteSubView pasteSubView;
    private boolean selectionOS, selectionLib;
    
    //Labels
    private JLabel importStatusLabel;
    
    
    public MainView(Controller controller) {
        this.controller = controller;
        genSubView = new GenerationSubView();
        pasteSubView = new PasteSubView();
        createWindow();
    }
    
    private void createWindow() {
        setTitle("Password Cracker");
        setSize(350, 300);
        setLocationRelativeTo(null);  // center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
        JLabel blankLabel = new JLabel("   ");

        JToggleButton osToggle = new JToggleButton ("Choose an OS");
        osToggle.addActionListener((ActionEvent e) -> {
            if(e.getSource() == osToggle) {
                if(osToggle.isSelected()) {
                    osToggle.setText("Windows");
                    selectionOS = true;
                }
                else {
                    osToggle.setText("Mac");
                    selectionOS = false;
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(osToggle, c);
        
        c.gridy = 1;
        mainPanel.add(blankLabel,c);
        
        JToggleButton libToggle = new JToggleButton ("Choose a Library");
        libToggle.addActionListener((ActionEvent e) -> {
            if(e.getSource() == libToggle) {
                if(libToggle.isSelected()) {
                    libToggle.setText("Generate a Library");
                    selectionLib = true;
                }
                else {
                    libToggle.setText("Import a Library");
                    selectionLib = false;
                }
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        mainPanel.add(libToggle, c);
        
        JButton genSetButton = new JButton ("Generation Settings");
        c.gridx = 0;
        c.gridy = 4;
        mainPanel.add(genSetButton, c);
        
        JButton pasteSetButton = new JButton ("Paste Settings");
        c.gridx = 0;
        c.gridy = 5;
        mainPanel.add(pasteSetButton, c);
        
        JButton importButton = new JButton("Import Library");
        c.gridx = 0;
        c.gridy = 6;
        mainPanel.add(importButton, c);
        
        importStatusLabel = new JLabel(" ", JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 7;
        mainPanel.add(importStatusLabel, c);
        
        JButton crackPassButton = new JButton("Crack Password");
        crackPassButton.addActionListener(event -> crackPasswords());
        
        
        genSetButton.addActionListener(event -> openGenerationSettings());
        pasteSetButton.addActionListener(event -> openPasteSettings());
        importButton.addActionListener(event -> importLibrary());
        
        c.gridy = 9;
        mainPanel.add(crackPassButton, c);
        

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
    }
    private void crackPasswords() {
        if(selectionLib) {
            ArrayList<Boolean> checkBoxes = genSubView.getCheckBoxes(); //Gift wrapped selections from genSubView
            controller.transferGenerationSettingChoices(genSubView.getCheckBoxes(), genSubView.getTextBoxes());
        }
        else {
            
        }
    }
    
    private void importLibrary(){
        boolean status = controller.getLibImportStatus(); // 
        if (true) {
            importStatusLabel.setText("Import Successful!!!");            
        }
        
        else {
            importStatusLabel.setText("Import Failed!!");
        }
    }
    
    private void openPasteSettings() {
        pasteSubView.setVisible(true);
    }
    private void openGenerationSettings () {
        genSubView.setVisible(true);
    }
   
}
    
    