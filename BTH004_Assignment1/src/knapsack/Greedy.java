package knapsack;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Greedy {
    public List<Bag> bags; //包列表
    public List<Goods> goods; //物品列表
    public Greedy(List<Bag> bags , List<Goods> goods) {
    	System.out.println("==============Initialize bags and goods==============");
    	//新建一个包列表 并用原有的包列表深度复制
        this.bags= new LinkedList <> (  );
        for (int i = 0 ; i < bags.size () ; i ++){
            this.bags.add ( (Bag) bags.get ( i ).clone () );
        }
        //新建一个物品列表 并用原有的物品列表深度复制
        this.goods= new LinkedList <> (  );
        for (int i = 0 ; i < goods.size () ; i ++){
            this.goods.add ( (Goods) goods.get ( i ).clone () );
        }
        //将物品列表按照单位价值从大到小排序
        System.out.println ("Sort bag list by value:");
        Collections.sort ( this.goods , new Comparator<Goods> () {
            @Override
            public int compare(Goods o1, Goods o2) {
                //return (double) (o2.getValue () - o1.getValue ());//倒序
                return new Double(o2.getValue ()).compareTo(new Double(o1.getValue ()));
            }
        });
        for (Goods temp: this.goods) {//Output Goods list
            System.out.print ( "Goods No:" + temp.getNo () + " ");
            System.out.println ("Value: "+ temp.getValue ());
        }
        System.out.println ();
        //将包列表按照包容量从大到小排序
        System.out.println ("Sort bag list by capacity:");
        Collections.sort ( this.bags , new Comparator<Bag> () {
            @Override
            public int compare(Bag o1, Bag o2) {
                return o2.getCapacity() - o1.getCapacity ();
            }
        });
        for (Bag temp: this.bags) {
            System.out.print ( "Bag No:"+ temp.getNo () + " ");
            System.out.println ("Capacity:"+ temp.getCapacity ());
        }
        System.out.println ();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LinkedList> startGreedy(){

        System.out.println("================Start Greedy Algorithom=================");
        List<LinkedList> result = new LinkedList <> (  );
        for (int i = 0 ; i < goods.size () ; i++ ){
            LinkedList<Integer> temp = new LinkedList<Integer> (  );
            temp.add ( goods.get ( i ).getNo () );
            result.add ( temp );
        }
        for (int i = 0 ; i < bags.size () ; i++) {  //先放最大的包
            for(int j = 0 ; j < goods.size () ; j++) {  //先放价值最高的东西
                if (goods.get ( j ).getWeight () <= bags.get ( i ).getCapacity () && goods.get ( j ).isChosed ()==false){ //如果物当前物品的重量小于当前包的重量，包没有被选择
                    for (int x= 0 ; x < result.size () ; x++) {
                        if ((Integer)result.get ( x ).get ( 0 ) == goods.get ( j ).getNo ()){ //找到结果中的将要放入的那个物品的位置
                        	System.out.println("Put goods No: " +goods.get ( j ).getNo ()+ " into bag No:"+bags.get ( i ).getNo ());
                        	result.get ( x ).add ( bags.get ( i ).getNo () );
                            int newCap = bags.get ( i ).getCapacity () - goods.get ( j ).getWeight ();
                            bags.get ( i ).setCapacity ( newCap  ); //包容量减少
                            goods.get ( j ).setChosed ( true ); //标识为已选过
                        }
                    }
                }
            }
        }
        System.out.println("Total price: " + calValue(result));
        System.out.println("================End Greedy Algorithom==================="+"\n");
        return result;
    }
    public int calValue(@SuppressWarnings("rawtypes") List<LinkedList> temp_result) { //计算给定结果的利益
        int value = 0;
        for (int i = 0 ; i <temp_result.size () ; i++) { //遍历全部解
            if (temp_result.get ( i ).size () == 2){ //如果解有第二位
            	for (int x= 0 ; x < goods.size () ; x++) {
                    if ((Integer)temp_result.get ( i ).get ( 0 ) == goods.get ( x ).getNo ()){ //找到结果中的将要放入的那个物品的位置
                    	value += goods.get ( x ).getPrice (); //该物品在包中，将当前物品价值算进利益
                    }
                }
                //System.out.println(value);
            }
        }
        return value;
    }

}
