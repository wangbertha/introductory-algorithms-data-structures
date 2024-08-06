function KnightsTravails() {
    let st = SearchTree();
    let rb = ReadBoard();

    function SearchNode() {
        let rowIndex = null;
        let colIndex = null;
        let parent = null;
        let moveCount = 0;

        return { rowIndex, colIndex, parent, moveCount };
    }

    function ReadNode() {
        let read = false;
        return { read };
    }

    function SearchTree() {
        let root = null;

        function getRoot() {
            return root;
        }

        return { root, getRoot };
    }

    function ReadBoard(size) {
        let arr = [];
        for (let i=0; i<size; i++) {
            let rowArr = [];
            for (let j=0; j<size; j++) {
                rowArr.push(ReadNode());
            }
            arr.push(rowArr);
        }
        
        function getArr() {
            return arr;
        }

        return { arr, getArr };
    }

    function knightMoves([startRow, startCol], [endRow, endCol]) {
        if (startRow===endRow && startCol===endCol) {
            const returnString = `You made it in 0 moves!`;
            return returnString;
        }
        st = SearchTree();
        rb = ReadBoard(8);

        let newRoot = SearchNode();
        newRoot.rowIndex = endRow;
        newRoot.colIndex = endCol;
        newRoot.moveCount = 0;
        st.root = newRoot;
        rb.arr[endRow][endCol].read = true;
        let levelOrderArr = [];
        levelOrderArr.push(st.root);

        while (levelOrderArr[0]) {
            let travNode = levelOrderArr.shift();
            let possibleMoves = [
                [travNode.rowIndex-1, travNode.colIndex-2],
                [travNode.rowIndex-2, travNode.colIndex-1],
                [travNode.rowIndex+1, travNode.colIndex-2],
                [travNode.rowIndex-2, travNode.colIndex+1],
                [travNode.rowIndex+2, travNode.colIndex-1],
                [travNode.rowIndex-1, travNode.colIndex+2],
                [travNode.rowIndex+1, travNode.colIndex+2],
                [travNode.rowIndex+2, travNode.colIndex+1],
            ];
            for (const [row, col] of possibleMoves) {
                if (0 <= row && row < rb.arr.length && 0 <= col && col < rb.arr.length) {
                    if (!rb.arr[row][col].read) {
                        // console.log(`Processed index: ${row} and ${col}`)
                        rb.arr[row, col].read = true;
                        if (row===startRow && col===startCol) {
                            const finalCount = travNode.moveCount + 1;
                            let returnArr = [];
                            returnArr.push([row, col]);
                            while (travNode) {
                                returnArr.push([travNode.rowIndex, travNode.colIndex]);
                                travNode = travNode.parent;
                            }
                            let returnArrString = [];
                            for (const [rowPrint, colPrint] of returnArr) {
                                returnArrString.push(`[${rowPrint}, ${colPrint}]`);
                            }
                            const returnString = `You made it in ${finalCount} moves! Here's your path:\n${returnArrString}`;
                            return returnString;
                        }
                        let newSearchNode = SearchNode();
                        newSearchNode.rowIndex = row;
                        newSearchNode.colIndex = col;
                        newSearchNode.parent = travNode;
                        newSearchNode.moveCount = travNode.moveCount + 1;
                        levelOrderArr.push(newSearchNode);
                    }
                }
            }
        }
    }

    return { knightMoves };
}

export { KnightsTravails };