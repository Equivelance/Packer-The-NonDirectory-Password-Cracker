
package passwordCracker;

import java.util.ArrayList;

public class Controller {
    
    private MainView mainView;
    private PasswordModel passModel;
    
    
    public Controller() {
        
        
        passModel = new PasswordModel();
        mainView = new MainView(this);
        mainView.setVisible(true);
    }
    
    public MainView getMainView() {
        return mainView;
    }
    
    public boolean getLibImportStatus(){
        return passModel.getReadFile().getFileReadStatus();
    }
    
    public void transferGenerationSettingChoices(ArrayList<Boolean> checkBoxes, ArrayList<String> textBoxes) {
        passModel.getGenPass().siftGenInfo(checkBoxes, textBoxes);
    }
    
}
