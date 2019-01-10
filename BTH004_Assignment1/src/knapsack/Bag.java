package knapsack;

public class Bag implements Cloneable{
    private int no; //包编号
    private int capacity; //包容量
    public Bag(int no , int capacity) { //包初始化
        this.no = no;
        this.capacity = capacity;
    }
    @Override
    public Object clone() { //实现Cloneable接口clone函数
        Bag bag = null;
        try{
            bag = (Bag) super.clone();   //浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bag;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
