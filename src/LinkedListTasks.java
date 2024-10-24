import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
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
        while (l1 != null || l2 != null || digit != 0) {
            int first = l1 != null ? l1.val : 0;
            int second = l2 != null ? l2.val : 0;
            temp.val = first + second + digit;
            digit = temp.val / 10;
            temp.val = temp.val % 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            if (l1 != null || l2 != null || digit != 0) {
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
        while (list1 != null || list2 != null) {
            res.next = new ListNode();
            res = res.next;
            if (list1 == null) {
                res.val = list2.val;
                list2 = list2.next;
                continue;
            }
            if (list2 == null) {
                res.val = list1.val;
                list1 = list1.next;
                continue;
            }
            if (list1.val < list2.val) {
                res.val = list1.val;
                list1 = list1.next;
            } else {
                res.val = list2.val;
                list2 = list2.next;
            }
        }
        return start.next;
    }

    //92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        left--;
        right--;
        temp = head;
        for (int i = right; i > left; i--) {
            list.get(i).next = list.get(i - 1);
        }
        if (left != 0)
            list.get(left - 1).next = list.get(right);
        if (right != list.size() - 1)
            list.get(left).next = list.get(right + 1);
        else
            list.get(left).next = null;
        return left == 0 ? list.get(right) : temp;
    }

    //25. Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int left = 0;
        int right = left + k - 1;
        while (right <= list.size() - 1) {
            for (int i = right; i > left; i--) {
                list.get(i).next = list.get(i - 1);
            }
            if (left == 0)
                temp = list.get(right);
            if (right != list.size() - 1)
                if (right + k <= list.size() - 1)
                    list.get(left).next = list.get(right + k);
                else
                    list.get(left).next = list.get(right + 1);
            else
                list.get(left).next = null;
            left = right + 1;
            right = left + k - 1;
        }
        return temp;
    }

    //19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        temp = head;
        if(n == 1) {
            if (list.size() >= 2)
                list.get(list.size() - 2).next = null;
            else
                temp = null;
        }
        else if(n == list.size()) {
            temp = list.get(1);
        }
        else {
            list.get(list.size() - n - 1).next = list.get(list.size() - n + 1);
        }
        return temp;
    }

    //82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode start = new ListNode(0);
        ListNode temp = start;
        start.next = head;

        while (head != null) {
            boolean isDuplicate = false;

            while (head.next != null && head.val == head.next.val) {
                isDuplicate = true;
                head = head.next;
            }
            if (isDuplicate) {
                head = head.next;
                continue;
            }
            temp.next = head;
            temp = temp.next;
            head = head.next;
        }
        temp.next = null;

        return start.next;
    }
    //138. Copy List with Random Pointer
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node res = new Node(0);
        Node temp = res;

        Node current = head;
        while (current != null) {
            temp.next = new Node(current.val);
            map.put(current, temp.next);
            current = current.next;
            temp = temp.next;
        }

        current = head;
        temp = res.next;
        while (current != null) {
            temp.random = (current.random != null) ? map.get(current.random) : null;
            current = current.next;
            temp = temp.next;
        }

        return res.next;
    }

    //61. Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0 || head.next == null) return head;
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        temp = head;
        int rotate = k % list.size();
        if(rotate == 0) return head;
        list.get(list.size() - 1).next = temp;
        temp = list.get(list.size() - rotate);
        list.get(list.size() - rotate - 1).next = null;
        return temp;
    }

    //86. Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(0);
        ListNode after = new ListNode(0);
        ListNode beforeCurr = before;
        ListNode afterCurr = after;

        while(head != null) {
            if(head.val < x) {
                beforeCurr.next = head;
                beforeCurr = beforeCurr.next;
            } else {
                afterCurr.next = head;
                afterCurr = afterCurr.next;
            }
            head = head.next;
        }

        afterCurr.next = null;
        beforeCurr.next = after.next;

        return before.next;
    }

}
