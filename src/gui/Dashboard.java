/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author kguna
 */
public class Dashboard extends javax.swing.JFrame {

    public static HashMap<String, Integer> subject1Map = new HashMap();
    public static HashMap<String, Integer> subject2Map = new HashMap();
    public static HashMap<String, Integer> subject3Map = new HashMap();
    public static HashMap<String, Integer> subject4Map = new HashMap();
    public static HashMap<String, Integer> subject5Map = new HashMap();
    public static HashMap<String, Integer> subject6Map = new HashMap();
    public static HashMap<String, Integer> subject7Map = new HashMap();
    public static HashMap<String, Integer> subject8Map = new HashMap();
    public static HashMap<String, Integer> subject9Map = new HashMap();
    public static HashMap<String, Integer> subjectTeacherMap = new HashMap();
    public static Integer teacherId = null;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        loadStudents();
        loadSubjects();
        countStudents();
        countTeachers();
        loadTeachers();
        loadSubjectTable();
        loadClassesTable();
    }

    private void countStudents() {

        try {

            ResultSet resultset = MySQL.execute("SELECT COUNT(*) AS studentCount FROM `student`");

            while (resultset.next()) {
                int studentCount = resultset.getInt("studentCount");
                dashboardStudentsCount.setText(String.valueOf(studentCount));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void countTeachers() {

        try {

            ResultSet resultset = MySQL.execute("SELECT COUNT(*) AS teacherCount FROM `teacher`");

            while (resultset.next()) {
                int teacherCount = resultset.getInt("teacherCount");
                dashboardTeachersCount.setText(String.valueOf(teacherCount));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSubjectTable() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `subject` ORDER BY `subno` DESC");

            DefaultTableModel model = (DefaultTableModel) subjectDetailTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("subno"));
                v.add(resultset.getString("name"));
                v.add(resultset.getString("description"));
                v.add(resultset.getString("price"));

                model.addRow(v);
                subjectDetailTable.setModel(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadClassesTable() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `class` INNER JOIN `teacher` ON `class`.`teacher_tno`=`teacher`.`tno` INNER JOIN `subject` ON `class`.`subject_subno`=`subject`.`subno` ORDER BY `clssno` DESC");

            DefaultTableModel model = (DefaultTableModel) classDetailTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("clssno"));
                v.add(resultset.getString("subject.name"));
                v.add(resultset.getString("teacher.firstName") + " " + resultset.getString("teacher.lastName"));
                v.add(resultset.getString("timeslot"));
                v.add(resultset.getString("description"));

                model.addRow(v);
                classDetailTable.setModel(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStudents() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `student` ORDER BY `sno` DESC");

            DefaultTableModel model = (DefaultTableModel) studentRegistrationTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("firstName"));
                v.add(resultset.getString("lastName"));
                v.add(resultset.getString("mobile"));
                v.add(resultset.getString("dob"));
                v.add(resultset.getString("email"));
                v.add(resultset.getString("address"));

                model.addRow(v);
                studentRegistrationTable.setModel(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadTeachers() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `teacher` INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`subno` ORDER BY `tno` DESC");

            DefaultTableModel modelteacher = (DefaultTableModel) teacherRegistrationTable.getModel();
            modelteacher.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("firstName") + " " + resultset.getString("lastName"));
                v.add(resultset.getString("mobile"));
                v.add(resultset.getString("subject.name"));

                modelteacher.addRow(v);
                teacherRegistrationTable.setModel(modelteacher);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {

        subject1ComboBox.setSelectedIndex(0);
        subject2ComboBox.setSelectedIndex(0);
        subject3ComboBox.setSelectedIndex(0);
        subject4ComboBox.setSelectedIndex(0);
        subject5ComboBox.setSelectedIndex(0);
        subject6ComboBox.setSelectedIndex(0);
        subject7ComboBox.setSelectedIndex(0);
        subject8ComboBox.setSelectedIndex(0);
        subject9ComboBox.setSelectedIndex(0);
        firstNameFieldStudentRegistration.setText("");
        lastNameFieldStudentRegistration.setText("");
        mobileFieldStudentRegistration.setText("");
        emailFieldStudentRegistration.setText("");
        addressFieldStudentRegistration.setText("");
        dateChooserStudentRegistration.setDate(null);
        buttonGroupStudentRegistration.clearSelection();
        firstNameFieldStudentRegistration.requestFocus();
        firstNameFieldTeacherRegistration.setText("");
        lastNameFieldTeacherRegistration.setText("");
        mobileFieldTeacherRegistration.setText("");
        emailFieldTeacherRegistration.setText("");
        addressFieldTeacherRegistration.setText("");
        jComboBoxTeacherSubject.setSelectedIndex(0);
        buttonGroupTeacherRegistration.clearSelection();
        firstNameFieldTeacherRegistration.requestFocus();
        teacherNameSearchResult.setText("");
        teacherSubjectNameSearchResult.setText("");
        teacherMobileSearchResult.setText("");
        teacherEmailSearchResult.setText("");
        teacherAddressSearchResult.setText("");

    }

    private void loadSubjects() {

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `subject`");

            Vector vSubject1 = new Vector();
            vSubject1.add("Select");

            Vector vSubject2 = new Vector();
            vSubject2.add("Subject 02");

            Vector vSubject3 = new Vector();
            vSubject3.add("Subject 03");

            Vector vSubject4 = new Vector();
            vSubject4.add("Subject 04");

            Vector vSubject5 = new Vector();
            vSubject5.add("Subject 05");

            Vector vSubject6 = new Vector();
            vSubject6.add("Subject 06");

            Vector vSubject7 = new Vector();
            vSubject7.add("Subject 07");

            Vector vSubject8 = new Vector();
            vSubject8.add("Subject 08");

            Vector vSubject9 = new Vector();
            vSubject9.add("Subject 09");

            while (resultset.next()) {
                vSubject1.add(resultset.getString("name"));
                vSubject2.add(resultset.getString("name"));
                vSubject3.add(resultset.getString("name"));
                vSubject4.add(resultset.getString("name"));
                vSubject5.add(resultset.getString("name"));
                vSubject6.add(resultset.getString("name"));
                vSubject7.add(resultset.getString("name"));
                vSubject8.add(resultset.getString("name"));
                vSubject9.add(resultset.getString("name"));
                subject1Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject2Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject3Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject4Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject5Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject6Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject7Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject8Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subject9Map.put(resultset.getString("name"), resultset.getInt("subno"));
                subjectTeacherMap.put(resultset.getString("name"), resultset.getInt("subno"));

            }

            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vSubject1);
            subject1ComboBox.setModel(model1);

            DefaultComboBoxModel model2 = new DefaultComboBoxModel(vSubject2);
            subject2ComboBox.setModel(model2);

            DefaultComboBoxModel model3 = new DefaultComboBoxModel(vSubject3);
            subject3ComboBox.setModel(model3);

            DefaultComboBoxModel model4 = new DefaultComboBoxModel(vSubject4);
            subject4ComboBox.setModel(model4);

            DefaultComboBoxModel model5 = new DefaultComboBoxModel(vSubject5);
            subject5ComboBox.setModel(model5);

            DefaultComboBoxModel model6 = new DefaultComboBoxModel(vSubject6);
            subject6ComboBox.setModel(model6);

            DefaultComboBoxModel model7 = new DefaultComboBoxModel(vSubject7);
            subject7ComboBox.setModel(model7);

            DefaultComboBoxModel model8 = new DefaultComboBoxModel(vSubject8);
            subject8ComboBox.setModel(model8);

            DefaultComboBoxModel model9 = new DefaultComboBoxModel(vSubject9);
            subject9ComboBox.setModel(model9);

            DefaultComboBoxModel modelTeacher = new DefaultComboBoxModel(vSubject1);
            jComboBoxTeacherSubject.setModel(modelTeacher);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupStudentRegistration = new javax.swing.ButtonGroup();
        buttonGroupTeacherRegistration = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        studentRegistration = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        firstNameFieldStudentRegistration = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lastNameFieldStudentRegistration = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        mobileFieldStudentRegistration = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        genderMaleStudentRegistrationRadioButton = new javax.swing.JRadioButton();
        genderFemaleStudentRegistrationRadioButton = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        emailFieldStudentRegistration = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateChooserStudentRegistration = new com.toedter.calendar.JDateChooser();
        subject7ComboBox = new javax.swing.JComboBox<>();
        subject3ComboBox = new javax.swing.JComboBox<>();
        subject1ComboBox = new javax.swing.JComboBox<>();
        subject4ComboBox = new javax.swing.JComboBox<>();
        subject2ComboBox = new javax.swing.JComboBox<>();
        subject5ComboBox = new javax.swing.JComboBox<>();
        subject8ComboBox = new javax.swing.JComboBox<>();
        subject6ComboBox = new javax.swing.JComboBox<>();
        subject9ComboBox = new javax.swing.JComboBox<>();
        registerBtnStudentRegistration = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentRegistrationTable = new javax.swing.JTable();
        addressFieldStudentRegistration = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        teacherRegistration = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        teacherNameSearchResult = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        addressFieldTeacherRegistration = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        firstNameFieldTeacherRegistration = new javax.swing.JTextField();
        lastNameFieldTeacherRegistration = new javax.swing.JTextField();
        mobileFieldTeacherRegistration = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxTeacherSubject = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        registerBtnTeacherRegistration = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        teacherNameSearch = new javax.swing.JTextField();
        searchTeacherBtn = new javax.swing.JButton();
        teacherMobileSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        teacherRegistrationTable = new javax.swing.JTable();
        updateBtnTeacherRegistration = new javax.swing.JButton();
        emailFieldTeacherRegistration = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        teacherMobileSearchResult = new javax.swing.JTextField();
        teacherSubjectNameSearchResult = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        teacherEmailSearchResult = new javax.swing.JTextField();
        teacherDetailsDeleteBtn = new javax.swing.JButton();
        teacherAddressSearchResult = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        classesSubjects = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        subjectDetailTable = new javax.swing.JTable();
        addSubjectDashboardBtn = new javax.swing.JButton();
        editSubjectDashboardBtn = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        addClassDashboardBtn = new javax.swing.JButton();
        editClassDashboardBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        classDetailTable = new javax.swing.JTable();
        studentSearch = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        dashboardSearchStudentBtn = new javax.swing.JButton();
        studentMobileSearchField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentSearchResultsTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        studentSearchNameViewer = new javax.swing.JTextField();
        studentSearchMobileViewer = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        studentSearchEmailViewer = new javax.swing.JTextField();
        dashboardPaymentBtn = new javax.swing.JButton();
        dashboardAttendanceBtn = new javax.swing.JButton();
        dashboardStudentEditBtn = new javax.swing.JButton();
        dashboardStudentDeleteBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dashboardStudentsCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dashboardTeachersCount = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        settingButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/speedometer.png"))); // NOI18N
        jLabel1.setText("Dashboard");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 153), 1, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shutdown.png"))); // NOI18N
        jLabel3.setText(" Logout");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel9.setText("Student Registration");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("First Name  ");

        firstNameFieldStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        firstNameFieldStudentRegistration.setToolTipText("First Name");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Last Name  ");

        lastNameFieldStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lastNameFieldStudentRegistration.setToolTipText("Last Name");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Mobile   ");

        mobileFieldStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mobileFieldStudentRegistration.setToolTipText("Mobile");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Address");

        buttonGroupStudentRegistration.add(genderMaleStudentRegistrationRadioButton);
        genderMaleStudentRegistrationRadioButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        genderMaleStudentRegistrationRadioButton.setText("Male");
        genderMaleStudentRegistrationRadioButton.setActionCommand("1");
        genderMaleStudentRegistrationRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderMaleStudentRegistrationRadioButtonActionPerformed(evt);
            }
        });

        buttonGroupStudentRegistration.add(genderFemaleStudentRegistrationRadioButton);
        genderFemaleStudentRegistrationRadioButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        genderFemaleStudentRegistrationRadioButton.setText("Female");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Subjects :-");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Email ");

        emailFieldStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emailFieldStudentRegistration.setToolTipText("Email");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Gender  :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Date of Birth ");

        dateChooserStudentRegistration.setForeground(new java.awt.Color(255, 255, 255));
        dateChooserStudentRegistration.setToolTipText("Date of Birth");
        dateChooserStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        subject7ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject3ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject4ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject5ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject8ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject6ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        subject9ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        registerBtnStudentRegistration.setBackground(new java.awt.Color(255, 51, 0));
        registerBtnStudentRegistration.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        registerBtnStudentRegistration.setForeground(new java.awt.Color(255, 255, 153));
        registerBtnStudentRegistration.setText("Register");
        registerBtnStudentRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtnStudentRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnStudentRegistrationActionPerformed(evt);
            }
        });

        studentRegistrationTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        studentRegistrationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Mobile", "DOB", "Email", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentRegistrationTable.setRowMargin(2);
        jScrollPane1.setViewportView(studentRegistrationTable);

        addressFieldStudentRegistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressFieldStudentRegistration.setToolTipText("Address");
        addressFieldStudentRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldStudentRegistrationActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Last Records Added to the Database");

        javax.swing.GroupLayout studentRegistrationLayout = new javax.swing.GroupLayout(studentRegistration);
        studentRegistration.setLayout(studentRegistrationLayout);
        studentRegistrationLayout.setHorizontalGroup(
            studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentRegistrationLayout.createSequentialGroup()
                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailFieldStudentRegistration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(genderMaleStudentRegistrationRadioButton)
                                            .addComponent(genderFemaleStudentRegistrationRadioButton)))
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(registerBtnStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(addressFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel9))
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(firstNameFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(subject1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(subject2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(subject3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lastNameFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184)
                                .addComponent(subject4ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(subject5ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(subject6ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mobileFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooserStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(subject7ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(subject8ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(subject9ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentRegistrationLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        studentRegistrationLayout.setVerticalGroup(
            studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentRegistrationLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addGap(34, 34, 34)
                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(firstNameFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(subject1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(subject2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(subject3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lastNameFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(subject4ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(subject5ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(subject6ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addComponent(mobileFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(dateChooserStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subject7ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject8ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject9ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressFieldStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(studentRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(82, 82, 82))
                            .addGroup(studentRegistrationLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(genderMaleStudentRegistrationRadioButton)
                                .addGap(5, 5, 5)
                                .addComponent(genderFemaleStudentRegistrationRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(registerBtnStudentRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(studentRegistrationLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        genderFemaleStudentRegistrationRadioButton.setActionCommand("2");

        jTabbedPane1.addTab("Student Registration", studentRegistration);

        teacherRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherRegistrationMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel15.setText("Teacher Registration & Enrolment");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("First Name  ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Last Name  ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Mobile   ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Email ");

        teacherNameSearchResult.setToolTipText("Teacher Name");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Address");

        addressFieldTeacherRegistration.setToolTipText("Address");
        addressFieldTeacherRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldTeacherRegistrationActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Gender  :");

        buttonGroupTeacherRegistration.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRadioButton3.setText("Male");
        jRadioButton3.setActionCommand("1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroupTeacherRegistration.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRadioButton4.setText("Female");
        jRadioButton4.setActionCommand("2");

        firstNameFieldTeacherRegistration.setToolTipText("First Name");

        lastNameFieldTeacherRegistration.setToolTipText("Last Name");

        mobileFieldTeacherRegistration.setToolTipText("Mobile");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Subject  :");

        jComboBoxTeacherSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        registerBtnTeacherRegistration.setBackground(new java.awt.Color(255, 51, 0));
        registerBtnTeacherRegistration.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        registerBtnTeacherRegistration.setForeground(new java.awt.Color(255, 255, 153));
        registerBtnTeacherRegistration.setText("Register");
        registerBtnTeacherRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtnTeacherRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnTeacherRegistrationActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        teacherNameSearch.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        teacherNameSearch.setForeground(new java.awt.Color(102, 102, 102));
        teacherNameSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherNameSearch.setText("Enter Name");
        teacherNameSearch.setToolTipText("Enter Name");
        teacherNameSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                teacherNameSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                teacherNameSearchFocusLost(evt);
            }
        });

        searchTeacherBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchTeacherBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        searchTeacherBtn.setText("Search");
        searchTeacherBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTeacherBtnActionPerformed(evt);
            }
        });

        teacherMobileSearch.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        teacherMobileSearch.setForeground(new java.awt.Color(102, 102, 102));
        teacherMobileSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherMobileSearch.setText("Enter Mobile");
        teacherMobileSearch.setToolTipText("Enter Mobile");
        teacherMobileSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                teacherMobileSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                teacherMobileSearchFocusLost(evt);
            }
        });
        teacherMobileSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teacherMobileSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(teacherNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teacherMobileSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchTeacherBtn)
                    .addComponent(teacherNameSearch)
                    .addComponent(teacherMobileSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        teacherRegistrationTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        teacherRegistrationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Name", "Mobile", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherRegistrationTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                teacherRegistrationTableMouseMoved(evt);
            }
        });
        teacherRegistrationTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                teacherRegistrationTableFocusGained(evt);
            }
        });
        teacherRegistrationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherRegistrationTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                teacherRegistrationTableMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(teacherRegistrationTable);

        updateBtnTeacherRegistration.setBackground(new java.awt.Color(0, 102, 0));
        updateBtnTeacherRegistration.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        updateBtnTeacherRegistration.setText("Update");
        updateBtnTeacherRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBtnTeacherRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnTeacherRegistrationActionPerformed(evt);
            }
        });

        emailFieldTeacherRegistration.setToolTipText("Email");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Teacher Name");

        teacherMobileSearchResult.setToolTipText("Teacher Mobile");

        teacherSubjectNameSearchResult.setToolTipText("Asigned Subject");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Subject");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Address");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Email");

        teacherEmailSearchResult.setToolTipText("Email");

        teacherDetailsDeleteBtn.setBackground(new java.awt.Color(102, 0, 51));
        teacherDetailsDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        teacherDetailsDeleteBtn.setForeground(new java.awt.Color(255, 204, 204));
        teacherDetailsDeleteBtn.setText("Delete");
        teacherDetailsDeleteBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        teacherDetailsDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        teacherDetailsDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherDetailsDeleteBtnActionPerformed(evt);
            }
        });

        teacherAddressSearchResult.setToolTipText("Address");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Mobile");

        javax.swing.GroupLayout teacherRegistrationLayout = new javax.swing.GroupLayout(teacherRegistration);
        teacherRegistration.setLayout(teacherRegistrationLayout);
        teacherRegistrationLayout.setHorizontalGroup(
            teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel15))
            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(firstNameFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lastNameFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(mobileFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jComboBoxTeacherSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(registerBtnTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(teacherNameSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(teacherMobileSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(teacherSubjectNameSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(teacherEmailSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(teacherAddressSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(updateBtnTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(teacherDetailsDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        teacherRegistrationLayout.setVerticalGroup(
            teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel15)
                .addGap(7, 7, 7)
                .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(firstNameFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lastNameFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mobileFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(emailFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel24)
                        .addGap(7, 7, 7)
                        .addComponent(addressFieldTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25))
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addGap(5, 5, 5)
                                .addComponent(jRadioButton4)))
                        .addGap(18, 18, 18)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(teacherRegistrationLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jComboBoxTeacherSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addComponent(registerBtnTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(teacherRegistrationLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherNameSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherMobileSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherSubjectNameSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherEmailSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherAddressSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(teacherRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBtnTeacherRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherDetailsDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jTabbedPane1.addTab("Teacher Registration", teacherRegistration);

        jLabel28.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Class Registration");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Subject Registration");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        subjectDetailTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        subjectDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Subject", "Description", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subjectDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subjectDetailTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(subjectDetailTable);

        addSubjectDashboardBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addSubjectDashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        addSubjectDashboardBtn.setText("  Add Subject");
        addSubjectDashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSubjectDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectDashboardBtnActionPerformed(evt);
            }
        });

        editSubjectDashboardBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        editSubjectDashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        editSubjectDashboardBtn.setText("  Edit Subject");
        editSubjectDashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSubjectDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSubjectDashboardBtnActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Subjects");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Classes");

        addClassDashboardBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addClassDashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        addClassDashboardBtn.setText("  Add Class");
        addClassDashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addClassDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassDashboardBtnActionPerformed(evt);
            }
        });

        editClassDashboardBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        editClassDashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        editClassDashboardBtn.setText("  Edit Class");
        editClassDashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editClassDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClassDashboardBtnActionPerformed(evt);
            }
        });

        classDetailTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        classDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "classno", "Subject", "Teacher", "Time Slot", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                classDetailTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(classDetailTable);

        javax.swing.GroupLayout classesSubjectsLayout = new javax.swing.GroupLayout(classesSubjects);
        classesSubjects.setLayout(classesSubjectsLayout);
        classesSubjectsLayout.setHorizontalGroup(
            classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classesSubjectsLayout.createSequentialGroup()
                .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addGroup(classesSubjectsLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(addClassDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(editClassDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, classesSubjectsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(classesSubjectsLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel34))
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(addSubjectDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(editSubjectDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane4))))
        );
        classesSubjectsLayout.setVerticalGroup(
            classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classesSubjectsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addClassDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editClassDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(classesSubjectsLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(classesSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSubjectDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editSubjectDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jTabbedPane1.addTab("Classes & Subjects", classesSubjects);

        studentSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentSearchMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel33.setText("Student Search");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        dashboardSearchStudentBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dashboardSearchStudentBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        dashboardSearchStudentBtn.setText("Search");
        dashboardSearchStudentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardSearchStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardSearchStudentBtnActionPerformed(evt);
            }
        });

        studentMobileSearchField.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        studentMobileSearchField.setForeground(new java.awt.Color(102, 102, 102));
        studentMobileSearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        studentMobileSearchField.setText("Enter Mobile");
        studentMobileSearchField.setToolTipText("Enter Mobile");
        studentMobileSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentMobileSearchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentMobileSearchFieldFocusLost(evt);
            }
        });
        studentMobileSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentMobileSearchFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentMobileSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dashboardSearchStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dashboardSearchStudentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentMobileSearchField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        studentSearchResultsTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentSearchResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Student Name", "Mobile", "Email", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentSearchResultsTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                studentSearchResultsTableMouseMoved(evt);
            }
        });
        studentSearchResultsTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentSearchResultsTableFocusGained(evt);
            }
        });
        studentSearchResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentSearchResultsTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentSearchResultsTableMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(studentSearchResultsTable);

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Student Name  :");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Mobile        :");

        studentSearchNameViewer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentSearchNameViewer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        studentSearchNameViewer.setToolTipText("Student Name");

        studentSearchMobileViewer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentSearchMobileViewer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        studentSearchMobileViewer.setToolTipText("Student Mobile");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Email         :");

        studentSearchEmailViewer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentSearchEmailViewer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        studentSearchEmailViewer.setToolTipText("Student Email");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentSearchNameViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentSearchMobileViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentSearchEmailViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentSearchNameViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentSearchMobileViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentSearchEmailViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        dashboardPaymentBtn.setBackground(new java.awt.Color(102, 0, 102));
        dashboardPaymentBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dashboardPaymentBtn.setText("Payments");
        dashboardPaymentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardPaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardPaymentBtnActionPerformed(evt);
            }
        });

        dashboardAttendanceBtn.setBackground(new java.awt.Color(102, 0, 0));
        dashboardAttendanceBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dashboardAttendanceBtn.setText("Attendance");
        dashboardAttendanceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardAttendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardAttendanceBtnActionPerformed(evt);
            }
        });

        dashboardStudentEditBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dashboardStudentEditBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        dashboardStudentEditBtn.setText("Edit");
        dashboardStudentEditBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardStudentEditBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dashboardStudentEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardStudentEditBtnActionPerformed(evt);
            }
        });

        dashboardStudentDeleteBtn.setBackground(new java.awt.Color(204, 0, 0));
        dashboardStudentDeleteBtn.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        dashboardStudentDeleteBtn.setForeground(new java.awt.Color(255, 255, 153));
        dashboardStudentDeleteBtn.setText("Delete Record");
        dashboardStudentDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout studentSearchLayout = new javax.swing.GroupLayout(studentSearch);
        studentSearch.setLayout(studentSearchLayout);
        studentSearchLayout.setHorizontalGroup(
            studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentSearchLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardPaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboardAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentSearchLayout.createSequentialGroup()
                        .addComponent(dashboardStudentDeleteBtn)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentSearchLayout.createSequentialGroup()
                        .addComponent(dashboardStudentEditBtn)
                        .addGap(36, 36, 36))))
            .addGroup(studentSearchLayout.createSequentialGroup()
                .addGroup(studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentSearchLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel33))
                    .addGroup(studentSearchLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        studentSearchLayout.setVerticalGroup(
            studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentSearchLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel33)
                .addGap(13, 13, 13)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentSearchLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(studentSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(studentSearchLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(dashboardPaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(dashboardAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(studentSearchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashboardStudentDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(dashboardStudentEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Student Search", studentSearch);

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.gray));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/graduating-student.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 28, -1, -1));

        dashboardStudentsCount.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        dashboardStudentsCount.setForeground(new java.awt.Color(255, 255, 51));
        dashboardStudentsCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(dashboardStudentsCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 28, 130, 70));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Students");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 99, 205, 61));

        jPanel5.setBackground(new java.awt.Color(51, 0, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.gray));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/teacher.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 26, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Teachers");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 97, 204, 61));

        dashboardTeachersCount.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        dashboardTeachersCount.setForeground(new java.awt.Color(255, 255, 51));
        dashboardTeachersCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(dashboardTeachersCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 26, 130, 70));

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ADYAPANA-Logo.png"))); // NOI18N
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        settingButton.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        settingButton.setForeground(new java.awt.Color(255, 255, 51));
        settingButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        settingButton.setText("Settings");
        settingButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        settingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(settingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(81, 81, 81)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        int logout = JOptionPane.showConfirmDialog(null, "Do You Want to Logout ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (logout == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_jLabel3MouseClicked

    private void genderMaleStudentRegistrationRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderMaleStudentRegistrationRadioButtonActionPerformed
        
    }//GEN-LAST:event_genderMaleStudentRegistrationRadioButtonActionPerformed

    private void registerBtnStudentRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnStudentRegistrationActionPerformed
        // TODO add your handling code here:

        String firstname = firstNameFieldStudentRegistration.getText();
        String lastname = lastNameFieldStudentRegistration.getText();
        String mobile = mobileFieldStudentRegistration.getText();
        String email = emailFieldStudentRegistration.getText();
        String address = addressFieldStudentRegistration.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String subject1 = String.valueOf(subject1ComboBox.getSelectedItem());
        String subject2 = String.valueOf(subject2ComboBox.getSelectedItem());
        String subject3 = String.valueOf(subject3ComboBox.getSelectedItem());
        String subject4 = String.valueOf(subject4ComboBox.getSelectedItem());
        String subject5 = String.valueOf(subject5ComboBox.getSelectedItem());
        String subject6 = String.valueOf(subject6ComboBox.getSelectedItem());
        String subject7 = String.valueOf(subject7ComboBox.getSelectedItem());
        String subject8 = String.valueOf(subject8ComboBox.getSelectedItem());
        String subject9 = String.valueOf(subject9ComboBox.getSelectedItem());

        ButtonModel genderSelection = buttonGroupStudentRegistration.getSelection();

        if (firstname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Students First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Students Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Students Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (dateChooserStudentRegistration.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Select Students Date of Birth", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Students Email Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Invalid Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Students Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (genderSelection == null) {
            JOptionPane.showMessageDialog(this, "Please Select Students Gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subject1.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String genderId = genderSelection.getActionCommand();

            int subject1Id = subject1Map.get(subject1);
//            int subject2Id = subject2Map.get(subject2);
//            int subject3Id = subject3Map.get(subject3);
//            int subject4Id = subject4Map.get(subject4);
//            int subject5Id = subject5Map.get(subject5);
//            int subject6Id = subject6Map.get(subject6);
//            int subject7Id = subject7Map.get(subject7);
//            int subject8Id = subject8Map.get(subject8);
//            int subject9Id = subject9Map.get(subject9);

            try {

                String dob = dateFormat.format(dateChooserStudentRegistration.getDate());

                MySQL.execute("INSERT INTO"
                        + "`student` (`firstName`,`lastName`,`mobile`,`address`,`gender_id`,`email`,`dob`)"
                        + "VALUES('" + firstname + "','" + lastname + "','" + mobile + "','" + address + "'," + genderId + ",'" + email + "','" + dob + "')");
                ResultSet resultset = MySQL.execute("SELECT MAX(`sno`) as last_sno from `student`");

                while (resultset.next()) {
                    int lastSno = resultset.getInt("last_sno");

                    MySQL.execute("INSERT INTO `student_has_subject` (`student_sno`,`subject_subno`)"
                            + "VALUES('" + lastSno + "','" + subject1Id + "')");
                }
                loadStudents();
                loadSubjects();
                countStudents();
                countTeachers();
                reset();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_registerBtnStudentRegistrationActionPerformed

    private void addressFieldStudentRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldStudentRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldStudentRegistrationActionPerformed

    private void addressFieldTeacherRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldTeacherRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldTeacherRegistrationActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void registerBtnTeacherRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnTeacherRegistrationActionPerformed
        // TODO add your handling code here:
        loadTeachers();
        String firstname = firstNameFieldTeacherRegistration.getText();
        String lastname = lastNameFieldTeacherRegistration.getText();
        String mobile = mobileFieldTeacherRegistration.getText();
        String email = emailFieldTeacherRegistration.getText();
        String address = addressFieldTeacherRegistration.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String teachersubject = String.valueOf(jComboBoxTeacherSubject.getSelectedItem());

        ButtonModel genderSelection = buttonGroupTeacherRegistration.getSelection();

        if (firstname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Teacher First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Teacher Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Teacher Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Teacher Email Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Invalid Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Teacher Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (genderSelection == null) {
            JOptionPane.showMessageDialog(this, "Please Select Teacher Gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (teachersubject.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            loadTeachers();
                countTeachers();
                reset();
                
                JOptionPane.showMessageDialog(this, "Successfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);

            String genderId = genderSelection.getActionCommand();

            int subjectTeacher = subjectTeacherMap.get(teachersubject);
            try {
                MySQL.execute("INSERT INTO"
                        + "`teacher` (`firstName`,`lastName`,`mobile`,`address`,`gender_id`,`email`,`subject_id`)"
                        + "VALUES('" + firstname + "','" + lastname + "','" + mobile + "','" + address + "'," + genderId + ",'" + email + "','" + subjectTeacher + "')");

                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_registerBtnTeacherRegistrationActionPerformed

    private void updateBtnTeacherRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnTeacherRegistrationActionPerformed
        // TODO add your handling code here:

        int selectedRow = teacherRegistrationTable.getSelectedRow();

        String teacherNameCheck = teacherNameSearchResult.getText();
        String teacherMobileCheck = teacherMobileSearchResult.getText();
        String teacherSubjectCheck = teacherSubjectNameSearchResult.getText();
        String teacherEmailCheck = teacherEmailSearchResult.getText();
        String teacherAddressCheck = teacherAddressSearchResult.getText();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Double Click on a Row to Select it", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (teacherNameCheck.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Name Field Cannot Be Empty", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (teacherSubjectCheck.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Subject Field Cannot Be Empty", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (teacherMobileCheck.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Mobile Field Cannot Be Empty", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (teacherEmailCheck.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Email Field Cannot Be Empty", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (teacherAddressCheck.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Address Field Cannot Be Empty", "Error", JOptionPane.WARNING_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(this, "Successfully Updated", "Updated", JOptionPane.INFORMATION_MESSAGE);

            try {

                MySQL.execute("UPDATE `teacher` SET `mobile`='" + teacherMobileCheck + "',"
                        + "`email`='" + teacherEmailCheck + "',"
                        + "`address`='" + teacherAddressCheck + "' WHERE `tno`='" + teacherId + "' ");
                countTeachers();
                loadTeachers();
                reset();

                registerBtnTeacherRegistration.setEnabled(true);
                teacherRegistrationTable.setEnabled(true);
                teacherNameSearchResult.setEnabled(false);
                teacherSubjectNameSearchResult.setEnabled(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_updateBtnTeacherRegistrationActionPerformed

    private void teacherDetailsDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherDetailsDeleteBtnActionPerformed
        // TODO add your handling code here:

        int selectedRow = teacherRegistrationTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Double Click on a Row to Select it", "Warning", JOptionPane.ERROR_MESSAGE);

        } else {

            String teacherNameCheck = teacherNameSearchResult.getText();
            String teacherMobileCheck = teacherSubjectNameSearchResult.getText();
            String teacherSubjectCheck = teacherMobileSearchResult.getText();
            String teacherEmailCheck = teacherEmailSearchResult.getText();
            String teacherAddressCheck = teacherAddressSearchResult.getText();

            try {

                MySQL.execute("DELETE FROM `teacher` "
                        + "WHERE `tno`='" + teacherId + "'");

                countTeachers();
                loadTeachers();
                reset();

                JOptionPane.showMessageDialog(this, "Selected Record is Deleted", "Warning", JOptionPane.WARNING_MESSAGE);

                registerBtnTeacherRegistration.setEnabled(true);
                teacherRegistrationTable.setEnabled(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_teacherDetailsDeleteBtnActionPerformed

    private void teacherNameSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teacherNameSearchFocusGained
        // TODO add your handling code here:

        if (teacherNameSearch.getText().equals("Enter Name")) {
            teacherNameSearch.setText("");
            teacherNameSearch.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_teacherNameSearchFocusGained

    private void teacherNameSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teacherNameSearchFocusLost
        // TODO add your handling code here:

        if (teacherNameSearch.getText().equals("")) {
            teacherNameSearch.setText("Enter Name");
            teacherNameSearch.setForeground(new Color(102, 102, 102));
            teacherNameSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (teacherNameSearch.getText().equals("Enter Name")) {
            teacherNameSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
            teacherNameSearch.setForeground(new Color(102, 102, 102));
        } else {
            teacherNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
            teacherNameSearch.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_teacherNameSearchFocusLost

    private void teacherMobileSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teacherMobileSearchFocusGained
        // TODO add your handling code here:

        if (teacherMobileSearch.getText().equals("Enter Mobile")) {
            teacherMobileSearch.setText("");
            teacherMobileSearch.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_teacherMobileSearchFocusGained

    private void teacherMobileSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teacherMobileSearchFocusLost
        // TODO add your handling code here:

        if (teacherMobileSearch.getText().equals("")) {
            teacherMobileSearch.setText("Enter Mobile");
            teacherMobileSearch.setForeground(new Color(102, 102, 102));
            teacherMobileSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (teacherMobileSearch.getText().equals("Enter Mobile")) {
            teacherMobileSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
            teacherMobileSearch.setForeground(new Color(102, 102, 102));
        } else {
            teacherMobileSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
            teacherMobileSearch.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_teacherMobileSearchFocusLost

    private void teacherRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherRegistrationMouseClicked
        // TODO add your handling code here:

        if (teacherMobileSearch.getText().equals("")) {
            teacherMobileSearch.setText("Enter Mobile");
            teacherMobileSearch.setForeground(new Color(102, 102, 102));
            teacherMobileSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (teacherMobileSearch.getText().equals("Enter Mobile")) {
            teacherMobileSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
            teacherMobileSearch.setForeground(new Color(102, 102, 102));
        } else {
            teacherMobileSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
            teacherMobileSearch.setForeground(new Color(255, 255, 255));
        }
        if (teacherNameSearch.getText().equals("")) {
            teacherNameSearch.setText("Enter Name");
            teacherNameSearch.setForeground(new Color(102, 102, 102));
            teacherNameSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (teacherNameSearch.getText().equals("Enter Name")) {
            teacherNameSearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
            teacherNameSearch.setForeground(new Color(102, 102, 102));
        } else {
            teacherNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
            teacherNameSearch.setForeground(new Color(255, 255, 255));
        }


    }//GEN-LAST:event_teacherRegistrationMouseClicked


    private void teacherRegistrationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherRegistrationTableMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {

            teacherRegistrationTable.setEnabled(false);
            registerBtnTeacherRegistration.setEnabled(false);
            teacherNameSearchResult.setEnabled(false);
            teacherSubjectNameSearchResult.setEnabled(false);

            int selectedRow = teacherRegistrationTable.getSelectedRow();

            String TeacherName = String.valueOf(teacherRegistrationTable.getValueAt(selectedRow, 0));
            teacherNameSearchResult.setText(TeacherName);

            String mobile = String.valueOf(teacherRegistrationTable.getValueAt(selectedRow, 1));
            teacherMobileSearchResult.setText(mobile);

            String subject = String.valueOf(teacherRegistrationTable.getValueAt(selectedRow, 2));
            teacherSubjectNameSearchResult.setText(subject);

            try {
                ResultSet resultset = MySQL.execute("SELECT * FROM `teacher` WHERE `mobile`='" + mobile + "' ");

                if (resultset.next()) {

                    teacherId = resultset.getInt("tno");
                    String email = resultset.getString("email");
                    String address = resultset.getString("address");
                    teacherEmailSearchResult.setText(email);
                    teacherAddressSearchResult.setText(address);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_teacherRegistrationTableMouseClicked

    private void teacherRegistrationTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherRegistrationTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherRegistrationTableMouseEntered

    private void teacherRegistrationTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherRegistrationTableMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherRegistrationTableMouseMoved

    private void teacherRegistrationTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teacherRegistrationTableFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherRegistrationTableFocusGained

    private void studentMobileSearchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentMobileSearchFieldFocusGained
        // TODO add your handling code here:

        if (studentMobileSearchField.getText().equals("Enter Mobile")) {
            studentMobileSearchField.setText("");
            studentMobileSearchField.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_studentMobileSearchFieldFocusGained

    private void studentMobileSearchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentMobileSearchFieldFocusLost
        // TODO add your handling code here:

        if (studentMobileSearchField.getText().equals("")) {
            studentMobileSearchField.setText("Enter Mobile");
            studentMobileSearchField.setForeground(new Color(102, 102, 102));
            studentMobileSearchField.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (studentMobileSearchField.getText().equals("Enter Mobile")) {
            studentMobileSearchField.setForeground(new Color(102, 102, 102));
            studentMobileSearchField.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else {
            studentMobileSearchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
            studentMobileSearchField.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_studentMobileSearchFieldFocusLost

    private void studentSearchResultsTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchResultsTableMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_studentSearchResultsTableMouseMoved

    private void studentSearchResultsTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentSearchResultsTableFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_studentSearchResultsTableFocusGained

    private void studentSearchResultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchResultsTableMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {

            studentSearchResultsTable.setEnabled(false);

            int selectedRow = studentSearchResultsTable.getSelectedRow();

            String studentId = String.valueOf(studentSearchResultsTable.getValueAt(selectedRow, 0));
            String studentName = String.valueOf(studentSearchResultsTable.getValueAt(selectedRow, 1));
            String studentMobile = String.valueOf(studentSearchResultsTable.getValueAt(selectedRow, 2));
            String studentEmail = String.valueOf(studentSearchResultsTable.getValueAt(selectedRow, 3));
            String studentAddress = String.valueOf(studentSearchResultsTable.getValueAt(selectedRow, 4));

            studentSearchNameViewer.setText(studentName);
            studentSearchMobileViewer.setText(studentMobile);
            studentSearchEmailViewer.setText(studentEmail);

            try {
                ResultSet resultset = MySQL.execute("SELECT * FROM `student` WHERE `sno`='" + studentId + "' ");

                if (resultset.next()) {
                    HashMap<String, String> map3 = new HashMap<>();
                    map3.put("studentSId", resultset.getString("sno"));
                    map3.put("studentFirstName", resultset.getString("firstName"));
                    map3.put("studentLastName", resultset.getString("lastName"));
                    map3.put("studentMobile", resultset.getString("mobile"));
                    map3.put("studentBirthday", resultset.getString("dob"));
                    map3.put("studentAddress", resultset.getString("address"));
                    map3.put("studentEmail", resultset.getString("email"));

                    editStudentDetails studentDetailUpdater = new editStudentDetails(map3);
                    studentDetailUpdater.setVisible(true);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_studentSearchResultsTableMouseClicked

    private void studentSearchResultsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchResultsTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_studentSearchResultsTableMouseEntered

    private void studentSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchMouseClicked
        // TODO add your handling code here:

        if (studentMobileSearchField.getText().equals("")) {
            studentMobileSearchField.setText("Enter Mobile");
            studentMobileSearchField.setForeground(new Color(102, 102, 102));
            studentMobileSearchField.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else if (studentMobileSearchField.getText().equals("Enter Mobile")) {
            studentMobileSearchField.setForeground(new Color(102, 102, 102));
            studentMobileSearchField.setFont(new Font("Tahoma", Font.ITALIC, 18));
        } else {
            studentMobileSearchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
            studentMobileSearchField.setForeground(new Color(255, 255, 255));
        }


    }//GEN-LAST:event_studentSearchMouseClicked

    private void addClassDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassDashboardBtnActionPerformed
        // TODO add your handling code here:

        addClass addClassWindow = new addClass();
        addClassWindow.setVisible(true);

    }//GEN-LAST:event_addClassDashboardBtnActionPerformed

    private void editClassDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editClassDashboardBtnActionPerformed
        // TODO add your handling code here:

        int selectedRow = classDetailTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Double Click on a Table Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (selectedRow == 1) {
            JOptionPane.showMessageDialog(this, "Double Click on Selected Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_editClassDashboardBtnActionPerformed

    private void addSubjectDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectDashboardBtnActionPerformed
        // TODO add your handling code here:

        addSubject addSubjectWindow = new addSubject();
        addSubjectWindow.setVisible(true);

    }//GEN-LAST:event_addSubjectDashboardBtnActionPerformed

    private void editSubjectDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSubjectDashboardBtnActionPerformed
        // TODO add your handling code here:

        int selectedRow = subjectDetailTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Double Click on a Table Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (selectedRow == 1) {
            JOptionPane.showMessageDialog(this, "Double Click on Selected Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_editSubjectDashboardBtnActionPerformed

    private void dashboardStudentEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardStudentEditBtnActionPerformed
        // TODO add your handling code here:

        int selectedRow = studentSearchResultsTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Double Click on a Table Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (selectedRow == 1) {
            JOptionPane.showMessageDialog(this, "Double Click on Selected Row to Edit", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_dashboardStudentEditBtnActionPerformed

    private void dashboardPaymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardPaymentBtnActionPerformed
        // TODO add your handling code here:

        payments paymentsWindow = new payments();
        paymentsWindow.setVisible(true);

    }//GEN-LAST:event_dashboardPaymentBtnActionPerformed

    private void dashboardAttendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardAttendanceBtnActionPerformed
        // TODO add your handling code here:

        attendance attendanceWindow = new attendance();
        attendanceWindow.setVisible(true);

    }//GEN-LAST:event_dashboardAttendanceBtnActionPerformed

    private void teacherMobileSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacherMobileSearchKeyReleased
        // TODO add your handling code here:

        String teacherMobileSearchTextField = teacherMobileSearch.getText();

        if (teacherMobileSearchTextField.length() == 10) {

            try {

                ResultSet resultset = MySQL.execute("SELECT * FROM `teacher`"
                        + "INNER JOIN `subject` ON `subject`.`subno`=`teacher`.`subject_id`"
                        + "WHERE `mobile`='" + teacherMobileSearchTextField + "' ");

                if (resultset.next()) {

                    String name = resultset.getString("firstName");
                    String subject = resultset.getString("subject.name");
                    String email = resultset.getString("email");
                    String address = resultset.getString("address");

                    teacherNameSearchResult.setText(name);
                    teacherMobileSearchResult.setText(teacherMobileSearchTextField);
                    teacherSubjectNameSearchResult.setText(subject);
                    teacherEmailSearchResult.setText(email);
                    teacherAddressSearchResult.setText(address);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            teacherMobileSearch.setText("Enter Mobile");
        }


    }//GEN-LAST:event_teacherMobileSearchKeyReleased

    private void searchTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTeacherBtnActionPerformed
        // TODO add your handling code here:

        String teacherNameSearchTextField = teacherNameSearch.getText();

        if (teacherNameSearchTextField.equals("Enter Name")) {
            JOptionPane.showMessageDialog(this, "Type Name to Start Searching", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultset = MySQL.execute("SELECT * FROM `teacher`"
                        + "INNER JOIN `subject` ON `subject`.`subno`=`teacher`.`subject_id`"
                        + "WHERE `firstName`='" + teacherNameSearchTextField + "' ");

                if (resultset.next()) {

                    String name = resultset.getString("firstName");
                    String subject = resultset.getString("subject.name");
                    String email = resultset.getString("email");
                    String address = resultset.getString("address");
                    String mobile = resultset.getString("mobile");

                    teacherNameSearchResult.setText(name);
                    teacherMobileSearchResult.setText(mobile);
                    teacherSubjectNameSearchResult.setText(subject);
                    teacherEmailSearchResult.setText(email);
                    teacherAddressSearchResult.setText(address);

                } else {
                    JOptionPane.showMessageDialog(this, "No Records According to the Search Name", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                reset();
                loadTeachers();
                teacherNameSearchResult.setText("");
                teacherMobileSearchResult.setText("");
                teacherSubjectNameSearchResult.setText("");
                teacherEmailSearchResult.setText("");
                teacherAddressSearchResult.setText("");
                teacherNameSearch.setText("Enter Name");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_searchTeacherBtnActionPerformed

    private void classDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classDetailTableMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {

            classDetailTable.setEnabled(false);

            int selectedRow = classDetailTable.getSelectedRow();

            String classNo = String.valueOf(classDetailTable.getValueAt(selectedRow, 0));
            String subject = String.valueOf(classDetailTable.getValueAt(selectedRow, 1));
            String teacher = String.valueOf(classDetailTable.getValueAt(selectedRow, 2));
            String timeslot = String.valueOf(classDetailTable.getValueAt(selectedRow, 3));
            String description = String.valueOf(classDetailTable.getValueAt(selectedRow, 4));

            try {
                ResultSet resultset = MySQL.execute("SELECT * FROM `class` INNER JOIN `teacher` ON `class`.`teacher_tno`=`teacher`.`tno` INNER JOIN `subject` ON `class`.`subject_subno`=`subject`.`subno` WHERE `clssno`='" + classNo + "' ");

                if (resultset.next()) {

                    //
                    HashMap<String, String> map = new HashMap<>();
                    map.put("classNo", resultset.getString("clssno"));
                    map.put("subject", resultset.getString("subject.name"));
                    map.put("teacher", resultset.getString("teacher.firstName") + " " + resultset.getString("teacher.lastName"));
                    map.put("timeslot", resultset.getString("timeslot"));
                    map.put("description", resultset.getString("description"));

                    editClass editClass = new editClass(map);
                    editClass.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(this, "Invalid Details", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_classDetailTableMouseClicked

    private void subjectDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectDetailTableMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {

            subjectDetailTable.setEnabled(false);

            int selectedRow = subjectDetailTable.getSelectedRow();

            String subjectId = String.valueOf(subjectDetailTable.getValueAt(selectedRow, 0));
            String subject = String.valueOf(subjectDetailTable.getValueAt(selectedRow, 1));
            String description = String.valueOf(subjectDetailTable.getValueAt(selectedRow, 2));
            String price = String.valueOf(subjectDetailTable.getValueAt(selectedRow, 3));

            try {
                ResultSet resultset = MySQL.execute("SELECT * FROM `subject` WHERE `subno`='" + subjectId + "' ");

                if (resultset.next()) {

                    //
                    HashMap<String, String> map2 = new HashMap<>();
                    map2.put("subjectId", resultset.getString("subno"));
                    map2.put("subject", resultset.getString("name"));
                    map2.put("description", resultset.getString("description"));
                    map2.put("price", resultset.getString("price"));

//                   
                    editSubject editSubject = new editSubject(map2);
                    editSubject.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(this, "Invalid Details", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            subjectDetailTable.setEnabled(true);
        }

    }//GEN-LAST:event_subjectDetailTableMouseClicked

    private void studentMobileSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentMobileSearchFieldKeyReleased
        // TODO add your handling code here:

        // TODO add your handling code here:
        String mobile = studentMobileSearchField.getText();

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `student` WHERE `mobile` LIKE '" + mobile + "%' ");

            DefaultTableModel model = (DefaultTableModel) studentSearchResultsTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("firstName") + " " + resultset.getString("lastName"));
                v.add(resultset.getString("mobile"));
                v.add(resultset.getString("email"));
                v.add(resultset.getString("address"));

                model.addRow(v);
                studentSearchResultsTable.setModel(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_studentMobileSearchFieldKeyReleased

    private void dashboardSearchStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardSearchStudentBtnActionPerformed
        // TODO add your handling code here:

        String mobile = studentMobileSearchField.getText();

        try {

            ResultSet resultset = MySQL.execute("SELECT * FROM `student` WHERE `mobile` LIKE '" + mobile + "%' ");

            DefaultTableModel model = (DefaultTableModel) studentSearchResultsTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> v = new Vector();

                v.add(resultset.getString("firstName") + " " + resultset.getString("lastName"));
                v.add(resultset.getString("mobile"));
                v.add(resultset.getString("email"));
                v.add(resultset.getString("address"));

                model.addRow(v);
                studentSearchResultsTable.setModel(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_dashboardSearchStudentBtnActionPerformed

    private void settingButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingButtonMouseClicked
        // TODO add your handling code here:

        usernamePassword adminPage = new usernamePassword();
        adminPage.setVisible(true);


    }//GEN-LAST:event_settingButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton addClassDashboardBtn;
    public static javax.swing.JButton addSubjectDashboardBtn;
    private javax.swing.JTextField addressFieldStudentRegistration;
    private javax.swing.JTextField addressFieldTeacherRegistration;
    private javax.swing.ButtonGroup buttonGroupStudentRegistration;
    private javax.swing.ButtonGroup buttonGroupTeacherRegistration;
    public static javax.swing.JTable classDetailTable;
    public static javax.swing.JPanel classesSubjects;
    public static javax.swing.JButton dashboardAttendanceBtn;
    public static javax.swing.JButton dashboardPaymentBtn;
    public static javax.swing.JButton dashboardSearchStudentBtn;
    public static javax.swing.JButton dashboardStudentDeleteBtn;
    public static javax.swing.JButton dashboardStudentEditBtn;
    private javax.swing.JLabel dashboardStudentsCount;
    private javax.swing.JLabel dashboardTeachersCount;
    private com.toedter.calendar.JDateChooser dateChooserStudentRegistration;
    public static javax.swing.JButton editClassDashboardBtn;
    public static javax.swing.JButton editSubjectDashboardBtn;
    private javax.swing.JTextField emailFieldStudentRegistration;
    private javax.swing.JTextField emailFieldTeacherRegistration;
    private javax.swing.JTextField firstNameFieldStudentRegistration;
    private javax.swing.JTextField firstNameFieldTeacherRegistration;
    private javax.swing.JRadioButton genderFemaleStudentRegistrationRadioButton;
    private javax.swing.JRadioButton genderMaleStudentRegistrationRadioButton;
    private javax.swing.JComboBox<String> jComboBoxTeacherSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastNameFieldStudentRegistration;
    private javax.swing.JTextField lastNameFieldTeacherRegistration;
    private javax.swing.JTextField mobileFieldStudentRegistration;
    private javax.swing.JTextField mobileFieldTeacherRegistration;
    private javax.swing.JButton registerBtnStudentRegistration;
    private javax.swing.JButton registerBtnTeacherRegistration;
    private javax.swing.JButton searchTeacherBtn;
    private javax.swing.JLabel settingButton;
    private javax.swing.JTextField studentMobileSearchField;
    public static javax.swing.JPanel studentRegistration;
    private javax.swing.JTable studentRegistrationTable;
    public static javax.swing.JPanel studentSearch;
    private javax.swing.JTextField studentSearchEmailViewer;
    private javax.swing.JTextField studentSearchMobileViewer;
    private javax.swing.JTextField studentSearchNameViewer;
    private javax.swing.JTable studentSearchResultsTable;
    private javax.swing.JComboBox<String> subject1ComboBox;
    private javax.swing.JComboBox<String> subject2ComboBox;
    private javax.swing.JComboBox<String> subject3ComboBox;
    private javax.swing.JComboBox<String> subject4ComboBox;
    private javax.swing.JComboBox<String> subject5ComboBox;
    private javax.swing.JComboBox<String> subject6ComboBox;
    private javax.swing.JComboBox<String> subject7ComboBox;
    private javax.swing.JComboBox<String> subject8ComboBox;
    private javax.swing.JComboBox<String> subject9ComboBox;
    public static javax.swing.JTable subjectDetailTable;
    private javax.swing.JTextField teacherAddressSearchResult;
    private javax.swing.JButton teacherDetailsDeleteBtn;
    private javax.swing.JTextField teacherEmailSearchResult;
    private javax.swing.JTextField teacherMobileSearch;
    private javax.swing.JTextField teacherMobileSearchResult;
    private javax.swing.JTextField teacherNameSearch;
    private javax.swing.JTextField teacherNameSearchResult;
    public static javax.swing.JPanel teacherRegistration;
    private javax.swing.JTable teacherRegistrationTable;
    private javax.swing.JTextField teacherSubjectNameSearchResult;
    private javax.swing.JButton updateBtnTeacherRegistration;
    // End of variables declaration//GEN-END:variables
}
