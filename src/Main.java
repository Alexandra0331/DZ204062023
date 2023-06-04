import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(1, "Irina", "+79236845621"));
        contactList.addToEnd(new Contact(2, "Anna", "+79236985412"));
        contactList.addToEnd(new Contact(3, "Sasha", "+79053158478"));
        contactList.addToEnd(new Contact(4, "Polina", "+79829453211"));
        contactList.addToEnd(new Contact(5, "Roma", "+79235886459"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }
        contactList.reverse();

        System.out.println("-------------------------------------");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {

        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }


    public static class SingleLinkList<T> implements Iterable {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

      
        private static class ListItem<T> {

            T data;
            ListItem<T> next;
        }

        //Голова пустая
        public boolean isEmpty() {
            return head == null;
        }

        //заполнение списка
        public void addToEnd(T item) {

            //Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            //Если, голова и хвост пустая
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else { //Если, не пустая то передаём элементу адрес и ставим в хвост
                tail.next = newItem;
                tail = newItem;
            }
        }

        //метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) {//Если голова не равна нулю
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
