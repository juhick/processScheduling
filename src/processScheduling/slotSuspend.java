package processScheduling;

import java.util.Queue;

//时间片中断
public class slotSuspend {
    public boolean suspend(PCB runningP, Queue<PCB>readyQueue, int i, Message message){
        if(runningP.getEstimatesRunningTime() == 0){
            System.out.println("时间片中断!进程" + runningP.getpId() + "运行完成！");
            message.setMessage("时间片中断!进程" + runningP.getpId() + "运行完成！\n");
        }
        runningP.setRunningStep(i + 1);
        readyQueue.add(runningP);
        System.out.println("时间片中断!进程" + runningP.getpId() + "停止运行，进入就绪队列！");
        message.setMessage("时间片中断!进程" + runningP.getpId() + "停止运行，进入就绪队列！\n");
        return false;
    }
}
