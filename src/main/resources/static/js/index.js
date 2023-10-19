var elements = document.querySelectorAll("#loginButton1, #loginButton2");
var elementsOfRegister = document.querySelectorAll("#backToHome");

function redirecionarParaLogin() {
    window.location.href = "/login/";
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

