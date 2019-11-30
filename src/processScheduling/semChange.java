package processScheduling;

public class semChange {
    private int S1;								//互斥资源S1

    public int getS1() {
        return S1;
    }

    public void setS1(int s1) {
        S1 = s1;
    }

    public int getS2() {
        return S2;
    }

    public void setS2(int s2) {
        S2 = s2;
    }

    private int S2;								//互斥资源S2
    semChange() {
        S1=1;
        S2=1;
    }
    public synchronized void Wait(String a) {	//P操作
        if(a.equals("S1")){
            while(S1<=0) {}
            S1--;
        }
        else if(a.equals("S2")) {
            while(S2<=0) {}
            S2--;
        }
        else {
            System.out.println("不存在该资源");
        }
    }

    public synchronized void Signal(String a) {	//V操作
        if(a.equals("S1")){
            S1++;
        }
        else if(a.equals("S2")) {
            S2++;
        }
        else {
            System.out.println("不存在该资源");
        }
    }
}
