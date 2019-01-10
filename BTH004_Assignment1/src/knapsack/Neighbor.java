package knapsack;
import java.util.LinkedList;
import java.util.List;

public class Neighbor {
    public List<Bag> bags; //包列表
    public List<Goods> goods; //物品列表
    public List<Object> emptyBag; //空包列表
    @SuppressWarnings("rawtypes")
	public List<LinkedList> result; //整个过程中产生的最大解
    public int max_value; //整个过程中最大利益
    public int MAXCOUNT = 5; //总共循环次数
    @SuppressWarnings({ "unchecked", "rawtypes" })
   public Neighbor(List<Bag> bags , List<Goods> goods , List<LinkedList> result){
	//深度复制包列表，物品列表
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
        this.result = new LinkedList <> (  ); //开辟结果空间
        this.emptyBag=new LinkedList <> (); //开辟空包空间
        int[] temp_emptyBag = new int[bags.size()];
        //初始化空包 和深度复制结果
        for (int i = 0 ; i < result.size () ; i ++){ 
            this.result.add ( (LinkedList<Integer>) result.get ( i ).clone () );
            if (result.get(i).size() == 2) 
               temp_emptyBag[(Integer)result.get(i).get(1)] = 1;
        }
        for (int i = 0 ; i < temp_emptyBag.length ; i++) {
           if(temp_emptyBag[i] != 1)
              emptyBag.add(i);
        }
        for (Goods temp: this.goods) {//Output Goods list
            System.out.print ( "Goods No:" + temp.getNo () + " ");
            System.out.println ("Value: "+ temp.getValue ());
        }
        System.out.println();
        for (Bag temp: this.bags) {
            System.out.print ( "Bag No:"+ temp.getNo () + " ");
            System.out.println ("Capacity:"+ temp.getCapacity ());
        }
        //System.out.println("emptybag: "+emptyBag+"\n");
        System.out.println("Result: " + result);
        this.max_value = calValue ( result ); //计算当前最大利润
        System.out.println("Current value: " + max_value);
    }
    
