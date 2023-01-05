import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class sales_page  implements ActionListener{
    JFrame salesPage = new JFrame("Sales");
    JLabel[] label = new JLabel[4];
    JTextField[] field = new JTextField[3];
    JTextArea area;
    Container container = new Container();
    String name,id;
    JScrollPane subScroll;
    String text = "";
    int cost = 0;

    public sales_page(){
        container = salesPage.getContentPane();
        container.setLayout(new FlowLayout());
        container.setBackground(Color.WHITE);
        initGui();
    }

    public void initGui(){
        label[0] = new JLabel("Customer id ");
        label[0].setFont(new Font("Serif",Font.PLAIN,20));
        container.add(label[0]);
        field[0] = new JTextField(10);
        try {
            File read = new File("sales.txt");
            Scanner readFIle = new Scanner(read);
            while(readFIle.hasNext()){
                String data = readFIle.nextLine();
                String stmp[] = data.split(";");
                name = stmp[3];
                id = stmp[4];
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        field[0].setText(id);
        field[0].setFont(new Font("Serif",Font.PLAIN,20));
        field[0].setEditable(false);
        field[0].setHorizontalAlignment(JTextField.RIGHT);
        container.add(field[0]);
        label[1] = new JLabel("Customer name ");
        label[1].setFont(new Font("Serif",Font.PLAIN,20));
        container.add(label[1]);
        field[1] = new JTextField(10);
        field[1].setText(name);
        field[1].setFont(new Font("Serif",Font.PLAIN,20));
        field[1].setEditable(false);
        field[1].setHorizontalAlignment(JTextField.RIGHT);
        container.add(field[1]);
        container.add(new JLabel("         "));
        area = new JTextArea(16,60);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        area.setBorder(BorderFactory.createCompoundBorder(border,
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        area.setEditable(false);
        subScroll = new JScrollPane(area);
        try {
            File read = new File("sales.txt");
            Scanner readFIle = new Scanner(read);
            while(readFIle.hasNext()){
                String data = readFIle.nextLine();
                String stmp[] = data.split(";");
                text += stmp[0] + " x  " + stmp[1] + " = " + stmp[2] + " bath" +"\n";
                cost += Integer.valueOf(stmp[2]);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        area.setFont(new Font("serif", Font.PLAIN, 16));
        area.setText(text);
        container.add(subScroll);
        label[2] = new JLabel("Total");
        label[2].setFont(new Font("Serif",Font.PLAIN,20));
        container.add(label[2]);
        field[2] = new JTextField(10);
        field[2].setText(String.valueOf(cost));
        field[2].setFont(new Font("Serif",Font.PLAIN,20));
        field[2].setEditable(false);
        field[2].setHorizontalAlignment(JTextField.RIGHT);
        container.add(field[2]);
        label[3] = new JLabel("bath");
        label[3].setFont(new Font("Serif",Font.PLAIN,20));
        container.add(label[3]);
        salesPage.setSize(740, 520);
        salesPage.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public static void main(String[] args) {
        sales_page page = new sales_page();
    
    }
    
}
