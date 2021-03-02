package edu.neu.coe.info6205.union_find_alternative;

import java.util.concurrent.ThreadLocalRandom;

public class UF_ALT_CLIENT extends WQUPC_ALT{


    //Calling WQUPC_ALT passing n objects and default PathCompression is true.
    public UF_ALT_CLIENT(int n,boolean compress) {
        super(n,compress);
    }

    public int count(int n){
        int connections = 0;
         while (count()!=1){
            int firstNum = ThreadLocalRandom.current().nextInt(0, n);
            int secondNum =ThreadLocalRandom.current().nextInt(0, n);
            while(secondNum==firstNum){
                secondNum =ThreadLocalRandom.current().nextInt(0, n);
            }
            //System.out.println("\nconnect("+firstNum+","+secondNum+")");
            union(firstNum,secondNum);
            connections+=1;
        }
    /*   System.out.println("union(0,2)");union(0,2);
        connections+=1;
        System.out.println("union(6,7)");union(6,7);
        connections+=1;
        System.out.println("union(4,6)");union(4,6);
        connections+=1;
        System.out.println("union(3,5)");union(3,5);
        connections+=1;
        System.out.println("union(0,2)");union(0,2);
        connections+=1;
        System.out.println("union(3,6)");union(3,6);
        connections+=1;
        System.out.println("union(7,1)");union(7,1);
        connections+=1;
        System.out.println("union(9,8)");union(9,8);
        connections+=1;
        System.out.println("union(1,9)");union(1,9);
        connections+=1;
        System.out.println("union(2,8)");union(2,8);
        connections+=1;
*/
        return connections;
    }
    public static void main(String[] args) {
       int[] inputList =new int[] {10,20,40,80,160};
        System.out.println("*****************************************************\n");
        System.out.println("Alternate Quick Union without Path Compression:\n");
        for(int i: inputList) {
            edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT client = new edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT(i,false);
      //    client.show();
            int result = client.count(i);
            System.out.println("For "+i+" objects, number of connections ="+result);
         //  client.show();
        }
        System.out.println("*****************************************************\n");
        System.out.println("Alternate Quick Union with Path Compression:\n");
        for(int i: inputList) {
            edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT client = new edu.neu.coe.info6205.union_find_alternative.UF_ALT_CLIENT(i,true);
         //   client.show();
            int result = client.count(i);
            System.out.println("For "+i+" objects, number of connections ="+result);
         //   client.show();
        }
    }

}
