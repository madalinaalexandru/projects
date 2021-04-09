package request;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import entity.Property;
import entity.User;
import messages.RequestTemplate;
import profile.ProfileView;
import repository.RequestTypeRepo;
import requestsManager.RequestsManagerController;

import javax.swing.*;

public class RequestView {

    private RequestController requestController;
    private ProfileView profileView;
    private JFrame requestView;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    private JLabel requestTypeLabel;
    private JPanel requestTypeMenu;
    private JLabel menuMessageLabel;
    private JComboBox<String> requestTypeMenuOptions;

    private JLabel fillInLabel;
    private JTextArea requestTextArea;

    private JButton approveButton;
    private JButton generateButton;

    private User user;
    private Property property;

    String[] choices;

    public RequestView(User user, Property property, ProfileView profileView) {

        this.user = user;
        this.property = property;
        this.profileView = profileView;

        createAllObjects();

        addActionListeners();

        setAspectForAllObjects();

        setBoundsForAllObjects();

        addAllObjects();

        drawLogInView();
    }

    public void createAllObjects() {

        requestController = new RequestController();
        requestView = new JFrame();

        welcomeMessage = new JTextArea(1, 1);
        welcomeMessage.setEditable(false);
        welcomeMessage.append("Submit a request");

        infoMessage = new JTextArea(1, 1);
        infoMessage.setEditable(false);
        infoMessage.append("(*) Choose the type of request you want to fill in and generate it.");

        requestTypeLabel = new JLabel("Request type:");
        menuMessageLabel = new JLabel("Select the option you want");

        requestTypeMenu = new JPanel();

        fillInLabel = new JLabel("Check if the request has been generated accordingly:");
        requestTextArea = new JTextArea(1, 1);
        requestTextArea.setLineWrap(true);
        requestTextArea.setWrapStyleWord(true);
        requestTextArea.setEditable(false);

        choices = (new RequestTypeRepo()).getAllRequestTypes().toArray(new String[]{});
        requestTypeMenuOptions = new JComboBox<String>(choices);

        approveButton = new JButton("Submit");
        generateButton = new JButton("Generate Request");

    }

    public void setBoundsForAllObjects() {

        welcomeMessage.setBounds(50, 32, 600, 60);
        infoMessage.setBounds(50, 90, 600, 60);

        requestTypeLabel.setBounds(50, 160, 150, 40);

        requestTypeMenu.add(menuMessageLabel);
        requestTypeMenu.add(requestTypeMenuOptions);

        requestTypeMenu.setBounds(200, 160, 400, 100);

        fillInLabel.setBounds(50, 280, 450, 40);
        requestTextArea.setBounds(50, 340, 500, 400);

        approveButton.setBounds(50, 780, 190, 60);
        generateButton.setBounds(260, 780, 290, 60);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());

    }

    public void addAllObjects() {

        requestView.add(welcomeMessage);
        requestView.add(infoMessage);
        requestView.add(requestTypeLabel);
        requestView.add(requestTypeMenu);
        requestView.add(fillInLabel);
        requestView.add(requestTextArea);
        requestView.add(approveButton);
        requestView.add(generateButton);

    }

    public void drawLogInView() {

        requestView.setTitle("Submit request");
        requestView.setSize(650, 900);
        requestView.setLayout(null);
        requestView.setLocationRelativeTo(null);
        requestView.setVisible(true);

    }

    public void setFontsForAllObjects(FontClass customFonts) {
        requestTypeLabel.setFont(customFonts.getCustomLabelFont());
        fillInLabel.setFont(customFonts.getCustomTextFieldFont());

        requestTypeMenuOptions.setFont(customFonts.getCustomTextFieldFont());
        menuMessageLabel.setFont(customFonts.getCustomTextFieldFont());
        requestTextArea.setFont(customFonts.getCustomTextFieldFont());

        approveButton.setFont(customFonts.getCustomButtonFont());
        generateButton.setFont(customFonts.getCustomButtonFont());

        welcomeMessage.setFont(customFonts.getTextAreaTitleFont());

        infoMessage.setFont(customFonts.getTextAreaContentFont());

    }

    public void setColorsForAllObjects(ColorClass customColors) {

        requestView.getContentPane().setBackground(customColors.getBackgroundColor());

        requestTypeLabel.setForeground(customColors.getLabelFontColor());
        fillInLabel.setForeground(customColors.getLabelFontColor());

        requestTypeMenuOptions.setBackground(customColors.getBackgroundColor());

        requestTypeMenuOptions.setForeground(customColors.getLabelFontColor());
        menuMessageLabel.setForeground(customColors.getLabelFontColor());
        requestTextArea.setForeground(customColors.getTextFieldFontColor());
        infoMessage.setForeground(customColors.getTextFieldFontColor());

        approveButton.setForeground(customColors.getButtonTextFontColor());
        generateButton.setForeground(customColors.getButtonTextFontColor());

        approveButton.setBackground(customColors.getButtonBackgroundColor());
        generateButton.setBackground(customColors.getButtonBackgroundColor());

        welcomeMessage.setForeground(customColors.getLabelFontColor());

        requestTypeMenu.setBackground(customColors.getBackgroundColor());

    }

    public void setBordersForAllObjects(BorderClass customBorders) {

        approveButton.setBorder(customBorders.getButtonBorder());
        generateButton.setBorder(customBorders.getButtonBorder());

        requestTypeMenuOptions.setBorder(customBorders.getCustomBorder());
        requestTextArea.setBorder(customBorders.getCustomBorder());
    }

    public void addActionListeners() {

        generateButton.addActionListener(a -> {
            String selectedItem = requestTypeMenuOptions.getSelectedItem().toString();

            RequestTemplate requestTemplate = new RequestTemplate(user, property);

            switch (selectedItem) {
                case "Construction approval request":
                    requestTextArea.setText(requestTemplate.getBuildRequest());
                    break;
                case "Sublet request":
                    requestTextArea.setText(requestTemplate.getSubletRequest());
                    break;
                case "Renovation request":
                    requestTextArea.setText(requestTemplate.getRenovationRequest());
                    break;
                default:
                    requestTextArea.setText(requestTemplate.getDefaultRequest());
                    break;
            }

            requestView.repaint();
        });

        approveButton.addActionListener(a -> {

            User u = requestController.addRequest(user, requestTypeMenuOptions.getSelectedItem().toString(), property);

            if (u != null) {
                profileView.refresh(u);
            }

            requestView.dispose();

        });
    }

}
