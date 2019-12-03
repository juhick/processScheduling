package processScheduling;

import java.util.Queue;

public class process  {

    private semChange sem;
    private slotSuspend suspend;
    private PCB p;
    private int timeSlice;
    private int runTime;
    private int[] book;

    public PCB getP() {
        return p;
    }

    public void setP(PCB p) {
        this.p = p;
    }

    private Queue<PCB> pcb;

    process(semChange sem, slotSuspend suspend, Queue<PCB> pcb, int timeSlice){
        this.sem = sem;
        this.suspend = suspend;
        this.p = null;
        this.pcb = pcb;
        this.timeSlice = timeSlice;
        this.runTime = 0;
        this.book = new int[3];
    }

    public boolean timeCheck(int i, Message message){
        if(p.getEstimatesRunningTime() == 0){
            System.out.println("进程" + p.getpId() + "运行完成！");
            message.setMessage("进程" + p.getpId() + "运行完成！\n");
            return true;
        }
        if(runTime == timeSlice){
            suspend.suspend(p, pcb, i, message);
            return true;
        }
        return false;
    }

    public void print(int pId, Message message){
        System.out.print("进程" + pId + "运行情况：");
        message.setMessage("进程" + pId + "运行情况：");
        System.out.print("  开始时间：" + p.getStartTime());
        message.setMessage("  开始时间：" + p.getStartTime());
        System.out.print("  服务时间：" + p.getServiceTime());
        message.setMessage("  服务时间：" + p.getServiceTime());
        System.out.println("  剩余运行时间：" + p.getEstimatesRunningTime());
        message.setMessage("  剩余运行时间：" + p.getEstimatesRunningTime() + "\n");

    }

