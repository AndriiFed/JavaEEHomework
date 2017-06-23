
function showError(container, errorMessage) {
    container.className = 'error';
    var msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
    }
}

function checkLetters(inputTxt) {
    var letters = /^[A-Za-z]+$/;
    return inputTxt.match(letters);
}

function validateForm() {
    var form = document.forms["userForm"];
    var elems = form.elements,
        name = elems.name.value,
        age = elems.age.value,
        isValid = true;

    resetError(elems.name.parentNode);
    if (!name || !checkLetters(name)) {
        showError(elems.name.parentNode, ' Please input name with A-z characters and without spaces! ');
        isValid = false;
    }

    resetError(elems.age.parentNode);
    if (isNaN(age) || age < 1 || age > 110) {
        showError(elems.age.parentNode, ' Please input age with value form 1 to 110!');
        isValid = false;
    }

    return isValid;
}
