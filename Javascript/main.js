// const arr=[1,2,3];
// // arr.forEach(console.log)

// arr.forEach(alert);

// let horizontalUnderline= document.getElementById("horizontal-underline");
// // 와...first-child a 라는 것은 문자열인데도 엄격한 검사도 없이 쓰임. 오직 결과를 통해서 틀렸는지를 알수 있음. 
// /*[[Prototype]]: NodeList*/
// let horizontalMenus=document.querySelectorAll("nav:first-child a")
// console.log(horizontalMenus)
// console.log(horizontalUnderline);
// console.log("No difference");
// console.log('No difference2');

// const target=['가','나','다','라', '마'];

// target=['사','다'];//불가능
// target[0]='나';//가능


// console.log(target)


// // console.log(0.1+0.2)
// // 각각의 메뉴에 클릭이벤트주기. click이벤트가 발생할 때마다 horizontalIndicator함수를 호출하겠다는 코드. 함수를 호출할때마다 e(이벤트)를 넘겨줌. 왜? 
// //이벤트 안에 내가 어떤 아이템을 선택했는지 정보가 있음. 
// horizontalMenus.forEach(menu=>menu.addEventListener("click",(e)=>horizontalIndicator(e)))


// function horizontalIndicator(e){
//     horizontalUnderline.style.left=e.currentTarget.offsetLeft+"px";
//     horizontalUnderline.style.width=e.currentTarget.offsetWidth+"px";
//     horizontalUnderline.style.top=e.currentTarget.offsetTop+ e.currentTarget.offsetHeight+"px";
// }

// let verticalUnderline=document.getElementById("vertical-underline");
// let verticalMenus=document.querySelectorAll("nav:nth-child(2) a");
// verticalMenus.forEach(menu=>menu.addEventListener("click", (e)=>verticalIndicator(e)));

// function verticalIndicator(e){
//     verticalUnderline.style.left=e.currentTarget.offsetLeft+"px";
//     verticalUnderline.style.width=e.currentTarget.offsetWidth+"px";
//     verticalUnderline.style.top=e.currentTarget.offsetTop+e.currentTarget.offsetHeight+"px";
// }


// verticalMenus.forEach(menu=>menu.addEventListener("click", (e)=>verticalIndicator(e)));
// function verticalIndicator(e){
//     verticalUnderline.style.left=e.currentTarget.offsetLeft+"px";
//     verticalUnderline.style.width=e.currentTarget.offsetWidth+"px";
//     verticalUnderline.style.top=e.currentTarget.offsetTop+e.currentTarget.offsetHeight+"px";
// }


/* 아래는 자스에서 메서드와 배열이 모두 객체임을 말해주는 단편적인 예. 둘다 객체이기 때문에 멤버변수를 가지는 것
function hello(){};
const arr=[];
hello.a="really?";
arr.b="wow";
console.log(hello.a)
console.log(arr.b)
*/

// const fs=require('fs');//fs=file system
// let input=fs.readFileSync('./input.txt').toString().split('\n');

// let T=+input[0];

// for(let i=1;i<=T;++i){
//     let arr=input[i].split(' ').map(item=>+item);
//     let length=arr[0];
//     nArray=[];
//     for(let j=1;j<=length;++j)
//         nArray.push(arr[j]);
    
//     const data={
//         len: length,
//         arr: nArray
//     }
//     console.log(data);

// }


const fs=require('fs');
// let input=fs.readFileSync('/dev/stdin').toString().split("\n");
let input=fs.readFileSync('./input.txt').toString().split("\n");

let T=input[0]*1;
for (let i=1;i<=T;++i){
    let arr=input[i].split(" ").map(item=>item*1)
    let length=arr.shift();
    console.log("function call")
    solution(length, arr);
}

function solution(length, arr){
    let count=0;
    let sum=arr.reduce((acc, ele)=>acc+=ele, 0);
    let avg=sum/length;
    for(let j=0;j<length;++j){
        if(arr[j]>avg){
            ++count;
        }
    }
    console.log((count/length*100).toFixed(3));
}

