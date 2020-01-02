package ch7.view;
import java.awt.*;
import javax.swing.*;
import ch7.data.RecordOrShowRecord;

public class ShowRecord extends JDialog{
    String [][] record;
    JTextArea showMess;
    public ShowRecord(){
        showMess = new JTextArea();
        showMess.setFont(new Font("楷体",Font.BOLD,15));
        add(new JScrollPane(showMess));
        setTitle("显示英雄榜");
        setBounds(400,200,400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void setRecord(String [][]record){
        this.record=record;
    }
    public void showRecord(){
        setRecord(new RecordOrShowRecord().getRecord());
        showMess.setText(null);
        if(record == null){
            JOptionPane.showMessageDialog
            (null,"没人上榜呢","消息框",JOptionPane.WARNING_MESSAGE);
        }
        else{
            for(int i =0;i<record.length;i++){
                int m = i+1;
                if(record[i][0] != null && record[i][1] != null) {
                    showMess.append("\n英雄" + m + ":" + record[i][0] + " " + "成绩:" + record[i][1]);
                    showMess.append("\n-------------------");
                }
            }
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        ShowRecord showRecord = new ShowRecord();
        String[][] record = new String[1][2];
        record[0][0] = "zz";
        record[0][1] = "100";
        showRecord.setRecord(new RecordOrShowRecord().getRecord());
        showRecord.showRecord();
    }
}