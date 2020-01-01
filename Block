package ch7.data;
import javax.swing.*;
import java.awt.*;
public class Block extends JTextField{
   Point point;            //方块所在点
   Object object;          //方块上的图像
   Point [][] allPoint;      //全部点位置
   int index_i,index_j ;    //点的索引
   public Block(){
      setEditable(false);
      setHorizontalAlignment(JTextField.CENTER);
      setFont(new Font("Arial",Font.BOLD,16));
      setForeground(Color.blue);
   } 
   public void setAtPoint(Point p){
        point=p;
   }
   public void setAllPoint(Point [][] point){
        allPoint = point;
   }
   public Point getAtPoint(){
       return point;
   }
   public void setObject(Object object){
      this.object=object;
      if(object instanceof Integer){
         Integer number=(Integer)object;
         setText(""+number.intValue());
      }
      else if(object instanceof Image){
         repaint();
      }
   }
   public Object getObject(){
      return object;
   }
   public void paintComponent(Graphics g){
      super.paintComponent(g);  
      int w=getBounds().width;
      int h=getBounds().height;
      try{ 
           g.drawImage((Image)object,0,0,w,h,this);
      }
      catch(Exception exp){}
   }
   public boolean move(){
       int m = -1,n=-1;
       boolean successMove = false;
       Point pStart = getAtPoint();
       findIndex(pStart,allPoint); // 见后面的findIndex(Point p,Point[][] allPoint)方法
       for(int i = 0;i<allPoint.length;i++){        //得到空盒子的位置索引m,n
           for(int j = 0;j<allPoint[i].length;j++){
               if(!allPoint[i][j].isHaveBlock()){
                   m = i;
                   n = j;
               }
           }
       }
       if (Math.abs(index_i-m)+Math.abs(index_j-n) == 1){
           this.setAtPoint(allPoint[m][n]);   //当前方块到达allPoint[m][n]点（空盒处）
           successMove = true;
           allPoint[m][n].setBlock(this);
           allPoint[m][n].setHaveBlock(true);
           pStart.setHaveBlock(false); //设置该点没有方块
           pStart.setBlock(null); //设置该点上的方块是null（设置为空盒子）
       }
       return successMove ; 
    }
    private void findIndex(Point p,Point[][] allPoint){  //寻找p在allPoint中的索引位置
       for(int i = 0;i<allPoint.length;i++){
           for(int j = 0;j<allPoint[i].length;j++){
               if(p == allPoint[i][j]){
                   index_i = i;
                   index_j = j;
                   break;
               }
           }
       }
    }
}
