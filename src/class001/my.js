const {swap, test  } = require('../tools.js')

function selectionSort(arr) {
    for (let i = 0; i < arr.length - 1; i++) {
        let minIdx = i;
        for (let j = i + 1; j < arr.length; j++) {
           minIdx = arr[minIdx] < arr[j] ? minIdx : j;
        }
        swap(arr, i, minIdx)
    }

    return arr;
}

// test(selectionSort)

function bubbleSort(arr) {
    for (let k = arr.length - 1; k > 0; k--) {
        for (let i = 0; i < k; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i+1);
            }
        }
    }

    return arr
}

// leetcode 136
// 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
// 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。

// var singleNumber = function(nums) {
//     let res = 0;
//     for (const i of nums) {
//         res ^= i;
//     }
//     return res
// };


// leetcode 260
// 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
// 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。

// function singleNumber(nums) {
//     let xorsum = 0;
//     for (const nu of nums) {
//         xorsum ^= nu;
//     }

//     let lsb = xorsum & -xorsum;
//     let resA = 0, resB = 0;
//     for (const num of nums) {
//         if (num & lsb) {
//             resA ^= num;
//         } else {
//             resB ^= num;
//         }
//     }

//     return [resA, resB]
// };



// leetcode 137
// 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
// 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。

// function singleNumber(nums: number[]): number {
//     const arr = new Array(32).fill(0);
//     for(let i = 0; i< nums.length; i++) {
//         for (let j = 0; j<32; j++) {
//             arr[j] += nums[i] & 1;
//             nums[i] >>>= 1;
//         }
//     }

//     let res = 0;
//     for(let i = 0; i<32; i++) {
//         res <<= 1;
//         res |= arr[31 - i] % 3;
//     }

//     return res;
// };