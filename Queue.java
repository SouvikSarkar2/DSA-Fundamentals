public class Queue{
    static class queue{
        static int arr[];
        static int size;
        static int front;
        static int rear;
        static int n;

        queue(int n){
            arr=new int[n];
            size=n;
            rear=front=-1;
        }
    
    boolean isEmpty(){
        return front==-1;
    }

    boolean isFull(){
        return (rear+1)%size==front;
    }

    void add(int data){
        if(isFull()){
            System.out.println("Queue is full");
            return ;
        }
        else{
            n++;
            if(isEmpty()){
                rear=front=0;
                arr[rear]=data;
            }
            else{
                rear=(rear+1)%size;
                arr[rear]=data;
            }
        }
    }

    int remove(){
        if(isEmpty()){
            System.out.println("queue is Empty");
            return -1;
        }
        else{
            n--;
            if(rear==front){
                int store=arr[front];
                front=rear=-1;
                return store;
            }
            int store=arr[front];
            front=(front+1)%size;
            return store;
        }
    }

    int peek(){
        if(isEmpty()){
            System.out.println("queue is Empty");
            return -1;
        }
        else{
            return arr[front];
        }
    }

    void queueReversal(){
        if(isEmpty()){
            return;
        }
        int store= remove();
        queueReversal();
        add(store);
    }
}

    public static void main(String args[]){
        queue q = new queue(4);
        q.add(1);
        q.add(2);
        q.add(3);
        q.queueReversal();
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
    }
}
