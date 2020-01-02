package ch7.view;
import javax.swing.*;
import java.awt.event.*;
import java.net.URISyntaxException;

import ch7.data.RecordOrShowRecord;
public class Record extends JDialog implements ActionListener{
    int time=0;
    String message=null;
    JTextField textName;
    JLabel label=null;
    JButton confirm,cancel;

    public Record(int time){
        setTitle("记录你的成绩");
        this.time=time;
        setBounds(100,100,240,160);
        setResizable(false);
        setModal(true);
        confirm=new JButton("确定");
        cancel=new JButton("取消");
        textName=new JTextField(8);
        textName.setText("匿名");
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        setLayout(new java.awt.GridLayout(2,1));
        label=new JLabel("输入您的大名看是否可上榜");
        add(label);
        JPanel p=new JPanel();
        p.add(textName);
        p.add(confirm);
        p.add(cancel);
        add(p);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
      if(e.getSource()==confirm){
          String name = textName.getText();
          try {
              writeRecord(name, time);
          } catch (URISyntaxException ex) {
              ex.printStackTrace();
          }
          setVisible(false);
      }
      if(e.getSource()==cancel){
          setVisible(false);
      }
    }
    public void writeRecord(String name,int time) throws URISyntaxException {
        if (new RecordOrShowRecord().saveRecord(name, time))
            JOptionPane.showMessageDialog(null,"恭喜您，上榜了","消息框",JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"上榜失败","消息框",JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        Record record = new Record(0);
    }
}
