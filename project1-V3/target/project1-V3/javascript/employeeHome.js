const create_request_form = document.querySelector("#reimbursement-request-submission-form");

//duplicate code, still have to learn how to modularize this
//this code keeps the placeholders inside span tags above the input field
//when there is content: courtesy of August Duet from his training video
const hasContent = (element) =>
{
    return element.value;
};

const inputElements = document.querySelectorAll(".form-control");
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
//end of duplicate code


create_request_form.addEventListener("submit", (e) =>
{
    alert("You submission was successful.");
});