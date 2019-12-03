package processScheduling;

import java.util.Queue;
//时间片调度
public class slot {
    public PCB select(Queue<PCB> readyQueue, Message message) {
        //如果有进程未执行完则继续执行
        if (!readyQueue.isEmpty()) {
            //获取就绪队列第一个并将其从队列中去除
            PCB p = readyQueue.poll();
            p.setpState(0);
            return p;
        }else{
            System.out.println("就绪队列无进程,所有进程已完成！");
            message.setMessage("就绪队列无进程,所有进程已完成！\n");
            return null;
        }
    }

    public void exit(PCB p){
        p.setpState(3);
        System.out.println("进程" + p.getpId() + "已完成！");
    }
}
