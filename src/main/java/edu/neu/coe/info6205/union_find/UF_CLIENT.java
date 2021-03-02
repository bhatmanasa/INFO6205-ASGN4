package edu.neu.coe.info6205.union_find;
import java.util.concurrent.ThreadLocalRandom;
public class UF_CLIENT extends UF_HWQUPC{

    //Calling UF_HWQUPC passing n objects and default PathCompression is true.
    public UF_CLIENT(int n) {
        super(n);
    }

    public int count(int n){
        int connections = 0;
        while (components()!=1){
       //     int random = ThreadLocalRandom.current().nextInt(0, n);
            int firstNum =ThreadLocalRandom.current().nextInt(0, n);
            int secondNum =ThreadLocalRandom.current().nextInt(0, n);
            while(secondNum==firstNum){
                secondNum =ThreadLocalRandom.current().nextInt(0, n);
            }
         //   System.out.println("first="+firstNum+"second="+secondNum);
            connect(firstNum,secondNum);
            connections+=1;
        }
        return connections;
    }
    public static void main(String[] args) {
        int[] inputList =new int[] {10,20,40,80,160};

        for(int i: inputList) {
            UF_CLIENT client = new UF_CLIENT(i);
            int result = client.count(i);
            System.out.println("For "+i+" objects, number of connections ="+result);
        }
    }

}