    public void process1(int[] currentTime, Message message){
        if(book[p.getpId() - 1] == 0){
            book[p.getpId() - 1] = 1;
            p.setStartTime(currentTime[0]);
            print(1, message);
            System.out.println("当前时间为：" + currentTime[0]);
            message.setMessage("当前时间为：" + currentTime[0] + "\n");
        }
        this.runTime = 0;
        switch (p.getRunningStep()) {
            case 0:
                System.out.println("进程1正在对S1进行P操作");
                message.setMessage("进程1正在对S1进行P操作\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Wait(p, "S1", message)) {
                    currentTime[0]++;
                    System.out.println("S1被占用!");
                    message.setMessage("S1被占用!" + '\n');
                    p.setRunningStep(1);
                    return;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                if(timeCheck(0, message)){
                    currentTime[0]++;
                    return;
                }
            case 1:
                System.out.println("进程1进入临界区1");
                message.setMessage("进程1进入临界区1\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                if(timeCheck(1, message)){
                    currentTime[0]++;
                    return;
                }
            case 2:
                System.out.println("进程1对S1进行V操作, 并且退出临界区1");
                message.setMessage("进程1对S1进行V操作, 并且退出临界区1\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Signal(pcb, "S1", message)) {
                    currentTime[0]++;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                if(timeCheck(2, message)){
                    currentTime[0]++;
                    return;
                }
            case 3:
                System.out.println("进程1正在对S2进行P操作");
                message.setMessage("进程1正在对S2进行P操作\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Wait(p, "S2", message)) {
                    currentTime[0]++;
                    System.out.println("S2被占用!");
                    message.setMessage("S2被占用!\n");
                    p.setRunningStep(4);
                    return;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                if(timeCheck(3, message)){
                    currentTime[0]++;
                    return;
                }
            case 4:
                System.out.println("进程1进入临界区2");
                message.setMessage("进程1进入临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                if(timeCheck(4, message)){
                    currentTime[0]++;
                    return;
                }
            case 5:
                System.out.println("进程1对S2进行V操作, 并且退出临界区2");
                message.setMessage("进程1对S2进行V操作, 并且退出临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Signal(pcb, "S2", message)) {
                    currentTime[0]++;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(1, message);
                timeCheck(5, message);
        }
    }
    public void process2(int[] currentTime, Message message){
        if(book[p.getpId() - 1] == 0){
            book[p.getpId() - 1] = 1;
            p.setStartTime(currentTime[0]);
            print(2, message);
            System.out.println("当前时间为：" + currentTime[0]);
            message.setMessage("当前时间为：" + currentTime[0] + '\n');
        }
        this.runTime = 0;
        switch (p.getRunningStep()) {
            case 0:
                System.out.println("进程2正在对S1进行P操作");
                message.setMessage("进程2正在对S1进行P操作\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Wait(p, "S1", message)) {
                    currentTime[0]++;
                    System.out.println("S1被占用!");
                    message.setMessage("S1被占用!\n");
                    p.setRunningStep(1);
                    return;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                if(timeCheck(0, message)){
                    currentTime[0]++;
                    return;
                }
            case 1:
                System.out.println("进程2进入临界区1");
                message.setMessage("进程2进入临界区1\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                if(timeCheck(1, message)){
                    currentTime[0]++;
                    return;
                }
            case 2:
                System.out.println("进程2对S1进行V操作, 并且退出临界区1");
                message.setMessage("进程2对S1进行V操作, 并且退出临界区1\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Signal(pcb, "S1", message)) {
                    currentTime[0]++;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                if(timeCheck(2, message)){
                    currentTime[0]++;
                    return;
                }
            case 3:
                System.out.println("进程2正在对S2进行P操作");
                message.setMessage("进程2正在对S2进行P操作\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Wait(p, "S2", message)) {
                    currentTime[0]++;
                    System.out.println("S2被占用!");
                    message.setMessage("S2被占用!\n");
                    p.setRunningStep(4);
                    return;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                if(timeCheck(3, message)){
                    currentTime[0]++;
                    return;
                }
            case 4:
                System.out.println("进程2进入临界区2");
                message.setMessage("进程2进入临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                if(timeCheck(4, message)){
                    currentTime[0]++;
                    return;
                }
            case 5:
                System.out.println("进程2对S2进行V操作, 并且退出临界区2");
                message.setMessage("进程2对S2进行V操作, 并且退出临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Signal(pcb, "S2", message)) {
                    currentTime[0]++;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(2, message);
                timeCheck(5, message);
        }
    }

    public void process3(int[] currentTime, Message message){
        if(book[p.getpId() - 1] == 0){
            book[p.getpId() - 1] = 1;
            p.setStartTime(currentTime[0]);
            print(3, message);
            System.out.println("当前时间为：" + currentTime[0]);
            message.setMessage("当前时间为：" + currentTime[0] + '\n');
        }
        this.runTime = 0;
        switch (p.getRunningStep()) {
            case 0:
                System.out.println("进程3正在对S2进行P操作");
                message.setMessage("进程3正在对S2进行P操作\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Wait(p, "S2", message)) {
                    currentTime[0]++;
                    System.out.println("S2被占用!");
                    message.setMessage("S2被占用!\n");
                    p.setRunningStep(1);
                    return;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(3, message);
                if(timeCheck(0, message)){
                    currentTime[0]++;
                    return;
                }
            case 1:
                System.out.println("进程3进入临界区2");
                message.setMessage("进程3进入临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(3, message);
                if(timeCheck(1, message)){
                    currentTime[0]++;
                    return;
                }
            case 2:
                System.out.println("进程3对S2进行V操作, 并且退出临界区2");
                message.setMessage("进程3对S2进行V操作, 并且退出临界区2\n");
                p.setEstimatesRunningTime(p.getEstimatesRunningTime() - 1);
                p.setServiceTime(p.getServiceTime() + 1);
                if (!sem.Signal(pcb, "S2", message)) {
                    currentTime[0]++;
                }
                this.runTime++;
                currentTime[0]++;
                System.out.println("当前时间为：" + currentTime[0]);
                message.setMessage("当前时间为：" + currentTime[0] + '\n');
                print(3, message);
                timeCheck(2, message);
        }
    }
}
