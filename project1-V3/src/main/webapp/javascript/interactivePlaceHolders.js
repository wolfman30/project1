const hasContent = (element) =>
{
    return element.value;
};

const inputElements = document.querySelectorAll(".form-control");

function makeInteractivePlaceholders(inputElements)
{
    inputElements.forEach(element =>
    {
        element.addEventListener('blur', (e) =>
        {
            if(hasContent(element))
            {
                element.nextElementSibling.classList.add('has-content');
            }
            else
            {
                element.nextElementSibling.classList.remove("has-content");
            }
        });
    });
}

export { hasContent, inputElements, makeInteractivePlaceholders };