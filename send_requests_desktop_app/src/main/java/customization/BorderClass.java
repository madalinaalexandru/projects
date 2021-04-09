package customization;

import javax.swing.border.Border;

import static javax.swing.BorderFactory.createMatteBorder;

public class BorderClass {

    private final Border customBorder;
    private final Border buttonBorder;

    public BorderClass() {

        ColorClass customColors = new ColorClass();

        buttonBorder = createMatteBorder(2, 2, 2, 2, customColors.getButtonBackgroundColor());
        customBorder = createMatteBorder(2, 2, 2, 2, customColors.getCustomBorderColor());

    }

    public Border getCustomBorder() {
        return customBorder;
    }

    public Border getButtonBorder() {
        return buttonBorder;
    }

}
