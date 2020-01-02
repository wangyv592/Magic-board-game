package ch7.view;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import ch7.data.Point;
import ch7.data.Block;
import ch7.data.HandleImage;
import ch7.data.VerifySuccess;
public class PuzzlePad extends JPanel{
     Point [][] point;
     Block [][] block;
     int distance=56,grade,m=3,n=3;
     HandleMove handleMove;
     HandleImage handleImage; 
     VerifySuccess verifySuccess; 
     Image image;
     boolean isDigitPlay; 
     //PlayMusic playMusic;
     public PuzzlePad(){
         setBackground(Color.gray);
         setLayout(null);
         handleMove=new HandleMove();
         handleMove.initSpendTime();
         handleImage=new HandleImage();
         verifySuccess=new VerifySuccess();
         handleMove.setVerifySuccess(verifySuccess);
     }
     public HandleMove getHandleMove(){
//        playMusic=new PlayMusic();
//        playMusic.setClipFile();
         return handleMove;
     }
     public void setImage(Image image){
        this.image=image;
     }
     public void setGrade(int grade){
         this.grade=grade;
         if(grade==1){
            m=3;
            n=3;
         } 
         else if(grade==2){
            m=4;
            n=4;
         }
     }
     public int getGrade(){
         return grade;
     }
     private void needInit(){
         handleMove.initSpendTime();
         removeAll();
         point=new Point[m][n];
         block=new Block[m][n];
         int Hspace=distance,Vspace=distance;
         for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
               point[i][j]=new Point(Hspace,Vspace);
               Hspace=Hspace+distance;
            }
            Hspace=distance;
            Vspace=Vspace+distance;
         } 
         handleMove.setPoint(point);
         verifySuccess.setPoint(point);
         handleMove.setVerifySuccess(verifySuccess); 
         int k=0;
         for(int i=0;i<m;i++){
             if(i<m-1)
                for(int j=0;j<n;j++){
                   block[i][j]=new Block();
                   block[i][j].setAllPoint(point);
                   block[i][j].addMouseListener(handleMove);
                   k++; 
                }
             else
               for(int j=0;j<n-1;j++){
                   block[i][j]=new Block();
                   block[i][j].setAllPoint(point);
                   block[i][j].addMouseListener(handleMove);
                   k++;     
                }
         } 
         for(int i=0;i<m;i++){
             if(i<m-1)
               for(int j=0;j<n;j++){
                  add(block[i][j]);
                  block[i][j].setSize(distance,distance);
                  block[i][j].setLocation(point[i][j].getX(),point[i][j].getY());
                  block[i][j].setAtPoint(point[i][j]);
                  point[i][j].setBlock(block[i][j]);
                  point[i][j].setHaveBlock(true);
               }
             else
               for(int j=0;j<n-1;j++){
                  add(block[i][j]);
                  block[i][j].setSize(distance,distance);
                  block[i][j].setLocation(point[i][j].getX(),point[i][j].getY());
                  block[i][j].setAtPoint(point[i][j]);
                  point[i][j].setBlock(block[i][j]);
                  point[i][j].setHaveBlock(true);
               }
         }
     }
     public void setIsDigitPlay(){
         needInit();
         isDigitPlay=true;
         ArrayList<Integer> numberList=new ArrayList<Integer>();
         for(int k=0;k<m*n-1;k++){
            numberList.add(k+1);
         }
         Object []object=numberList.toArray();
         verifySuccess.setObject(object);
         Collections.shuffle(numberList); //����������� 
         int k=0;
         for(int i=0;i<m;i++){
             if(i<m-1)
                for(int j=0;j<n;j++){
                    block[i][j].setObject(numberList.get(k));
                    k++; 
                }
             else
               for(int j=0;j<n-1;j++){
                   block[i][j].setObject(numberList.get(k));
                   k++;     
                }
         } 
         repaint(); 
    }
    public void setIsImagePlay(){
         needInit(); 
         isDigitPlay=false;
         ArrayList<Image> imageList=new ArrayList<Image>();
         Image [] blockImage=handleImage.getImages(image,m,n);
         for(int k=0;k<blockImage.length-1;k++){
            imageList.add(blockImage[k]);
         }
         Object []object=imageList.toArray();
         verifySuccess.setObject(object);
         Collections.shuffle(imageList); //�������ͼ��
         int k=0;
         for(int i=0;i<m;i++){
             if(i<m-1)
                for(int j=0;j<n;j++){
                    block[i][j].setObject(imageList.get(k));
                    block[i][j].repaint();
                    block[i][j].setBorder(null); 
                    k++; 
                }
             else
               for(int j=0;j<n-1;j++){
                   block[i][j].setObject(imageList.get(k));
                   block[i][j].repaint();
                   block[i][j].setBorder(null); 
                   k++;     
                }
         } 
         repaint();  
    }
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      if(isDigitPlay==false)  
        try{ 
             g.drawImage(image,20+distance*(m+1),point[0][0].getY(),
                       distance*m,distance*n,this);
        }
        catch(Exception exp){}
   }
} 
