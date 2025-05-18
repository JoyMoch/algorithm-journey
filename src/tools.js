const Len = 20;
function createArr() {
    let res = new Array(Len);
    for (let i = 0; i < Len; i++) {
        res[i] = Math.floor(Math.random() * 1000);
    }
    console.log('arr is:', res);
    return res;
}

function time(fn) {
    return function (...args) {
        const name = fn.name;
        const start = Date.now();
        console.log(`${name} exec:`);
        const res = fn(...args);
        console.log(`${name} cost:`, Date.now() - start);
        return res;
    }
}

function swap(arr, i, j) {
    [arr[i], arr[j]] = [arr[j], arr[i]]
}

function test(fn) {
    console.log(time(fn)((createArr())));
}


module.exports = {
    createArr,
    time,
    swap,
    test
}
