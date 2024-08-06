import { LinkedList } from "./LinkedList.js";

function BinarySearchTree(arr) {
    let size;
    let root;
    let rebalanceArr;
    buildTree(arr);

    function Node() {
        let value = null;
        let left = null;
        let right = null;

        return { value, left, right };
    }

    function getRoot() {
        return root;
    }

    function buildTree(buildArray) {
        size = 0;
        root = null;
        const sortedArray = buildArray.sort((a, b) => { return a-b });
        buildTreeInsertMidVal(sortedArray);
    }

    function buildTreeInsertMidVal(subArray) {
        const insertIndex = Math.floor(subArray.length/2);
        const insertValue = subArray[insertIndex];
        insert(insertValue);
        if (insertIndex>0) {
            buildTreeInsertMidVal(subArray.slice(0,insertIndex));
        }
        if (subArray.length>insertIndex+1) {
            buildTreeInsertMidVal(subArray.slice(insertIndex+1));
        }
    }

    function insert(value) {
        if (root) {
            let travNode = root;
            let prevNode = travNode;
            while (travNode) {
                let isLeft = (value < travNode.value);
                if (travNode.value === value) {
                    return 'Value is already in tree.';
                }
                else if (isLeft && travNode.left) {
                    prevNode = travNode;
                    travNode = travNode.left;
                }
                else {
                    prevNode = travNode;
                    travNode = travNode.right;
                }
            }
            travNode = Node();
            travNode.value = value;
            if (value < prevNode.value) {
                prevNode.left = travNode;
            }
            else {
                prevNode.right = travNode;
            }
            size++;
        }
        else {
            root = Node();
            root.value = value;
        }
    }

    function deleteItem(value) {
        let travNode = root;
        let prevNode = travNode;
        while (travNode) {
            let isLeft = (value < travNode.value);
            if (travNode.value === value) {
                while (travNode.left && travNode.right) {
                    let prevBiggestNode = travNode;
                    let nextBiggestNode = travNode.right;
                    while (nextBiggestNode.left) {
                        prevBiggestNode = nextBiggestNode;
                        nextBiggestNode = nextBiggestNode.left;
                    }
                    travNode.value = nextBiggestNode.value;
                    prevNode = prevBiggestNode;
                    travNode = nextBiggestNode;
                }
                if (!travNode.left && !travNode.right) {
                    if (prevNode.value === travNode.value) {
                        if (prevNode.right === travNode) {
                            prevNode.right = null;
                        }
                        else {
                            prevNode.left = null;
                        }
                    }
                    else {
                        (value < prevNode.value) ? prevNode.left = null : prevNode.right = null;
                    }
                }
                else if ((!travNode.left && travNode.right) || (travNode.left && !travNode.right)) {
                    let prevPoint;
                    let travPoint;
                    (travNode.value < prevNode.value) ? prevPoint = 'left' : prevPoint = 'right';
                    (travNode.left) ? travPoint = 'left' : travPoint = 'right';
                    prevNode[prevPoint] = travNode[travPoint];
                }
                return;
            }
            else if (isLeft && travNode.left) {
                prevNode = travNode;
                travNode = travNode.left;
            }
            else {
                prevNode = travNode;
                travNode = travNode.right;
            }
        }
        return "There is no item to delete.";
    }

    function find(value) {
        let travNode = root;
        while (travNode) {
            let isLeft = (value < travNode.value);
            if (travNode.value === value) {
                return true;
            }
            else if (isLeft && travNode.left) {
                travNode = travNode.left;
            }
            else {
                travNode = travNode.right;
            }
        }
        return false;
    }

    function levelOrder(callback) {
        if (!callback) {
            throw new Error('No callback is provided.');
        }
        let dequeue = LinkedList();
        let travNode = root;
        dequeue.append(travNode);
        let travDeq = dequeue.head();
        while (travDeq) {
            if (travDeq.value.left) {
                dequeue.append(travDeq.value.left);
            }
            if (travDeq.value.right) {
                dequeue.append(travDeq.value.right);
            }
            const val = dequeue.at(0).value;
            travDeq = travDeq.nextNode;
            dequeue.removeAt(0);
            callback(val);
        }
    }

    function inOrder(callback) {
        if (!callback) {
            throw new Error('No callback is provided.');
        }
        inOrderRecursive(root);
        function inOrderRecursive(node) {
            if (node.left) {
                inOrderRecursive(node.left);
            }
            callback(node.value);
            if (node.right) {
                inOrderRecursive(node.right);
            }
        }
    }

    function preOrder(callback) {
        if (!callback) {
            throw new Error('No callback is provided.');
        }
        preOrderRecursive(root);
        function preOrderRecursive(node) {
            callback(node.value);
            if (node.left) {
                preOrderRecursive(node.left);
            }
            if (node.right) {
                preOrderRecursive(node.right);
            }
        }
    }

    function postOrder(callback) {
        if (!callback) {
            throw new Error('No callback is provided.');
        }
        postOrderRecursive(root);
        function postOrderRecursive(node) {
            if (node.left) {
                postOrderRecursive(node.left);
            }
            if (node.right) {
                postOrderRecursive(node.right);
            }
            callback(node.value);
        }
    }

    function height(node) {
        let maxHeight = 0;
        function heightRecursive(node, distance) {
            if (node.left) {
                heightRecursive(node.left, distance+1);
            }
            if (node.right) {
                heightRecursive(node.right, distance+1);
            }
            if (!node.left && !node.right) {
                maxHeight = Math.max(maxHeight, distance);
            }
        }
        if (node) {
            heightRecursive(node, 0)
            return maxHeight;
        }
        return 'Error: Node is not in tree.'
    }

    function depth(node) {
        if (root && node) {
            return height(root) - height(node);
        }
        return 'Error: Either the root or node are invalid.'
    }

    function isBalanced() {
        let leftSubtreeHeight;
        let rightSubtreeHeight;
        if (root.left) {
            leftSubtreeHeight = height(root.left);
        }
        else {
            leftSubtreeHeight = -1;
        }
        if (root.right) {
            rightSubtreeHeight = height(root.right);
        }
        else {
            rightSubtreeHeight = -1;
        }
        if (Math.abs(rightSubtreeHeight - leftSubtreeHeight)<=1) {
            return true;
        }
        return false;
    }

    function rebalance() {
        if (isBalanced()) {
            return 'Tree is already balanced.';
        }
        rebalanceArr = [];
        inOrder((val) => rebalanceArr.push(val));
        buildTree(rebalanceArr);
    }

    function prettyPrint(node, prefix = "", isLeft = true) {
        if (node === null) {
            return;
        }
        if (node.right !== null) {
            prettyPrint(node.right, `${prefix}${isLeft ? "│   " : "    "}`, false);
        }
        console.log(`${prefix}${isLeft ? "└── " : "┌── "}${node.value}`);
        if (node.left !== null) {
            prettyPrint(node.left, `${prefix}${isLeft ? "    " : "│   "}`, true);
        }
    };

    return { getRoot, buildTree, insert, deleteItem, find, levelOrder, inOrder, preOrder, postOrder, height, depth, isBalanced, rebalance, prettyPrint };
}

export { BinarySearchTree };