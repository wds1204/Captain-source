
#1.概念

    HashMap继承AbstractMap,实现Map、Cloneable, Serializable接口。它的key、value都可以为null，映射不是有序的。
    HashMap不是同步，如果想要线程安全，可以通过Collection类的静态方法synchronizedMap获得线程安全的hashMap。

 ````
    Map map= Collections.synchronizedMap(new HashMao());
 ````

 HashMap中有两个重要的参数：初始容量和加载因子

    容量 是哈希表中捅的数量，厨师容量 只是哈希表在创建时的容量。
    加载因子 是哈希表在其容量自动增加前可以达到多满的一种尺度（默认0.75）。
    当哈希表中的条目数超过俩加载因子与当前容量的乘积时，则要对该哈希表进行rehash操作。

 HashMap的数据结构

    HashMap本质是数组加链表。通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样，然后再计算出
    数组下标，

<img src="https://camo.githubusercontent.com/0033942e062a94d633d46e462eb4b0adbef503ec/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630343239313632333132353430"/>


map.put(key,value)

系统将调用key的hashCode方法得到其hashCode值，然后再通过Hash算法的后两步运算来定位该键值对的存储位置，
有时两个key会定位到相同的位置，表示发生了Hash碰撞。当然Hash算法计算结果越分散均匀，Hash碰撞的概率就越小，
map的存取效率就会越高。

如果哈希桶数组很大，即使较差的Hash算法也会比较分散，如果哈希桶数组很小，即使好的Hash算法也会出现较多碰撞，
所以就需要在空间成本和时间成本之间权衡，其实就是哎根据实际情况确定哈希桶数组的大小，并在此基础上设计好的
Hash算法减少碰撞。那么通过什么方式来控制map使得Hash碰撞的概率又小，哈希桶数组(Node[]table)占用空间又少？
答案就是好的Hash算法和扩容机制。


   int threshold;               //所能容纳的key-value对极限
   final float loadFactor;      //负载因子

首先，Node[]table的初始化长度length（默认值是16），Load factor为负载因子(默认值是0.75),threshold是
HashMap所能容纳的最大数据量的Node(键值对)个数。threshold=length*LoadFactor。也就是说，在数组定义长度
之后，负载因子越大，所能容纳的键值对个数越多。

结合负载因子的定义公式可知，threshold就是在此Load factor和length对应下允许的最大元素数目，超过这个数目
就重新resize扩容，扩容后的Hash Map容量是之前容量的两倍。默认的负载因子0.75是对空间和时间效率的一个平衡选择，
建议大家不要修改，






























