import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
public class New{
    public static void main(String args[]){
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.println(tree.nodeCount(root));
        

    }

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left=buildTree(nodes);
            newNode.right=buildTree(nodes);

            return newNode;
        }

        public static void preorder(Node root){
            if(root == null){
                return ;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void inorder(Node root){
            if(root==null){
                return;
            }
                inorder(root.left);
                System.out.print(root.data+" ");
                inorder(root.right);
        }

        public static void postorder(Node root){
            if(root==null){
                return ;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }

        public static void levelorder(Node root){
            if(root==null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node currNode=q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }
                    else{
                        q.add(null);
                    }
                }
                else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        q.add(currNode.right);
                    }
                }
            }

        }

        public static int heightCount(Node root){
            if(root == null){
                return 0;
            }
            int lh = heightCount(root.left);
            int rh= heightCount(root.right);
            return Math.max(lh,rh)+1;
        }

        public static int nodeCount(Node root){
            if(root==null){
                return 0 ;
            }
            return nodeCount(root.left)+nodeCount(root.right)+1;
        }

        public static int nodeSum(Node root){
            if(root==null){
                return 0;
            }
            return nodeSum(root.left)+nodeSum(root.right)+root.data;
        }

        public static int diameter(Node root){
            if(root==null){
                return 0;
            }
            int lh = heightCount(root.left);
            int rh = heightCount(root.right);
            int currDiam=lh+rh+1;
            return Math.max(currDiam,Math.max(diameter(root.left),diameter(root.right)));
        }

        static class Info{
            int diam;
            int ht;
            public Info(int diam,int ht){
                this.diam=diam;
                this.ht=ht;
            }
        }

        public static Info diameter2(Node root){
            if(root==null){
                return new Info(0,0);
            }
            Info lt = diameter2(root.left);
            Info rt = diameter2(root.right);
            int diam=Math.max(Math.max(lt.diam,rt.diam),lt.ht+rt.ht+1);
            int ht = Math.max(lt.ht,rt.ht)+1;
            return new Info(diam,ht);
        }

        public static boolean isIdentical(Node root,Node subTree){
            if(root==null && subTree==null){
                return true;
            }
            if(root==null || subTree==null || root.data!=subTree.data){
                return false;
            }
            return isIdentical(root.left,subTree.left) && isIdentical(root.right,subTree.right);
        }

        public static boolean isSubtree(Node root,Node subTree){
            if(root==null){
                return false;
            }
            if(root.data==subTree.data){
                if(isIdentical(root,subTree)){
                    return true;
                }
            }
            return isSubtree(root.left,subTree) || isSubtree(root.right,subTree);
        }

        static class Info2{
            int hd;
            Node node;

            public Info2(int hd,Node node){
                this.hd=hd;
                this.node=node;
            }
            
        }

        public static void TopView(Node root){
            if(root==null){
                return;
            }
            int min = 0 ,max=0;
            Queue<Info2> q = new LinkedList<>();
            HashMap<Integer,Node> map = new HashMap<>();
            q.add(new Info2(0,root));
            q.add(null);
            while(!q.isEmpty()){
                Info2 curr = q.remove();
                if(curr==null){
                    if(q.isEmpty()){
                        break;
                    }
                    else{
                        q.add(null);
                    }
                }
                else{
                    if(!map.containsKey(curr.hd)){
                        map.put(curr.hd,curr.node);
                    }

                    if(curr.node.left!=null){
                        q.add(new Info2(curr.hd-1,curr.node.left));
                        min = Math.min(min,curr.hd-1);
                    }

                    if(curr.node.right!=null){
                        q.add(new Info2(curr.hd+1,curr.node.right));
                        max=Math.max(max,curr.hd+1);
                    }
                }
            }
                for(int i = min ; i<=max ; i++){
                    System.out.print(map.get(i).data+" ");
                }
        }

        public static void kTH(Node root,int k){//iterative way
            if(root==null){
                return;
            }
            int lvl = 1;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node curr = q.remove();
                if(curr==null){
                    if(q.isEmpty()){
                        break;
                    }
                    else{
                        q.add(null);
                        lvl++;
                    }
                }
                else{
                    if(lvl==k){
                        System.out.print(curr.data+" ");
                    }
                    if(curr.left!=null){
                        q.add(curr.left);
                    }
                    if(curr.right!=null){
                        q.add(curr.right);
                    }
                }

            }
        }

        public static void Kth(Node root,int k,int level){//recursive way
            if(root==null){
                return;
            }
            if(k==level){
                System.out.print(root.data+" ");
                return;
            }
            Kth(root.left,k,level+1);
            Kth(root.right,k,level+1);
        }

    }
}
