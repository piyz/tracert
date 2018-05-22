package by.matrosov.tracert;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tracert {

    private static final int MAX_THREADS = 5;

    public static void main(String[] args) {
        Set<String> ipSet = new HashSet<>();
        initIpSet(ipSet);

        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

        for (String ip : ipSet) {
            pool.execute(new Task(ip));
        }

        pool.shutdown();
    }

    private static void initIpSet(Set<String> set){
        //GER
        set.add("37.61.218.17");
        set.add("85.195.82.22");
        set.add("85.195.114.95");
        set.add("89.163.144.220");
        set.add("89.163.145.60");
        set.add("89.163.145.167");
        set.add("89.163.251.146");
        set.add("146.0.32.83");
        set.add("89.163.140.230");
        set.add("146.0.247.68");
        //NED
        set.add("46.166.136.90");
        set.add("46.166.189.26");
        //FRA
        set.add("62.210.244.82");
        //SWE
        set.add("31.3.153.19");
    }
}
