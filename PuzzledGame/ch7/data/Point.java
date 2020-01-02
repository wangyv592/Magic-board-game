package ch7.data;
public class Point{
    int x,y;             //在坐标系中的x-坐标和y-坐标
    boolean haveBlock;   //点上是否有方块
    Block  block=null;   //点上的方块
    public Point(){
    }                  
    public Point(int x,int y){
       this.x=x;
       this.y=y;
    }
    public boolean isHaveBlock(){
       return haveBlock;
    }
    public void setHaveBlock(boolean boo){
       haveBlock=boo;
    }
    public int getX(){
       return x;
    }
    public int getY(){
       return y;
    }
    public void setBlock(Block block){
       this.block=block;  
    }
    public Block getBlock(){
       return block;
    }
}
