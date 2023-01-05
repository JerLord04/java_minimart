import java.io.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class employee_file {

    public String dataTmp[][];
    public int len = 0;
    public Boolean flg = false;

    public employee_file() {
        dataTmp = new String[5000][4];
    }


    public void employee_write(String id, String fname, String lname,String position) {
        File file = new File("employee.txt");
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt"));
            if (flg == true) {
                writer.write(id + ";" + fname + ";" + lname + ";" + position);
            } else {
                for (int i = 0; i < len; i++) {
                    writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
                }
                writer.write(id + ";" + fname + ";" + lname + ";" + position);
            }
            writer.close();
            len = 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void employee_write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt"));
            for (int i = 0; i < len; i++) {
                writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2] + ";"+ dataTmp[i][3] +"\n");
            }
            writer.close();
            len = 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void employee_edit(String id) {
        editFrame test = new editFrame(id);
    }

    public void employee_delete(String id) {
        fileToArray();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt"));
            if (len == 1) {
                for (int i = 0; i < len; i++) {
                    if (dataTmp[i][0].equals(id)) {
                        writer.write("");
                    } else {
                        writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2]  + ";" + dataTmp[i][3]  +"\n");
                    }
                }
            } else {
                for (int i = 0; i < len; i++) {
                    if (dataTmp[i][0].equals(id)) {
                        i++;
                    }
                    if(dataTmp[i][0] != null){
                        writer.write(dataTmp[i][0] + ";" + dataTmp[i][1] + ";" + dataTmp[i][2]  + ";" + dataTmp[i][3]  +"\n");
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
            File file = new File("employee.txt");
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
        String fname,lname,position;
        JLabel[] label = new JLabel[3];
        JTextField[] field = new JTextField[3];
        JButton enterBtn,cancelBtn;
        String id;
        
        public editFrame(String id){
            super("Edit employee");
            this.id=id;
            setLayout(new FlowLayout());
            label[0] = new JLabel("fname ");
            add(label[0]);
            field[0] = new JTextField(10);
            add(field[0]);
            label[1] = new JLabel("lname ");
            add(label[1]);
            field[1] = new JTextField(10);
            add(field[1]);
            label[2] = new JLabel("position ");
            add(label[2]);
            field[2] = new JTextField(10);
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
                        this.fname = field[0].getText();
                        this.lname = field[1].getText();
                        this.position = field[2].getText();
                        dataTmp[i][1] = fname;
                        dataTmp[i][2] = lname;
                        dataTmp[i][3] = position;
                    }
                }
                employee_write();
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
