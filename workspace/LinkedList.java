/*
Problem:  Write a program that keeps and manipulates a linked list of
	    String data. The data will be provided by the user one item at a time.
      The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list
	Input:  commands listed above
	Output:  the results to the screen of each menu
	    choice, and error messages where appropriate.
*/

// By JD Peppelman, 9-22-24
// LinkedList class that implements basic functionality of adding and removing list elements, while maintaining alphabetical order
public class LinkedList {

    // instance variables go here (think about what you need to keep track of!)
    private ListNode head;

    // constructors go here
    public LinkedList (String initValue) {
        head = new ListNode(initValue, null);
    }

    public LinkedList() {
        head = new ListNode("", null);
    }
    // precondition: the list has been initialized
    // postcondition: the ListNode containing the appropriate value has been added
    // and returned
    public ListNode addAValue(String line) {
        ListNode current = head;

        // List is currently blank,, set first Node to have a value
        if (current.getValue().equals("")) {
            head.setValue(line);
            return head;
        }

        // Value to be added to the list is the new head of the list
        else if (((String) current.getValue()).toLowerCase().compareTo(line.toLowerCase()) > 0) {
            ListNode newNode = new ListNode(line, null);
            
            ListNode temp = new ListNode(current.getValue(), current.getNext());
            newNode.setNext(temp);
            head = newNode;

            return newNode;
        }

        // Value is added somewhere in the list, not at the head
        while (
            current.getNext() != null && 
            ((String) current.getNext().getValue()).toLowerCase().compareTo(line.toLowerCase()) < 0
        ) {
            current = current.getNext();
        }

        ListNode newNode = new ListNode(line, current.getNext());
        current.setNext(newNode);

        return newNode;
    }

    // precondition: the list has been initialized
    // postcondition: the ListNode containing the appropriate value has been deleted
    // and returned.
    // if the value is not in the list returns null
    public ListNode deleteAValue(String line) {
        ListNode current = head;

        // Node to be deleted is the head
        if (((String) current.getValue()).equals(line)) {
            head = current.getNext();
            return current;
        }

        while (
            current.getNext() != null &&
            !((String) current.getNext().getValue()).equals(line)
        ) {
            current = current.getNext();
        }

        if (current.getNext() == null) return null;

        ListNode temp = current.getNext();

        if (current.getNext().getNext() == null) {
            current.setNext(null);
        } else {
            current.setNext(current.getNext().getNext());
        }

        return temp;
    }

    // precondition: the list has been initialized
    // postconditions: returns a string containing all values appended together with
    // spaces between.
    public String showValues() {
        ListNode current = head;
        String values = (String) current.getValue();

        while (current.getNext() != null) {
            current = current.getNext();
            values += " " + current.getValue();
        }

        return values;
    }

    // precondition: the list has been initialized
    // postconditions: clears the list.
    public void clear() {
        head = new ListNode("", null);
    }
}
