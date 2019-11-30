package processScheduling;

import java.util.Queue;
//时间片调度
public class slot {
    //时间片大小
    private int timeSlice = 1;
    private int s1 = 1;
    private int s2 = 1;
    private int cpu = 1;
    //运行进程
    public void runProcess(Queue<PCB> readyQueue){
        //如果有进程未执行完则继续执行
        while(true){
            if(!readyQueue.isEmpty()){
                //获取就绪队列第一个并将其从队列中去除
                PCB p = readyQueue.poll();
                if(p.getpId() == 1 || p.getpId() == 2){
                    //申请资源s1
                    //Wait(s1);
                }
                //申请资源s2
                //Wait(s2);
                //申请cpu
                //Wait(cpu);
                //获取不到资源就进入阻塞状态
                //TODO 时间片中断
                //计算运行时间，若仍需服务的时间不足一个时间片，则运行完就回收
                int runTime = Math.min(p.getEstimatesRunningTime(), timeSlice);
                //进程执行
                //run(p);
                //执行一个时间片时间或执行完
                try {
                    Thread.sleep((long) runTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //停止进程
                //stop(p);
                //释放cpu
                //Signal(cpu);
                if(p.getpId() == 1 || p.getpId() == 2){
                    //释放资源s1
                    //Signal(s1);
                }
                //释放资源s2
                //Signal(s2);
                //在仍需服务时间中减去已服务时间
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - runTime);
                //如果进程已经完成
                if(p.getEstimatesRunningTime() == 0){
                    //修改进程状态为完成并不再将其放回就绪队列队尾
                    p.setpState(0);
                }else{
                    //若进程未完成，则再将进程放回就绪队列队尾
                    readyQueue.add(p);
                }
            }
        }
    }

}
