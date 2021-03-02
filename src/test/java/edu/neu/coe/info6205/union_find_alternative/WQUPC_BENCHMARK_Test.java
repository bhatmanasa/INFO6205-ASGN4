package edu.neu.coe.info6205.union_find_alternative;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.union_find.UF_CLIENT;
import edu.neu.coe.info6205.union_find.WQUPC;
import edu.neu.coe.info6205.util.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WQUPC_BENCHMARK_Test {
//Class will benchmark the timings for Weighted Quick Union by height  with Path Compression.

    List<Integer> list = new ArrayList<>();
    Integer[] xs = null;
    final int zzz = 20;
    int n=10; //
    int obj=0;// Number of objects to be used in union find to structure it to a tree.
    int result;
    @Test
    public void WQUPCTest() throws Exception {

        final Timer timer = new Timer();
        final double mean= timer.repeat( n, () -> zzz, t -> {
                UF_CLIENT client = new UF_CLIENT(obj);
                result = client.count(obj);
           //     System.out.println("For "+obj+" objects, number of connections ="+result);
            return null;
        }, t -> {
            obj=40;
            return t;
        }, t ->logger.trace("WQUPC Complete!*"));
        System.out.println("Weighted Quick Union By Height with Path Compression MeanTime="+mean+"; Objects = 40 ; connections="+result);
    }
    @Test
    public void WQUFDepthTest() throws Exception {

        final Timer timer = new Timer();
        final double mean= timer.repeat( n, () -> zzz, t -> {
            edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT client = new edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT(obj,false);
            result = client.count(obj);
       //         System.out.println("For "+obj+" objects, number of connections ="+result);
            return null;
        }, t -> {
            obj=40;
            return t;
        }, t ->logger.trace("WQUPC_ALT Complete!*"));
        System.out.println("Weighted Quick Union By Depth without Path Compression MeanTime="+mean+"; Objects = 40 ; connections="+result);
    }

    @Test
    public void WQUPCDepthTest() throws Exception {

        final Timer timer = new Timer();
        final double mean= timer.repeat( n, () -> zzz, t -> {
            edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT client = new edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT(obj,true);
            result = client.count(obj);
    //        System.out.println("For "+obj+" objects, number of connections ="+result);
            return null;
        }, t -> {
            obj=40;
            return t;
        }, t ->logger.trace("WQUPC_ALT Complete!*"));
        System.out.println("Weighted Quick Union By Depth with Path Compression MeanTime="+mean+"; Objects = 40 ; connections="+result);
    }

    @Test
    public void UFTest() throws Exception {

        final Timer timer = new Timer();
        final double mean= timer.repeat( n, () -> zzz, t -> {
            WQUPC h = new WQUPC(obj);
            int connections = 0;
            while (h.count()!=1){
                int firstNum = ThreadLocalRandom.current().nextInt(0, obj);
                int secondNum =ThreadLocalRandom.current().nextInt(0, obj);
                while(secondNum==firstNum){
                    secondNum =ThreadLocalRandom.current().nextInt(0, obj);
                }
                h.union(firstNum,secondNum);
                connections+=1;
            }

            result= connections;
            return null;
        }, t -> {
            obj=40;
            return t;
        }, t ->logger.trace("UFCLIENT Complete!*"));
        System.out.println("Standard Quick Union by size without path compression MeanTime="+mean+"; Objects = 40 ; connections="+result);
    }

    final static LazyLogger logger = new LazyLogger(InsertionSort.class);


}
