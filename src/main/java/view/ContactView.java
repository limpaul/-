package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import db.ContactManager;
import dto.ContactVO;
import db.MemoryRepository;


public class ContactView extends JFrame implements ActionListener {
    JButton b,b1,b2,b3;

    JTextField nameText;
    JTextField ageText;
    JTextField callText;
    JTextField emailText;

    JTextField sn;//이름 텍스트
    JTextField dn;//이름 삭제

    JTextArea ar;//검색결과

    JRadioButton r1;
    JRadioButton r2;
    ButtonGroup bg;
    private MemoryRepository db = new MemoryRepository();
    public ContactView() {


        JPanel p1 = new JPanel(); //연락처 등록
        //p1.setBackground(Color.lightGray);
        p1.setBorder(new TitledBorder("연락처 등록"));




        JLabel Name=new JLabel("이름",SwingConstants.LEFT);
        nameText=new JTextField(10);

        JLabel Age=new JLabel("나이",SwingConstants.LEFT);
        ageText=new JTextField(10);

        JLabel Call=new JLabel("전화번호",SwingConstants.LEFT);
        callText=new JTextField(10);

        JLabel Gender =new JLabel("성별",SwingConstants.LEFT);
        r1=new JRadioButton("남");
        r2=new JRadioButton("여");
        bg=new ButtonGroup();//그룹으로 묶음
        bg.add(r1);bg.add(r2);

        JLabel Email=new JLabel("이메일",SwingConstants.LEFT);
        emailText=new JTextField(10);

        b = new JButton("등록");



        p1.setLayout(new GridLayout(3,3));
        p1.add(Name);p1.add(nameText);
        p1.add(Age);p1.add(ageText);
        p1.add(Call);p1.add(callText);
        p1.add(Email);p1.add(emailText);
        p1.add(Gender);
        p1.add(r1);p1.add(r2);p1.add(b);
        p1.add(b);


        //이름 검색
        JPanel p2=new JPanel();
        p2.setBorder(new TitledBorder("연락처 검색"));
        sn=new JTextField(10);
        p2.add(sn);
        b1 = new JButton("검색");
        p2.add(b1);
        b3 = new JButton("전체조회");
        p2.add(b3);


        JPanel p3=new JPanel();
        p3.setBorder(new TitledBorder("연락처 삭제"));
        dn=new JTextField(10);
        p3.add(dn);
        b2=new JButton("삭제");
        p3.add(b2);


        JPanel p4=new JPanel();
        p4.setBorder(new TitledBorder("연락처 검색 결과"));
        ar=new JTextArea(14,45);
        p4.add(ar);




        this.setTitle("연락처 관리프로그램 ver 1.0");
        this.setBounds(0,0,550,550);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.add(BorderLayout.NORTH,p1);//등록
        this.add(BorderLayout.WEST,p2);//검색
        this.add(BorderLayout.CENTER,p3);//삭제
        this.add(BorderLayout.SOUTH,p4);//전체 조회
        this.setVisible(true);

        b.addActionListener(this);//등록
        b1.addActionListener(this);//검색
        b2.addActionListener(this);//삭제
        b3.addActionListener(this);//전체조회
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //String cmd = e.getActionCommand();
        ContactManager m;
        Object o=e.getSource();
        String gender = null;
        String name=nameText.getText();
        String age=ageText.getText();
        String call=callText.getText();
        String email=emailText.getText();
        if(o==b) {//등록

            if(r1.isSelected()==true) gender="남";else gender="여";


            if(name.equals("")||age.equals("")||call.equals("")||email.equals("")||!r1.isSelected()&&!r2.isSelected()) {
                JOptionPane.showMessageDialog(null, "빈칸이 있으니 확인바랍니다 ");
            }else {

                ContactVO vo = new ContactVO(name, age, call, email, gender);
                /*
                Date: 2022.01.09
                Author: Bole
                */
                db.save(vo);

                JOptionPane.showMessageDialog(null, "등록됨");
            }
        }
        //////////////////////////////////////////////////////////////////
        if(o==b1) {//검색
            String sname=sn.getText();
            /*
                Date: 2022.01.09
                Author: Bole
             */
            List<ContactVO> memberByNames = db.findByName(sname);
            if(memberByNames.size() == 0){
                ar.setText("검색결과 없음");
            }else{
                ar.setText("검색결과:"+memberByNames.toString());
            }

        }
        /////////////////////////////////////////////////////////////
        if(o==b2) {//삭제
            String dname=dn.getText();
            /*
                Date: 2022.01.09
                Author: Baole
             */
            /* search all of info by using name */
            //List<ContactVO> removeEntities = db.findAll().stream().filter(it -> it.getName().equals(dname)).collect(Collectors.toList());
            List<ContactVO> removeEntities = db.findByName(dname);
            /* remove info */
            if(!removeEntities.isEmpty()){
                removeEntities.forEach(it -> db.deleteById(it.getId()));
            }else{
                System.out.println("해당 사용자는 존재하지 않습니다..");
            }
            ar.setText(db.findAll().toString());
        }
        ///////////////////////////////////////////////////////////////
        if(o==b3) {//전체조회
             /*
                Date: 2022.01.09
                Author: Bole
             */
            ar.setText(db.findAll().toString());

        }
    }

}