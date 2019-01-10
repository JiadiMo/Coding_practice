package knapsack;

public class Goods implements Cloneable{
    private int no; //��Ʒ���
    private int price; //��Ʒ�۸�
    private int weight; //��Ʒ����
    private double value; //��Ʒ��ֵ
    private boolean chosed; //��Ʒ�Ƿ�ѡ��
    public Goods(int no , int price, int weight) { //��ʼ������
        this.no = no;
        this.price = price;
        this.weight = weight;
        this.value = (double)price/weight;
        this.chosed = false;
    }
    @Override
    public Object clone() { //ʵ��Cloneable�ӿ�clone����
        Goods goods = null;
        try{
            goods = (Goods) super.clone();   //ǳ����
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
