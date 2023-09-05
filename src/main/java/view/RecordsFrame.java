package view;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class RecordsFrame  extends JFrame {
    public RecordsFrame(){
        super("Рекорды");
        setSize(500, 500);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension framesize = getSize();
        int x = (int)screensize.getWidth() / 2 - (int)framesize.getWidth() / 2;
        int y = (int)screensize.getHeight() / 2 - (int)framesize.getHeight() / 2;
        setLocation(x,y);
        String[] columnNames = {"NAME", "POINTS", "GAMES"};
        String[][] data = GameFrame.result.fillArray();
        JTable table = new JTable(data, columnNames);
        table.getTableHeader().setBackground(Color.BLACK);
        table.setBackground(Color.BLACK);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,15));
        table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN,15));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                int y = getSize().height;
                table.setRowHeight(y / 10);
            }
        });
        setVisible(true);
    }
}
