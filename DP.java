import java.util.function.IntFunction;
import java.util.Arrays;

public class DynamicProgramming{
    public static void main(String args[]){
        String str1="abcdgefghijkk";
        String str2="abedgfghijkm";
        int dp[][]=new int[5][5];
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j<5 ; j++){
                dp[i][j]=-1;
            }
        } 
        int ar1[]={1,2,3,4,3};
        System.out.println(mcmTab(ar1));
        
    }
    //climbing stairs memorisation is same as fiboancci memorisation
    public static int  climbingStairs(int n){
        int dp[]= new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i = 2 ; i<=n ; i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int knapsack(int val[],int wt[],int W,int n,int dp[][]){
        if(W==0 || n==0){
            return 0;
        }
        if(dp[n][W]!=-1){
            return dp[n][W];
        }
        if(wt[n-1]<=W){
            int ans1 = val[n-1]+knapsack(val,wt,W-wt[n-1],n-1,dp);
            int ans2 = knapsack(val,wt,W,n-1,dp);
            dp[n][W] = Math.max(ans1,ans2);
            return dp[n][W];
        }
        else{
            dp[n][W] = knapsack(val,wt,W,n-1,dp);
            return dp[n][W];
        }
    }

    public static int knapsackTab(int val[],int wt[],int W){
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        for(int i = 0 ; i<dp.length ; i++){
            dp[i][0]=0;
        }
        for(int i = 0 ; i<dp[0].length ; i++){
            dp[0][i]=0;
        }

        for(int i = 1 ; i<=n ; i++){
            for(int j = 1 ; j<=W ; j++){
                int v = val[i-1];
                int w = wt[i-1];
                if(w<=j){
                    int incProfit = v+ dp[i-1][j-w];
                    int excProfit = dp[i-1][j];
                    dp[i][j]=Math.max(incProfit,excProfit);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static boolean targetSumSubset(int arr[],int sum){
        int n = arr.length;
        boolean dp[][]=new boolean[n+1][sum+1];
        for(int i = 0 ; i<n+1 ; i++){
            dp[i][0]=true;
        }

        for(int i = 1 ; i<n+1 ; i++){
            for(int j = 1 ; j<sum+1 ; j++){
                int v = arr[i-1];
                if(v<=j && dp[i-1][j-v]==true){
                    dp[i][j]=true;
                }
                else if(dp[i-1][j]==true){
                    dp[i][j]=true;
                }
            }
        }
        return dp[n][sum];
    }

    public static int knapsackUnbound(int val[],int wt[],int W){
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        for(int i = 0 ; i<dp.length ; i++){
            dp[i][0]=0;
        }
        for(int i = 0 ; i<dp[0].length ; i++){
            dp[0][i]=0;
        }
        for(int i = 1 ; i<=n ; i++){
            for(int j = 1 ; j<=W ; j++){
                int v = val[i-1];
                int w = wt[i-1];
                if(w<=j){
                    int incProfit = v+ dp[i][j-w];
                    int excProfit = dp[i-1][j];
                    dp[i][j]=Math.max(incProfit,excProfit);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        print(dp);
        return dp[n][W];
    }

    public static void print(int dp[][]){
        for(int i = 0 ; i< dp.length ; i++){
            for(int j = 0 ; j < dp[0].length ; j++){
                if(dp[i][j]/10==0){
                    System.out.print(dp[i][j]+"   ");
                }
                else if(dp[i][j]/100 == 0){
                    System.out.print(dp[i][j]+"  ");
                }
                else if(dp[i][j]/1000 == 0){
                    System.out.print(dp[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    public static int coinChange(int coins[],int sum){
        int n = coins.length;
        int dp[][]=new int[n+1][sum+1];

        for(int i = 0 ; i<n+1 ; i++){
            dp[i][0]=1;
        }

        for(int i = 1 ; i<n+1 ; i++){
            for(int j = 1 ; j<sum+1 ; j++){
                if(coins[i-1]<=j){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    public static int  LCS(String str1,String str2,int n , int m,int dp[][]){
        if(n==0 || m==0){
            return 0;
        }

        if(dp[n][m]!=-1){
            return dp[n][m];
        }

        if(str1.charAt(n-1)==str2.charAt(m-1)){
            dp[n][m] = LCS(str1,str2,n-1,m-1,dp)+1;
            return dp[n][m];
        }
        else{
            dp[n][m] = Math.max(LCS(str1,str2,n-1,m,dp),LCS(str1,str2,n,m-1,dp));
            return dp[n][m];
        }
    }
    
    public static int LCSTab(String str1,String str2,int n ,int m){
        int dp[][]=new int[n+1][m+1];

        for(int i = 1 ; i<n+1 ; i++){
            for(int j = 1 ; j<m+1 ; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

    public static int LCSubString(String str1,String str2,int n ,int m){
        int dp[][]=new int[n+1][m+1];
        int max=0;
        for(int i = 1 ; i<n+1 ; i++){
            for(int j = 1 ; j<m+1 ; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                    max=Math.max(max,dp[i][j]);
                }
                else{
                    dp[i][j]=0;
                }
            }
        }

        return max;
    }
    
    public static int LIS(int ar2[]){ //longest increasing subsequence
        int ar1[]=ar2;
        Arrays.sort(ar2);
        int dp[][]=new int[ar1.length+1][ar2.length+1];

        for(int i = 1 ; i< ar1.length+1 ; i++){
            for(int j = 1 ; j<ar2.length+1 ; j++){
                if(ar1[i-1]==ar2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[ar1.length][ar2.length];
    }

    public static int catalanRec(int n,int dp[]){
        if(dp[n]!=-1){
            return dp[n];
        }
        if(n==0 || n==1){
            return 1;
        }
        int ans=0;
        for(int i = 0 ; i<n ; i++){
            ans+=catalanRec(i,dp)*catalanRec(n-i-1,dp);
        }

        return ans;
    }

    public static int catalanTab(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i = 2 ; i<=n ; i++){
            for(int j = 0 ; j<i ; j++){
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
    
    public static int mcm(int ar[],int i ,int j){
        if(i==j){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i ; k<j ; k++){
            int cost1 = mcm(ar,i,k);
            int cost2 = mcm(ar,k+1,j);
            int cost3 = ar[i-1]*ar[k]*ar[j];
            ans = Math.min(ans,cost1+cost2+cost3);
        }
        return ans;
    }

    public static int mcmM(int ar[],int i ,int j,int dp[][]){
        if(i==j){
            return 0;
        }

        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i ; k<j ; k++){
            int cost1 = mcmM(ar,i,k,dp);
            int cost2 = mcmM(ar,k+1,j,dp);
            int cost3 = ar[i-1]*ar[k]*ar[j];
            dp[i][j] = Math.min(ans,cost1+cost2+cost3);
        }
        return dp[i][j];
    }

    public static int mcmTab(int ar[]){
        int n = ar.length;
        int dp[][]=new int[n][n];
        for(int len=2 ; len<n ; len++){
            for(int i = 1 ; i<=n-len ; i++){
                int j = i+len-1;
                int ans=Integer.MAX_VALUE;
                for(int k = i ; k<j ; k++){
                    ans=Math.min(ans,dp[i][k]+dp[k+1][j]+ar[i-1]*ar[k]*ar[j]);
                }
                dp[i][j]=ans;
            }
        }
        return dp[1][n-1];
         
    }

    

}
