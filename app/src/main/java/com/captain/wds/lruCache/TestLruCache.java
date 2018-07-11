package com.captain.wds.lruCache;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import java.util.LinkedHashMap;

/**
 * Created by wds on 2018-4-18.
 */

public class TestLruCache {
    public static void main(String[] args) {
        //实现Lru算法，可以利用双向链表
        ///////////////////////////////////////////////////////////////////////////
        // 假如 我们从表尾访问数据，在表头删除数据。当访问的数据项在链表中存在，则将该数据项移
        // 到链表尾，否则在表尾新建一个数据项。当链表容量超过一定的阈值，则移除表头的数据
        ///////////////////////////////////////////////////////////////////////////

        /*===========验证LinkHashMap============*/
        //布尔参数accessOrder，当它为true时，LinkedHashMap会以访问顺序为序排列元素，否则以插入顺序为序排序元素。
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>(5, 0.75F, true);

        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        linkedHashMap.put(4, 4);
        linkedHashMap.put(5, 5);
        for (LinkedHashMap.Entry<Integer, Integer> entry : linkedHashMap.entrySet()) {
            System.out.print(String.valueOf(entry.getValue()) + "\t");
        }
        linkedHashMap.get(3);
        linkedHashMap.get(4);
        System.out.println("");
        for (LinkedHashMap.Entry<Integer, Integer> entry : linkedHashMap.entrySet()) {
            System.out.print(String.valueOf(entry.getValue()) +"\t");
        }

//        tempLruCache();


    }

    private static int getBitmapSize(Bitmap bitmap) {
        //API 19
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }
        //API 12
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        // Earlier Version
        return bitmap.getRowBytes() * bitmap.getHeight();
    }


    public static LruCache<String, Bitmap> getLruCache() {
          /*LruCache的是简单使用*/
        int maxMemorySize = (int) (Runtime.getRuntime().totalMemory() / 1024);
        int cacheMemorySize = maxMemorySize / 8;
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheMemorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return getBitmapSize(value);
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }

            @Override
            protected Bitmap create(String key) {
                return super.create(key);
            }
        };
        return lruCache;


    }
}
