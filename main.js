import { BinarySearchTree } from './JavaScript/BinarySearchTree.js';
import { HashMap } from './JavaScript/HashMap.js';
import { LinkedList } from './JavaScript/LinkedList.js';
import { KnightsTravails } from "./knight.js";

// Tests for JavaScript Linked List
// const list = LinkedList();

// list.prepend("dog");
// list.prepend("cat");
// list.prepend("parrot");
// list.prepend("hamster");

// console.log(list.toString());
// console.log(list.removeAt(4));
// console.log(list.toString());


// const list2 = LinkedList();
// list2.append("dog");
// list2.append("cat");
// list2.append("bird");

// console.log(list2.toString());
// console.log(list2.removeAt(0));
// console.log(list2.toString());

// Tests for JavaScript Hash Map
// const hashmap = HashMap();


// hashmap.set('first', 0);
// hashmap.set('secon', 1);
// hashmap.set('firsfdt', 2);
// hashmap.set('sesdf', 3);
// hashmap.set('firsdt', 4);
// hashmap.set('sesfn', 5);
// hashmap.set('hjsd', 6);

// console.log(hashmap.keys());
// console.log(hashmap.remove('sesdf'));
// console.log(hashmap.keys());
// console.log(hashmap.values());
// console.log(hashmap.entries());

// console.log(hashmap.keys().toString());
// console.log(hashmap.get('secon'));

const bst = BinarySearchTree([1, 7, 4, 23, 8, 9, 4, 3, 5, 10, 9, 67, 63, 324, 100]);

// Tests for JavaScript Knights Travails
bst.prettyPrint(bst.getRoot())
bst.rebalance();
bst.prettyPrint(bst.getRoot())

const kt = KnightsTravails();
console.log(kt.knightMoves([1,1],[1,1]))
console.log(kt.knightMoves([1,2],[7,7]))