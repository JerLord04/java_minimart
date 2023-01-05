import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;


public class add_product_page implements ActionListener{
    JFrame productPage = new JFrame("Product");
    JTextArea area;
    Container container = new Container();
    String text = "";
    JScrollPane subScroll;
    JButton addBtn,editBtn,deleteBtn;
    product_file product_add;
    pageAdd pAdd;
    pageEdit pEdit;
    pageDelete pDelete;
    Timer time = new Timer(1000, this);

    public add_product_page(){
        container = productPage.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JLabel("Product"));
        area = new JTextArea(10,45);
        try {
            File read = new File("product.txt");
            Scanner readFile = new Scanner(read);
            while(readFile.hasNext()){
                String data = readFile.nextLine();
                String stmp[] = data.split(";");
                text += " id : " + stmp[0] + " name : " + stmp[1] + " quantity : "  + stmp[2] + " price : " + stmp[3] + "\n";
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        area.setText(text);
        area.setEditable(false);
        subScroll = new JScrollPane(area);
        container.add(subScroll);
        addBtn = new JButton("ADD");
        addBtn.addActionListener(this);
        container.add(addBtn);
        editBtn = new JButton("EDIT");
        editBtn.addActionListener(this);
        container.add(editBtn);
        deleteBtn = new JButton("DELETE");
        deleteBtn.addActionListener(this);
        container.add(deleteBtn);     
        productPage.setSize(500,280);
        productPage.setVisible(true);
        time.start();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){
            pAdd = new pageAdd();
        }
        if(e.getSource() == editBtn){
            pEdit = new pageEdit();
        }
        if(e.getSource() == deleteBtn){
            pDelete = new pageDelete();
        }
        setDataInJtextArea();
        SwingUtilities.updateComponentTreeUI(productPage);
    }

    public void setDataInJtextArea(){
        text = "";
        try {
            File read = new File("product.txt");
            Scanner readFile = new Scanner(read);
            while(readFile.hasNext()){
                String data = readFile.nextLine();
                String stmp[] = data.split(";");
                text += " id : " + stmp[0] + " name : " + stmp[1] + " quantity : "  + stmp[2] + " price : " + stmp[3] + "\n";
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        area.setText(text);
    }
    public static void main(String[] args) {
        add_product_page test = new add_product_page();
    }

    class pageAdd extends JFrame implements ActionListener{
        JLabel[] label = new JLabel[5];
        JTextField[] field = new JTextField[4];
        JButton addBtn,cancelBtn;
        public pageAdd(){
            super("Add product");
            setLayout(new FlowLayout());
            label[0] = new JLabel("Product");
            add(label[0]);
            label[1] = new JLabel("id ");
            add(label[1]);
            field[0] = new JTextField(10);
            field[0].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                         e.consume();
                    }
                }
             });
            add(field[0]);
            label[2] = new JLabel("name ");
            add(label[2]);
            field[1] = new JTextField(10);
            add(field[1]);
            label[3] = new JLabel("quantity ");
            add(label[3]);
            field[2] = new JTextField(10);
            field[2].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                         e.consume();
                    }
                }
             });
            add(field[2]);
            label[4] = new JLabel("price ");
            add(label[4]);
            field[3] = new JTextField(10);
            field[3].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                         e.consume();
                    }
                }
             });
            add(field[3]);
            addBtn = new JButton("ADD");
            addBtn.addActionListener(this);
            add(addBtn);
            cancelBtn = new JButton("CANCEL");
            cancelBtn.addActionListener(this);
            add(cancelBtn);
            setSize(380,140);
            setVisible(true);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {  
            if(e.getSource() == addBtn){
                product_add = new product_file();
                String id = field[0].getText();
                String name = field[1].getText();
                String quantity = field[2].getText();
                String price = field[3].getText();
                product_add.product_write(id,name,quantity,price);
                setDataInJtextArea();
                field[0].setText("");
                field[1].setText("");
                field[2].setText("");
                field[3].setText("");
            } 
            if(e.getSource() == cancelBtn){
                field[0].setText("");
                field[1].setText("");
                field[2].setText("");
                field[3].setText("");
            } 
        }
    }

    class pageEdit extends JFrame implements ActionListener{
        JLabel label;
        JTextField field;
        JButton enterBtn;
        public pageEdit(){
            super("edit Product");
            setLayout(new FlowLayout());
            label = new JLabel("Enter product ID");
            add(label);
            field = new JTextField(10);
            field.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                         e.consume();
                    }
                }
             });
            add(field);
            enterBtn = new JButton("Enter");
            enterBtn.addActionListener(this);
            add(enterBtn);
            setSize(270,100);
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == enterBtn){
                if(field.getText().equals("")){
                }else{
                    product_add = new product_file();
                    product_add.product_edit(field.getText());
                    field.setText("");
                }
                
            }
            
        }

    }

    class pageDelete extends JFrame implements ActionListener{
        JLabel label;
        JButton enterBtn,cancelBtn;
        JTextField field;
        public pageDelete(){
            super("Delete product");
            setLayout(new FlowLayout());
            label = new JLabel("Enter product id");
            add(label);
            field = new JTextField(10);
            add(field);
            enterBtn = new JButton("Enter");
            enterBtn.addActionListener(this);
            add(enterBtn);
            cancelBtn = new JButton("Cancel");
            cancelBtn.addActionListener(this);
            add(cancelBtn);
            setSize(270,100);
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == enterBtn){
                int n = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if(n == 0){
                    product_add = new product_file();
                    product_add.product_delete(field.getText());
                }
                field.setText("");
            }
            if(e.getSource() == cancelBtn){
                field.setText("");
            }

            
        }
        
    }
}


