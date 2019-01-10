package knapsack;
import java.util.LinkedList;
import java.util.List;

public class Tabu {
    public List<Bag> bags; //���б�
    public List<Goods> goods; //��Ʒ�б�
    public List<Object> emptyBag; //�հ��б�
    public List<LinkedList> result; //���������в���������
    public int max_value; //�����������������
    public int MAXCOUNT = 5; //�ܹ�ѭ������
    public List<List<LinkedList>> Ttable; //��¼�ѱ�ѡ��Ľ��
    @SuppressWarnings("unchecked")
	public Tabu(List<Bag> bags , List<Goods> goods , List<LinkedList> result){
        //��ȸ��ư��б���Ʒ�б�
    	System.out.println("==============Initialize bags and goods==============");
    	this.bags = new LinkedList <> (  );
    	for (int i = 0 ; i < bags.size () ; i ++)
    		this.bags.add ( (Bag) bags.get ( i ).clone () );
    	this.goods = new LinkedList <> (  );
    	for (int i = 0 ; i < goods.size () ; i ++)
        	this.goods.add ( (Goods) goods.get ( i ).clone () );
    	this.result = new LinkedList <> (  ); //���ٽ���ռ�
    	this.emptyBag=new LinkedList <> (); //���ٿհ��ռ�
    	int[] temp_emptyBag = new int[bags.size()];
    	for (int i = 0 ; i < result.size () ; i ++){ //��ʼ���հ� ����ȸ��ƽ��
        	this.result.add ( (LinkedList<Integer>) result.get ( i ).clone () );
        	if (result.get(i).size() == 2)
            	temp_emptyBag[(Integer)result.get(i).get(1)] = 1;
    	}
    	for (int i = 0 ; i < temp_emptyBag.length ; i++) {
        	if(temp_emptyBag[i] != 1)
            	emptyBag.add(i);
    	}
        Ttable = new LinkedList <> (  ); //����һ�����������ɱ�
        Ttable.add ( result );
        
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
        this.max_value = calValue ( result ); //���㵱ǰ�������
        System.out.println("Current value: " + max_value);

    }
    public boolean findTtable(List<LinkedList> target){ //���ҽ��ɱ�
        for (int i = 0 ;i < Ttable.size () ; i++ ) {
            if (target.containsAll(Ttable.get ( i )))
                return true;
        }
        return false;
    }
   public int calValue(List<LinkedList> temp_result) { //����������������
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
    public List<LinkedList> reverse(int x , int y){ //����һ���� ����ȫ����
        List<LinkedList> reverse_result = new LinkedList<>(); //�½�һ�����
        for (int i = 0 ; i < result.size () ; i ++){ //��ȸ���
        int temp = result.get(i).size();
        reverse_result.add ( (LinkedList<Integer>) result.get ( i ).clone () );
    }
        LinkedList<Integer> o1 = result.get(x);
        LinkedList<Integer> o2 = result.get(y);
        if (o1.size() == 2 && o2.size() == 2) { //���������Ʒ�������˱��� ������λ��
            int temp1 = (Integer) reverse_result.get(x).get(1);
            int temp2 = (Integer) reverse_result.get(y).get(1);
            if (temp1 != temp2) {
                reverse_result.get(x).set(1, temp2);
                reverse_result.get(y).set(1, temp1);
            }
        } else if (o1.size() == 1 && o2.size() == 1){ //���������Ʒ�����ڰ��� �������κβ���
        } else {
            if (o1.size() == 2 && o2.size() == 1) { //һ���� һ������ �Ѳ��ڵķŽ��ڵ� �ڵ���Ϊ��
                int temp = (Integer) reverse_result.get(x).get(1);
                reverse_result.get(x).remove(1);
                reverse_result.get(y).add(temp);
            } else if(o1.size() == 1 && o2.size() == 2) {
               int temp = (Integer) reverse_result.get(y).get(1);
               reverse_result.get(y).remove(1);
               reverse_result.get(x).add(temp);
            }
        }
        modify ( reverse_result ); //���е��� ��������
        return reverse_result;
    }
    //����һ����� ���ý�������������ܷŽ��� �����ٵĿ���Ʒ
   public void modify(List<LinkedList> neighborhood) {
        List temp_emptyBag = new LinkedList (  ); //��ʼ���հ�
        for (Object i: emptyBag) //�������еĿհ�
            temp_emptyBag.add ( (Integer)i );
        int [] bagCapacity = new int[bags.size()]; //������Ʒ��������
        for (Bag i : bags)
            bagCapacity[i.getNo()] = i.getCapacity();
        int[] current_bagCapacity = new int[bags.size()]; //����Ŀǰ��������
        for (LinkedList i : neighborhood) { //������ǰһ������
            if (i.size()==2){ //�����ǰ��Ʒ�ڰ���
                current_bagCapacity[(Integer) i.get(1)] += goods.get((Integer) i.get(0)).getWeight(); //Ŀǰ����������
            } else { //��ǰ��Ʒ���ڰ���
                for (int j = 0 ; j < temp_emptyBag.size() ; j++) { //�����հ�
                    if ( bags.get((Integer)temp_emptyBag.get(j)).getCapacity() > goods.get((Integer)i.get(0)).getWeight()){ //������пհ����Ը������Ʒ��
                        i.add( (Integer)temp_emptyBag.get(j)); //�Ž��հ�
                        current_bagCapacity[(Integer) i.get(1)] += goods.get((Integer) i.get(0)).getWeight(); //����������
                        temp_emptyBag.remove ( j ); //���ŵİ�ɾ��
                        break;
                    }
                }
            }
        }
        for (int i = 0 ; i < current_bagCapacity.length ; i++) { //����������
            if (current_bagCapacity[i] > bagCapacity[i]) { //�����ǰ����������֮ǰ��
                int difference = current_bagCapacity[i] - bagCapacity[i];
                for (int j = 0 ; j < neighborhood.size() ; j++) { //����һ������
                    if (neighborhood.get(j).size() == 2 && (Integer)neighborhood.get(j).get(1) == i && difference > 0 ) { //��ǰ��Ʒ�ڰ��� ������Ŀ����� ���Ұ��е���Ʒ����
                        difference -= goods.get((Integer)neighborhood.get(j).get(0)).getWeight ();
                        emptyBag.add ( neighborhood.get ( j ).get ( 1 ) ); //�հ����ӵ�ǰ��
                        neighborhood.get(j).remove(1); //�Ӱ������ߵ�ǰ��Ʒ
                    }
                }
            }
        }
    }
	//�������
    public List<List<LinkedList>> getNeighborhoods() {
        List<List<LinkedList>> neighborhoods = new LinkedList<>();
        for( int x = 0 ; x < result.size() ; x ++ ) {//������������Ԫ�� �γ�һ���µ�����
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
    //
    public void startTabu(){
        for(int i = 0 ; i < MAXCOUNT ; i++){
            List<List<LinkedList>> neighborhoods = getNeighborhoods();//�����������
            System.out.println("================Interaction "+(i+1)+"============================");
            for (int j = 0 ; j < neighborhoods.size () ; j++) { //�����������Ƿ��н��ɱ��еĽ��
                if (findTtable ( neighborhoods.get ( j ) )){ //������ڵĻ� 
                    neighborhoods.remove ( j );
                    j--;
                }
            }
            System.out.println("Negihobrhoods:");
            int temp_value = 0; //��ʱ��ǰ����
            int temp_maxvalue = 0; //��ʱ�������
            List<LinkedList> temp_result = new LinkedList <> (  );
            for (List<LinkedList> y : neighborhoods) {
                System.out.println(y);
               temp_value = calValue ( y ); //���㵱ǰһ�����������
               if (temp_value >= temp_maxvalue) { //��������������������
                   temp_maxvalue = temp_value; //�滻�������
                   temp_result = y; //��¼��Ӧ������
               }
            }
            if (max_value <= temp_maxvalue) { //���ȫ�����ֵ ������
                max_value = temp_maxvalue;
                result = temp_result;
            }

            System.out.println (  );
            System.out.println("Tabu:"+Ttable);
            Ttable.remove (0 );
            Ttable.add ( result );
            
            
            
        	System.out.println ("Temp_maxvalue:"+temp_maxvalue);
        	System.out.println ("Temp_result:"+temp_result);
        }

        System.out.println("====================Final Result===================");
        System.out.println ("max value is " + max_value);
        System.out.println ("result is " +result);
    }
}
