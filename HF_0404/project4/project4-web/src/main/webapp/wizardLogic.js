/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var currentTab = 0; 
showTab(currentTab); 
var selectedJob;

function showTab(n) { 
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  if (n == 0) { //gombok
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (!(n == (x.length - 1))) { // ha nem az utolsĂł
    document.getElementById("nextBtn").innerHTML = "Next";
    //document.getElementById("submitbutton").style.visibility = "hidden"; 
    document.getElementById("submitbutton").disabled = true;
    document.getElementById("submitbutton").style.background='#d6e5cc';
    document.getElementById("nextBtn").style.visibility = "visible";
  } else {
    document.getElementById("nextBtn").style.visibility = "hidden";
//    document.getElementById("submitbutton").disabled = true;
    document.getElementById("submitbutton").style.background='#d6e5cc';
    //document.getElementById("submitbutton").disabled = false;
  }
  fixStepIndicator(n) //pĂ¶ttyĂ¶k
}

function nextPrev(n) {
  var x = document.getElementsByClassName("tab");
  if (n == 1 && !validateForm()) return false;
  x[currentTab].style.display = "none";
  currentTab = currentTab + n;
  // form vĂ©ge:
  if (currentTab >= x.length) {
    document.getElementById("regForm").submit();
    return false;
  }
  showTab(currentTab);
}

function validateForm() {
  var x, y, i, valid = true;
  x = document.getElementsByClassName("tab");
  y = x[currentTab].getElementsByTagName("input");
  // megnĂ©zĂĽnk minden inputot:
  for (i = 0; i < y.length; i++) {
    if (y[i].value == "") { //ĂĽres
      y[i].className += " invalid";
      valid = false;
    }
  }
  if (valid) {
    document.getElementsByClassName("step")[currentTab].className += " finish";
  }
  return valid; 
}

function validateForm2() {
  var x, y, i, valid = true;
  x = document.getElementsByClassName("tab");
  y = x[currentTab].getElementsByTagName("input");
  // megnĂ©zĂĽnk minden inputot:
  for (i = 0; i < y.length; i++) {
    if (y[i].value != "") { //ĂĽres
//      y[i].className += " invalid";
      document.getElementById("submitbutton").disabled = false;
      document.getElementById("submitbutton").style.background='#4CAF50';
      valid = true;
      
    }
  }
  if (valid) {
    document.getElementsByClassName("step")[currentTab].className += " finish";
  }
  return valid; 
}

function fixStepIndicator(n) {
  var i, x = document.getElementsByClassName("step");
  for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
  }
  x[n].className += " active";
}

function jobSelected(selectObject) {
  selectedJob = selectObject.value;
  document.getElementById("selectedJob").innerHTML = selectedJob.toString();
}

