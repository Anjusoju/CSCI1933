//turnq070
public class ArrayList<T extends Comparable<T>> implements List<T>  {
    private T[] hypArray;
    private boolean isSorted = true;
    public ArrayList(){
        hypArray = (T[]) new Comparable[2];
    }

    @Override
    public boolean add(T element) {
        if (element == null){
            return false;
        }
        if (size() == hypArray.length){
            hypArray = resizeArray(hypArray);
        }
        int i = 0;
        while (hypArray[i] != null){
            i++;
        }
        hypArray[i] = element;
        isSorted = false;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size()){
            return false;
        }
        if (size() == hypArray.length){
            hypArray = resizeArray(hypArray);
        }
        int ptr;
        ptr = index;
        T tmp;
        while (hypArray[ptr] != null){
            tmp = hypArray[ptr];
            hypArray[ptr] = element;
            element = tmp;
            ptr++;
        }
        hypArray[ptr] = element;
        isSorted = false;
        return true;
    }

    @Override
    public void clear() {
        hypArray = (T[]) new Comparable[2];
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()){
            return null;
        }
        return hypArray[index];
    }

    @Override
    public int indexOf(T element) {
        if(element == null){
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (element.equals(hypArray[i]))
                return i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return hypArray[0] == null;

    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < hypArray.length; i++) {
            if (hypArray[i] == null){
                return size;
            }
            size++;
        }
        return size;
    }

    @Override
    public void sort() {
        if (isSorted){
            return;
        }
        int i,j,s;
        T n;
        s = size();
        for (i = 1; i < s; i++) {
            n = hypArray[i];
            for (j = i-1; j >= 0 && n.compareTo(hypArray[j]) < 0; j--){
                hypArray[j+1] = hypArray[j];
            }
            hypArray[j+1] = n;
        }
        isSorted = true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        T removed = hypArray[index];
        T[] tmp = (T[]) new Comparable[hypArray.length];
        for (int i = 0; i < index; i++) {
            tmp[i] = hypArray[i];
        }
        for (int i = index + 1; i < hypArray.length; i++) {
            tmp[i-1] = hypArray[i];
        }
        hypArray = tmp;
        return removed;
    }

    @Override
    public void greaterThan(T element) {
        for (int i = 0; i < size(); i++) {
            if (!(get(i).compareTo(element) > 0)) {
                remove(i);
                i--;
            }

        }
    }

    @Override
    public void lessThan(T element) {
        for (int i = 0; i < size(); i++) {
            if (!(get(i).compareTo(element) < 0)) {
                remove(i);
                i--;
            }

        }
    }

    @Override
    public void equalTo(T element) {
        for (int i = 0; i < size(); i++) {
            if (!(get(i).compareTo(element) == 0)) {
                remove(i);
                i--;
            }

        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            s.append(hypArray[i]);
            s.append("\n");
        }
        return s.toString();
    }

    //helper
    public T[] resizeArray(T[] arr){
        T[] tmp = (T[]) new Comparable[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            tmp[i] = arr[i];
        }
        return tmp;
    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("abc");
        a.add("def");
        a.add("ghi");
        a.add("jkl");
        a.add("mno");
        a.remove(2);
        a.remove(2);
        System.out.println(a);
    }
}
