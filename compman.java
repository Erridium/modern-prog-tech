import javax.swing.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

class VerticalLayout implements LayoutManager
{
    private Dimension size = new Dimension();

    public void addLayoutComponent   (String name, Component comp) {}
    public void removeLayoutComponent(Component comp) {}

    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    public void layoutContainer(Container container)
    {
        Component list[] = container.getComponents();
        int currentY = 5;
        for (int i = 0; i < list.length; i++) {
            Dimension pref = list[i].getPreferredSize();
            list[i].setBounds(5, currentY, pref.width, pref.height);
            currentY += 5;
            currentY += pref.height;
        }
    }
    private Dimension calculateBestSize(Container c)
    {
        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < list.length; i++) {
            int width = list[i].getWidth();
            if ( width > maxWidth ) 
                maxWidth = width;
        }
        size.width = maxWidth + 5;
        int height = 0;
        for (int i = 0; i < list.length; i++) {
            height += 5;
            height += list[i].getHeight();
        }
        size.height = height;
        return size;
    }
}

public class VerticalLayoutTest
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("VerticalLayoutTest");
        frame.setSize(260, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contents = new JPanel(new VerticalLayout());
        contents.add(new JButton("Продукты"  ));
        contents.add(new JButton("Галантерея"));
        contents.add(new JTextField(20));
        frame.setContentPane(contents);
        frame.setVisible(true);
    }
}
