function filterTheZone2(inputElement) {
    filterTheZone(inputElement.parentNode, inputElement.value);
}

function filterTheZone(zone, filterInput) {
    let filteredElements = zone.getElementsByClassName("filteredElement");
    filter = formatStringForFiltering(filterInput);
    let filterString;
    for (let i = 0; i < filteredElements.length; i++) {
        const filteredElement = filteredElements[i];

        if (formatStringForFiltering(filteredElement.innerHTML).includes(filter)) {
            filteredElement.style.display = "";
        } else {
            filteredElement.style.display = "none";
        }

        filterString = filteredElement.getAttribute("filterString");
        if (filterString != null && filterString !== "") {
            if (filterString.includes(filter)) {
                filteredElement.style.display = "";
            }
        }
    }
}

function formatStringForFiltering(arg) {
    return arg
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .replace(/\s/g, "")
        .toUpperCase();
}
