// Tests for JavaScript Linked List
import { LinkedList } from './JavaScript/LinkedList.js';

const list = LinkedList();

list.prepend("dog");
list.prepend("cat");
list.prepend("parrot");
list.prepend("hamster");

console.log(list.toString());
console.log(list.removeAt(4));
console.log(list.toString());


const list2 = LinkedList();
list2.append("dog");
list2.append("cat");
list2.append("bird");

console.log(list2.toString());
console.log(list2.removeAt(0));
console.log(list2.toString());