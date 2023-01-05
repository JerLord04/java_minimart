import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;

public class product_file {

    public String dataTmp[][];
    public int len = 0;
    public Boolean flg = false;

    public product_file() {
        dataTmp = new String[5000][4];
    }

    public void product_write(String id, String name, String quantity,String price) {
        File file = new File("product.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                flg = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileToArray();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("product.txt"));
            if (flg == true) {
                writer.write(id + ";" + name + ";" + quantity + ";" + price);
            } else {
                for (int i = 0; i < len; i++) {
                    writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
                }
                writer.write(id + ";" + name + ";" + quantity + ";" + price);
            }
            writer.close();
            len = 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void product_write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("product.txt"));
            for (int i = 0; i < len; i++) {
                writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
            }
            writer.close();
            len = 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void product_edit(String id) {
        editFrame edit = new editFrame(id);
    }

    public void product_delete(String id) {
        fileToArray();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("product.txt"));
            if (len == 1) {
                for (int i = 0; i < len; i++) {
                    if (dataTmp[i][0].equals(id)) {
                        writer.write("");
                    } else {
                        writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
                    }
                }
            } else {
                for (int i = 0; i < len; i++) {
                    if (dataTmp[i][0].equals(id)) {
                        i++;
                    }
                    if(dataTmp[i][0] != null){
                        writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
                    }
                    
                }

            }
            writer.close();
            len = 0;

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void fileToArray() {
        int i = 0;
        try {
            File file = new File("product.txt");
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                String stmp[] = data.split(";");
                dataTmp[i][0] = stmp[0];
                dataTmp[i][1] = stmp[1];
                dataTmp[i][2] = stmp[2];
                dataTmp[i][3] = stmp[3];
                i++;
                len++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    class editFrame extends JFrame implements ActionListener{
        String name,quantity,price;
        JLabel[] label = new JLabel[3];
        JTextField[] field = new JTextField[3];
        JButton enterBtn,cancelBtn;
        String id;
        
        public editFrame(String id){
            super("Edit product");
            this.id=id;
            setLayout(new FlowLayout());
            label[0] = new JLabel("name ");
            add(label[0]);
            field[0] = new JTextField(10);
            add(field[0]);
            label[1] = new JLabel("quantity ");
            add(label[1]);
            field[1] = new JTextField(10);
            field[1].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                         e.consume();
                    }
                }
             });
            add(field[1]);
            label[2] = new JLabel("price ");
            add(label[2]);
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
            enterBtn = new JButton("ENTER");
            enterBtn.addActionListener(this);
            add(enterBtn);
            cancelBtn = new JButton("CANCEL");
            cancelBtn.addActionListener(this);
            add(cancelBtn);
            setSize(200,150);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == enterBtn){
                fileToArray();
                for (int i = 0; i < len; i++) {
                    if (dataTmp[i][0].equals(this.id)) {
                        this.name = field[0].getText();
                        this.quantity = field[1].getText();
                        this.price = field[2].getText();
                        dataTmp[i][1] = name;
                        dataTmp[i][2] = quantity;
                        dataTmp[i][3] = price;
                    }
                }
                product_write();
                field[0].setText("");
                field[1].setText("");
                field[2].setText("");
                setVisible(false);
                dispose();
            }
            if(e.getSource() == cancelBtn){
                setVisible(false);
                dispose();
            }

        }

    }
}
