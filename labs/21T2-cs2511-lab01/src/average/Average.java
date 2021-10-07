package average;

public class Average {
    /**
     * Returns the average of an array of numbers
     * 
     * @param the array of integer numbers
     * @return the average of the numbers
     */
    public float computeAverage(int[] nums) {
        float result = 0;
        for (int number: nums) {
            result += number;
        }
        int size = nums.length;
        return result / size;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        Average myaAverage = new Average();
        double result = myaAverage.computeAverage(nums);
        System.out.printf("%f\n",result);
    }
}
