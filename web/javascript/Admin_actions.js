function finishCreateUser() {
    let role;
    if (document.getElementById("Pharmaceut").checked === true) {
        role = "Farmaceut";
    }

    if (document.getElementById("AdminID").checked === true) {
        role = "Administrator";
    }

    if (document.getElementById("ProduktionslederRadio").checked === true) {
        role = "Produktionsleder";
    }

    if (document.getElementById("Laborant").checked === true) {
        role = "Laborant";
    }
    if (!document.getElementById("id").value == "" &&
        !document.getElementById("username").value == "" &&
        !document.getElementById("ini").value == "" &&
        !document.getElementById("cpr").value == "" &&
        !document.getElementById("hashedPass").value == "" &&
        (document.getElementById("Pharmaceut").checked == true ||
            document.getElementById("AdminID").checked == true ||
            document.getElementById("ProduktionslederRadio").checked == true ||
            document.getElementById("Laborant").checked == true)) {
        var ID = document.getElementById("id").value;
        var username = document.getElementById("username").value;
        var ini = document.getElementById("ini").value;
        var CPR = document.getElementById("cpr").value;
        var nonHashedPass = document.getElementById("hashedPass").value;
        try {
            createUser("rest/actions/user-post", ID, username, ini, CPR, nonHashedPass, role, "Aktiv");
        }
        catch(e) {
            alert("Wrong error handling");
        }
        //PUTUser(user);
        resetValuesCreateUser();
    } else {
        alert("Please fill out all the fields!");
    }

}

function resetValuesCreateUser() {
    document.getElementById("id").value = "";
    document.getElementById("username").value = "";
    document.getElementById("ini").value = "";
    document.getElementById("cpr").value = "";
    document.getElementById("hashedPass").value = "";
    document.getElementById("Pharmaceut").checked = false;
    document.getElementById("AdminID").checked = false;
    document.getElementById("ProduktionslederRadio").checked = false;
    document.getElementById("Laborant").checked = false;
}
