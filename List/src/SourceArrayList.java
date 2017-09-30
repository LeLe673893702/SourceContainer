import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by 刺雒 on 2017/9/29.
 */
public class SourceArrayList<E>{
    private Object[] elementData;
    private int size;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /*
    *  初始化List
    * */
    public SourceArrayList(int initialCapacity) {
        // 如果数组不合法就抛出异常
        if (initialCapacity < 0) {
            try {
                throw new Exception("数组长度不合法");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Objects[initialCapacity];
    }

    public SourceArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /*
    * 数组末尾添加某个元素
    * */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = (Object) e;
        return true;
    }


    /*
    * 添加元素到指定位置之前
    * */
    public void add(int index, E e) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);

        // 比如1,2,3,4,5,index = 2 size-index = 3，复制完1,2,3,3,4,5
        System.arraycopy(elementData,index,elementData,index+1,size - index);
        // 替换过去
        elementData[index] = e;
        size++;
    }


    /*
    * 获取元素
    *
    * */
    public E get(int index) {
        rangeCheck(index);

        return (E)elementData[index];

    }

    /*
    * 按照索引位置删除元素
    * */
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = (E)elementData[index];
        // 需要复制几个元素
        int numMoved = size-index-1;
        // 以index为界限后半部分复制进去，复制的数量就是((size-1) -index)，就是index和最后一个元素之间相差多少
        // 如果移除最后一个元素就不用拷贝
        // srtPos:表示开始复制的位置
        // src:从这个数组里面复制元素
        // desPos: 表示开始拷贝的位置
        // dest：拷贝到这个数组里面
        // length:复制的元素
        if (numMoved > 0) {
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }


    /*
    * 根据对象删除元素
    * 如果有多个相同的元素删除最近的
    * */
    public boolean remove(Object object) {
        // 如果传入的是空的就移除空的
        if (object == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0 ; index < size; index++) {
                if (object.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * 设置元素
    * */
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = (E)elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /*
    *  快速去除元素
    * */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
    }

    /*
    * 索引范围检测
    * */
    public void rangeCheck(int index) {
        if ( index >= elementData.length) {
            throw new IndexOutOfBoundsException("超出数组");
        }
    }

    /*
    * 添加数组范围检测
    * */
    public void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("数组越界");
        }
    }

    /*
    * 数组内部扩容
    * minCapacity = size + 1，代表当前数组中的元素数量+1
    * */
    private void ensureCapacityInternal(int minCapacity) {
        // 如果是空数组,minCapacity 等于10和传入的数组大小中的最大值
        // 空数组size+1肯定是1，所以minCapacity = 10
        // 默认空数组是10
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY,minCapacity);
        }

        ensureExplicitCapacity(minCapacity);

    }

    /*
    * 扩容道指定容量
    * */
    private void ensureExplicitCapacity(int minCapacity) {

        // 如果目前数组元素需要最小容量 > 数组现在的容量
        // 就需要扩容
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /*
    * 增大数组的容量
    * */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // 新的扩容数组容量大小，数组长度 + 数组长度/2 = 1.5数组长度
        // 这样就不用每次扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        // 当传入的空数组的时候minCapacity = 10
        // newCapacity = 1.5,newCapacity = 10
        // 空数组容量在第一次添加之后就会被扩容成10
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        // 重新创建一个新的容量的数组，然后把元素复制进去
        elementData = Arrays.copyOf(elementData,newCapacity);
    }


}
