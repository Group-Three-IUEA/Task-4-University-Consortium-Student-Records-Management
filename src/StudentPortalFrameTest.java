import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentPortalFrameTest {

    private Object getField(Object target, String name) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(target);
    }

    // 1. PASSES: menu bar has the 5 required menus
    @Test
    void menuBarHasFiveMenus() {
        StudentPortalFrame frame = new StudentPortalFrame();
        assertEquals(5, frame.getJMenuBar().getMenuCount());
        frame.dispose();
    }

    // 2. PASSES: menu names are correct and in order
    @Test
    void menuNamesAreCorrect() {
        StudentPortalFrame frame = new StudentPortalFrame();
        JMenuBar bar = frame.getJMenuBar();
        assertEquals("Student Registration", bar.getMenu(0).getText());
        assertEquals("Course Management", bar.getMenu(1).getText());
        assertEquals("Examination Results", bar.getMenu(2).getText());
        assertEquals("Finance", bar.getMenu(3).getText());
        assertEquals("Help", bar.getMenu(4).getText());
        frame.dispose();
    }

    // 3. PASSES: table has 4 columns and 4 students
    @Test
    void tableHasCorrectColumnsAndRows() throws Exception {
        StudentTablePanel panel = new StudentTablePanel();
        JTable table = (JTable) getField(panel, "studentTable");
        assertEquals(4, table.getColumnCount());
        assertEquals(4, table.getRowCount());
        assertEquals("Alice Nakato", table.getValueAt(0, 1));
    }

    // 4. PASSES: right-click menu has the 4 required actions
    @Test
    void popupMenuHasFourActions() throws Exception {
        StudentTablePanel panel = new StudentTablePanel();
        JPopupMenu menu = (JPopupMenu) getField(panel, "contextMenu");
        List<String> labels = new ArrayList<>();
        for (Component c : menu.getComponents()) {
            if (c instanceof JMenuItem) labels.add(((JMenuItem) c).getText());
        }
        assertEquals(List.of("View Profile", "Register Courses",
                "Print Transcript", "Deactivate Record"), labels);
    }

    // 5. FAILS: "View Profile" with no row selected crashes (getValueAt(-1, 1))
    @Test
    void viewProfileWithNoSelectionShouldNotCrash() throws Exception {
        StudentTablePanel panel = new StudentTablePanel();
        JPopupMenu menu = (JPopupMenu) getField(panel, "contextMenu");
        JMenuItem viewProfile = (JMenuItem) menu.getComponent(0);
        assertDoesNotThrow(() -> viewProfile.doClick());
    }
}
