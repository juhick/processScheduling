package processScheduling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class process implements Runnable {
    @Override
    public void run() {
        //TODO 进程内容
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(""));

            String content = br.readLine();
            while(content != null){
                System.out.println(content);
                content = br.readLine();
            }

            br.close();
        }catch (FileNotFoundException e){
            System.out.println("系统找不到指定的文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
