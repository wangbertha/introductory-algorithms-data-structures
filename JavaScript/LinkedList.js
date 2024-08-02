function Node() {
    let value = null;
    let nextNode = null;
    return { value, nextNode };
}

function LinkedList() {
    let headNode = Node();
    let tailNode = Node();
    let length = 0;

    const size = () => {
        return length;
    }

    const append = (value) => {
        if (length === 0) {
            headNode.value = value;
        }
        else if (length === 1) {
            tailNode.value = value;
            headNode.nextNode = tailNode;
        }
        else {
            let newNode = Node();
            newNode.value = value;
            tailNode.nextNode = newNode;
            tailNode = tailNode.nextNode;
        }
        length++;
    }

    const head = () => {
        return headNode.value;
    }

    const tail = () => {
        return tailNode.value;
    }

    const prepend = (value) => {
        if (headNode.value === null) {
            headNode.value = value;
        }
        else if (tailNode.value === null) {
            tailNode = headNode;
            headNode = Node();
            headNode.value = value;
            headNode.nextNode = tailNode;
        }
        else {
            let newNode = Node();
            newNode.value = value;
            newNode.nextNode = headNode;
            headNode = newNode;
        }
        length++;
    }

    const at = (index) => {
        if (length === 0) {
            return "Linked List is empty.";
        }
        if (length < index || index < 0) {
            return "Index is not in range.";
        }
        let currNode = headNode;
        for (let i=0; i<index; i++) {
            currNode = currNode.nextNode;
        }
        return currNode.value;
    }

    const pop = () => {
        if (length === 0) {
            return "There are no nodes to pop!";
        }
        let popValue;
        if (length === 1) {
            popValue = headNode.value;
            headNode = Node();
        }
        else {
            popValue = tailNode.value;
            let currNode = headNode;
            let newTailNode;
            while (currNode.nextNode) {
                newTailNode = currNode;
                currNode = currNode.nextNode;
            }
            tailNode = newTailNode;
            tailNode.nextNode = null;
        }
        length--;
        return popValue;
    }

    const insertAt = (value, index) => {
        if (index === 0) {
            prepend(value);
            length++;
            return;
        }
        if (index === length) {
            append(value);
            length++;
            return;
        }
        if (length < index || index < 0) {
            return "Index is not in range.";
        }
        let currNode = headNode;
        let prevNode;
        for (let i=0; i<index; i++) {
            prevNode = currNode;
            currNode = currNode.nextNode;
        }
        const newNode = Node();
        newNode.value = value;
        prevNode.nextNode = newNode;
        newNode.nextNode = currNode;
        length++;
    }

    const removeAt = (index) => {
        if (length === 0 || length <= index || index < 0) {
            return "Index is not in range.";
        }
        if (index === 0) {
            if (length === 1) {
                headNode = Node();
            }
            else if (length === 2) {
                headNode = tailNode;
                tailNode = Node();
            }
            else {
                headNode = headNode.nextNode;
            }
            length--;
            return;
        }
        if (index === length-1) {
            pop();
            return;
        }
        let currNode = headNode;
        let prevNode;
        for (let i=0; i<index; i++) {
            prevNode = currNode;
            currNode = currNode.nextNode;
        }
        currNode = currNode.nextNode;
        prevNode.nextNode = currNode;
    }

    const contains = (value) => {
        const getFind = find(value);
        if (getFind) {
            return true;
        }
        return false;
    }

    const find = (value) => {
        let currNode = headNode;
        let index = 0;
        while (currNode) {
            if (currNode.value === value) {
                return index;
            }
            currNode = currNode.nextNode;
            index++;
        }
        return null;
    }

    const toString = () => {
        if (length === 0) {
            return "Linked List is empty.";
        }
        let currNode = headNode;
        let arrValues = [];
        while (currNode) {
            arrValues.push(currNode.value);
            currNode = currNode.nextNode;
        }
        return arrValues.toString();
    }

    return { size, head, tail, append, prepend, at, pop, insertAt, removeAt, contains, find, toString };
}

export { LinkedList };