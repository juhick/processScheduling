package processScheduling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Window extends JFrame{

    //线程
    private Thread thread;
    //确定按钮
    private JButton confirm;
    //停止按钮
    private JButton stop;
    //响应比优先调度算法
    private JRadioButton hnnrButton;
    //先来先服务算法
    private JRadioButton fifoButton;
    //短作业优先
    private JRadioButton sjfButton;
    //优先级调度算法
    private JRadioButton psaButton;
    //优先级输入框
    private JTextField inputPriorityMidTop;
    private JTextField inputPriorityMid;
    private JTextField inputPriorityMidBottom;
    //服务时间输入框
    private JTextField inputTimeMidTop;
    private JTextField inputTimeMid;
    private JTextField inputTimeMidBottom;
    //进程标签
    private JLabel proOneTarLeft;
    private JLabel proOneTarRight;
    private JLabel proTwoTarLeft;
    private JLabel proTwoTarRight;
    private JLabel proThreeTarLeft;
    private JLabel proThreeTarRight;
    //显示信息
    private JTextArea showMes;
    //按钮组
    private ButtonGroup group;
    //网格面板
    private JPanel top;
    private JPanel midGroup;
    private JPanel midTop;
    private JPanel mid;
    private JPanel midBottom;
    private JPanel bottom;
    private JPanel controllButton;
    //滑动条面板
    private JScrollPane showMesScroll;
    //对话框
    private JDialog dialog;
    //对话框图标
    private JLabel exceptionImage;

    //相关数据
    //优先级
    private int[] proOnePsa;
    private int proTwoPsa;
    private int proThreePsa;
    //服务时间
    private int[] proOneSjf;
    private double proTwoSjf;
    private double proThreeSjf;
    //算法先择标志
    private int target;
    //输出信息变量
    private Message message;

    public Window() {

        //数据初始化
        dataInitializer();
        //设置窗口图标
        setWindowImage();
        //组件初始化
        componentInitializer();
        //对话框属性设置
        setDialogProperty();
        //设置面板的内容
        setPaneLayout();
        //为组件添加事件处理监听器
        addButtonListener();
        //获取内容窗格
        Container con = this.getContentPane();
        //添加布局和组件
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(top);
        con.add(midGroup);
        con.add(bottom);
        con.add(controllButton);
        //设置窗口相关属性
        this.setBounds(400,200,500,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //调用相关函数
        /*
         * 	将返回信息传递给message变量以显示于文本域
         * 	message = &{method} + "\n";
         * 	showMes.setText(message);
         *
         */
    }

    //数据初始化器
    private void dataInitializer() {
        proOneSjf = new int[3];
        proTwoSjf = 0;
        proThreeSjf = 0;
        proOnePsa = new int[3];
        proTwoPsa = 0;
        proThreePsa = 0;
        message = new Message();
    }

    //组件初始化器
    private void componentInitializer() {


        //初始化确定按钮
        confirm = new JButton("确定");
        //响应比优先调度算法选择按钮
        hnnrButton = new JRadioButton("高响应比");
        //短作业优先算法选择按钮
        sjfButton = new JRadioButton("短作业优先");
        //优先级调度算法算法选择按钮
        psaButton = new JRadioButton("优先级");
        //初始化按钮组
        group = new ButtonGroup();
        //将上述四个单选按钮添加至统一按钮组
        group.add(hnnrButton);
        group.add(sjfButton);
        group.add(psaButton);
        //优先级输入框
        inputPriorityMidTop = new JTextField();
        inputPriorityMid = new JTextField();
        inputPriorityMidBottom = new JTextField();
        //服务时间输入框
        inputTimeMidTop = new JTextField();
        inputTimeMid = new JTextField();
        inputTimeMidBottom = new JTextField();
        //进程标签
        proOneTarLeft = new JLabel("进程1->优先级：");
        proOneTarRight = new JLabel("服务时间：");
        proTwoTarLeft = new JLabel("进程2->优先级：");
        proTwoTarRight = new JLabel("服务时间：");
        proThreeTarLeft = new JLabel("进程3->优先级：");
        proThreeTarRight = new JLabel("服务时间：");
        //显示信息
        showMes = new JTextArea();
        showMes.setEditable(false);
        //对话框显示
        dialog = new JDialog(this,"选择有误",true);
        //标签初始化
        exceptionImage = new JLabel();
    }

    //设置面板格式
    private void setPaneLayout() {

        //初始化面板
        midTop = new JPanel();
        midTop.setLayout(new BoxLayout(midTop,BoxLayout.X_AXIS));//new GridLayout(1,4)
        mid = new JPanel();
        mid.setLayout(new BoxLayout(mid,BoxLayout.X_AXIS));
        midBottom = new JPanel();
        midBottom.setLayout(new BoxLayout(midBottom,BoxLayout.X_AXIS));
        midGroup = new JPanel(new GridLayout(3,1));

        top = new JPanel(new BorderLayout());
        bottom = new JPanel();
        controllButton = new JPanel();
        showMesScroll = new JScrollPane(showMes);
        showMesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //设置各个面板的相关属性
        midGroup.setBorder(BorderFactory.createTitledBorder("请输入优先级和服务时间"));
        showMes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottom.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //向面板中添加组件
        top.add(showMesScroll);
        //进程1
        midTop.add(proOneTarLeft);
        midTop.add(inputPriorityMidTop);
        midTop.add(proOneTarRight);
        midTop.add(inputTimeMidTop);
        //进程2
        mid.add(proTwoTarLeft);
        mid.add(inputPriorityMid);
        mid.add(proTwoTarRight);
        mid.add(inputTimeMid);
        //进程3
        midBottom.add(proThreeTarLeft);
        midBottom.add(inputPriorityMidBottom);
        midBottom.add(proThreeTarRight);
        midBottom.add(inputTimeMidBottom);

        midGroup.add(midTop);
        midGroup.add(mid);
        midGroup.add(midBottom);

        bottom.add(hnnrButton);
        bottom.add(sjfButton);
        bottom.add(psaButton);
        bottom.add(confirm);

        controllButton.add(confirm);
    }

    //设置窗口图标
    private void setWindowImage() {
        Toolkit kt = Toolkit.getDefaultToolkit();
        Image img = kt.getImage("RES/label.png");
        this.setIconImage(img);
    }

    //设置对话框相关信息
    private void setDialogProperty() {

        //设置图标
        Toolkit kt = Toolkit.getDefaultToolkit();
        Image img = kt.getImage("RES/label.png");
        dialog.setIconImage(img);
        //设置大小
        dialog.add(exceptionImage);
        dialog.setBounds(400,200,500,400);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    //添加监听器
    private void addButtonListener() {
        //为确定按钮添加事件监听器
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                //获取优先级输入框参数
                String PriorityMidTopMes = inputPriorityMidTop.getText();
                String PriorityMidMes = inputPriorityMid.getText();
                String PriorityMidBottomMes = inputPriorityMidBottom.getText();
                //获取服务时间输入框参数
                String TimeMidTopMes = inputTimeMidTop.getText();
                String TimeMidMes = inputTimeMid.getText();
                String TimeMidBottomMes = inputTimeMidBottom.getText();

                //获取单选按钮结果
                if(hnnrButton.isSelected()) {
                    target = 1;
                    System.out.println(target);
                }else if(sjfButton.isSelected()) {
                    target = 3;
                    System.out.println(target);
                }else if(psaButton.isSelected()) {
                    target = 4;
                    System.out.println(target);
                }else {
                    //输出对话框，重置数据
                    System.out.println("未选中");
                    exceptionImage.setIcon(new ImageIcon("src/ExceptionNoSelect.jpg"));
                    dialog.setVisible(true);
                    return;
                }

                //判断获取的参数是否为空
                if(isEmpty(PriorityMidTopMes)||isEmpty(PriorityMidMes)||
                        isEmpty(PriorityMidBottomMes)||isEmpty(TimeMidTopMes)||
                        isEmpty(TimeMidMes)||isEmpty(TimeMidBottomMes)) {
                    //输出对话框，重置数据
                    exceptionImage.setIcon(new ImageIcon("src/ExceptionNoSelect.jpg"));
                    dialog.setVisible(true);
                    return;
                }else {
                    try {
                        /*
                         * 将各个进程所需要的数据转换类型
                         * 并且判断格式是否正确
                         */
                        //进程优先级
                        proOnePsa[0] = Integer.parseInt(PriorityMidTopMes);
                        proOnePsa[1] = Integer.parseInt(PriorityMidMes);
                        proOnePsa[2] = Integer.parseInt(PriorityMidBottomMes);
                        //进程服务时间
                        proOneSjf[0] = Integer.parseInt(TimeMidTopMes);
                        proOneSjf[1] = Integer.parseInt(TimeMidMes);
                        proOneSjf[2] = Integer.parseInt(TimeMidBottomMes);

                    }catch(Exception exception) {
                        exceptionImage.setIcon(new ImageIcon("src/ExceptionNumberFormat.jpg"));
                        dialog.setVisible(true);
                        return;
                    }

                    //进程函数
                    /*
                     *	在此处调用设置优先级和服务时间的函数
                     * 	启动进程执行
                     * 	以及切换进程调度算法
                     *
                     */

                    System.out.println("----进程管理系统启动----");
                    int[] currentTime = {0};//设置当前时间
                    int[] serviceTime = {6, 6, 3};
                    int timeSlice = 2;
                    PCB[] pcb=new PCB[3];
                    semChange sem = new semChange();//信号量控制
                    controlStructure cs = new controlStructure();//进程阻塞和唤醒
                    slot schedule = new slot();//进程调度
                    slotSuspend suspend = new slotSuspend();
                    schedulingMethod pr=new schedulingMethod();//调度方法
                    //进程初始化
                    for (int i = 0; i < 3; i++){
                        pcb[i] = new PCB();
                        pcb[i].setpId(i + 1);
                        pcb[i].setArriveTime(0);
                        pcb[i].setEstimatesRunningTime(proOneSjf[i]);
                        pcb[i].setpPriority(proOnePsa[i]);
                        pcb[i].setRunningStep(0);
                    }

                    Queue<PCB> readyQueue = new LinkedList<>();

                    switch (target){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            readyQueue = pr.SJF_algorithm(pcb);
                            break;
                        case 4:
                            readyQueue = pr.PSA_algorithm(pcb);
                            break;
                    }


                    process ps = new process(sem, suspend, readyQueue, timeSlice);
                    if(target == 3 || target == 4){

                        PCB nowP;

                        while((nowP = schedule.select(readyQueue, message)) != null){
                            ps.setP(nowP);
                            switch (nowP.getpId()){
                                case 1:
                                    ps.process1(currentTime, message);
                                    break;
                                case 2:
                                    ps.process2(currentTime, message);
                                    break;
                                case 3:
                                    ps.process3(currentTime, message);
                                    break;
                            }
                            showMes.setText(message.getMessage());
                        }
                    }else{
                        PCB nowP;
                        while((nowP = pr.HNNR_algorithm(pcb)) != null){
                            ps.setP(nowP);
                            switch (nowP.getpId()){
                                case 1:
                                    ps.process1(currentTime, message);
                                    break;
                                case 2:
                                    ps.process2(currentTime, message);
                                    break;
                                case 3:
                                    ps.process3(currentTime, message);
                                    break;
                            }
                            int count = 0;
                            for (int i = 0; i < pcb.length; i++){
                                if(pcb[i].getEstimatesRunningTime() != 0){
                                    count++;
                                }
                            }
                            if(count == 0){
                                System.out.println("所有进程运行完成");
                                message.setMessage("所有进程运行完成\n");
                                break;
                            }
                            if(count != pcb.length){
                                PCB[] nPcb = new PCB[count];
                                int t = 0;
                                for (int i = 0; i < pcb.length; i++){
                                    if(pcb[i].getEstimatesRunningTime() != 0){
                                        nPcb[t++] = pcb[i];
                                    }
                                }
                                pcb = new PCB[count];
                                pcb = nPcb;
                            }
                            showMes.setText(message.getMessage());
                        }

                    }
                }
            }

        });
    }

    //判断信息是否为空
    private static boolean isEmpty(String mes) {

        boolean target = false;

        if(mes == null) {
            target = true;
        }

        return target;

    }
}