    public int calValue(List<LinkedList> temp_result) { //计算给定结果的利益
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   public List<LinkedList> reverse(int x , int y){ //对于一个解 进行全排列
    	//深度复制初始化reverse_result
       List<LinkedList> reverse_result = new LinkedList<>(); 
       for (int i = 0 ; i < result.size () ; i ++){ 
           reverse_result.add ( (LinkedList<Integer>) result.get ( i ).clone () );
        }
       LinkedList<Integer> o1 = result.get(x);
       LinkedList<Integer> o2 = result.get(y);

       if (o1.size() == 2 && o2.size() == 2) { //如果两个物品都放入了背包 交换包位置
          int temp1 = (Integer) reverse_result.get(x).get(1);
          int temp2 = (Integer) reverse_result.get(y).get(1);
          if (temp1 != temp2) {
             reverse_result.get(x).set(1, temp2);
              reverse_result.get(y).set(1, temp1);
          } 
       } else if (o1.size() == 1 && o2.size() == 1){ //如果两个物品都不在包里 不用做任何操作
       } else {
          if (o1.size() == 2 && o2.size() == 1) { //一个在 一个不在 把不在的放进在的 在的设为空
             int temp = (Integer) reverse_result.get(x).get(1);
              reverse_result.get(x).remove(1);
              reverse_result.get(y).add(temp);
          } else if(o1.size() == 1 && o2.size() == 2) {
             int temp = (Integer) reverse_result.get(y).get(1);
              reverse_result.get(y).remove(1);
              reverse_result.get(x).add(temp);
          }
       }
       modify ( reverse_result ); //进行调整 满足条件
       return reverse_result;
    }
    
	//给定一个结果 将该结果调整已满足能放进包 有最少的空物品
    public void modify(List<LinkedList> neighborhood) { 
    	//初始化空包
    	List<Integer> temp_emptyBag = new LinkedList<Integer> (  ); 
    	for (Object i: emptyBag)
    		temp_emptyBag.add ( (Integer)i );
    	//初始化物品容量数组
    	int [] bagCapacity = new int[bags.size()]; 
    	for (Bag i : bags) 
    		bagCapacity[i.getNo()] = i.getCapacity();
    	//初始化目前包的容量
    	int[] current_bagCapacity = new int[bags.size()]; 
    	for (LinkedList i : neighborhood) { 
    		if (i.size()==2){ //如果当前物品在包中
    			current_bagCapacity[(Integer) i.get(1)] += goods.get((Integer) i.get(0)).getWeight(); //目前包容量增加
    		} else { //当前物品不在包中
    			//System.out.println(temp_emptyBag);
    			for (int j = 0 ; j < temp_emptyBag.size() ; j++) { //遍历空包
    				if ( bags.get((Integer)temp_emptyBag.get(j)).getCapacity() > goods.get((Integer)i.get(0)).getWeight()){ //如果还有空包可以给这个物品放
    					i.add( (Integer)temp_emptyBag.get(j)); //放进空包
    					current_bagCapacity[(Integer) i.get(1)] += goods.get((Integer) i.get(0)).getWeight(); //包容量增加
    					temp_emptyBag.remove ( j ); //被放的包删除
    					break;
    				}
    			}
    		}
    	}
       for (int i = 0 ; i < current_bagCapacity.length ; i++) { //遍历包容量
    	   //System.out.println("i:"+i);
          if (current_bagCapacity[i] > bagCapacity[i]) { //如果当前包容量大于之前的
             int difference = current_bagCapacity[i] - bagCapacity[i];
             //System.out.println("1 "+difference);
             for (int j = 0 ; j < neighborhood.size() ; j++) { //遍历一个领域
                if (neighborhood.get(j).size() == 2 && (Integer)neighborhood.get(j).get(1) == i && difference > 0 ) { //当前物品在包里 而且在目标包中 而且包中的物品过多
                   difference -= goods.get((Integer)neighborhood.get(j).get(0)).getWeight ();
                   //System.out.println("2"+difference);
                  emptyBag.add ( neighborhood.get ( j ).get ( 1 ) ); //空包增加当前包 
                   neighborhood.get(j).remove(1); //从包中移走当前物品
                }
             }
          }
       }
    }   
	//获得领域
    public List<List<LinkedList>> getNeighborhoods() {
       List<List<LinkedList>> neighborhoods = new LinkedList<>();
       neighborhoods.add(result);
       for( int x = 0 ; x < result.size() ; x ++ ) { //交换所有两个元素 形成一个新的领域
          for (int y = x + 1 ; y < result.size() ; y ++ ){
             List<LinkedList> temp = reverse(x, y);
             if (temp!=null)
                neighborhoods.add(temp);
             else 
                continue;
          }
       }
       return neighborhoods;
    }   
    public void startNeighbor(){
        for(int i = 0 ; i < MAXCOUNT ; i++){
        	List<List<LinkedList>> neighborhoods = getNeighborhoods(); //获得所有领域
        	System.out.println("================Interaction "+(i+1)+"============================");
        	int temp_value = 0; //临时当前利润
        	int temp_maxvalue = 0; //临时最大利润
        	List<LinkedList> temp_result = new LinkedList <> (  );
        	System.out.println("NEIGHBORHOODS:");
        	for (List<LinkedList> y : neighborhoods) {
        		System.out.println(y);
        		temp_value = calValue ( y ); //计算当前一个领域的利益
        		if (temp_value >= temp_maxvalue) { //如果这个利润比最大的利润大
        			temp_maxvalue = temp_value; //替换最大利益
        			temp_result = y; //记录对应的排列
        		}
        	}
        	if (max_value <= temp_maxvalue) { //检查全局最大值 并更新
        		max_value = temp_maxvalue;
        		result = temp_result;
        	}
        	System.out.println (  );
        	System.out.println ("Temp_maxvalue:"+temp_maxvalue);
        	System.out.println ("Temp_result:"+temp_result);
        }
        System.out.println("====================Final Result===================");
        System.out.println ("Max value is " + max_value);
        System.out.println ("Result is " +result);
        System.out.println();
    }
}
