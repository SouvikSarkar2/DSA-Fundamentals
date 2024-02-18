import java.util.*;
public class SubarraySum{
    public static void main(String args[]){
      Scanner sc = new Scanner(System.in);
      int array[]={2,4,7,-2,6};
      System.out.print(kadanes(array));
    


    }
    public static int brute(int array[]){
        int max=Integer.MIN_VALUE;
        for(int start = 0 ; start<array.length ; start++){
            for(int end = start ; end<array.length ; end++){
                int mss=0;
              for(int sum = start ; sum<=end ; sum++){
                
                mss+=array[sum];
              }
              max=Math.max(max,mss);
              
            }
        }
           return max;
    }
    public static int prefix(int array[]){
        int max=Integer.MIN_VALUE;
        int prefix[]=new int[array.length];
        prefix[0]=array[0];
        for(int i = 1 ; i<array.length ; i++){
        prefix[i]=prefix[i-1]+array[i];    
        }
        for(int start=0 ; start<array.length ; start++){
            int ss;
            for(int end=start ; end<array.length ; end++){
                
                if(start==0){
                 ss = prefix[end];
                
                }
                else{
                     ss = prefix[end]-prefix[start-1];
                }
                max=Math.max(ss,max);
            }
            
            
        }
       return max;

    }
    public static int kadanes(int array[]){
        int cs=0;
        int ms=Integer.MIN_VALUE;
      for(int i = 0 ; i<array.length ; i++){
        
        cs=cs+array[i];
        if(cs<0){
            cs=0;
        }
        ms=Math.max(cs,ms);
      }



        return ms;
    }
}
