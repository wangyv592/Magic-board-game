package ch7.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import ch7.view.PuzzlePad;
import ch7.view.ShowRecord;

public class AppWindow extends JFrame implements ActionListener{
    PuzzlePad puzzlePad;
    JMenuBar bar;
    JMenu gradeMenu,choiceImage;
    JMenuItem oneGrade,twoGrade,newImage,defaultImage;
    JRadioButton digitPlay,imagePlay;
    ButtonGroup group=null;
    JButton startButton;
    JButton showRecordButton;
    Image image;
    Toolkit tool;
    public AppWindow(){
        tool=getToolkit();
        bar=new JMenuBar();
        gradeMenu=new JMenu("选择级别");
        choiceImage=new JMenu("选择图像");
        oneGrade=new JMenuItem("初级");
        twoGrade=new JMenuItem("高级");
        newImage=new JMenuItem("选择一幅新图像");
        defaultImage=new JMenuItem("使用默认图像");
        gradeMenu.add(oneGrade);
        gradeMenu.add(twoGrade);
        choiceImage.add(newImage);
        choiceImage.add(defaultImage);
        bar.add(gradeMenu);
        bar.add(choiceImage);
        setJMenuBar(bar);
        oneGrade.addActionListener(this);
        twoGrade.addActionListener(this);
        newImage.addActionListener(this);
        defaultImage.addActionListener(this);
        startButton=new JButton("开始");
        showRecordButton =new JButton("查看英雄榜");
        showRecordButton.addActionListener(this);
        startButton.addActionListener(this);
        group=new ButtonGroup();
        digitPlay=new JRadioButton("数字玩法",true);
        imagePlay=new JRadioButton("图像玩法",false);
        group.add(digitPlay);
        group.add(imagePlay);
        puzzlePad=new PuzzlePad();
        puzzlePad.setGrade(1);
        puzzlePad.setIsDigitPlay();
        add(puzzlePad,BorderLayout.CENTER);
        JPanel pNorth=new JPanel();
        pNorth.add(digitPlay);
        pNorth.add(imagePlay);
        pNorth.add(showRecordButton);
        pNorth.add(startButton);
        pNorth.add(new JLabel("如果图像不能立刻显示,请再单击一次按扭"));
        add(pNorth,BorderLayout.NORTH);
        add(puzzlePad.getHandleMove(),BorderLayout.SOUTH);
        validate();
        setVisible(true);
        setBounds(100,50,550,380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            image=tool.createImage(new File("拼图图像/default.jpg").toURI().toURL());
            puzzlePad.setImage(image);
        }
        catch(Exception exp){}
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==startButton){
            if(digitPlay.isSelected()){
                puzzlePad.setIsDigitPlay();
            }
            else if(imagePlay.isSelected()){
                puzzlePad.setImage(image);
                puzzlePad.setIsImagePlay();
            }
        }
        else if(e.getSource()==oneGrade){
            puzzlePad.setGrade(1);
        }
        else if(e.getSource()==twoGrade){
            puzzlePad.setGrade(2);
        }
        else if(e.getSource()==newImage){
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
            JFileChooser chooser=new JFileChooser();
            chooser.setFileFilter(filter);
            int state=chooser.showOpenDialog(null);
            File file=chooser.getSelectedFile();
            if(file!=null&&state==JFileChooser.APPROVE_OPTION){
                try{
                    image=tool.createImage(file.toURI().toURL());
                    puzzlePad.setImage(image);
                }
                catch(Exception exp){}
            }
        }
        else if(e.getSource()==defaultImage){
            try{
                image=tool.createImage(new File("拼图图像/default.jpg").toURI().toURL());
                puzzlePad.setImage(image);
            }
            catch(Exception exp){}
        } else if (e.getSource()==showRecordButton) {
            ShowRecord showRecord = new ShowRecord();
            showRecord.showRecord();
        }
    }
    public  static void main(String args[]){
        new AppWindow();
    }
}