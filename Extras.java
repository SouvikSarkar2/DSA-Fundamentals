import java.util.*;

public class SpiralMatrix{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int matrix[][]={{1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,16}};
        spiral(matrix);
    }
    public static void spiral(int matrix[][]){
        int sr=0;
        int sc=0;
        int er=matrix.length-1;
        int ec=matrix[0].length-1;
        while(sr<=er && sc<=ec){
            //top 
            for(int i = sc ; i<=ec; i++){
                System.out.print(matrix[sr][i]+" ");
            }
            //right
            for(int i = sr+1 ; i<=er ; i++){
                System.out.print(matrix[i][ec]+" ");
            }
            //bottom
            for(int i=ec-1 ; i>=sc ; i--){
                if(sr==er){
                    return;
                }
                System.out.print(matrix[er][i]+" ");
            }
            //left
            for(int i = er-1 ; i>sr ; i--){
                if(ec==sc){
                    return;
                }
                System.out.print(matrix[i][sc]+" ");
            }
            sr++;
            sc++;
            er--;
            ec--;
            
        }
    }
}
















import java.util.*;
public class FractionalKnapsack{
    public static void main(String args[]){
        int val[]={60,100,120};
        int weight[]={10,20,30};
        int W = 50;

        double ratio[][]=new double[val.length][2];

        for(int i = 0 ; i < val.length ; i++){
            ratio[i][0]=i;
            ratio[i][1]=(double)val[i]/(double)weight[i];
        }
        Arrays.sort(ratio,Comparator.comparingDouble(o -> o[1]));
        int capacity = W;
        int value=0;
        for(int i = ratio.length-1 ; i>=0 ; i--){
            int idx=(int)ratio[i][0];
            if(capacity>=weight[idx]){
                capacity=capacity-weight[idx];
                value=value+val[idx];
            }
            else{
                value+=capacity*ratio[i][1];
                break;
            }
        }
        System.out.println(value);
    }
}












public static int search(int arr[],int tar,int si,int ei){

        if(si>ei){
            return -1;
        }

        int mid = (si+ei)/2;

        if(arr[mid]==tar){
            return mid;
        }

        if(arr[si]<=arr[mid]){
            if(arr[si]<=tar && tar<=arr[mid]){
                return search(arr,tar,si,mid-1);
            }
            else{
                return search(arr,tar,mid+1,ei);
            }
        }
        else{
            if(arr[mid]<=tar && tar<=arr[ei]){
                return search(arr,tar,mid+1,ei);
            }
            else{
                return search(arr,tar,si,mid-1);
            }
        }
    }
