import java.util.Arrays;
import java.util.HashMap;

/**
 
 This is a solution for Pramp.com's question:
 

Given an array of numbers arr and a number S, find 4 different numbers in arr that sum up to S.

Write a function that gets arr and S and returns an array with 4 indices of such numbers in arr, or null if no such combination exists.
Explain and code the most efficient solution possible, and analyze its runtime and space complexity.


The solution I implemented uses Sorting and Binary Search with 4 pointers to achieve a more efficient solution than the one offered.

*Detailed RunTime: O(nLog(n) + log(n))
*Detailed Space:   O(n)
  
  
 @author richardmpanga
 
 */

public class QuadCombination
{
	public int[] numsSum(double[] nums , double sum)
	{
		
	    if (nums == null || nums.length < 4)
	       return new int[0];

	    //Lower ptrs
	    int l1 = 0;
	    int l2 = 1;
	    
	    //higher ptrs
	    int h1 = nums.length - 1;
	    int h2 = nums.length - 2;
	    
	    //Turns for movement
	    boolean l2Turn = true;
	    boolean h2Turn = true;
	    
	    HashMap<Double, Integer> oIdxes = populateValueIdxes(nums);
	    
	    Arrays.sort(nums);
	   
	    boolean keepSearching = true;

	    while ( keepSearching && validPointers (l1, l2, h2, h1))
	    {
	    	
	        double currentSum = addSum(nums[l1], nums[l2], nums[h1], nums[h2]);
	        if (isTouching(l1, l2, h1, h2))
	        {
	            keepSearching = false;
	        }
	        
	        if (currentSum == sum)
	        {
	        	int idx1 = oIdxes.get(nums[l1]);
	        	int idx2 = oIdxes.get(nums[l2]);
	        	int idx3 = oIdxes.get(nums[h2]);
	        	int idx4 = oIdxes.get(nums[h1]);
	        	
	            return new int[] { idx1, idx2, idx3, idx4};
	        }

	        else if ( currentSum < sum)
	        {
	            if (l2Turn)
	            {
	            	l2++;
	            	l2Turn = false;
	            }
	            else
	            {
	              l1++;
	              l2Turn = true;
	            }
	        }    
	        else if (currentSum > sum)
	        {
	        	if (h2Turn)
	        	{
	        		h2--;
	        		h2Turn = false;
	        	}
	        	else 
	        	{
	        		h1--;
	        		h2Turn = true;
	        	}
	        }
	        
	}

	    return new int[0];
	}

	/**
	 *  Checks to see if the pointers are contiguous
	 * @param l1
	 * @param l2
	 * @param h1
	 * @param h2
	 * @return
	 */
	private boolean isTouching(int l1, int l2, int h1, int h2)
	{
		if (l1 + 1 == l2 && l2 + 1 == h2 && h2 + 1 == h1)
			return true;
		else
			return false;

	}

	private double addSum(double a, double b, double c, double d)
	{
		return a + b + c + d;
	}
	
	private HashMap<Double, Integer> populateValueIdxes(double [] nums)
	{
	    HashMap<Double, Integer> map = new HashMap<Double, Integer>();
	    
	for (int i = 0; i < nums.length; i++)
	    {
	        map.put(nums[i] , i);
	    }
	    return map;
	}
	
	private boolean validPointers(int l1, int l2 , int h2, int h1)
	{
		if (l1 < l2 && l2 < h2 && h2 < h1)
			return true;
		else
			return false;
		
	}


	public static void main (String [] anythingButArgs)
	{
		QuadCombination qc = new QuadCombination();
		double[] nums = new double[] { -4, 405, 11, -5.32, 4.983, 6, 20};
		
		double sum = 30;
		
		System.out.println(Arrays.toString(qc.numsSum(nums, sum)));
	
	}
}
