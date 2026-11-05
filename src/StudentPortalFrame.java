import javax.swing.*;
import java.awt.*;

public class StudentPortalFrame extends JFrame {

    public StudentPortalFrame() {
        setTitle("University Student Records Portal");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());

        add(new StudentTablePanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuStudent = new JMenu("Student Registration");
        JMenu menuCourse = new JMenu("Course Management");
        JMenu menuExam = new JMenu("Examination Results");
        JMenu menuFinance = new JMenu("Finance");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem itemRegister = new JMenuItem("New Registration");
        itemRegister.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Student Registration Form..."));
        menuStudent.add(itemRegister);

        JMenuItem itemExit = new JMenuItem("Exit Portal");
        itemExit.addActionListener(e -> System.exit(0));
        menuHelp.add(itemExit);


        menuBar.add(menuStudent);
        menuBar.add(menuCourse);
        menuBar.add(menuExam);
        menuBar.add(menuFinance);
        menuBar.add(menuHelp);

        return menuBar;
    }
}