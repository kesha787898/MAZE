from PIL import Image, ImageDraw 
draw=None
pix=None
Columns=None
Rows=None
Size=20
st=21
def init(name,with_mine):
        Blue=(0,0,255)
        Hsize=int(Size/2)
        image = Image.open(name) #Открываем изображение.
        global draw
        global pix
        global Columns
        global Rows
        draw = ImageDraw.Draw(image) #Создаем инструмент для рисования. 	
        pix = image.load() #Выгружаем значения пикселей.
        sf=list()
        Columns=(int)((image.size[0]-23)/Size)
        Rows=(int)((image.size[1]-63)/Size)
        for i in range(Columns) :
                for j in range(Rows) :
                        if ((pix[st+i*Size,st+j*Size][0]!=255)or(pix[st+i*Size,st+j*Size][1]!=255)or(pix[st+i*Size,st+j*Size][2]!=255)):
                                sf.append((i,j))        
        nsf=(sf[0][1]+sf[0][0]*Rows,sf[1][1]+sf[1][0]*Rows)
        sf=nsf
        matr=list()
        for i in range(Columns):
                matr.append(list())
                for j in range(Rows):
                        matr[i].append(list())
        for i in range(Columns):
                for j in range(Rows):
                        if (pix[st+Hsize+i*Size,st+j*Size][0]!=0):
                                matr[i][j].append((i+1,j))
                                matr[i+1][j].append((i,j))
                        if (pix[st+i*Size,st+Hsize+j*Size][0]!=0):
                                matr[i][j].append((i,j+1))
                                matr[i][j+1].append((i,j))
        matr=matr_to_enum(matr)
        g=graf(matr)
        g.BFS(sf[0],sf[1])    
        image.save("ans.jpg", "JPEG")
        del draw
        del g
def Draw_Square(x,y,S=0):
    global draw
    draw.point((x+1, y+1), (S, S, S))
    draw.point((x+1, y-1), (S, S, S))
    draw.point((x+1, y), (S, S, S))
    draw.point((x-1, y-1), (S, S, S))
    draw.point((x-1, y), (S, S, S))
    draw.point((x-1, y+1), (S, S, S))
    draw.point((x, y+1), (S, S, S))
    draw.point((x, y-1), (S, S, S))
    draw.point((x, y), (S, S, S))
def Draw_Lineoy(x,y1,y2,s):
        for y in range(y1,y2):

            pix[x,y]=s
def Draw_Lineox(x1,x2,y,s):
    for x in range(x1,x2):
        pix[x,y]=s
def DrawLine(a,b,color=(255,0,0)):
        ax=st+(a//Rows)*Size
        ay=st+(a%Rows)*Size
        bx=st+(b//Rows)*Size
        by=st+(b%Rows)*Size
        if (ax==bx):
                if ay<by:
                        Draw_Lineoy(ax,ay,by,color)
                else:   
                        Draw_Lineoy(ax,by,ay,color)
        if (ay==by):
                if ax<bx:
                        Draw_Lineox(ax,bx,by,color)
                else:
                        Draw_Lineox(bx,ax,by,color)
def matr_to_enum(matr):#из списка ребер в список смежности
    rez=list()
    for i in matr: 
        for j in i:
                rez.append(j)
    nrez=list()
    for i in rez:
            versh=list()
            for j in i:
                    versh.append(j[0]*Rows+j[1])
            nrez.append(tuple(versh))
    return nrez    
class Stack:
     def __init__(self):
         self.items = []
     def isEmpty(self):
         return self.items == []
     def push(self, item):
         self.items.append(item)
     def pop(self):
         return self.items.pop()
     def peek(self):
         return self.items[len(self.items)-1]
     def size(self):
         return len(self.items)
class graf:
        V=[]
        E=[]
        m_svyazey=None
        def __init__(self,m):
                self.m_svyazey=m
                self.V=range(len(self.m_svyazey))
        def matr_svyaz_dict(self):
                rez=dict()
                for i in self.V:
                        #print (self.m_svyazey[k],k)
                        rez[i]=self.m_svyazey[i]
                return(rez)
        def BFS(self,a,b):
                way=Stack()
                col= dict.fromkeys(self.V,0)
                mas=self.matr_svyaz_dict()
                come_from=dict()
                come_from[a]=None
                way.push(a)
                col[a]=1
                tek=None
                while (True):
                        tek=way.pop()
                        if tek==b:
                                break
                        for i in mas[tek]:
                                if col[i]==0:
                                        col[i]=1
                                        come_from[i]=tek
                                        way.push(i)
                pred=come_from[b]
                to_draw=list()   
                to_draw.append(b)                
                while (pred!=None):
                        to_draw.append(pred)
                        pred=come_from[pred]
                for i in range(len(to_draw)-1):
                        DrawLine(to_draw[i],to_draw[i+1])
                return to_draw
init("C:\\Users\\alex\\IdeaProjects\\Generate_Maze\\image.bmp",False)
#raise SystemExit(Columns)
