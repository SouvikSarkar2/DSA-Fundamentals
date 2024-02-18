import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Graph{

    static class Edge{
        int src;
        int dest;
        int wt;

        public  Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
    }

    public static void BFS(ArrayList<Edge> graph[]){
        Queue<Integer> q = new LinkedList<>();
        boolean vis[]=new boolean[graph.length];
        q.add(0);   //src==0
        while(!q.isEmpty()){
            int curr = q.remove();
            if(!vis[curr]){
                System.out.print(curr+" ");
                vis[curr]=true;
                for(int i = 0 ; i<graph[curr].size() ; i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void DFS(ArrayList<Edge> graph[],int curr,boolean vis[]){
        System.out.print(curr+" ");
        vis[curr]=true;
        for(int  i = 0 ; i <graph[curr].size() ; i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                DFS(graph,e.dest,vis);
            }

        }
        
    }

    public static boolean hasPath(ArrayList<Edge>graph[],int src,int dest,boolean vis[]){
        if(src==dest){
            return true;
        }
        vis[src]=true;
        for(int i = 0 ; i < graph[src].size();i++){
            Edge e = graph[src].get(i);
            if(!vis[e.dest] && hasPath(graph,e.dest,dest,vis)){
                return true;
            }
        }
        return false;
    }

    static void createGraph(ArrayList<Edge>graph[]){
        for(int i = 0 ; i<graph.length ; i++){
            graph[i]=new ArrayList<>();
        }
        //0-vertex
        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        //1-vertex
        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        //2-vertex
        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,4,1));

        //3-vertex
        graph[3].add(new Edge(3,1,1));
        graph[3].add(new Edge(3,4,1));
        graph[3].add(new Edge(3,5,1));

        //vertex-4
        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,3,1));
        graph[4].add(new Edge(4,5,1));

        //vertex-5
        graph[5].add(new Edge(5,3,1));
        graph[5].add(new Edge(5,4,1));
        graph[5].add(new Edge(5,6,1));

        //vertex-6
        graph[6].add(new Edge(6,5,1));
    }
    public static void main(String args[]){
        int V = 7;
        ArrayList<Edge> graph[]=new ArrayList[V];
        createGraph(graph); 
        System.out.println(hasPath(graph,0,7,new boolean[V]));      
    }
}

//ADVANCED



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.*;
public class a {
    static class Edge{
        int src;
        int dest;
        int wt;

        public  Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
    }

