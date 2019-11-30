package processScheduling;

public class controlStructure {
    private int[] blocks=new int[3];			//阻塞队列
    private int n;								//阻塞进程数
    private semChange sChange;
    controlStructure(semChange s){
        int i=0;
        while(i<3){
            blocks[i]=-1;
            i++;
        }
        n=0;
        sChange=s;
    }
    public void Block(int a,String b) {			//阻塞进程
        int c=0;
        if(b.equals("S1")){
            c=sChange.getS1();
        }
        else if(b.equals("S2")) {
            c=sChange.getS2();
        }
        else {
            System.out.println("不存在该资源");
            return ;
        }
        if(c<=0) {
            blocks[n]=a;
            n++;
        }
        sChange.Wait(b);
    }
    public void Wakeup(String b) {				//唤醒进程
        if(!(b.equals("S1")||b.equals("S2"))){
            System.out.println("不存在该资源");
            return ;
        }
        blocks[0]=-1;
        int i=0;
        while(i<2) {
            blocks[i]=blocks[i+1];
            blocks[i+1]=-1;
            i++;
        }
        n--;
        sChange.Signal(b);
    }
}
