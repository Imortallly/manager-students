var elements = document.querySelectorAll("#loginButton1, #loginButton2");
var elementsOfRegister = document.querySelectorAll("#backToHome");
var elementsOfConsult = document.querySelectorAll("#backToConsult");

function redirecionarParaLogin() {
    window.location.href = "/logout/";
}

elements.forEach(function(element) {
    element.addEventListener("click", redirecionarParaLogin);
});

function redirecionarParaHome() {
    window.location.href = "/home/";
}

elementsOfRegister.forEach(function(element) {
    element.addEventListener("click", redirecionarParaHome);
});

function redirecionarParaConsulta() {
    window.location.href = "/home/consult-students/";
}

elementsOfConsult.forEach(function(element) {
    element.addEventListener("click", redirecionarParaConsulta);
});



