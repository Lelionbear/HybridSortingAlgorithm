public class MergeAlgo {
    public void sort(int[] arr) {
//        mergeSort(arr,new int[arr.length],0,arr.length-1);
//        while(mList.size() > 1){
//            for (int i = 0;i < mList.size()-1;i++){
//                mergeArr(arr,mList.get(i),mList.get(i+1));
//                setPointY(mList.get(i),getPointY(mList.get(i+1)));
//                mList.remove(mList.get(i+1));
//            }
//        }
    }

    private void mergeSort(int[] arr,int[] sortedArr, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) //base case
            return;
        int mid = (leftStart+rightEnd)/2;
        mergeSort(arr,sortedArr,leftStart,mid);//divide the leftmost half of arr
        mergeSort(arr,sortedArr,mid+1,rightEnd);//divide the rightmost half of arr
        mergeArr(arr,sortedArr, leftStart,rightEnd);//sort arr into new array "sortedArr"
    }

    private void mergeArr(int[] arr, int[] sortedArr,int leftStart, int rightEnd) {
        //array boundaries, limits the extent of how far along the array it traverses
        int leftEnd = (leftStart + rightEnd)/2;
        int rightStart = leftEnd +1;
        int size = rightEnd - leftStart + 1;//size of current scope of the array

        //index markers indicate where along the array the comparison is taking place
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left  <= leftEnd && right <= rightEnd){
            if (arr[left] <= arr[right]){
                sortedArr[index] = arr[left];
                left++;
            }
            else{
                sortedArr[index] = arr[right];
                right++;
            }
            index++;
        }
        System.arraycopy(arr,left,sortedArr,index,leftEnd - left + 1);//copies left side of sorted array into sortedArr
        System.arraycopy(arr,right,sortedArr,index,rightEnd - right + 1);//copies right side of sorted array into sortedArr
        System.arraycopy(sortedArr,leftStart,arr,leftStart,size);//copies sortedArr back to original array arr
    }
}
