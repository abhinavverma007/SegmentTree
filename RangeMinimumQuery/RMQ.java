/*Problem Link*/
/* https://practice.geeksforgeeks.org/problems/range-minimum-query/1 */

/*Check AC code */

//https://ideone.com/tDL4nL
import java.util.*;
import java.lang.*;

class Minimum
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int n = sc.nextInt();
            int arr[] = new int[n];
            
            for(int i = 0; i < n; i++)
              arr[i] = sc.nextInt();
              
              
            int Q = sc.nextInt();
            
            
            int st[] = constructST(arr, n);
            int l,r;
            for(int i = 0; i < Q; i++)
            {
                int a = sc.nextInt();
                int b = sc.nextInt();
                l = Math.min(a,b);
                r = Math.max(a,b);
                
                System.out.print(RMQ(st, n, l, r) + " ");
                
            }
            System.out.println();
        }
    }
// } Driver Code Ends



/* The functions which 
builds the segment tree */

    static int st[]=new int[100000];
    
    
    public static int[] constructST(int arr[], int n)
    {
        // Add your code here
        constructSThelper(arr,n,1,0,n-1);
        return st;
        
    }
    public static void constructSThelper(int arr[], int n, int idx, int start,int end)
    {
        // Add your code here
        if(start>end){
            return ;
        }
        if(start==end){
            st[idx]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        constructSThelper(arr,n,2*idx,start,mid);
        constructSThelper(arr,n,2*idx+1,mid+1,end);
        st[idx]=Math.min(st[2*idx],st[2*idx+1]);
        return;
    }
    
    
    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int st[], int n, int l, int r)
    {
       // Add your code here
       return RMQhelper(st,n,1,0,n-1,l,r);
       
    }
    public static int RMQhelper(int st[], int n, int idx, int start,int end,int l, int r)
    {
       // Add your code here
       // Our query lies out side [l,r,start,end] Completely outside
       if(l>end || r<start){
           return Integer.MAX_VALUE;
       }
       //Our query is [start, l,r, end] Completely inside
       if(start>=l && end<=r){
           return st[idx];
       }
       int mid=(start+end)/2;
       int left=RMQhelper(st,n,2*idx,start,mid,l,r);
       int right=RMQhelper(st,n,2*idx+1,mid+1,end,l,r);
       return Math.min(left,right);
       
    }
    
    

}
