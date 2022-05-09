/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function validateForm() {
    let username = document.forms["myForm"]["txtUsername"].value;
    let password = document.forms["myForm"]["txtPassword"].value;
    if (username === "") {
        document.getElementById("validate").textContent = "Username must be filled out";
        return false;
    } else if (password === "") {
        document.getElementById("validate").textContent = "Password must be filled out";
        return false;
    }
}

