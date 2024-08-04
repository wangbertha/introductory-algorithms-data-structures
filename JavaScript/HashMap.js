import { LinkedList } from './LinkedList.js';

function HashMap() {
    let capacity = 8;
    let size = 0;
    let loadFactor = 0.75;
    let arr = [];
    initializeArr();

    function initializeArr() {
        for (let i=0; i<capacity; i++) {
            arr.push(null);
        }
    }

    function length() {
        return size;
    }

    function clear() {
        arr = [];
        initializeArr();
    }

    function hash(key) {
        let hashCode = 0;
        const primeNumber = 31;
        for (let i=0; i<key.length; i++) {
            hashCode = (primeNumber * hashCode + key.charCodeAt(i)) % capacity;
        }
        return hashCode;
    }

    function addOne() {
        size++;
        if (capacity*loadFactor < size) {
            growRefactorArr();
        }
    }

    function growRefactorArr() {
        const tempArr = [...arr];
        arr = [];
        size = 0;
        capacity *= 2;
        console.log(capacity);
        initializeArr();
        for (const row of tempArr) {
            if (row) {
                let currNode = row.head();
                while (currNode) {
                    set(currNode.value.key, currNode.value.value);
                    currNode = currNode.nextNode;
                }
            }
        }
    }

    function set(key, value) {
        const obj = {key: key, value: value};
        const hashCode = hash(key);
        console.log(`Key: ${key} Value: ${value} HashCode: ${hashCode}`)
        if (arr[hashCode]) {
            let linkedList = arr[hashCode];
            const index = linkedList.find();
            if (index) {
                linkedList.removeAt(index);
                linkedList.insertAt(obj, index);
            }
            else {
                linkedList.append(obj);
                addOne();
            }
        }
        else {
            const newLinkedList = LinkedList();
            newLinkedList.append(obj);
            arr[hashCode] = newLinkedList;
            addOne();
        }
    }

    function get(key) {
        const hashCode = hash(key);
        if (arr[hashCode]) {
            let currNode = arr[hashCode].head();
            while (currNode) {
                if (currNode.value.key === key) {
                    return currNode.value.value;
                }
                currNode = currNode.nextNode;
            }
            return null;
        }
        return null;
    }

    function has(key) {
        if (get(key)) {
            return true;
        }
        return false;
    }

    function remove(key) {
        const hashCode = hash(key);
        if (arr[hashCode]) {
            let linkedList = arr[hashCode];
            let currNode = linkedList.head();
            let index = 0;
            while (currNode) {
                if (currNode.value.key === key) {
                    linkedList.removeAt(index);
                    if (linkedList.size() === 0) {
                        arr[hashCode] = null;
                    }
                    size--;
                    return currNode.value.value;
                }
                currNode = currNode.nextNode;
                index++;
            }
            return false;
        }
        return false;
    }

    function keys() {
        let keyArr = [];
        for (const linkedList of arr) {
            if (linkedList) {
                let currNode = linkedList.head();
                while (currNode) {
                    keyArr.push(currNode.value.key);
                    currNode = currNode.nextNode;
                }
            }
        }
        return keyArr;
    }

    function values() {
        let valArr = [];
        for (const linkedList of arr) {
            if (linkedList) {
                let currNode = linkedList.head();
                while (currNode) {
                    valArr.push(currNode.value.value);
                    currNode = currNode.nextNode;
                }
            }
        }
        return valArr;
    }

    function entries() {
        let entriesArr = [];
        for (const linkedList of arr) {
            if (linkedList) {
                let currNode = linkedList.head();
                while (currNode) {
                    entriesArr.push([currNode.value.key, currNode.value.value]);
                    currNode = currNode.nextNode;
                }
            }
        }
        return entriesArr;
    }

    return { length, clear, set, keys, values, entries, get, has, remove };
}

export { HashMap };