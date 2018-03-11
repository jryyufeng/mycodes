//选快希堆不稳，堆归选基不变
// 堆排序时间复杂度分析http://blog.csdn.net/yuzhihui_no1/article/details/44258297
//新建堆O（n） 调整堆一趟为O(logN)
import java.util.*;
public class try11 {
    static class sort1 {
        //冒泡排序O(n2)稳定，空间复杂度O（1）
        public void mopao(int array[]){
            int temp;
            for(int i=0;i<array.length-1;i++){
                for(int j=0;j<array.length-i-1;j++){
                    if(array[i]<array[j]){
                        temp=array[j];
                        array[j]=array[i];
                        array[i]=temp;
                    }
                }
            }
        }
    //O(n2) 不稳定，选择排序，空间复杂度O（1）
        public int[] choose_sort(int arr[]){
            int min,temp;
            for(int i=0;i<arr.length-1;i++){
                min=i;
                for(int j=i+1;j<arr.length;j++) {
                    if(arr[j]<arr[min]){
                        min=j;
                    }
                }
                //一次内循环完毕后再换
                if(i!=min){
                    temp=arr[min];
                    arr[min]=arr[i];
                    arr[i]=temp;
                }
            }
            return arr ;
        }
        //插入排序O(n2)稳定 前n-1排好序，往前插入 数据基本有序时排序高效,空间复杂度O（n）
         public int[] insert_sort(int arr[]){
            int temp;
            for(int i=0;i<arr.length-1;i++){
                for(int j=i+1;j>0;j--){
                    if(arr[j]<arr[j-1]){
                        temp=arr[j];
                        arr[j]=arr[j-1];
                        arr[j-1]=temp;
                    }
                    else{
                        break;
                    }
                }

            }
            return arr;
        }
        //希尔排序，又叫缩小增量排序，选择一定的步长把序列分成几部分，对每个序列进行插入排序
        //完成后步长递减，最后会对整个序列进行一次插入排序，此时整个序列基本有序
        //空间复杂度O（1），不稳定排序
        //与插入排序最直观区别：插入排序步长一直为1，希尔排序则是先设定步长来处理
        //三层循环，最外层用来根据步长划分部分
        public int[] shell_sort(int[] arr){
            int temp;
            int step=arr.length;
            while(true){
                step=step/2;
                for(int k=0;k<step;k++){//根据增量分为若干子序列
                    //对每个子部分进行插入排序
                    for(int i=k+step;i<arr.length;i+=step){
                        for(int j=i;j>k;j-=step){
                            if(arr[j]<arr[j-step]){
                                temp=arr[j];
                                arr[j]=arr[j-step];
                                arr[j-step]=temp;
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
                if(step==1){
                    break;
                }
            }
            return arr;
        }
        //快速排序，基本思想：分治,利用递归实现
        //1.先从数列中取出来一个数最为key值
        //2.将比这个数小的数全部放在它的左边，大于或等于的数全部放在右边
        //3.对左右两个小数列重复第二步直到各区间只有1个数
        //O(Nlog(N)),不稳定，空间复杂度O（nlogn）
        public int par1(int arr[],int l,int r){
            int key=arr[l];//初始化
            while(l<r){
                //从右向左找第一个小的数
                while(l<r && arr[r]>=key) {
                    r--;
                }
                //出循环表示找到了相应的值
                arr[l]=arr[r];
                //从左向右找第一个大的数
                while(l<r&&arr[l]<=key){
                    l++;
                }
                //出循环表示找到了相应的值
                arr[r]=arr[l];
            }
            //key值到位
            arr[r]=key;
            //返回key下标
            return r;

        }
        public void quick_sort(int[] arr,int l,int r){
                if(l>=r){
                    return;
                }
                int l1=par1(arr,l,r);
                quick_sort(arr,l,l1-1);
                quick_sort(arr,l1+1,r);
        }
        //归并排序，分治思想 O(N*log(N))
        //分小组，每个小组内有序，然后合并有序
        //这样通过先递归的分解数列，再合并数列就完成了归并排序
        //小组由小到大
       //空间复杂度O（1）
        public static void merge(int[] a, int low, int mid, int high) {
            int[] temp = new int[high - low + 1];
            int i = low;// 左指针
            int j = mid + 1;// 右指针
            int k = 0;
            // 把较小的数先移到新数组中
            while (i <= mid && j <= high) {
                if (a[i] < a[j]) {
                    temp[k++] = a[i++];
                } else {
                    temp[k++] = a[j++];
                }
         }
            // 把左边剩余的数移入数组
            while (i <= mid) {
                temp[k++] = a[i++];
            }
            // 把右边边剩余的数移入数组
            while (j <= high) {
                temp[k++] = a[j++];
            }            // 把新数组中的数覆盖nums数组
            for (int k2 = 0; k2 < temp.length; k2++) {
                a[k2 + low] = temp[k2];
            }
        }

        public static void mergeSort(int[] a, int low, int high) {
            int mid = (low + high) / 2;
            if (low < high) {
                // 左边
                mergeSort(a, low, mid);                // 右边
                mergeSort(a, mid + 1, high);
                // 左右归并
                merge(a, low, mid, high);
                //System.out.println(Arrays.toString(a));
            }

        }
        //堆排序，O堆排序是一种选择排序 O(N*log(N)) 
        //堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
        //或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
        //大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
        //小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
        //堆排序的基本思想是：将待排序序列构造成一个大顶堆，
        //此时，整个序列的最大值就是堆顶的根节点。
       // 将其与末尾元素进行交换，此时末尾就为最大值。
        //然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
        //如此反复执行，便能得到一个有序序列了
      
        public void d_sort(int arr[]){
            //1.构建大顶堆
            for(int i=arr.length-1;i>0;i--){
                //从第一个非叶子节点从下至上，从右到左调整
                adjustHasp(arr,i,arr.length);
            }
            //2.调整堆结构+交换堆顶元素与末尾元素
            for(int j=arr.length-1;j>0;j--){
                swap(arr,0,j);//将堆顶元素与末尾元素进行交换
                adjustHasp(arr,0,j);//重新对堆进行调整
            }
        }
        //调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
        public void adjustHasp(int arr[],int i,int length){
            int temp=arr[i];//先取出当前元素i
            for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
                if(k+1<length && arr[k]<arr[k+1]){
                    //如果左子结点小于右子结点，k指向右子结点
                    k++;
                }
                if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）{
                        arr[i] = arr[k];
                        i=k;
                    }
                else{
                    break;
                }
            }
            arr[i] = temp;//将temp值放到最终的位置
        }
        //交换元素
        public static void swap(int []arr,int a ,int b){
            int temp=arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        //基数排序(桶排序的改进)，十字链表
        //桶排序：首先创建数组A[MaxValue]；然后将每个数放到相应的位置上（例如17放在下标17的数组位置）；最后遍历数组，即为排序后的结果。
        //基数数排序需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据。
        //链式基数排序，以排序两位数为例
        //1、建立从0到9的链表
        //2.以个位数为关键字链到相应的链表值之后
        //3.从0开始遍历链表，以十位数为关键字调整相应的链
        //4.从0开始输出链表值之后的数据即为排序完成数据
        //空间复杂度O（rd+n）,时间复杂度O（d（r+n））r代表关键字的基数，n代表关键字个数，d代表长度
        public static void RadixSort(int A[],int temp[],int n,int k,int r,int cnt[]){

            //A:原数组
            //temp:临时数组
            //n:序列的数字个数
            //k:最大的位数2
            //r:基数10
            //cnt:存储bin[i]的个数

            for(int i=0 , rtok=1; i<k ; i++ ,rtok = rtok*r){

                //初始化
                for(int j=0;j<r;j++){
                    cnt[j] = 0;
                }
                //计算每个箱子的数字个数
                for(int j=0;j<n;j++){
                    cnt[(A[j]/rtok)%r]++;
                }
                //cnt[j]的个数修改为前j个箱子一共有几个数字
                for(int j=1;j<r;j++){
                    cnt[j] = cnt[j-1] + cnt[j];
                }
                for(int j = n-1;j>=0;j--){      //重点理解
                    cnt[(A[j]/rtok)%r]--;
                    temp[cnt[(A[j]/rtok)%r]] = A[j];
                }
                for(int j=0;j<n;j++){
                    A[j] = temp[j];
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] arr1=new int[10];
        int[] arr=new int[]{1,3,4,10,2,5,7,6,8,9,0};

        sort1 s1=new sort1();
        //int[] arr2=s1.shell_sort(arr);
        int r=arr.length;
        //int[] arr2=s1.quick_sort(arr,0,r-1);
        //s1.quick_sort(arr,0,r-1);
        s1. mergeSort(arr,0,r-1);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
