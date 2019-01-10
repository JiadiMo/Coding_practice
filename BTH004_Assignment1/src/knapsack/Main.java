package knapsack;
import java.util.*;

public class Main {
    public static List<Bag> bags = new LinkedList <Bag> (  ); //���б�
    public static List<Goods> goods = new LinkedList <Goods> (  ); //��Ʒ�б�
    public static int[] bags1 = new int[5];
    public static int[] good_value = new int[5];
    public static int[] good_weight = new int[5];
    
    static void input()                        
    {
        System.out.println("Please input capacity of 4 bags:");
        Scanner in_t1 = new Scanner(System.in);
        for(int i=0;i<4;i++){
            bags1[i]=in_t1.nextInt();}
        System.out.println("Please input value of 5 goods:");
        Scanner in_t2 = new Scanner(System.in);
        for(int i=0;i<5;i++){
        	good_value[i]=in_t2.nextInt();}
        System.out.println("Please input weight of 5 goods:");
        Scanner in_t3 = new Scanner(System.in);
        for(int i=0;i<5;i++){
        	good_weight[i]=in_t2.nextInt();}

    }
    
    public static void main(String[] args) {
    	input();
//        int[] bags1 = {3,7,4,15}; //������
//        int[] good_value={2,4,5,10,9}; //��Ʒ��ֵ
//        int[] good_weight={2,8,5,7,3}; //��Ʒ����
        //������б�
        for (int i = 0 ; i < bags1.length ; i++) {
            Bag temp = new Bag ( i , bags1[i] );
            bags.add ( temp );
        }
        //������Ʒ�б� 
        for (int i = 0 ; i < good_value.length ; i++) {
            Goods temp = new Goods ( i , good_value[i] , good_weight[i] );
            goods.add ( temp );
        }
        Greedy greedy = new Greedy ( bags , goods );
        
		@SuppressWarnings("rawtypes")
		List<LinkedList> result = greedy.startGreedy();
        
        Neighbor neighbor = new Neighbor ( bags , goods , result );
        neighbor.startNeighbor();

        Tabu tabu = new Tabu ( bags , goods , result );
        tabu.startTabu();

    }
}

