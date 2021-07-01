//turnq070
public class HashTable<T> {
    //Instance Variable(s)

    private LinkedList[] tableArray;//Underlying Array:

    public HashTable(){ //Constructor for general case
        tableArray = new LinkedList[113];
        for (int i = 0; i < tableArray.length; i++) {
            tableArray[i] = new LinkedList();
        }
    }

    public HashTable(int numOfElements){ //Constructor for specific case
        tableArray = new LinkedList[(int) (numOfElements)];
        for (int i = 0; i < tableArray.length; i++) {
            tableArray[i] = new LinkedList();
        }
    }

    public void add(T item){
        int index = hash3(item);
        if (tableArray[index].indexOf(item) == -1) { //"if the item is not in the linked list at this index"
            tableArray[index].addAtBeginning(item);//"then add the item to the linked list (in O(1))"
        }
    }

    public void display(){
        //I think the following is fairly self-explanatory
        int numUniqueTokens = 0;
        int numNonEmptyIndices = 0;
        int longestChain = 0;
        int tmpSize;
        for (int i = 0; i < tableArray.length; i++) {
            tmpSize = tableArray[i].size();
            numUniqueTokens += tmpSize;
            if (tmpSize != 0){
                numNonEmptyIndices ++;
            }
            if (tmpSize > longestChain){
                longestChain = tmpSize;
            }
            System.out.println(i + ": " + tmpSize);
        }
        System.out.println("average collision length = " + (0.0 + numUniqueTokens) / numNonEmptyIndices);
        System.out.println("longest chain: " + longestChain + "\n");
    }

    public void addStringsInFile(String fileName){
        //helper function to make the main cleaner, uses modified TextScan.java
        LinkedList tokenList = TextScan.generateTokens(fileName);
        for (int i = 0; i < tokenList.size(); i++) {
            add((T)tokenList.get(i));
        }
    }

    private int hash1(T key) {
        //my first attempt only considered the first and last characters
        return (int) (key.toString().charAt(0) + //adds first character
                key.toString().charAt(key.toString().length() - 1)) //adds last character
                % tableArray.length;
    }

    private int hash2(T key) {
        //my second attempt included consideration of the middle character,
        // but it was still not quite effective.
        return (int) (key.toString().charAt(0) +
                key.toString().charAt(key.toString().length() / 2) + //adds middle(ish) character
                key.toString().charAt(key.toString().length() - 1))
                % tableArray.length;
    }

    private int hash3(T key){
        //this is the finalized function used in the main, which multiplied the middle value by a prime (7).
        //using a prime number seems to have done the trick
        //Based on how well it ended up working, I think the quality of the Hash function
        // is considerably more important than the length of the table.
        return (int)(key.toString().charAt(0) +
                key.toString().charAt(key.toString().length() / 2) * 7 + //multiplies it by a prime
                key.toString().charAt(key.toString().length() - 1))
                % tableArray.length;
    }

    public static void main(String[] args) {
        //general
        HashTable gb = new HashTable();
        gb.addStringsInFile("gettysburg.txt");
        gb.display();
        //specific
        //if I'm not mistaken, my hash function is good enough to only need fifty spots
        //to meet the required largest collision of size three.
        //interestingly, ramping the size up to 500 only decreases the longest chain to size two.
        //decreasing it to 25 makes the longest chain size four.
        HashTable kw = new HashTable(50);
        kw.addStringsInFile("keywords.txt");
        kw.display();
    }
}
