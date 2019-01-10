import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {
    public static void Merge(List<Integer> nums , List<Integer> temp , int leftPos , int rightPos , int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int elementNum = rightEnd - leftPos + 1;

		while(leftPos <= leftEnd && rightPos <= rightEnd) {
            if(nums.get(leftPos) <= nums.get(rightPos)) {
                temp.set(tempPos , nums.get(leftPos));
                leftPos++;
                tempPos++;
            }
            else {
                temp.set(tempPos , nums.get(rightPos));
                rightPos++;
                tempPos++;
            }
        }
        while(leftPos <= leftEnd) {
            temp.set(tempPos , nums.get(leftPos));
            leftPos++;
            tempPos++;
        }
        while(rightPos <= rightEnd) {
            temp.set(tempPos , nums.get(rightPos));
            rightPos++;
            tempPos++;
        }

        for(int i = 0 ; i < elementNum ; i++ , rightEnd--) {
            nums.set(rightEnd , temp.get(rightEnd));
        }
    }

    public static List<Integer> MergeSort(List<Integer> nums , List<Integer> temp , int left , int right){
        if(left < right) {
            int mid = (left + right)/2;
            MergeSort(nums , temp , left , mid);
            MergeSort(nums , temp , mid+1 , right);
            Merge(nums , temp , left , mid+1 , right);
        }
        return nums;
    }
 

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i = 0 ; i < 10000000 ; i++) {
            nums.add(new Random().nextInt());
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0 ; i < nums.size() ; i++) {
            temp.add(0);
        }
        long startTime = System.nanoTime();   //获取开始时间
        MergeSort(nums , temp , 0, nums.size()-1);
        long endTime = System.nanoTime(); //获取结束时间
        System.out.println("Runtime： "+(endTime-startTime)+"ns");

    }
}
