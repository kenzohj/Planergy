function setLoading(element) {
    const spinnerExistant = element.querySelector(".spinner-border");
    if (!(spinnerExistant === undefined || spinnerExistant == null)) {
        return;
    }

    const conteneurSpinner = element;

    const spinner = document.createElement("span");
    spinner.className = "spinner-border spinner-border-sm";
    spinner.setAttribute("role", "status");

    conteneurSpinner.appendChild(spinner);
    element.querySelector("p");
    if (!(element.querySelector(".spinner-border") == null || element.querySelector(".spinner-border") === undefined)) {
    } else {
        element.appendChild(conteneurSpinner);
    }
}

function unsetLoading(element) {
    const spinnerExistant = element.querySelector(".spinner-border");
    if (!(spinnerExistant === undefined)) element.removeChild(spinnerExistant);
}
