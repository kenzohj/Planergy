function togglePasswordVisibility() {
    const passwordInput = document.getElementById("mdp");
    const eyeIcon = document.getElementById("eyeIcon");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        eyeIcon.classList.remove("far", "fa-eye");
        eyeIcon.classList.add("fas", "fa-eye-slash");
    } else {
        passwordInput.type = "password";
        eyeIcon.classList.remove("fas", "fa-eye-slash");
        eyeIcon.classList.add("far", "fa-eye");
    }
}
