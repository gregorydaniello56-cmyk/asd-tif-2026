/**
 * SinglyLinkedList
 */
public class SinglyLinkedList implements LinkedList{

    public Node head,tail;
    private int size=0;

    public SinglyLinkedList(){
        head=tail=null;
    }
    public boolean isEmpty(){
        return(size==0);
    }
    public int size(){
        return size;
    }
    public void addFirst(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else {
            baru.pointer = head;
            head = baru;
            size++;
        }
    }

    public void addLast(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else {
            tail.pointer = baru;
            tail = baru;
            size++;
        }
    }

    public void addAfter(int index,Object inputData){
        Node baru = new Node(inputData);
        Node C=head;
        for(int i=0;i<index;i++){
            C = C.pointer;
        }
        baru.pointer = C.pointer;
        C.pointer = baru;
    }

    public void deleteFirst(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){//else if(head==tail)
            head = null;
            tail = null;
            size--;
        }
        else{
            head = head.pointer;
            size--;
        }
    }

    public void deleteLast(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){//else if(head==tail)
            head = null;
            tail = null;
            size--;
        }
        else{
            Node temp = head;
            for(int i=1;i<size;i++){
                temp = temp.pointer;
            }
            tail = temp;
            tail.pointer = null;
            size--;
        }
    }

    public void deleteAfter(int index){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){//else if(head==tail)
            head = null;
            tail = null;
            size--;
        }
        else{
            Node temp = head;
            for(int i=0;i<index;i++){
                temp = temp.pointer;
            }
            temp.pointer = temp.pointer.pointer;
            size--;
        }
    }

    public void print(){
        Node currentNode = head;
        for(int i =0;i<size;i++){
            System.out.println(currentNode.data);
            currentNode = currentNode.pointer;
        }
    }

    // Membandingkan dua data dengan aman (mendukung data null)
    private boolean samaDengan(Object a, Object b){
        return (a == null) ? (b == null) : a.equals(b);
    }

    @Override
    public Object get(int index) {
        // mengembalikan data pada index ke-i dimulai dari head (head = index 0)
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node currentNode = head;
        for(int i=0;i<index;i++){
            currentNode = currentNode.pointer;
        }
        return currentNode.data;
    }

    @Override
    public int indexOf(Object targetData) {
        // mencari kemunculan pertama targetData, mengembalikan indeksnya (-1 jika tidak ada)
        Node currentNode = head;
        for(int i=0;i<size;i++){
            if (samaDengan(currentNode.data, targetData)){
                return i;
            }
            currentNode = currentNode.pointer;
        }
        return -1;
    }

    @Override
    public void printReverse() {
        // mencetak data dari tail ke head.
        // Singly linked list tidak punya pointer ke belakang, sehingga data
        // dikumpulkan dulu ke array (urut head -> tail) lalu dicetak dari belakang.
        Object[] data = toArray();
        for(int i=data.length-1;i>=0;i--){
            System.out.println(data[i]);
        }
    }

    @Override
    public boolean remove(Object targetData) {
        // mencari dan menghapus node pertama dengan data = targetData
        // true jika berhasil, false jika targetData tidak ada
        Node previousNode = null;
        Node currentNode = head;
        for(int i=0;i<size;i++){
            if (samaDengan(currentNode.data, targetData)){
                if (previousNode == null){          // node yang dihapus adalah head
                    head = currentNode.pointer;
                } else {
                    previousNode.pointer = currentNode.pointer;
                }
                if (currentNode == tail){           // node yang dihapus adalah tail
                    tail = previousNode;            // null jika list menjadi kosong
                }
                currentNode.pointer = null;
                size--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.pointer;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        // mengembalikan seluruh data dalam array, urut dari head sampai tail
        Object[] result = new Object[size];
        Node currentNode = head;
        for(int i=0;i<size;i++){
            result[i] = currentNode.data;
            currentNode = currentNode.pointer;
        }
        return result;
    }
}
