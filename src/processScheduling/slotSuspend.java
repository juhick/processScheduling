package processScheduling;

import java.util.Queue;

//时间片中断
public class slotSuspend {
    public void handlePCB(Queue<PCB>blockingQueue, PCB p){
        //将当前进程放入阻塞队列
        blockingQueue.add(p);
    }

    public void checkBlocking(Queue<PCB>blockingQueue, Queue<PCB>readyQueue){
        while(!blockingQueue.isEmpty() || !readyQueue.isEmpty()){
            if(!blockingQueue.isEmpty()){
                PCB p = blockingQueue.poll();
                if(p.getpId() == 3){
                    //如果s2可用，则将其放入就绪队列队尾
                    readyQueue.add(p);
                    //否则，再将其放入阻塞队列队尾
                    blockingQueue.add(p);
                }else{
                    //如果s1和s2均可用，则将其放入就绪队列队尾
                    readyQueue.add(p);
                    //否则，再将其放入阻塞队列队尾
                    blockingQueue.add(p);
                }
            }
        }
    }
}
