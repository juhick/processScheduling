package processScheduling;

import java.util.Queue;

public class semChange {
    public int S1;											//互斥资源S1
    public int S2;											//互斥资源S2
    controlStructure cs;
    public semChange() {
        cs = new controlStructure();
        S1=1;
        S2=1;
    }

    public boolean Wait(PCB pcb,String a, Message message) {					//P操作
        if(a.equals("S1")){
            if(S1<=0) {
                cs.Block(pcb, a, message);
                S1--;
                return false;
            }
            else {
                S1--;
                return true;
            }
        }
        else if(a.equals("S2")) {
            if(S2<=0) {
                cs.Block(pcb, a, message);
                S2--;
                return false;
            }
            else {
                S2--;
                return true;
            }
        }
        else {
            System.out.println("不存在该资源");
            return false;
        }
    }

    public boolean Signal(Queue<PCB> queue, String a, Message message) {		//V操作
        if(a.equals("S1")){
            S1++;
            if(S1<=0) {
                cs.Wakeup(queue, a, message);
                return false;
            }
            else {
                return true;
            }
        }
        else if(a.equals("S2")) {
            S2++;
            if(S2<=0) {
                cs.Wakeup(queue, a, message);
                return false;
            }
            else {
                return true;
            }
        }
        else {
            System.out.println("不存在该资源");
            return false;
        }
    }
}
