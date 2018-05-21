package by.matrosov.tracert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void run() {
        try{
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("tracert " + name);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("cp866")));

            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            System.out.println(list.get(list.size() - 3));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
