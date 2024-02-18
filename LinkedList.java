public class LinkedList{
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public static boolean b;

    void addFirst(int data){
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }

    void addLast(int data){
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }

    void add(int idx,int data){
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        if(idx==0){
            addFirst(data);
            return;
        }
        Node prev=head;
        for(int i = 0 ; i<idx-1 ; i++){
            prev=prev.next;
        }
        newNode.next=prev.next;
        prev.next=newNode;
    }

    void removeFirst(){
        head=head.next;
        size--;
    }

    void removeLast(){
        Node prev=head;
        for(int i = 0 ; i<size-2 ; i++){
            prev=prev.next;
        }
        prev.next=null;
        tail=prev;
        size--;
    }

    void remove(int idx){
        if(idx==0){
            removeFirst();
            return;
        }
        if(idx==size-1){
            removeLast();
            return;
        }
        Node prev = head;
        for(int i = 0 ; i<idx-1 ; i++){
            prev=prev.next;
        }
        Node curr=prev.next;
        prev.next=prev.next.next;
        curr.next=null;
        size--;
    }

    public void print(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
            
        }
    }

    public static int search(int num){
        Node s = new Node(num);
        Node temp=head;
        for(int i = 0 ; i<size ; i++){
            if(temp.data==s.data){
                return i;
            }
            temp=temp.next;
        }
        return -1;
        

    }

    public void reverse(){
        Node prev=null;
        Node curr=tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;

        }
        head=prev;
    }

    public boolean cycleCheck(){
        Node fast=head;
        Node slow=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
    }
    return false;
    }

    public void cycleBreak(){
        if(b){
            Node slow=head;
            Node fast=head;
            slow=slow.next;
            fast=fast.next.next;
            while(slow!=fast){
                slow=slow.next;
                fast=fast.next.next;
            }
            slow=head;
            Node prev=null;
            while(slow!=fast){
                prev=fast;
                slow=slow.next;
                fast=fast.next;

            }
            prev.next=null;
        }
    }

    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        Node temp = head.next;
        tail.next=temp;

        b=ll.cycleCheck();
        System.out.println(b);
        ll.cycleBreak();
        b=ll.cycleCheck();
        System.out.println(b);
        ll.print();
        

    }
}
