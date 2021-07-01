//turnq070
public class LinkedList<T extends Comparable<T>> implements List<T> {
    Node<T> buffer;
    private boolean isSorted;
    public LinkedList(){
        buffer = new Node<>(null, null);
    }
    @Override
    public boolean add(T element) {
        if (element == null){
            return false;
        }
        Node<T> ptr = buffer;
        while (ptr.getNext() != null){
            ptr = ptr.getNext();
        }
        ptr.setNext(new Node<T>(element));
        isSorted = false;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (index >= size() || index < 0 || element == null){
            return false;
        }
        if (index == 0){
            Node<T> temp = buffer.getNext();
            buffer.setNext(new Node<T>(element, temp));
            isSorted = false;
            return true;
        } else {
            Node<T> prev = getNodeAtIndex(index-1);
            prev.setNext(new Node<T>(element, prev.getNext()));
            isSorted = false;
        }
        return true;
    }

    @Override
    public void clear() {
        buffer.setNext(null);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()){
            return null;
        }
        int i = 0;
        Node<T> ptr = buffer.getNext();
        while (i != index){
            ptr = ptr.getNext();
            i++;
        }
        return ptr.getData();
    }

    @Override
    public int indexOf(T element) {
        if (element == null || isEmpty()){
            return -1;
        }
        int i = 0;
        Node<T> ptr = buffer.getNext();
        while (ptr != null){
            if (ptr.getData().equals(element)){
                return i;
            }
            i++;
            ptr = ptr.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return buffer.getNext() == null;
    }

    @Override
    public int size() {
        int i = 0;
        Node<T> ptr = buffer;
        while (ptr.getNext() != null){
            ptr = ptr.getNext();
            i++;
        }
        return i;
    }

    @Override
    public void sort() {
        if (isSorted) {
            return;
        }
        int i, j, s;
        s = size();
        T n;
        for (i = 1; i < s; i++) {
            n = get(i);
            for (j = i - 1; j >= 0 && n.compareTo(get(j)) < 0; j--) {
                getNodeAtIndex(j + 1).setData(get(j));
            }
            getNodeAtIndex(j + 1).setData(n);
        }
    }



    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        if (index == 0){
            Node<T> tmp = buffer.getNext();
            buffer.setNext(tmp.getNext());
            return tmp.getData();
        }
        Node<T> prev = getNodeAtIndex(index-1);
        T tmp = getNodeAtIndex(index).getData();
        Node<T> next = getNodeAtIndex(index + 1);
        prev.setNext(next);
        return tmp;
    }

    @Override
    public void greaterThan(T element) {
        int index = 0;
        Node<T> ptr = buffer.getNext();
        while (ptr != null){
            if(!(ptr.getData().compareTo(element) > 0)){
                remove(index);
            } else {
                index++;
            }
            ptr = ptr.getNext();
        }
    }

    @Override
    public void lessThan(T element) {
        int index = 0;
        Node<T> ptr = buffer.getNext();
        while (ptr != null){
            if(!(ptr.getData().compareTo(element) < 0)){
                remove(index);
            } else {
                index++;
            }
            ptr = ptr.getNext();
        }
    }

    @Override
    public void equalTo(T element) {
        int index = 0;
        Node<T> ptr = buffer.getNext();
        while (ptr != null){
            if(!(ptr.getData().compareTo(element) == 0)){
                remove(index);
            } else {
                index++;
            }
            ptr = ptr.getNext();
        }
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        Node<T> ptr = buffer.getNext();
        while (ptr != null){
            out.append(ptr.getData().toString()).append("\n");
            ptr = ptr.getNext();
        }
        return out.toString();
    }

    //helper
    private Node<T> getNodeAtIndex(int index){
        if (index >= size() || index < 0 ){
            return null;
        }
        int i = 0;
        Node<T> ptr = buffer.getNext();
        while (i != index){
            ptr = ptr.getNext();
            i++;
        }
        return ptr;
    }

    //debugging
    public static void main(String[] args) {
    }
}
