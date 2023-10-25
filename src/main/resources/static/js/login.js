var element = document.getElementById("error-message");

if (window.location.href.indexOf("/login/?error") > -1) {
    element.style.display = "block";
}

