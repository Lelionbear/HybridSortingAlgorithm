import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HybridSortingAlgorithm {
    //                 x    4    y          x   3  y x   3  y                         x  3   y
//    private static int[] arr = {7,9,0,62,9,94,10,31,31,36,78,71,86,28,28,34,35,77,90,28,52,10,46,23,53};
    // = {36,37,42,63,8,15,51,15,6,77,38,84,89,97,9,58,43,16,77,76,87,84,94,9,1};
    // = {51,50,50,49,26,31,74,80,79,29,59,17,64,68,16,24,53,10,72,41,71,55,12,83,52,27,66,67};
    private int runSize;// = 5;
    private ArrayList<Point> runList = new ArrayList<Point>();
    private ArrayList<Point> unsortedList = new ArrayList<Point>();
    private ArrayList<Point> mList = new ArrayList<Point>();
//    private static int[] forMerge = new int[];

    public HybridSortingAlgorithm(){
        runSize = 65;
    }

    public void sortMe(int[] arr,int[] otherArr){
//        printArr(arr);
        findRuns(arr);
        if (runList.size() > 0)
            findUnsortedRuns(arr);
        else
            unsortedList.add(new Point(0,arr.length-1));
        breakUnsortedRuns(arr);
        sortUnsortedList(arr);
//        System.out.println(unsortedList);
//        System.exit(77);//Execute order 77!!!!!!!!!!!!!!!!
        mergeListsMaker();
//        System.out.println(arr.length);
//        System.out.println("R: "+runList.size()+" UR: "+unsortedList.size()+" total: "+mList.size());
//        System.out.println(unsortedList);
//        printArr(arr);
//        System.arraycopy(arr,0,otherArr,0,arr.length-1);
        myMerge(arr);
//        printArr(arr);
//        System.out.println(" "+isSorted(arr));
    }

    private void mergeListsMaker() {
//        int i,left = 0,right = 0,min,max,sum;
//        String minName,maxName;
//        if (unsortedList.size() > runList.size()){
//            min = runList.size();
//            max = unsortedList.size();
//            maxName = "notRun";
//        }
//        else{
//            min = unsortedList.size();
//            max = runList.size();
//            maxName = "run";
//        }
//        sum = min+max;
        ArrayList<Point> temp = new ArrayList<Point>();
        temp.addAll(runList);
        temp.addAll(unsortedList);
        //sort temp arraylist and save into mlist
        int k = 0;
        Point A = temp.get(0);
        while(temp.size() > 0){
            for (Point aTemp : temp) {
                A = aTemp;
                if (k == getPointX(A)) {
                    mList.add(A);
                    k = getPointY(A) + 1;
                    break;
                }
//                temp.remove(A);
            }
            temp.remove(A);

        }
//        System.out.println(Arrays.toString(new ArrayList[]{temp}));
//        System.out.println(mList);


//        findRuns(mList);

//        for (int b = 0;b < runList.size();b++){
//            for (int t = 0;t < unsortedList.size();t++){
//                if (getPointX(runList.get(t)) < getPointX(unsortedList.get(b)))
//                    mList.add(runList.get(t));
//                else{
//                    mList.add(unsortedList.get(b));
////                    break;
//                }
//            }
//        }

//        while(left < runList.size() || right < unsortedList.size()){
//            System.out.println(runList.get(left)+" "+unsortedList.get(right));
//            if (getPointX(runList.get(left)) < getPointX(unsortedList.get(right))){
//                mList.add(runList.get(left));
//                if (left < runList.size())
//                    left++;
//                else
//                    break;
//            }
//            else{
//                mList.add(unsortedList.get(right));
//                if (right < unsortedList.size())
//                    right++;
//                else
//                    break;
//            }
//        }

//        for(i = 0;i < sum; i++){
//            if (getPointX(unsortedList.get(left)) < getPointX(runList.get(right))){
//                mList.add(unsortedList.get(left));
////                mList.add(runList.get(right));
//                left++;
//            }
//            else{
//                mList.add(runList.get(right));
////                mList.add(unsortedList.get(left));
//                right++;
//            }
//        }
//        while(i < max){
//            if (maxName.equals("notRun")){
//                mList.add(unsortedList.get(i));
//            }
//            else{
//                mList.add(runList.get(i));
//            }
//            i++;
//        }
    }

    private void myMerge(int[] arr) {
        while(mList.size() > 1){
            for (int i = 0;i < mList.size()-1;i++){
                mergeArr(arr,mList.get(i),mList.get(i+1));
                setPointY(mList.get(i),getPointY(mList.get(i+1)));
                mList.remove(mList.get(i+1));
            }
        }
    }

    private void mergeArr(int[] arr, Point runOne, Point runTwo) {
        //array boundaries, limits the extent of how far along the array it traverses
        int leftStart = getPointX(runOne);
        int leftEnd = getPointY(runOne);//(leftStart + rightEnd)/2;
        int rightStart = getPointX(runTwo);//leftEnd +1;
        int rightEnd = getPointY(runTwo);
        int size = rightEnd - leftStart + 1;//size of current scope of the array

        //index markers indicate where along the array the comparison is taking place
        int left = 0;//leftStart;
        int right = 0;//rightStart;
        int index = leftStart;

        int temp[] = new int [size];
        int lSize = leftEnd-leftStart+1;
        int rSize = rightEnd-rightStart+1;
        int L[] = new int[lSize];
        int R[] = new int[rSize];
        for (int i = 0;i < lSize;i++){
            L[i] = arr[leftStart+i];
        }
        for (int i = 0;i < rSize; i++){
            R[i] = arr[rightStart+i];
        }
//        System.out.println(Arrays.toString(L));
//        System.out.println(Arrays.toString(R));
//        System.exit(0);

        while(left  < lSize && right < rSize){
            if (L[left] <= R[right]){
                arr[index] = L[left];
                left++;
            }
            else{
                arr[index] = R[right];
                right++;
            }
            index++;
        }
//        System.out.println(Arrays.toString(temp));
//        System.out.println(index+" "+left+" "+right);
//        printArr();
        while(left < lSize){
            arr[index] = L[left];
            index++;
            left++;
        }
        while(right < rSize){
            arr[index] = R[right];
            index++;
            right++;
        }
//        System.out.println(Arrays.toString(temp));
//        System.arraycopy(temp,leftStart,arr,leftStart,size);
//        System.arraycopy(temp,0,arr,index,size);
//        printArr();
//        System.arraycopy(arr,left,sortedArr,index,leftEnd - left + 1);//copies left side of sorted array into sortedArr
//        System.arraycopy(arr,right,sortedArr,index,rightEnd - right + 1);//copies right side of sorted array into sortedArr
//        System.arraycopy(sortedArr,leftStart,arr,leftStart,size);//copies sortedArr back to original array arr
    }

    private void sortUnsortedList(int[] arr) {
//        Point p;
//        p = unsortedList.get(2);
//        System.out.println(p);
//        inSort(getPointX(p),getPointY(p));
//        printArr();
////        p = unsortedList.get(0);
////        inSort(getPointX(p),getPointY(p));
////        p = unsortedList.get(1);
////        inSort(getPointX(p),getPointY(p));
//        System.exit(10);
        for (Point p : unsortedList) {
            bbSort(arr,getPointX(p), getPointY(p));
        }
    }

//    private static void inSort(int pointX, int pointY) {
//        for (int i = pointX + 1; i < pointY ; i++){
//            int temp = arr[i];
//            int k = i - 1;
//            while (k >= 0 && arr[k] > temp){
//                arr[k+1] = arr[k];
//                --k;
//            }
//            arr[k + 1] = temp;
//        }
//    }

    private void bbSort(int[] arr, int pointX, int pointY) {
        boolean swapped = true;
        int j = 0;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = pointX; i < pointY; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr,i, i + 1);
                    swapped = true;
                }
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void breakUnsortedRuns(int[] arr){
        ArrayList<Point> temp = new ArrayList<Point>(unsortedList);
//        System.out.println(unsortedList);
        unsortedList.clear();
//        System.exit(9);
        int i;
        for (Point p: temp) {
            int start = getPointX(p);
            int end = getPointY(p);
            int steps = runSize;
            while(start+steps-1 < end){
//                System.out.println(start+" "+(start+steps-1));
                unsortedList.add(new Point(start,start+steps-1));
                start+=steps;
            }
//            for (i = 1; i<=runSize ;i++){
//            }

//            boolean firstZero = true;
//            System.out.println(start+" "+end);
            unsortedList.add(new Point(start,end));
//            for (i = 0; (i+1)<=runSize; i++){
//                System.out.println(i);
////                if (i % runSize == 0 && !firstZero){
//////                    unsortedList.add(new Point(getPointX(p)+i,getPointX(p)+i));
//////                    start = i+1;
////                }
////                firstZero = false;
//            }
////            unsortedList.add(new Point(getPointX(p)+i,getPointY(p)));
////            while(i < getPointY(p)+1){
////                System.out.println(i);
////            }

        }
    }

    private void findUnsortedRuns(int[] arr) {
        int rx,ry,ux,uy;
        Point pone,ptwo;
        ux = 0;
        uy = 0;
//        unsortedList.add(new Point(ux,uy));

        for (int i = 0; i < runList.size()-1; i++){ //for btw runs
            pone = runList.get(i);
            ptwo = runList.get(i+1);

            if(getPointX(pone)-1 >= 0){//if there are sequences in between points even if there's one in between runs
                ux = getPointY(pone)+1;
                uy = getPointX(ptwo)-1;
                if (ux <= uy){
//                    if (uy-ux > runsize){
//                        for (int i = ux; i <= uy;i+=runsize){
//
//                        }
//                    }
                    unsortedList.add(new Point(ux,uy));
                }
            }
        }

        //throws error when runList is 1
        pone = runList.get(0);
        ptwo = runList.get(1);
        if (getPointX(pone)-1 == -1){//if there's a run at 0,0
            //get last point's y+1 and set it equal to new point's x
            ux = getPointY(pone)+1;
            uy = getPointX(ptwo)-1;
//            System.out.println("run at 0,0 test "+ux+" "+uy);
            if (ux <= uy)
                unsortedList.add(0,new Point(ux,uy));
        }
        else if(getPointX(pone)-1 >= 0){
            uy = getPointX(pone)-1;
//            if (ux <= uy )
            unsortedList.add(0,new Point(0,uy));
        }

        ptwo = runList.get(runList.size()-1);
        if (getPointY(ptwo)+1 != arr.length){//if there's a run at last section of arr
            //get last point's y+1 and set it equal to new point's x
            ux = getPointY(ptwo)+1;
            uy = arr.length-1;//getPointX(ptwo)-1;
//            System.out.println("no run at last part of array test "+ux+" "+uy);
            unsortedList.add(new Point(ux,uy));
        }
    }

    private int getPointY(Point p) {
        return (int)p.getY();
    }

    private int getPointX(Point p){
        return (int)p.getX();
    }

    private void setPointY(Point p,int y){
        p.setLocation((int)p.getX(),y);
    }

    private void setPointX(Point p,int x){
        p.setLocation(x,(int)p.getY());
    }

    private void findRuns(int[] arr) {
        int y,uy = 0,ux = 0,counter = 0,left,j;
        boolean foundRun = false;
        Point p = new Point(0,0);
        for (int x = 0; x < arr.length-1; x+=y){
            //use recursive function to get y if there is a run then increment x by y

            y = recursiveY(arr,x) + 1;//size of run aka len of run
            int nextX = x+y;
//            counter += y;
//
            if (y >= runSize){
                p = new Point(x,(x+y-1));
                runList.add(p);//System.out.println("X: "+x+" Y: "+(x+y-1));
//                if (counter < runSize){
//                    int t = counter;
//                    counter=0;//(counter%runSize)+1;
//                    p = new Point(x-t+1,nextX-1);
//                    System.out.println(nextX+"-");
//                }
            }
//            else
//                counter+=y;
//
//            if (counter >= runSize){
////                int t = counter;
//                counter = counter%runSize;
////                t-=counter;
////                p = new Point(x-t+1,x+y-1);
//                System.out.println(" "+x+" "+" "+counter);
//            }

//            if (counter < runSize){
//                foundRun = false;
//                System.out.println(nextX+"-");
//            }
//                int t = counter;
//                counter=(counter%runSize)+1;
//                p = new Point(x-t+1,x+1);
//                System.out.println(p+" "+(t)+" "+counter);
////                counter++;
////                foundRun = false;
//            }
//            else{
//                counter += y;
//            }
        }
    }

    private int recursiveY(int[] arr,int index) {
        if (index+1 == arr.length || arr[index+1] < arr[index])
            return 0;

        return recursiveY(arr,index+1) + 1;
    }

    private void printArr(int[] arr) {
        for (int num: arr) {
            System.out.print(num+" ");
        }
        System.out.println(" ");
    }
}
