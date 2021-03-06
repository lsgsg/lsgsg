import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.io.*;
import java.net.*;
public class ChatClient_hc extends JFrame implements ActionListener, Runnable {
    private JLabel jLabel1 = new JLabel();
    private JTextField txtname = new JTextField();
    private JButton btnconn = new JButton();
    private JTextArea txtarea = new JTextArea();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextField txtsend = new JTextField();
    private JButton btnok = new JButton();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel lblinwon = new JLabel();
    private JRadioButton radio1 = new JRadioButton();
    private JRadioButton radio2 = new JRadioButton();
    private JButton btnclose = new JButton();
    private List list = new List();
    private JButton btnchange = new JButton();

    private BufferedReader in;
    private OutputStream out;
    private Socket soc;
    int count=0; //접속 인원수
     
    public ChatClient_hc() {

        try {
            jbInit();
            addListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(652, 264));
        this.setTitle("채팅 프로그램");
        this.setBackground(new Color(198, 214, 255));
        jLabel1.setText("대화명:");
        jLabel1.setBounds(new Rectangle(15, 10, 45, 25));
        txtname.setBounds(new Rectangle(60, 10, 105, 25));
        btnconn.setText("접속");
        btnconn.setBounds(new Rectangle(165, 10, 60, 25));
        jScrollPane1.setBounds(new Rectangle(15, 40, 495, 155));
        txtsend.setBounds(new Rectangle(15, 200, 435, 25));
        btnok.setText("확인");
        btnok.setBounds(new Rectangle(450, 200, 60, 25));
        jLabel2.setText("접속자 목록");
        jLabel2.setBounds(new Rectangle(520, 10, 75, 20));
        jLabel3.setText("인원:");
        jLabel3.setBounds(new Rectangle(530, 170, 35, 25));
        lblinwon.setText("0");
        lblinwon.setBounds(new Rectangle(565, 170, 50, 25));
        lblinwon.setBackground(new Color(198, 198, 200));
        lblinwon.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon.setHorizontalTextPosition(SwingConstants.CENTER);
        radio1.setText("귓속말");
        radio1.setBounds(new Rectangle(345, 10, 70, 25));
        radio2.setText("귓속말해제");
        radio2.setBounds(new Rectangle(420, 10, 90, 25));
        btnclose.setText("나가기");
        btnclose.setBounds(new Rectangle(530, 200, 90, 25));
        list.setBounds(new Rectangle(525, 40, 110, 120));
        btnchange.setText("대화명 변경");
        btnchange.setBounds(new Rectangle(230, 10, 110, 25));
        ButtonGroup group = new ButtonGroup();
        group.add(radio1); 
        group.add(radio2);
        this.getContentPane().add(btnchange, null);
        this.getContentPane().add(list, null);
        this.getContentPane().add(btnclose, null);
        this.getContentPane().add(radio1, null);
        this.getContentPane().add(radio2, null);
        this.getContentPane().add(lblinwon, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(btnok, null);
        this.getContentPane().add(txtsend, null);
        jScrollPane1.getViewport().add(txtarea, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(btnconn, null);
        this.getContentPane().add(txtname, null);
        this.getContentPane().add(jLabel1, null);
    }

 

    public void addListener(){
        txtname.addActionListener(this); // 접속을 누르거나 엔터를 치면 가능.
        txtsend.addActionListener(this);
        btnok.addActionListener(this);
        btnconn.addActionListener(this);
        btnclose.addActionListener(this);
        btnchange.addActionListener(this);
   }


   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == txtsend || e.getSource() == btnok) { // 메세지 전송
         try {
            if(radio1.isSelected()) { // 귓속말
               String name = list.getSelectedItem(); // 접속자 선택
               // /s 접속자이름 - 메세지
               out.write(("/s" + name + "-" + txtsend.getText() + "\n").getBytes("euc-kr"));
            } else { // 일반 메세지
               out.write((txtsend.getText() + "\n").getBytes("euc-kr"));
            }
            txtsend.setText(""); // 메세지 전송 후 텍스트 비움.
            txtsend.requestFocus(); // 포커스 맞춤.
         } catch (Exception e2) {
            txtarea.append("메세지 전송 실패 : " + e2);
         }
      } else if (e.getSource() == btnconn || e.getSource() == txtname) { // 대화명 입력 후 접속.
         if (txtname.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "대화명 입력");
            txtname.requestFocus(); // 대화명 입력을 다시 할 수 있도록 Focus를 txtname에 옮김.
            return; // 대화명 입력 하지 않으면 아무것도 하지 않음. return 함.
         }

         try {
            // System.out.println(txtname.getText());
            soc = new Socket("192.168.0.18", 7777); // 객체를 만나는 순간 Server에 accept()를 만남.
            in = new BufferedReader(new InputStreamReader(soc.getInputStream(), "euc-kr")); // 메세지를 보내면 받아서 저장 후 밑에서
                                                                        // readLine하여 출력.
            out = soc.getOutputStream();
            out.write((txtname.getText() + "\n").getBytes()); // 클라이언트에 대화명을 보냄.
            new Thread(this).start();
         } catch (Exception e2) {
            System.out.println("접속 오류 : " + e2);
         }
      } else if (e.getSource() == btnchange) { // 대화명 변경
         try {
            // 나중에 ...
         } catch (Exception e2) {
            txtarea.append("대화명 변경 실패 : " + e2);
         }

      } else if (e.getSource() == btnclose) { // 나가기
         try {

         } catch (Exception e2) {
            txtarea.append("나가기 실패 : " + e2);
         }

      }
   }
    
   @Override
   public void run() {
      while(true) {
         try {
            String msg = in.readLine(); // 서버로 부터 메시지 수신
            if(msg == null || msg.equals("")) return; // 메세지 값이 없으면 아무 작업을 하지 않음.
            
            if(msg.charAt(0) == '/') { // '/'(슬래시) c, q를 해야지 접속, 퇴장이 가능.
               if(msg.charAt(1) == 'c') { // 여기는 접속 메세지 // ex) 만약에 대화명 입력하고 들어왔다면 요렇게 들어옴 -> /ctom : 최초 접속자가 들어왔다고 보내주는 것임. 
                  list.add(msg.substring(2), count); // /c를 뺀 나머지 대화명을 접속자 목록에 계속 append 해줌. 2번째자리부터 나머지 끝자리까지.
                  count++;
                  lblinwon.setText(String.valueOf(count)); // 인원수를 출력해주기 위해서 해주는 것인데 count가 int이기 때문에 String으로 바꿔서 출력해줌.
                  txtarea.append("**" + msg.substring(2) + "님이 입장했습니다.\n"); // 접속한 사람을 txtarea에 나타내줌.
                  txtname.setEditable(false); // 대화명 입력불가
                  btnconn.setEnabled(false);
               } else if (msg.charAt(1) == 'q') { // 퇴장
                  txtarea.append("^^" + msg.substring(2) + "님이 퇴장하셨습니다.\n");
                  String cname = msg.substring(2);
                  for (int i = 0; i < count; i++ ) {
                     if(cname.equals(list.getItem(i))) { // 0번째부터 들어온 대화명을 찾음.
                        list.remove(i); // list에서 그 대화명 삭제.
                        count--;
                        lblinwon.setText(String.valueOf(count)); // 인원 수 카운트 값 감소 시킴.
                        break;
                     }
                  }
               } else if (msg.charAt(1) == 'r') { // 대화명 변경
                  // 나중에 ...
               }
            } else { // 일반 메세지
               txtarea.append(msg + "\n");
            }
         } catch (Exception e) {
            txtarea.append("run err : " + e); // 에러를 txtarea에 출력해줌.
         }
      }
   }
   
   public static void main(String args[]){
	   ChatClient_hc fr=new ChatClient_hc();

        fr.getPreferredSize();
        fr.setLocation(200,200);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
