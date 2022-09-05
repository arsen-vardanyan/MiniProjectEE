let text = [
    "Hi! Welcome to my mini social network.",
    "If you have an account, you can sign in by filling out the fields on the left:",
    "And if not, you can create an account on the right. Good luck"
];

let speed = 50;
let index = 0;
let arrLength = text[0].length;
let scrollAt = 20;

let textPos = 0;
let contents = '';
let row;

function typewriter() {
    contents = ' ';
    row = Math.max(0, index - scrollAt);
    let destination = document.getElementById("typedtext");

    while (row < index) contents += text[row++] + '<br />';

    destination.innerHTML = contents + text[index].substring(0, textPos) + "_";

    if (textPos++ === arrLength) {
        textPos = 0;
        index++;
        if (index !== text.length) {
            arrLength = text[index].length;
            setTimeout("typewriter()", 500);
        }
    } else setTimeout("typewriter()", speed);
}

typewriter();