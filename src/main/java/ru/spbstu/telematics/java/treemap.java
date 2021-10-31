package ru.spbstu.telematics.java;
import java.util.Objects;
import java.util.Iterator;
import java.lang.Iterable;

public class treemap<K,V> implements Iterable<treemap.Node<K,V>> {
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new MapIterator();
    }

    private Node<K,V> root;
    private int size = 0;
    private int modCount = 0;
    public void clearAll() {
        this.size=0;
        this.modCount++;
        root=null;
    }
    public void put(K key, V value) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(key);
        Node<K,V> t = root;
        if(root==null) {

            root = new Node<>(key,value,null);
            size=1;
            modCount++;
            return;
        }
        int cmp;
        Node<K,V> pre ;

        Comparable<? super K> k = (Comparable<? super K>) key;
        do {
            pre = t;
            cmp=k.compareTo(t.m_key);
            if(cmp<0) {
                t=t.m_left;
            }
            else if (cmp>0) {
                t=t.m_right;
            }
            else {
                t.m_value=value;
            }
        } while(t!=null);
        addToTree(key,value,pre,cmp<0);
    }
    private void addToTree(K key,V value, Node<K,V> parent, boolean side) {
        Node<K,V> n = new Node<>(key,value,parent);
        if(side==false)
            parent.m_right = n;
        else
            parent.m_left = n;
        fixAfterInsertion(n);
        size++;
        modCount++;
    }
    public int size() {
        return this.size;
    }
    public boolean containsByKey(K key) {
        Objects.requireNonNull(key);
        Comparable<? super  K> k= (Comparable<? super K>) key;
        Node<K,V> n = root;
        while(n!=null) {
            int cmp = k.compareTo(n.m_key);
            if(cmp<0) {
                n=n.m_left;
            }
            else if(cmp>0) {
                n= n.m_right;
            }
            else {
                return true;
            }
        }
        return false;
    }
    public boolean containsByValue(V value) {
        Objects.requireNonNull(value);
        for (Node<K,V> e = getFirstNode(); e != null; e = pre(e))
            if (value.equals(e.m_value))
                return true;
        return false;
    }

    public boolean remove(K key) {
        Objects.requireNonNull(key);
        Node<K,V> n = root;
        Comparable<? super K> k = (Comparable<? super K>)key;
        while(n!= null) {
            int cmp = k.compareTo(n.m_key);
            if(cmp<0) {
                n = n.m_left;
            }
            else if(cmp>0) {
                n = n.m_right;
            }
            else {
                deleteNode(n);
                return true;
            }
        }
        return false;
    }
    public Node<K,V> getNode(K key) {
        Objects.requireNonNull(key);
        Node<K,V> n = root;
        Comparable<? super K> k = (Comparable<? super K>)key;
        while(n!= null) {
            int cmp = k.compareTo(n.m_key);
            if(cmp<0) {
                n = n.m_left;
            }
            else if(cmp>0) {
                n = n.m_right;
            }
            else {

                return n;
            }
        }
        return null;
    }


    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static final class Node<K,V>  {
        K m_key;
        V m_value;
        Node<K,V> m_left;
        Node<K,V> m_right;
        Node<K,V> m_parent;
        boolean clr = BLACK;


        Node(K key, V value, Node<K,V> parent) {
            this.m_key=key;
            this.m_value=value;
            this.m_parent=parent;
        }
        public K getM_key() {
            return this.m_key;
        }
        public V getM_value() {
            return this.m_value;
        }

        public V setM_value(V value) {
            V oldValue = this.m_value;
            this.m_value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "m_key=" + m_key +
                    ", m_value=" + m_value +
                    '}';
        }
    }
    final Node<K,V>  getFirstNode() {
        Node<K,V> n= root;
        if(n!= null) {
            while (n.m_left!=null){
                n=n.m_left;
            }
        }
        return n;
    }
    final Node<K,V> getLastNode() {
        Node<K,V> n= root;
        if(n!= null) {
            while (n.m_right!=null){
                n=n.m_right;
            }
        }
        return n;
    }
    private static <K,V> boolean colorOf(Node<K,V> n) { return (n==null ? BLACK: n.clr); }
    private static <K,V> Node<K,V> parentOf(Node<K,V> n) {return (n==null ? null: n.m_parent);}
    private static <K,V> void setColor(Node <K,V> n, boolean c) {
        if (n != null)
            n.clr=c;
    }
    private static <K,V> Node<K,V> leftOf(Node<K,V> n) {return (n==null ? null : n.m_left);}
    private static <K,V> Node<K,V> rightOf(Node<K,V> n) {return (n==null ? null : n.m_right);}

    private void rotateLeft(Node<K,V> n) {
        if (n != null) {
            Node<K,V> r = n.m_right;
            n.m_right = r.m_left;
            if (r.m_left != null)
                r.m_left.m_parent = n;
            r.m_parent = n.m_parent;
            if (n.m_parent == null)
                root = r;
            else if (n.m_parent.m_left == n)
                n.m_parent.m_left = r;
            else
                n.m_parent.m_right = r;
            r.m_left = n;
            n.m_parent = r;
        }
    }
    private void rotateRight(Node<K,V> n) {
        if (n != null) {
            Node<K,V> l = n.m_left;
            n.m_left = l.m_right;
            if (l.m_right != null) l.m_right.m_parent = n;
            l.m_parent = n.m_parent;
            if (n.m_parent == null)
                root = l;
            else if (n.m_parent.m_right == n)
                n.m_parent.m_right = l;
            else n.m_parent.m_left = l;
            l.m_right = n;
            n.m_parent = l;
        }
    }
    private void fixAfterInsertion(Node<K,V> x) {
        x.clr = RED;

        while (x != null && x != root && x.m_parent.clr == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node<K,V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.clr = BLACK;
    }
    private void deleteNode(Node<K,V> p) {
        modCount++;
        size--;


        if (p.m_left != null && p.m_right != null) {
            Node<K,V> s = pre(p);
            p.m_key = s.m_key;
            p.m_value = s.m_value;
            p = s;
        }

        Node<K,V> replace = (p.m_left != null ? p.m_left : p.m_right);

        if (replace != null) {

            replace.m_parent = p.m_parent;
            if (p.m_parent == null)
                root = replace;
            else if (p == p.m_parent.m_left)
                p.m_parent.m_left  = replace;
            else
                p.m_parent.m_right = replace;


            p.m_left = p.m_right = p.m_parent = null;


            if (p.clr == BLACK)
                fixAfterDeletion(replace);
        } else if (p.m_parent == null) {
            root = null;
        } else {
            if (p.clr == BLACK)
                fixAfterDeletion(p);

            if (p.m_parent != null) {
                if (p == p.m_parent.m_left)
                    p.m_parent.m_left = null;
                else if (p == p.m_parent.m_right)
                    p.m_parent.m_right = null;
                p.m_parent = null;
            }
        }
    }
    static <K,V> Node<K,V> pre(Node<K,V> t) {
        if (t == null)
            return null;
        else if (t.m_right != null) {
            Node<K,V> p = t.m_right;
            while (p.m_left != null)
                p = p.m_left;
            return p;
        } else {
            Node<K,V> p = t.m_parent;
            Node<K,V> ch = t;
            while (p != null && ch == p.m_right) {
                ch = p;
                p = p.m_parent;
            }
            return p;
        }
    }
    private void fixAfterDeletion(Node<K,V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Node<K,V> sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Node<K,V> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
    }
    public class MapIterator<T> implements Iterator<T> {
        Node<K,V> it;


        public MapIterator(){
            it=getFirstNode();
        }
        @Override
        public boolean hasNext() {
            return (pre(it)==null) ? false : true;
        }

        @Override
        public T next() {
            T node = (T)pre(it);
            it=pre(it);
            return node;
        }
    }

}

