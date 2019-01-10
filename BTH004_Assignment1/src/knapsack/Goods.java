package knapsack;

public class Goods implements Cloneable{
    private int no; //物品编号
    private int price; //物品价格
    private int weight; //物品重量
    private double value; //物品价值
    private boolean chosed; //物品是否被选择
    public Goods(int no , int price, int weight) { //初始化参数
        this.no = no;
        this.price = price;
        this.weight = weight;
        this.value = (double)price/weight;
        this.chosed = false;
    }
    @Override
    public Object clone() { //实现Cloneable接口clone函数
        Goods goods = null;
        try{
            goods = (Goods) super.clone();   //浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return goods;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public double getValue() {
        return value;
    }
    public void setValue(){
        if(price!=0 && weight!=0) {
            value = price/weight;
        }
    }
    public boolean isChosed() {
        return chosed;
    }
    public void setChosed(boolean chosed) {
        this.chosed = chosed;
    }
}
