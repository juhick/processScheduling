package processScheduling;
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
		ready=pr.PSA_algorithm(ready);
		for(int i=0;i<ready.length;i++) {
			System.out.println(ready[i].getpPriority());
		}
//
//		//短作业
//		ready=pr.SJF_algorithm(ready);
//		for(int i=0;i<ready.length;i++) {
//			System.out.println(ready[i].getServiceTime());
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