    static void createGraph(ArrayList<Edge>graph[]){
        for(int i = 0 ; i<graph.length ; i++){
            graph[i]=new ArrayList<>();
        }
        /* //0-vertex
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));

        //1-vertex
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));

        //2-vertex
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));

        //3-vertex
        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,4));

        //vertex-4
        graph[4].add(new Edge(4,3)); */

        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));

        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,3,40));

        graph[2].add(new Edge(2,0,15));
        graph[2].add(new Edge(2,3,50));

        graph[3].add(new Edge(3,1,40));
        graph[3].add(new Edge(3,2,50));
       
    }

    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[]=new boolean[graph.length];
        for(int i = 0 ; i < graph.length ; i++){
            if(!vis[i]){
                if(detectCycleUtil(graph,vis,i,-1)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycleUtil(ArrayList<Edge> graph[],boolean vis[],int curr,int par){
        vis[curr]=true;
        for(int i = 0 ; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                if(detectCycleUtil(graph,vis,e.dest,curr)){
                return true;
                }
            }
            else if(vis[e.dest] && e.dest!=par){
                return true;
            }
        }
        return false;
    }

    public static boolean isBipartite(ArrayList<Edge> graph[]){
        int col[]=new int [graph.length];
        for(int i = 0 ; i < col.length ; i++){
            col[i]=-1;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i  = 0 ; i<graph.length ; i++){
            if(col[i]==-1){
                q.add(i);
                col[i]=0;
                while(!q.isEmpty()){
                    int curr=q.remove();
                    for(int j = 0 ; j<graph[curr].size(); j++){
                        Edge e = graph[i].get(j);
                        if(col[e.dest]==-1){
                            int nextCol = col[curr] == 0 ? 1 : 0;
                            col[e.dest]=nextCol;
                            q.add(e.dest);
                        }
                        else if(col[e.dest]==col[curr]){
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }

    //cycle for directed graph
    public static boolean isCycle(ArrayList<Edge> graph[]){
        boolean vis[]=new boolean[graph.length];
        boolean stack[]=new boolean[graph.length];

        for(int i = 0 ; i<graph.length ; i++){
            if(!vis[i]){
                if(isCycleUtil(graph,i,vis,stack)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtil(ArrayList<Edge> graph[],int curr,boolean vis[],boolean stack[]){
        vis[curr]=true;
        stack[curr]=true;

        for(int  i = 0 ; i<graph[curr].size() ; i++){
            Edge e = graph[curr].get(i);
            if(stack[e.dest]){
                return true;
            }
            if(!vis[e.dest] && isCycleUtil(graph,e.dest,vis,stack)){
                return true;
            }
        }
				stack[curr]=false;
        return false;
    }
    //topological sort is only used for directed Acyclic Graph(DAG)
    public static void topSort(ArrayList<Edge> graph[]){
        boolean vis[]=new boolean[graph.length];
        Stack<Integer> s = new Stack<>();

        for(int i = 0 ; i<graph.length ; i++){
            if(!vis[i]){
                topSortUtil(graph,i,vis,s);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }

    public static void topSortUtil(ArrayList<Edge> graph[],int curr,boolean vis[],Stack<Integer> s){
        vis[curr]=true;
        for(int i = 0 ; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph,e.dest,vis,s);
            }
        }
        s.push(curr);
    }

		public static void calcIndeg(ArrayList<Edge> graph[],int indeg[]){
        for(int i = 0 ; i<graph.length ; i++){
            for(int j=0 ; j<graph[i].size() ; j++){
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }

    public static void topSortBFS(ArrayList<Edge> graph[]){
        int indeg[]=new int[graph.length];
        calcIndeg(graph,indeg);
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i<graph.length ; i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }   
            while(!q.isEmpty()){
                int curr = q.remove();
                System.out.print(curr+" ");
                for(int i = 0 ; i<graph[curr].size() ; i++){
                    Edge e = graph[curr].get(i);
                    indeg[e.dest]--;
                    if(indeg[e.dest]==0){
                        q.add(e.dest);
                    }
                }
            }
        }


    public static void printAllPath(ArrayList<Edge> graph[],int src,int dest,String path){
        if(src==dest){
            System.out.println(path+dest);
            return ;
        }
        for(int i = 0 ; i<graph[src].size() ; i++){
            Edge e = graph[src].get(i);
            printAllPath(graph,e.dest,dest,path+src);
        }
    }

    static class Pair implements Comparable<Pair>{
        int n;
        int path;

        public Pair(int n,int path){
            this.n=n;
            this.path=path;
        }

        @Override
        public int compareTo(Pair p2){
            return this.path - p2.path;
        }
    }

    
    public static void dijkstra(ArrayList<Edge> graph[],int src){
    int dist[] = new int[graph.length];
    for(int i = 0 ; i<graph.length ; i++){
        if(i!=src){
            dist[i]=Integer.MAX_VALUE;
        }
    }
        boolean vis[]=new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src,0));

        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.n]){
                vis[curr.n]=true;
                for(int i = 0 ; i< graph[curr.n].size() ; i++){
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if(dist[u]+wt<dist[v]){
                        dist[v]=dist[u]+wt;
                        pq.add(new Pair(v,dist[v]));
                    }
                }
            }
        }

        for(int i = 0 ; i<dist.length ; i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }

    public static void bellmanFord(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];
        for(int i = 0 ; i<dist.length ; i++){
        if(i!=src){
            dist[i]=Integer.MAX_VALUE;
        } 
    }
        int V = graph.length;
        for(int i = 0 ; i<V-1 ; i++){
            for(int j = 0 ; j< graph.length ; j++){
                for(int k = 0 ; k<graph[j].size() ; k++){
                    Edge e = graph[j].get(k);

                    int u = e.src;
                    int v = e.dest;
                    int wt= e.wt;
                    if(dist[u]!=Integer.MAX_VALUE && dist[u]+ wt < dist[v]){
                        dist[v]= dist[u]+wt;
                    }
                }
            }
        }
        for(int i = 0 ;i<dist.length ; i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }

		public static void prims(ArrayList<Edge> graph[]){
        boolean vis[]=new boolean[graph.length];
        PriorityQueue <Pair2> pq = new PriorityQueue<>();
        pq.add(new Pair2(0,0));
        int finalCost = 0;
        while(!pq.isEmpty()){
            Pair2 curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                finalCost+=curr.cost;

                for(int i = 0 ; i<graph[curr.v].size() ; i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair2(e.dest,e.wt));
                }
            }
        }

        System.out.println(finalCost);

    }

    static class Pair2 implements Comparable<Pair2>{
        int v;
        int cost;

        public Pair2(int v,int c){
            this.v=v;
            this.cost=c;
        }

        @Override
        public int compareTo(Pair2 p2){
            return this.cost-p2.cost;
        }
    }

    public static void main(String args[]){
        int V = 5;
        ArrayList<Edge> graph[]=new ArrayList[V];
        createGraph(graph); 
        //dijkstra(graph,0);
        //bellmanFord(graph,0);
        prims(graph);
        
    }
    }
