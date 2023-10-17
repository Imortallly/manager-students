var elements = document.querySelectorAll("#loginButton1, #loginButton2");

elements.forEach(function(element) {
    element.addEventListener("click", function() {
        window.location.href = "/login/";
    });
});