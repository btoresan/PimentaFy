import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.text.StyleConstants.setBackground;

public class TopPanel extends JPanel {
    private static final String SMALL_PEPPERFY_ICON_PATH = "images/small_pepperfy_icon.png";
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";

    public TopPanel(JFrame convertFrame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center panel for title and icon
        JPanel centralTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centralTopPanel.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel();
        title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        centralTopPanel.add(title);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(SMALL_PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        centralTopPanel.add(pepperfyLabel);

        add(centralTopPanel, BorderLayout.CENTER);

        // Style backButton
        ImageIcon backIcon = new ImageIcon(BACK_BUTTON_ICON_PATH);
        JButton backButton = new JButton(backIcon);
        backButton.setBackground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Back");
        backButton.setFocusPainted(false);

        add(backButton, BorderLayout.WEST);

        // Action backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertFrame.dispose();
                CurrentScreen.setCurrentScreen("menu");
            }
        });
    }
}