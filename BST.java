public class BST{
    public static void main(String args[]){
        int val[]={8,5,3,1,4,6,10,11,14};
        Node root=null;
        for(int  i= 0 ; i<val.length ;i++ ){
            root=insert(root,val[i]);
        }
        Info info=largestBST(root);
        System.out.println(maxBST);
        
    }

    static class Node{
        int data;
        Node left ; 
        Node right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(root.data<val){
            root.right=insert(root.right,val);
        }
        if(root.data>val){
            root.left=insert(root.left,val);
        }
        return root;
    }

    public static void inorder(Node root){
        if(root==null){
            return ;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static void search(Node root,int k,int key){
        if(root==null){
            System.out.println("key not found");
            return;
        }
        if(root.data==key){
            System.out.println("key is at "+k+" lvl");
            return;
        }
        if(root.data>key){
            search(root.left,k+1,key);
        }
        if(root.data<key){
            search(root.right,k+1,key);
        }
    }

    public static Node inorderSuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

    public static Node delete(Node root,int val){
         if(root==null){
            return null;
         }
         if(root.data<val){
            root.right=delete(root.right,val);
         }
         else if(root.data>val){
            root.left=delete(root.left,val);
         }
         else{
            if(root.left==null && root.right==null){
                return null;
            }

            if(root.left==null){
                return root.right;
            }

            if(root.right==null){
                return root.left;
            }

            Node IS = inorderSuccessor(root.right);
            root.data=IS.data;
            root.right=delete(root.right,IS.data);
         }
         return root;
    }

    public static boolean isValidBST(Node root,Node max,Node min){
    if(root==null){
    return true;
    }

    if(min!=null &&  root.data<=min.data){
        return false;
    }
    if(max!=null && root.data>=max.data){
        return false;
    }
    return isValidBST(root.right,max,root) && isValidBST(root.left,root,min);
}

   public Node mirrorBST(Node root){
        if(root==null){
        return null;
        }
        root.left=mirrorBST(root.right);
        root.right=mirrorBST(root.left);
        return root;
    }

    public static Node createBST(int arr[],int st,int ed){
        if(st>ed){
            return null;
        }
        int mid = (st+ed)/2;
        Node root = new Node(arr[mid]);
        root.left=createBST(arr,st,mid-1);
        root.right=createBST(arr,mid+1,ed); 
        return root;
    }

    static class Info{
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(boolean isBST,int size,int min,int max){
            this.isBST=isBST;
            this.size=size;
            this.min=min;
            this.max=max;
        }
    }
        
    public static int maxBST=0;

    public static Info largestBST(Node root){
            if(root==null){
                return new Info(true,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
            }
            Info lt=largestBST(root.left);
            Info rt=largestBST(root.right);
            int size=lt.size+rt.size+1;
            int min=Math.min(root.data,Math.min(lt.min,rt.min));
            int max=Math.max(root.data,Math.max(lt.max,rt.max));

            if(root.data<=lt.max || root.data>=rt.min){
                return new Info(false,size,min,max);
            }

            if(lt.isBST && rt.isBST){
                maxBST=Math.max(maxBST,size);
                return new Info(true,size,min,max);
            }
            return new Info(false,size,min,max);
        }
    }
