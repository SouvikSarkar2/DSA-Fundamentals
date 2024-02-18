import java.util.ArrayList;
public class stack{
    static class Stack{
        static ArrayList<Integer> list = new ArrayList<>();
        int top =-1;

        boolean isEmpty(){
            return top == -1;
        }

        void push(int data){
            top++;
            list.add(data);
        }

        int pop(){
            if(isEmpty()){
                System.out.println("Stack Underflow");
                return -1;
            }
            int store = list.remove(top);
            top--;
            return store;
        }

        int peek(){
            if(isEmpty()){
                System.out.println("Stack Underflow");
                return -1;
            }
            int store = list.get(top); 
            return store;
        }

        void pushAtBottom(int data){
            if(isEmpty()){
                push(data);
                return;
            }
            int store = pop();
            pushAtBottom(data);
            push(store);
        }

        void reverseStack(){
            if(isEmpty()){
                return;
            }
            int store = pop();
            reverseStack();
            pushAtBottom(store);
        }
        

    }

    public static void main(String args[]){
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.reverseStack(); 
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }
}
