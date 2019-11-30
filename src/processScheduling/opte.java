package processScheduling;

import java.util.Queue;

public class opte {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PCB[] ready=new PCB[3];
        for(int i=0;i<ready.length;i++) {
            ready[i]=new PCB();
            ready[i].setServiceTime(1);
            ready[i].setpId(i);
        }
        //初始化
        schedulingMethod pr=new schedulingMethod();
		//优先级
		Queue<PCB> readyQueue = pr.PSA_algorithm(ready);
		while(!readyQueue.isEmpty()){
			System.out.println(readyQueue.poll().getpPriority());
		}
//
//		//短作业
//		Queue<PCB> readyQueue=pr.SJF_algorithm(ready);
//		while(!readyQueue.isEmpty()){
//			System.out.println(readyQueue.poll().getServiceTime());
//		}
//        //hrrn
//        PCB rea=new PCB();
//
//        for(int j=0;j<15;j++) {
//
//            rea=pr.HNNR_algorithm(ready);
//            System.out.println("rea的id: "+rea.id);
////			for(int i=0;i<ready.length;i++) {
////				ready[i].setWait_time(rea.id);
////			}
//        }
    }
}
