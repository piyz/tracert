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
                if (!line.replaceAll(" ", "").isEmpty()){
                    list.add(line);
                }
            }
            System.out.println(list.get(list.size() - 3) + "----->" + takeHighPingFromTheLine(list));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String takeHighPingFromTheLine(List<String> list){
        int globalSum = 0;
        String globalLine = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("*") || list.get(i).contains("<") || list.get(i).contains("Трассировка") || list.get(i).contains("максимальным")){

            }else {

                String[] arr = list.get(i).split("ms");
                int sumPing = 0;
                for (int j = 0; j < 3; j++) {
                    if (j == 0){
                        sumPing = sumPing + calculatePing1(arr[j]);
                    }
                    sumPing = sumPing + calculatePing(arr[j]);
                }

                if (sumPing > globalSum){
                    globalSum = sumPing;
                    globalLine = list.get(i);
                }
            }
        }
        return globalLine;
        //System.out.println(globalLine);
    }

    private static int calculatePing(String s){
        s = s.trim().replaceAll(" ", "");
        return Integer.parseInt(s);
    }

    private static int calculatePing1(String s){
        s = s.trim();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }

        String[] arr = s.split(sb.toString());

        return Integer.parseInt(arr[1]);
    }
}
