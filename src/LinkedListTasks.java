import java.util.HashSet;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int x) {
          val = x;
          next = null;
      }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class LinkedListTasks {

    //141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode temp = res;
        int digit = 0;
        while(l1 != null || l2 != null || digit != 0) {
            int first = l1 != null ? l1.val : 0;
            int second = l2 != null ? l2.val : 0;
            temp.val = first + second + digit;
            digit = temp.val / 10;
            temp.val = temp.val % 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            if(l1 != null || l2 != null || digit != 0) {
                temp.next = new ListNode();
                temp = temp.next;
            }
        }
        return res;
    }

    //21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var res = new ListNode();
        var start = res;
        while(list1 != null || list2 != null) {
            res.next = new ListNode();
            res = res.next;
            if(list1 == null) {
                res.val = list2.val;
                list2 = list2.next;
                continue;
            }
            if(list2 == null) {
                res.val = list1.val;
                list1 = list1.next;
                continue;
            }
            if(list1.val < list2.val) {
                res.val = list1.val;
                list1 = list1.next;
            }
            else {
                res.val = list2.val;
                list2 = list2.next;
            }
        }
        return start.next;
    }

}
