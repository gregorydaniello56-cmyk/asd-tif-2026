public class DoublyLinkedList implements LinkedList{
    private Node2P head,tail;
    int size=0;

    public DoublyLinkedList(){
        head = tail = null;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public int size(){
        return size;
    }

    public void addFirst(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            baru.next = head;
            head.prev = baru;
            head = baru;
            size++;
        }
    }
    public void addLast(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
            size++;
        }
    }

    public void addAfter(int index,Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            Node2P temp = head;
            for(int i=0;i<index;i++){
                temp = temp.next;
            }
            baru.prev = temp;
            baru.next = temp.next;
            temp.next = baru;
            baru.next.prev = baru;
            size++;
        }
    }
    
    public void deleteFirst(){
        Node2P temp = head;
        if (!isEmpty()){ 
            if (head == tail)
                head = tail = null;
            else
            {
                head.next.prev = null;
                head = temp.next;
            } size--;
        }
        else
            System.out.println("List kosong!");
    }

    public void deleteLast(){
        Node2P temp = tail;
        if (!isEmpty()){
            if (tail == head){
                head = tail = null;
            }
            else {
                tail.prev.next = null;
                tail=temp.prev;
            } size--;
        }
        else System.out.println("List kosong!");

    }
    public void deleteAfter(int index){
        Node2P temp = head;
        if(index < size-2){
            for(int i=0;i<index;i++){
                temp = temp.next;
            }
            temp.next.next.prev = temp;
            temp.next = temp.next.next;
            size--;
        }
        else if(index==size-2){//menghapus tail
            this.deleteLast();
        }
        else{
            System.out.println("Index lebih besar atau sama dari ukuran list");
        }

    }


    public void print(){
        Node2P currentNode = head;
        for(int i =0;i<size;i++){
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
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
        Node2P currentNode;
        if (index < size/2){                        // lebih dekat ke head: telusuri dari head
            currentNode = head;
            for(int i=0;i<index;i++){
                currentNode = currentNode.next;
            }
        } else {                                    // lebih dekat ke tail: telusuri dari tail
            currentNode = tail;
            for(int i=size-1;i>index;i--){
                currentNode = currentNode.prev;
            }
        }
        return currentNode.data;
    }

    @Override
    public int indexOf(Object targetData) {
        // mencari kemunculan pertama targetData, mengembalikan indeksnya (-1 jika tidak ada)
        Node2P currentNode = head;
        for(int i=0;i<size;i++){
            if (samaDengan(currentNode.data, targetData)){
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public void printReverse() {
        // mencetak data dari tail ke head memanfaatkan pointer prev
        Node2P currentNode = tail;
        for(int i=0;i<size;i++){
            System.out.println(currentNode.data);
            currentNode = currentNode.prev;
        }
    }

    @Override
    public boolean remove(Object targetData) {
        // mencari dan menghapus node pertama dengan data = targetData
        // true jika berhasil, false jika targetData tidak ada
        Node2P currentNode = head;
        for(int i=0;i<size;i++){
            if (samaDengan(currentNode.data, targetData)){
                if (currentNode == head && currentNode == tail){    // satu-satunya node
                    head = tail = null;
                } else if (currentNode == head){                    // menghapus head
                    head = currentNode.next;
                    head.prev = null;
                } else if (currentNode == tail){                    // menghapus tail
                    tail = currentNode.prev;
                    tail.next = null;
                } else {                                            // menghapus node di tengah
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                }
                currentNode.next = null;
                currentNode.prev = null;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        // mengembalikan seluruh data dalam array, urut dari head sampai tail
        Object[] result = new Object[size];
        Node2P currentNode = head;
        for(int i=0;i<size;i++){
            result[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }
}
