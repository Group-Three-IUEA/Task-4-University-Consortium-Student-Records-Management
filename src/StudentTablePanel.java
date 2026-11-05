
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class StudentTablePanel extends JPanel {


    private JTable studentTable;

    private JPopupMenu contextMenu;

    public StudentTablePanel() {

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel instructionLabel = new JLabel(
                "Right-click on any student record below to open quick actions:"
        );



        add(instructionLabel, BorderLayout.NORTH);


        String[] columns = {
                "Student ID", "Full Name", "Program", "Status"
        };

        Object[][] data = {
                {"S001", "Alice Nakato", "BSc. Software Engineering", "Active"},
                {"S002", "John Mukasa", "BBA Finance", "Active"},
                {"S003", "Sarah Namubiru", "Bachelor of IT", "Active"},
                {"S004", "David Ochieng", "BSc. Computer Science", "Inactive"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);

        studentTable = new JTable(model);

        createPopupMenu();

        studentTable.addMouseListener(new MouseAdapter() {

            // Called when a mouse button is pressed.
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            // Called when a mouse button is released.
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }


            private void showPopup(MouseEvent e) {

                if (e.isPopupTrigger()) {

                    int row = studentTable.rowAtPoint(e.getPoint());

                    if (row >= 0 && row < studentTable.getRowCount()) {
                        studentTable.setRowSelectionInterval(row, row);
                    }

                    contextMenu.show(
                            e.getComponent(),
                            e.getX(),
                            e.getY()
                    );
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentTable);

        add(scrollPane, BorderLayout.CENTER);
    }


    private void createPopupMenu() {

        contextMenu = new JPopupMenu();


        JMenuItem itemProfile = new JMenuItem("View Profile");
        JMenuItem itemCourses = new JMenuItem("Register Courses");
        JMenuItem itemTranscript = new JMenuItem("Print Transcript");
        JMenuItem itemDeactivate = new JMenuItem("Deactivate Record");


        itemProfile.addActionListener(e -> {

            int row = studentTable.getSelectedRow();
            String name = studentTable.getValueAt(row, 1).toString();

            JOptionPane.showMessageDialog(
                    this,
                    "Viewing profile for: " + name
            );
        });

        itemCourses.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Opening course registration module..."
                )
        );

        itemTranscript.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Sending transcript to printer..."
                )
        );


        itemDeactivate.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Student record deactivated successfully."
            );
        });


        contextMenu.add(itemProfile);
        contextMenu.add(itemCourses);
        contextMenu.add(itemTranscript);
        contextMenu.addSeparator();

        contextMenu.add(itemDeactivate);
    }
}
