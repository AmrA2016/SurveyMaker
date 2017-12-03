function validateemail() {
    var x = document.form.email.value;
    var atposition = x.indexOf("@");
    var dotposition = x.lastIndexOf(".");
    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= x.length) {
        document.getElementById("emailmsg").style.display="block";
        return false;
    }
    else {

      return true;
    }
};

function matchpass() {
    var firstpassword = document.form.password1.value;
    var secondpassword = document.form.password2.value;

    if (firstpassword == secondpassword) {
       document.getElementById("passwordmsg").style.display="none"
        return true;
    } else {
        document.getElementById("passwordmsg").style.display="block";
        return false;
    }
};

function validateNumber() {

    var num = document.form.mobile.value;
    if (isNaN(num)) {
        document.getElementById("mobilemsg").style.display="block";
        return false;
    } else {
        document.getElementById("mobilemsg").style.display="none";
        return true;
    }
};

function validation() {

  var mailIsValid =  validateemail();
  var mobileIsValid =  validateNumber();
  var passwordIsmatch =  matchpass();
   if(mobileIsValid == false || mailIsValid == false || passwordIsmatch == false){
     return false;
   }else{
     return true;
   }

}
