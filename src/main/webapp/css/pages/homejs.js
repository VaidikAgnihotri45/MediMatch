let index=0;

const slides=document.querySelector(".slides");
const dots=document.querySelectorAll(".dot");

function showSlide(){

index++;

if(index>2){
index=0;
}

slides.style.transform="translateX("+(-index*100)+"%)";

dots.forEach(dot=>dot.classList.remove("active"));
dots[index].classList.add("active");

}

setInterval(showSlide,4000);/**
 * 
 */