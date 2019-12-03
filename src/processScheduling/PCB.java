package processScheduling;
//进程控制块
public class PCB {
    private int pId;
    private String pName;
    private int arriveTime;//到达时间
    private int serviceTime;//服务时间
    private int estimatesRunningTime;//估计运行时间
    private int startTime;//开始时间
    private int endTime;//完成时间
    private int turnaroundTime;//周转时间
    private int weightedTurnaroundTime;//带权周转时间
    private int waitTime;//等待时间
    private double hrrn;//响应比
    private int pPriority;//优先级
    private int pState;//进程状态
    private int runningStep;

    public int getRunningStep() {
        return runningStep;
    }

    public void setRunningStep(int runningStep) {
        this.runningStep = runningStep;
    }

    /*  pState：
                0：运行
                1：就绪
                2：阻塞
                3：完成
         */
    private PCB next;

    public int getWaitTime() {
        return waitTime;
    }

    public int getpPriority() {
        return pPriority;
    }

    public void setpPriority(int pPriority) {
        this.pPriority = pPriority;
    }

    public void setWaitTime(int id) {
        if((this.pId==id)) {
            if(this.waitTime>0) {
                this.waitTime = this.waitTime-2;
            }
        }else{
            this.waitTime = this.waitTime+2;
        }
    }

    public double getHrrn() {
        return hrrn;
    }

    public void setHrrn (double hrrn) {
        this.hrrn = hrrn;
    }

    PCB(){
        this.waitTime = 0;
    }

    public PCB getNext() {
        return next;
    }

    public int getEstimatesRunningTime() {
        return estimatesRunningTime;
    }

    public void setEstimatesRunningTime (int estimatesRunningTime) {
        this.estimatesRunningTime = estimatesRunningTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime (int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWeightedTurnaroundTime() {
        return weightedTurnaroundTime;
    }

    public void setWeightedTurnaroundTime (int weightedTurnaroundTime) {
        this.weightedTurnaroundTime = weightedTurnaroundTime;
    }

    public void setNext(PCB next) {
        this.next = next;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime (int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime (int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime (int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime (int endTime) {
        this.endTime = endTime;
    }

    public int getpState() {
        return pState;
    }

    public void setpState(int pState) {
        this.pState = pState;
    }
}
