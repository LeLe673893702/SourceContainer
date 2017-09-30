import java.util.LinkedList;

/**
 * Created by 刺雒 on 2017/9/30.
 */
public class SourceLinkedList<E> {
    public class Node<E> {
        public E item;
        public Node<E> pre;
        public Node<E> next;

        public Node(Node<E> pre,E item,Node<E> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    public Node<E> first;
    public Node<E> last;

    public int size ;
    /*
    * 添加节点
    *
    * */
    public void add(E item) {
        // 默认添加在尾部
        addLast(item);

        size++;
    }

    /*
    * 添加节点到指点位置之前
    * */
    public void add(int index,E item) {
        //判断添加在尾部
        if (index == size) {
            addLast(item);
        } else {
            addBefore(item,findNode(index));
        }
        size++;
    }

    /*
    * 返回头节点
    * */
    public E get(int index) {
        checkElementIndex(index);

        return findNode(index).item;
    }

    /*
    * 设置节点
    * */
    public E set(int index,E e) {
        checkElementIndex(index);
        Node<E> x = findNode(index);
        E oldValue = x.item;
        x.item = e;
        return oldValue;
    }

    /*
    * 根据索引删除节点
    * */
    public E remove(int index) {
        checkElementIndex(index);

        return removeNode(findNode(index));
    }


    /*
    * 根据对象删除节点
    * */
    public boolean remove(Object o) {
        // 找到和对象相同的那个节点然后删除掉
        if (o == null) {
            for (Node<E> f = first; f != null; f = f.next) {
                if (f.item == null) {
                    removeNode(f);
                    return true;
                }
            }
        } else {
            for (Node<E> f= first; f != null; f = f.next) {
                if (f.item.equals(o)) {
                    removeNode(f);
                    return true;
                }
            }
        }
        return false;
    }

    public E removeNode(Node<E> node) {
        E element = node.item;
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        if (pre == null) {
            // 如果移除的是头节点,后面的那个节点就是头结点了
            first = next;
        } else {
            // 前节点指向后节点
            pre.next = next;
            node.pre = null;
        }

        if (next == null) {
            // 如果移除的是尾节点
            last = next;
        } else {
            // 后节点指向前节点
            next.pre = pre;
            node.next = null;
        }
        node.item = null;
        size--;
        return element;
    }

    /*
    * 根据索引返回节点
    * */
    public Node<E> findNode(int index) {
        // 牛逼还是还是这个牛逼，虽然之前想到了从头遍历到尾，添加比较慢，但是这样方法还是没想到,效率提升了,下面来看

        // 看index是在前半部分还是在后半部分
        if (index < (size >> 1)) {
            Node<E> f = first;
            // 如果在前半部分就从first开始遍历
            for (int i = 0; i < index; i++) {
                f = f.next;
            }
            return f;
        } else {
            Node<E> l = last;
            // 如果在后半部分就从last开始遍历
            for (int i = size-1; i > index; i++) {
                l = l.pre;
            }
            return l;
        }
    }

    /*
    * 添加某个元素之前
    * */
    private void addBefore(E item,Node<E> node) {
        Node<E> newNode = new Node<>(node.pre,item,node);
        node.pre = newNode;

        if (newNode.pre == null) {
            // 如果添加在头节点之前，first重新指向新节点
            first = newNode;
        } else {
            // 如果不是头结点，node前面的节点next指向新节点
            node.pre.next = newNode;
        }
    }



    /*
    * 添加元素到头部
    * */
    private void addFirst(E item) {
        // 之前的头结点
        Node<E> f = first;
        // 他是新的头结点了
        Node<E> newNode = new Node<>(null,item,f);
        // 添加在头部，first永远都是刚创建的节点
        first = newNode;
        // 如果头节点为空，即第一次创建链表
        // 刚开始last和first都指向头节点
        if (f == null) {
            last = newNode;
        } else {
            f.pre = newNode;
        }
        size ++;
    }

    // 添加元素到尾部
    private void addLast(E item) {
        // 之前的尾节点
        Node<E> l = last;
        // 它是新的尾节点
        Node<E> newNode = new Node<>(l,item,null);
        // 添加在尾部，last永远指向刚创建的节点
        l = newNode;
        // 如果尾节点为空，即第一次创建链表
        // first，last都指向该节点
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /*
    * 判断index是否合法
    * */
    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw  new IndexOutOfBoundsException("索引越界");
        }
    }

    /*
    * 检查索引
    * */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }
 }
