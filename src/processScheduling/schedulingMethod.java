package processScheduling;

class schedulingMethod {
    PCB[] PSA_algorithm(PCB[] ready){//优先级
        for(int i=0;i<ready.length;i++) {
            for(int j=i;j<ready.length;j++) {
                if(ready[i].getpPriority()>ready[j].getpPriority()) {
                    PCB mid=ready[i];
                    ready[i]=ready[j];
                    ready[j]=mid;
                }
            }
        }
        return ready;
    }
    PCB[] SJF_algorithm(PCB[] ready){//短作业
        for(int i=0;i<ready.length;i++) {
            for(int j=i;j<ready.length;j++) {
                if(ready[i].getServiceTime()>ready[j].getServiceTime()) {
                    PCB mid=ready[i];
                    ready[i]=ready[j];
                    ready[j]=mid;
                }
            }
        }
        return ready;
    }
    PCB HNNR_algorithm(PCB[] ready){//高响应比
        //计算响应比
        for(int i=0;i<ready.length;i++) {
            int wt=ready[i].getWaitTime();
            int sj=ready[i].getServiceTime();
            double hr=1+wt/sj;
            System.out.print(hr+" ");
            ready[i].setHrrn(hr);
        }
        //找最大那个
        PCB re=new PCB();
        re=ready[0];
        for(int i=0;i<ready.length;i++) {
            if(re.getHrrn()<ready[i].getHrrn()) {
                re=ready[i];
            }
        }
        //修改等待时间
        for(int i=0;i<ready.length;i++) {
            ready[i].setWaitTime(re.getpId());
        }
        return re;
    }
}