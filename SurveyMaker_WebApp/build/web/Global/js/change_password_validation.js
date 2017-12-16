

function matchpass() {
    var firstpassword = document.form.new_password.value;
    var secondpassword = document.form.confirm_password.value;

    if (firstpassword == secondpassword) {
       document.getElementById("passwordmsg").style.display="none"
        return true;
    } else {
        document.getElementById("passwordmsg").style.display="block";
        return false;
    }
};
