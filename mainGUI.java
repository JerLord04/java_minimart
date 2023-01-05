import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.undo.StateEditable;

import java.io.*;
import java.util.Scanner;

public class mainGUI implements ActionListener {
    JFrame mainPage = new JFrame("Super market program");
    JTextField[] amount = new JTextField[3];
    JTextField fieldProduct;
    JButton[] conBtn = new JButton[4];
    JButton addBtn;
    JButton refreshDropdown;
    Container container;
    private int[] len = new int[3];
    Timer time = new Timer(5000, this);
    JComboBox<String> productNameCombo, customerNameCombo;
    JTextArea areaf;
    JScrollPane subScroll;
    DefaultComboBoxModel dm, dm1;
    int comboSize = 0;
    public String[] stmp = new String[1000];
    public String[][] fileData;
    public String[][] bill;
    int r = 0;
    String textInJtext = "";
    String sCost,name,cusId;

    public mainGUI() {
        container = mainPage.getContentPane();
        container.setLayout(new FlowLayout());
        container.setBackground(Color.WHITE);
        unitGui();
    }

    public mainGUI(int a) {
    }


    public void unitGui() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.ORANGE);
        conBtn[0] = new JButton("ADD PRODUCT");
        conBtn[1] = new JButton("ADD EMPLOYEE");
        conBtn[2] = new JButton("ADD CUSTOMER");
        conBtn[3] = new JButton("SALES");
        conBtn[0].addActionListener(this);
        conBtn[1].addActionListener(this);
        conBtn[2].addActionListener(this);
        conBtn[3].addActionListener(this);
        northPanel.add(conBtn[0]);
        northPanel.add(conBtn[1]);
        northPanel.add(conBtn[2]);
        northPanel.add(conBtn[3]);
        len[0] = count("product.txt");
        len[1] = count("employee.txt");
        len[2] = count("customer.txt");
        container.add(northPanel, BorderLayout.NORTH);
        ///////////////////////////////////////////////////////////////////////
        JPanel field1 = new JPanel(new GridBagLayout());
        field1.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        field1.add(new JLabel("           products"), gbc);
        amount[0] = new JTextField(7);
        amount[0].setFont(new Font("Serif", Font.BOLD, 20));
        amount[0].setText(String.valueOf(len[0]));
        amount[0].setEditable(false);
        amount[0].setHorizontalAlignment(JTextField.CENTER);
        gbc.insets.top = 6;
        field1.add(amount[0], gbc);
        field1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field1.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        container.add(field1);
        ///////////////////////////////////////////////////////////////////////
        JPanel field2 = new JPanel(new GridBagLayout());
        field2.setOpaque(false);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridwidth = GridBagConstraints.REMAINDER;
        gbc1.anchor = GridBagConstraints.BASELINE_LEADING;
        field2.add(new JLabel("        employees  "), gbc1);
        amount[1] = new JTextField(7);
        amount[1].setFont(new Font("Serif", Font.BOLD, 20));
        amount[1].setText(String.valueOf(len[1]));
        amount[1].setEditable(false);
        amount[1].setHorizontalAlignment(JTextField.CENTER);
        gbc1.insets.top = 6;
        field2.add(amount[1], gbc1);
        field2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field2.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        container.add(field2);
        mainPage.setSize(500, 500);
        mainPage.setVisible(true);
        ///////////////////////////////////////////////////////////////////////
        JPanel field3 = new JPanel(new GridBagLayout());
        field3.setOpaque(false);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.anchor = GridBagConstraints.BASELINE_LEADING;
        field3.add(new JLabel("        customers"), gbc2);
        amount[2] = new JTextField(7);
        amount[2].setFont(new Font("Serif", Font.BOLD, 20));
        amount[2].setText(String.valueOf(len[2]));
        amount[2].setEditable(false);
        amount[2].setHorizontalAlignment(JTextField.CENTER);
        gbc2.insets.top = 6;
        field3.add(amount[2], gbc1);
        field3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field3.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        container.add(field3);
        ///////////////////////////////////////////////////////////////////////
        refreshDropdown = new JButton("re-dropdown");
        refreshDropdown.addActionListener(this);
        container.add(refreshDropdown);
        ///////////////////////////////////////////////////////////////////////
        JPanel field4 = new JPanel(new GridBagLayout());
        field4.setOpaque(false);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridwidth = GridBagConstraints.REMAINDER;
        gbc3.anchor = GridBagConstraints.BASELINE_LEADING;
        field4.add(new JLabel("choose product"), gbc3);
        gbc3.insets.top = 6;
        initDropdown();
        field4.add(new JLabel("   "));
        field4.add(productNameCombo, gbc3);
        field4.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field4.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        container.add(field4);
        ///////////////////////////////////////////////////////////////////////
        JPanel field5 = new JPanel(new GridBagLayout());
        field5.setOpaque(false);
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridwidth = GridBagConstraints.REMAINDER;
        gbc4.anchor = GridBagConstraints.BASELINE_LEADING;
        field5.add(new JLabel("      enter number"), gbc4);
        gbc4.insets.top = 6;
        fieldProduct = new JTextField(6);
        fieldProduct.setFont(new Font("Serif", Font.BOLD, 18));
        fieldProduct.setText("0");
        fieldProduct.setHorizontalAlignment(JTextField.RIGHT);
        fieldProduct.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
         });
        field5.add(new JLabel("   "));
        field5.add(fieldProduct, gbc4);
        field5.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field5.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        container.add(field5);
        ///////////////////////////////////////////////////////////////////////
        JPanel field6 = new JPanel(new GridBagLayout());
        field6.setOpaque(false);
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridwidth = GridBagConstraints.REMAINDER;
        gbc5.anchor = GridBagConstraints.BASELINE_LEADING;
        field6.add(new JLabel("choose cus-name"), gbc5);
        gbc5.insets.top = 6;
        initDropdownCus();
        field6.add(new JLabel("   "));
        field6.add(customerNameCombo, gbc5);
        field6.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field6.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        container.add(field6);
        ///////////////////////////////////////////////////////////////////////
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        container.add(addBtn);
        areaf = new JTextArea(16, 40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        areaf.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        areaf.setEditable(false);
        // areaf.setBounds(10,30, 200,200);
        subScroll = new JScrollPane(areaf);
        container.add(subScroll);
        // container.add(areaf);
        ///////////////////////////////////////////////////////////////////////
        bill = new String[1000][5];
        mainPage.setSize(550, 500);
        mainPage.setVisible(true);
        time.start();
    }

    public static void main(String[] args) {
        mainGUI test = new mainGUI();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == conBtn[0]) {
            add_product_page testShow = new add_product_page();
        }
        if (e.getSource() == conBtn[1]) {
            add_employee_page testShow = new add_employee_page();
        }
        if (e.getSource() == conBtn[2]) {
            add_customer_page testShow = new add_customer_page();
        }
        if (e.getSource() == conBtn[3]) {
            int n = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
            if(n == 0){
                appendProductSale();
                this.r = 0;
                for(int i = 0;i < bill.length;i++){
                    bill[i][0] = null;
                    bill[i][1] = null;
                    bill[i][2] = null;
                    bill[i][3] = null;
                    bill[i][4] = null;
                }
                areaf.setText("");
                textInJtext = "";
            }
            sales_page testShow = new sales_page();
        }
        if (e.getSource() == refreshDropdown) {
            for (int i = productNameCombo.getItemCount() - 1; i >= 0; i--) {
                productNameCombo.removeItemAt(i);
            }
            for (int i = customerNameCombo.getItemCount() - 1; i >= 0; i--) {
                customerNameCombo.removeItemAt(i);
            }
            updateDropdown();
            updateDropdownCus();
        }
        if (e.getSource() == addBtn) {
            Boolean flg = checkQuantity();
            if(flg == false){
                JOptionPane.showMessageDialog(null, "Not enough products");
            }else{
                String value = productNameCombo.getSelectedItem().toString();
                this.name = customerNameCombo.getSelectedItem().toString();
                try {
                    File readFile = new File("customer.txt");
                    Scanner readCus = new Scanner(readFile);
                    while(readCus.hasNext()){
                        String data = readCus.nextLine();
                        String stmp[] = data.split(";");
                        if(stmp[1].equals(name)){
                             cusId = stmp[0];
                             System.out.println(cusId);
                        }
                    }
                } catch (Exception event) {
                    //TODO: handle exception
                }
                textInJtext += value + " x  " + fieldProduct.getText() + " = " + this.sCost + " bath" +"\n";
                areaf.setText(textInJtext);
                bill[r][0] = value;
                bill[r][1] = fieldProduct.getText();
                bill[r][2] = this.sCost;
                bill[r][3] = this.name;
                bill[r][4] = this.cusId;
                r++;
                fieldProduct.setText("0");
            }
        }
        updateInfield();
        SwingUtilities.updateComponentTreeUI(mainPage);
    }


    public int count(String path) {
        int n = 0;
        try {
            File read = new File(path);
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                n++;
            }
        } catch (Exception e) {

        }
        return n;
    }

    public String getCusId(){
        return this.cusId;
    }
    public void updateInfield() {
        len[0] = count("product.txt");
        len[1] = count("employee.txt");
        len[2] = count("customer.txt");
        amount[0].setText(String.valueOf(len[0]));
        amount[1].setText(String.valueOf(len[1]));
        amount[2].setText(String.valueOf(len[2]));
    }

    public void initDropdown() {
        countComboSize("product.txt");
        dm = new DefaultComboBoxModel();
        int i = 0;
        String[] updateTmp = new String[comboSize];
        try {
            File read = new File("product.txt");
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                String tmpdata[] = data.split(";");
                updateTmp[i] = tmpdata[1];
                dm.addElement(tmpdata[1]);
                i++;
            }
        } catch (Exception e) {

        }
        productNameCombo = new JComboBox<String>();
        productNameCombo.setModel(dm);
        comboSize = 0;
    }

    public void initDropdownCus() {
        countComboSize("customer.txt");
        dm1 = new DefaultComboBoxModel();
        int i = 0;
        String[] updateTmp = new String[comboSize];
        try {
            File read = new File("customer.txt");
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                String tmpdata[] = data.split(";");
                updateTmp[i] = tmpdata[1];
                dm1.addElement(tmpdata[1]);
                i++;
            }
        } catch (Exception e) {

        }
        customerNameCombo = new JComboBox<String>();
        customerNameCombo.setModel(dm1);
        comboSize = 0;
    }

    public void updateDropdown() {
        countComboSize("product.txt");
        String[] datatemp = new String[comboSize];
        int i = 0;
        try {
            File read = new File("product.txt");
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                String tmpdata[] = data.split(";");
                dm.addElement(tmpdata[1]);
                i++;
            }
        } catch (Exception e) {

        }
        productNameCombo.setModel(dm);
        comboSize = 0;
    }

    public void updateDropdownCus() {
        countComboSize("customer.txt");
        String[] datatemp = new String[comboSize];
        int i = 0;
        try {
            File read = new File("customer.txt");
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                String tmpdata[] = data.split(";");
                dm1.addElement(tmpdata[1]);
                i++;
            }
        } catch (Exception e) {

        }
        customerNameCombo.setModel(dm1);
        comboSize = 0;
    }

    public void countComboSize(String path) {
        try {
            File read = new File(path);
            Scanner readFile = new Scanner(read);
            while (readFile.hasNext()) {
                String data = readFile.nextLine();
                comboSize++;
            }
        } catch (Exception e) {
        }
    }

    public boolean checkQuantity(){
        fileData = new String[1000][4];
        int i = 0,j = 0,len = 0;
        boolean flg = false;
        String varName = (String)productNameCombo.getSelectedItem();
        String value = productNameCombo.getSelectedItem().toString();
        String sQuantity = fieldProduct.getText();
        int qNum = Integer.valueOf(sQuantity);
        try {
            File read = new File("product.txt");
            Scanner readFile = new Scanner(read);
            while(readFile.hasNext()){
                String data = readFile.nextLine();
                String stmp[] = data.split(";");
                if(value.equals(stmp[1])){
                    int productQ = Integer.valueOf(stmp[2]);
                    if(qNum > productQ){
                        flg = false;
                    }else{
                        flg = true;
                        int cost = Integer.valueOf(stmp[3]);
                        int result = productQ - qNum;
                        int nCost = qNum*cost;
                        String sResult = String.valueOf(result);
                        this.sCost = String.valueOf(nCost);
                        File readProd = new File("product.txt");
                        Scanner readFilePro = new Scanner(readProd);
                        while(readFilePro.hasNext()){
                            String dataPro = readFilePro.nextLine();
                            String stmpPro[] = dataPro.split(";");  
                            fileData[j][0] = stmpPro[0];
                            fileData[j][1] = stmpPro[1];
                            fileData[j][2] = stmpPro[2];
                            fileData[j][3] = stmpPro[3];
                            len++;
                            j++;
                        }
                        for (i = 0; i < len; i++) {
                            if(fileData[i][0].equals(stmp[0])){
                                fileData[i][2] = sResult;
                            }
                        }
                    }
                }
            }
            if(flg == true){
                BufferedWriter writer = new BufferedWriter(new FileWriter("product.txt"));
                for (int k = 0; k < len; k++) {
                    writer.write(fileData[k][0] + ";" + fileData[k][1] + ";" + fileData[k][2] + ";"+ fileData[k][3] +"\n");
                }
                writer.close();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }

        return flg;
    }

    public void appendProductSale(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt"));
            for (int k = 0; k < bill.length; k++) {
                if(bill[k][0] != null){
                    writer.write(bill[k][0] + ";" + bill[k][1] + ";" + bill[k][2] + ";" + bill[k][3] + ";" + bill[k][4] +"\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            //TODO: handle exception
        }


    }

}