public class FindPeakElement {

	public static int findPeakFor(int[] nums){
		int left=0;
		int right=nums.length-1;
		
		for(;left<right;){
			int middle=(left+right)/2;
			if(nums[middle]>nums[middle+1]){
				right=middle;
			}else{
				left=middle+1;
			}
		}
		return left;
	}
    
    public static void main(String args[]){
    	int[] nums={1,2,6,5,3,7,4};
    	System.out.println("The peak is "+ nums[findPeakFor(nums)]);
    }
}


