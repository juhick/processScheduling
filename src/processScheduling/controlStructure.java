package processScheduling;

import java.util.LinkedList;
import java.util.Queue;

public class controlStructure {
    public Queue<PCB> S1blocks;									//阻塞队列S1
    public Queue<PCB> S2blocks;									//阻塞队列S2 .poll() .add()
    public controlStructure(){
        S1blocks= new LinkedList<>();
        S2blocks= new LinkedList<>();
    }
    public void Block(PCB pcb,String a, Message message) {						//阻塞进程
        if(a.equals("S1")) {
            S1blocks.add(pcb);
            System.out.println(pcb.getpId()+"进入阻塞队列");
            message.setMessage(pcb.getpId()+"进入阻塞队列\n");
        }
        else if(a.equals("S2")) {
            S2blocks.add(pcb);
            System.out.println(pcb.getpId()+"进入阻塞队列");
            message.setMessage(pcb.getpId()+"进入阻塞队列\n");
        }
    }
    public void Wakeup(Queue<PCB> queue,String a, Message message) {				//唤醒进程
        if(a.equals("S1")) {
            if(!S1blocks.isEmpty()) {
                PCB pcb=S1blocks.poll();
                queue.add(pcb);
                System.out.println("进程" + pcb.getpId()+"被唤醒");
                message.setMessage("进程" + pcb.getpId()+"被唤醒\n");
            }
        }
        else if(a.equals("S2")) {
            if(!S2blocks.isEmpty()){
                PCB pcb=S2blocks.poll();
                queue.add(pcb);
                System.out.println("进程" + pcb.getpId()+"被唤醒");
                message.setMessage("进程" + pcb.getpId()+"被唤醒\n");
            }
        }
    }
}