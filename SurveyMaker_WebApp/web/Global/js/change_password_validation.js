

function matchpass() {
    var firstpassword = document.getElementById("changePasswordForm").new_password.value;
    var secondpassword = document.getElementById("changePasswordForm").confirm_password.value;
    
    if (firstpassword == secondpassword) {
       document.getElementById("passwordmsg").style.display="none"
        return true;
    } else {
        document.getElementById("passwordmsg").style.display="block";
        return false;
    }
};
