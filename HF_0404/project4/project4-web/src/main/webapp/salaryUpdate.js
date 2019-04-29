
function validateForm() {
  var x, y, i;
  x = document.getElementsByTagName("input");
  console.log(x);
  for (i = 0; i < x.length; i++) {
    if (x[i].value == "" || parseInt(x[i].value) >=30000 || parseInt(x[i].value) <=300) { 
      x[i].className += " invalid";
      valid = false;
    }
  }
}

