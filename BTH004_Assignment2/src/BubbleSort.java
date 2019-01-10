import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class BubbleSort {
    public static void Swap(List<Integer> nums , int m , int n) {
        int temp = nums.get( m );
        nums.set( m, nums.get( n ) );
        nums.set( n, temp );
    }
    public static List<Integer> BubbleSort(List<Integer> nums) {
        for(int i = nums.size()-1 ; i > 0 ; i--) {
            for(int j = i ; j > 0 ; j--) {
                if(nums.get(j-1) > nums.get(j)) {
                    Swap(nums , j-1 , j);
                }
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i = 0 ; i < 100000 ; i++) {
            nums.add(new Random().nextInt());
        }
        long startTime=System.nanoTime();   //获取开始时间
        BubbleSort(nums); 
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("Runtime： " + (endTime-startTime)+ "ns");
    }
}
