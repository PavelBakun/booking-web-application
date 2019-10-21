function formValidation() {
    var passId = document.registration.passId;
    var uname = document.registration.userName;
    var uemail = document.registration.email;

    if (passid_validation(passId)) {
        if (allLetter(uname)) {
            if (validateEmails()) {
            }
        }
    }
    return false;
}

function passid_validation(passid) {
    var pass = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{6,}/;
    if (passid.value.match(pass)) {
        return true;
    } else {
        alert('User password must have more then 6 chars (must contain figure, chars in uppercase and lowercase)');
        pass.focus();
        return false;
    }
    return true;
}

function allLetter(uname) {
    var letters = /^([A-Za-z])[\w+]{5,}/;
    if (uname.value.match(letters)) {
        return true;
    } else {
        alert('Username must have Latin alphabet characters only and symbol "_", must begin from character');
        uname.focus();
        return false;
    }
}

function ValidateEmail(uemail) {
    var mailformat = /^.+@.+\..+$/;
    if (uemail.value.match(mailformat)) {
        return true;
    } else {
        alert("You have entered an invalid email address!");
        uemail.focus();
        return false;
    }
}

function validateEmails() {
    var count = document.getElementsByName("email").length;
    for (var i = 0; i < count; i++) {
        var email = document.registration.email[i];
        if (!ValidateEmail(email)) {
            return false;
        }
    }
    return true;
}








