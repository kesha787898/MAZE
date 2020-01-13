package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
public class Graph {
private boolean isOrgraph =false;
private Relationship E;
private HashSet V;
private HashMap<Integer,Integer> marks ;
public Graph(){}

public void optimize(){
    int v=VQuantity();
   int e=EQuantity();
    if(v*v<e*v){this.E=E.toMatrix();}
    else{this.E=E.toList();}
}
public int density(){return (2*this.EQuantity())/(VQuantity()*(VQuantity()-1));}


static Graph Empty(int v,boolean isList){
    Graph rez =new Graph();
    if (isList){
    rez.E=Adjacency_list.Empty(v) ; }
    else{rez.E=Adjacency_matrix.Empty(v) ;}


    return  rez;
}
public int coordx(int num){
    int size= (int)Math.sqrt((double) this.VQuantity());
    int X=num%size;;
    return GLOBALCONSTS.left+X*GLOBALCONSTS.size+GLOBALCONSTS.size/2-1;
}
public int coordy(int num){
        int size= (int)Math.sqrt((double) this.VQuantity());
        int Y=num /size;
        return GLOBALCONSTS.right+Y*GLOBALCONSTS.size+GLOBALCONSTS.size/2;
    }
public int EQuantity(){return E.E_quantity();}
public int VQuantity(){return E.V_quantity();}
void createArc(int f,int s){E.createArc(f,s);}
void deleteArc(int f,int s){E.deleteArc(f,s);}
boolean isAdjacent(int F,int S){return this.E.isAdjacent(F,S);};
HashSet<Integer> allAdjacent(int arg){return this.E.allAdjacent(arg);};
void alg(){
    int counter=0;/////there is error
    marks=new HashMap<Integer,Integer>(VQuantity());
    for (int i=0;i<VQuantity();i++){marks.put(i,0);}
    int tek = new Random().nextInt(VQuantity()+1);
    Stack<Integer> st =new Stack<Integer>();
marks.put(tek,2);
    counter++;
while(counter!=VQuantity()){
    int size= (int)Math.sqrt((double) this.VQuantity());
    ArrayList<Integer> l=new ArrayList<Integer>();
    if ((tek%size!=0)&&(marks.get(tek-1)==0)){l.add(tek-1);}
    if ((tek%size!=size-1)&&(marks.get(tek+1)==0)){l.add(tek+1);}
    if ((tek/size!=0)&&(marks.get(tek-size)==0)){l.add(tek-size);}
    if ((tek/size!=size-1)&&(marks.get(tek+size)==0)){l.add(tek+size);}
    if (!l.isEmpty()){st.push(tek);
    int ntek= l.get(new Random().nextInt(l.size()));
         this.createArc(tek,ntek);
    tek=ntek;
    marks.put(tek,1);
        counter++;}
    else if (!st.isEmpty()){tek=st.pop();}
    else {ArrayList<Integer> list=new ArrayList<Integer>();
    for (int i=0;i<this.VQuantity();i++){if (this.marks.get(i)!=0)list.add(i);}
    tek= list.get(new Random().nextInt(list.size()));
    this.marks.put(tek,1);counter++;
    System.out.println("new");
    }
}
    marks.put(tek,3);
}
BufferedImage firstdraw(boolean draw_e,boolean draw_v){
    int size= (int)Math.sqrt((double) this.VQuantity());
    System.out.println(size);
    int sizex=GLOBALCONSTS.right+size*GLOBALCONSTS.size+GLOBALCONSTS.left;
    int sizey=GLOBALCONSTS.up+size*GLOBALCONSTS.size+GLOBALCONSTS.down;
    BufferedImage rez=new BufferedImage(sizex,sizey,BufferedImage.TYPE_INT_RGB);
    for (int i=0;i<sizex;i++){
        for (int j=0;j<sizey;j++){
            rez.setRGB(i,j,new Color(255,255,255).getRGB());
        }
    }
    for (int i=0;i<VQuantity();i++){
        if (draw_v) {
            rez.setRGB(this.coordx(i), this.coordy(i), new Color(0, 0, 0).getRGB());
        }
        boolean left;
        boolean right;
        boolean top;
        boolean bot;
        if (i%size==0){left=true;}
        else left =!isAdjacent(i,i-1);
        if (i%size==size-1){right=true;}
        else right =!isAdjacent(i,i+1);
        if (i/size==0){top=true;}
        else top =!isAdjacent(i,i-size);
        if (i/size==size-1){bot=true;}
        else bot =!isAdjacent(i,i+size);
        draw.squad(rez,this.coordx(i),this.coordy(i),new Color(0,0,0).getRGB(),GLOBALCONSTS.size/2,left,right,top,bot);
        if (!this.allAdjacent(i).isEmpty()){
if(draw_e){
        for (int e:this.allAdjacent(i) ){
            draw.line(rez, coordx(i), coordy(i), coordx(e), coordy(e), new Color(0, 0, 0).getRGB());
        }}}
      int xk;int yk;
for (int j=0;j<marks.size();j++){
    if (marks.get(j)==2){
        draw.filled_squad(rez,this.coordx(j),this.coordy(j),new Color(0,255,0).getRGB(),GLOBALCONSTS.size/2-1);
    }
    if (marks.get(j)==3){
        draw.filled_squad(rez,this.coordx(j),this.coordy(j),new Color(255,0,0).getRGB(),GLOBALCONSTS.size/2-1);
    }
}
    }
    return  rez;
}
}
