const loginForm = document.forms[0];

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


loginForm.addEventListener("submit", async (e) =>
{

        e.preventDefault();

        const loadingMessage = document.querySelector("#loading_message");
        loadingMessage.innerHTML = "Loading...please wait...";

        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;
       //const {email, password} = document.querySelectorAll(".input").values();

        const response = await fetch('http://localhost:8080/login.do',
            {
                    method: 'post',
                    headers:
                        {
                                'Content-Type': 'application/json'
                        },
                    body: JSON.stringify({email, password})
                });

        setTimeout(() => loadingMessage.innerHTML = "", 2000);

        if (response.status === 320)
        {
                alert("Wrong email or password, try again.");
        }
        else if (response.status === 302)
        {
            location.replace("http://localhost:8080/employeeHome.html");
        }
        else if (response.status === 303)
        {
            location.replace("http://localhost:8080/managerHome.html")
        }
});



