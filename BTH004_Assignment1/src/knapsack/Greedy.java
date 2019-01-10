package knapsack;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Greedy {
    public List<Bag> bags; //���б�
    public List<Goods> goods; //��Ʒ�б�
    public Greedy(List<Bag> bags , List<Goods> goods) {
    	System.out.println("==============Initialize bags and goods==============");
    	//�½�һ�����б� ����ԭ�еİ��б���ȸ���
        this.bags= new LinkedList <> (  );
        for (int i = 0 ; i < bags.size () ; i ++){
            this.bags.add ( (Bag) bags.get ( i ).clone () );
        }
        //�½�һ����Ʒ�б� ����ԭ�е���Ʒ�б���ȸ���
        this.goods= new LinkedList <> (  );
        for (int i = 0 ; i < goods.size () ; i ++){
            this.goods.add ( (Goods) goods.get ( i ).clone () );
        }
        //����Ʒ�б��յ�λ��ֵ�Ӵ�С����
        System.out.println ("Sort bag list by value:");
        Collections.sort ( this.goods , new Comparator<Goods> () {
            @Override
            public int compare(Goods o1, Goods o2) {
                //return (double) (o2.getValue () - o1.getValue ());//����
                return new Double(o2.getValue ()).compareTo(new Double(o1.getValue ()));
            }
        });
        for (Goods temp: this.goods) {//Output Goods list
            System.out.print ( "Goods No:" + temp.getNo () + " ");
            System.out.println ("Value: "+ temp.getValue ());
        }
        System.out.println ();
        //�����б��հ������Ӵ�С����
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
        for (int i = 0 ; i < bags.size () ; i++) {  //�ȷ����İ�
            for(int j = 0 ; j < goods.size () ; j++) {  //�ȷż�ֵ��ߵĶ���
                if (goods.get ( j ).getWeight () <= bags.get ( i ).getCapacity () && goods.get ( j ).isChosed ()==false){ //����ﵱǰ��Ʒ������С�ڵ�ǰ������������û�б�ѡ��
                    for (int x= 0 ; x < result.size () ; x++) {
                        if ((Integer)result.get ( x ).get ( 0 ) == goods.get ( j ).getNo ()){ //�ҵ�����еĽ�Ҫ������Ǹ���Ʒ��λ��
                        	System.out.println("Put goods No: " +goods.get ( j ).getNo ()+ " into bag No:"+bags.get ( i ).getNo ());
                        	result.get ( x ).add ( bags.get ( i ).getNo () );
                            int newCap = bags.get ( i ).getCapacity () - goods.get ( j ).getWeight ();
                            bags.get ( i ).setCapacity ( newCap  ); //����������
                            goods.get ( j ).setChosed ( true ); //��ʶΪ��ѡ��
                        }
                    }
                }
            }
        }
        System.out.println("Total price: " + calValue(result));
        System.out.println("================End Greedy Algorithom==================="+"\n");
        return result;
    }
    public int calValue(@SuppressWarnings("rawtypes") List<LinkedList> temp_result) { //����������������
        int value = 0;
        for (int i = 0 ; i <temp_result.size () ; i++) { //����ȫ����
            if (temp_result.get ( i ).size () == 2){ //������еڶ�λ
            	for (int x= 0 ; x < goods.size () ; x++) {
                    if ((Integer)temp_result.get ( i ).get ( 0 ) == goods.get ( x ).getNo ()){ //�ҵ�����еĽ�Ҫ������Ǹ���Ʒ��λ��
                    	value += goods.get ( x ).getPrice (); //����Ʒ�ڰ��У�����ǰ��Ʒ��ֵ�������
                    }
                }
                //System.out.println(value);
            }
        }
        return value;
    }

}
