function validateemail() {
    var x = document.signup_form.email.value;
    var atposition = x.indexOf("@");
    var dotposition = x.lastIndexOf(".");
    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= x.length) {
        alert("Please enter a valid e-mail address");
        return false;
    }
};

function matchpass() {
    var firstpassword = document.signup_form.password1.value;
    var secondpassword = document.signup_form.password2.value;

    if (firstpassword == secondpassword) {
        return true;
    } else {
        alert("password must be same!");
        return false;
    }
};

function validateNumber() {

    var num = document.signup_form.mobile.value;
    if (isNaN(num)) {
        document.getElementById("exampleInputMobile1").innerHTML = "Enter Numeric value only";
        return false;
    } else {
        return true;
    }
};

function validation() {

    validateemail();
    validateNumber();
    matchpass();


}
