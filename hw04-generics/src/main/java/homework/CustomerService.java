package homework;


import java.util.*;

public class CustomerService {
    private final NavigableMap<Customer, String> map = new TreeMap<>(Comparator
            .comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        return copyEntry(entry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = map.higherEntry(customer);
        return copyEntry(entry);
    }

    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
        if (entry == null) {
            return null;
        }
        Customer customerCopy = new Customer(entry.getKey());
        return new AbstractMap.SimpleEntry<>(customerCopy, entry.getValue());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
