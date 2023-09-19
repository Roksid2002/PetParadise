const email_string = /^[a-zA-Z\d._%-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,20}$/;
const password_string = /^[a-zA-Z\d\-\xE0\xE8\xE9\xF9\xF2\xEC\x27]{6,16}/;
const phone_string = /^\d{10}$/;
const number_string = /^\d+$/;
const double_string = /^(\d+(?:[.,]\d{2})?)$/;


function controlEmail(){
    let email = document.getElementById("email").value;
    if (email_string.test(email)){
        $("#email").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#email").css("border-color","#C92403");
        return false;
    }
}
function controlPasswd(){
    let password = document.getElementById("password").value;
    if (password_string.test(password)){
        $("#password").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#password").css("border-color","#C92403");
        return false;
    }
}
function controlTelefono(){
    let telefono = document.getElementById("telefono").value;
    if (phone_string.test(telefono)){
        $("#telefono").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#telefono").css("border-color","#C92403");
        return false;
    }
}




function validateRegistration(){
    if(controlEmail() && controlPasswd() && controlTelefono()){
        document.getElementById("loginForm").submit();
        return true;
    }

}

function validateLogin() {

    if (controlEmail() && controlPasswd()){
        document.getElementById("loginForm").submit();
        return true;
    }
    return false;

}

function validateEditProfile(){
    if(controlEmail() && controlPasswd() && controlTelefono() ){
        document.getElementById("edit-profile").submit();
        return true;
    }
}



function validateNumberCart() {

    let numberCard = document.getElementById("numberCard").value;
    if (number_string.test(numberCard) && numberCard.length === 16) {
        $("#numberCard").css("border-color", "#E5E5E5");
        return true;
    }
    else {
        $("#numberCard").css("border-color", "#C92403");
        return false;
    }
}
function validateCVV() {

    let cvv = document.getElementById("CVV").value;
    if (number_string.test(cvv) && cvv.length === 3) {
        $("#CVV").css("border-color", "#E5E5E5");
        return true;
    }
    else {
        $("#CVV").css("border-color", "#C92403");
        return false;
    }
}
function validateDeadline() {

    let deadline = new Date(document.getElementById("deadline").value);
    let today = new Date();

    if (deadline > today) {
        $("#deadline").css("border-color", "#E5E5E5");
        return true;
    }
    else {
        $("#deadline").css("border-color", "#C92403");
        return false;
    }
}

function validatePayment() {

    if (validateNumberCart() & validateCVV() & validateDeadline())
        document.getElementById("pay-form").submit();
}

function validateAddProduct(){
    if(validateProdName() && validateProdPrice() && validateProdQuantity() && validateProdDescription() &&
        validateImage() && validateCategoryProduct()){
        document.getElementById("add-product-form").submit();
        return true;
    }
    return false;
}


