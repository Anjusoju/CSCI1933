//written and modified by turnq070
//This is my linked list implementation from project 3, edited to
//be compatible with the NGen class for this project
public class LinkedList<T> {
    NGen<T> buffer;
    private boolean isSorted;

    public LinkedList() {
        buffer = new NGen<>(null, null);
    }

    //Method added for project 5
    public boolean addAtBeginning(T element){
        if (element == null) {
            return false;
        }
        NGen<T> temp = buffer.getNext();
        buffer.setNext(new NGen<T>(element, temp));
        return true;
    }

    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        NGen<T> ptr = buffer;
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        ptr.setNext(new NGen<T>(element, null));
        isSorted = false;
        return true;
    }

    public boolean add(int index, T element) {
        if (index >= size() || index < 0 || element == null) {
            return false;
        }
        if (index == 0) {
            NGen<T> temp = buffer.getNext();
            buffer.setNext(new NGen<T>(element, temp));
            isSorted = false;
            return true;
        } else {
            NGen<T> prev = getNodeAtIndex(index - 1);
            prev.setNext(new NGen<T>(element, prev.getNext()));
            isSorted = false;
        }
        return true;
    }

    public void clear() {
        buffer.setNext(null);
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        int i = 0;
        NGen<T> ptr = buffer.getNext();
        while (i != index) {
            ptr = ptr.getNext();
            i++;
        }
        return ptr.getData();
    }

    public int indexOf(T element) {
        if (element == null || isEmpty()) {
            return -1;
        }
//        if (isSorted) {
//            if (element.compareTo(get(0)) < 0 || element.compareTo(get(size() - 1)) > 0) {
//                return -1;
//            }
//        }
        int i = 0;
        NGen<T> ptr = buffer.getNext();
        while (ptr != null) {
            if (ptr.getData().equals(element)) {
                return i;
            }
            i++;
            ptr = ptr.getNext();
        }
        return -1;
    }

    public boolean isEmpty() {
        return buffer.getNext() == null;
    }

    public int size() {
        int i = 0;
        NGen<T> ptr = buffer;
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
            i++;
        }
        return i;
    }

//    public void sort() {
//        if (isSorted) {
//            return;
//        }
//        int i, j, s;
//        s = size();
//        T n;
//        for (i = 1; i < s; i++) {
//            n = get(i);
//            for (j = i - 1; j >= 0 && n.compareTo(get(j)) < 0; j--) {
//                getNodeAtIndex(j + 1).setData(get(j));
//            }
//            getNodeAtIndex(j + 1).setData(n);
//        }
//    }


    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        if (index == 0) {
            NGen<T> tmp = buffer.getNext();
            buffer.setNext(tmp.getNext());
            return tmp.getData();
        }
        NGen<T> prev = getNodeAtIndex(index - 1);
        T tmp = getNodeAtIndex(index).getData();
        NGen<T> next = getNodeAtIndex(index + 1);
        prev.setNext(next);
        return tmp;
    }

//    public void greaterThan(T element) {
//        int index = 0;
//        if (isSorted) {
//            index = indexOf(element);
//        }
//        NGen<T> ptr = buffer.getNext();
//        while (ptr != null) {
//            if (!(ptr.getData().compareTo(element) > 0)) {
//                remove(index);
//            } else {
//                index++;
//            }
//            ptr = ptr.getNext();
//        }
//    }

//    public void lessThan(T element) {
//        int index = 0;
//        NGen<T> ptr = buffer.getNext();
//        while (ptr != null) {
//            if (!(ptr.getData().compareTo(element) < 0)) {
//                remove(index);
//            } else {
//                index++;
//            }
//            ptr = ptr.getNext();
//        }
//    }

//    public void equalTo(T element) {
//        int index = 0;
//        NGen<T> ptr = buffer.getNext();
//        while (ptr != null) {
//            if (!(ptr.getData().compareTo(element) == 0)) {
//                remove(index);
//            } else {
//                index++;
//            }
//            ptr = ptr.getNext();
//        }
//    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        NGen<T> ptr = buffer.getNext();
        while (ptr != null) {
            out.append(ptr.getData().toString()).append("\n");
            ptr = ptr.getNext();
        }
        return out.toString();
    }

    //helper
    private NGen<T> getNodeAtIndex(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        int i = 0;
        NGen<T> ptr = buffer.getNext();
        while (i != index) {
            ptr = ptr.getNext();
            i++;
        }
        return ptr;
    }

    // Note: this method assumes a headed list
    public void removeEvery(int n) {
        if (n == 1) {
            buffer.setNext(null);
        } else if (n > 0) {
            int index = 0;
            NGen<T> trailer = buffer;
            NGen<T> ptr = buffer.getNext();
            while (ptr != null) {
                if (((index + 1) % n) == 0) {
                    trailer.setNext(ptr.getNext());
                } else {
                    trailer = ptr;
                }
                ptr = ptr.getNext();
                index++;
            }
        }
    }

    public void update(int n) { // headed, specifically using my project 3 implementation
        if (isEmpty() || n >= size()){
            buffer.setNext(null);
            return;
        }
        if (n <= 0){
            return;
        }
        int i = 0;
        NGen<T> ptr = buffer.getNext();
        while (i < n){
            remove(0);
            i++;
        }
    }

    //debugging
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.addAtBeginning("A");
        ll.addAtBeginning("B");
        ll.addAtBeginning("D");


        System.out.println(ll);
    }
}