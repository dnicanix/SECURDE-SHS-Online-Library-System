// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == edit_modal) {
        edit_modal.style.display = "none";
    }
    if (event.target == delete_modal) {
        delete_modal.style.display = "none";
    }
    if (event.target == add_modal) {
        add_modal.style.display = "none";
    }
}


// EDIT
// Get the modal
var edit_modal = document.getElementById("EditModal");

// Get the button that opens the modal
var edit_btn = document.getElementById("button_edit");
//var confirm = document.getElementById("modal_confirmE");

// Get the <span> element that closes the modal
var edit_span = document.getElementsByClassName("edit_close")[0];

// When the user clicks on the button, open the modal 
edit_btn.onclick = function() {
    edit_modal.style.display = "block";

    document.getElementById("modal_title").value = document.getElementById('title').textContent;
    document.getElementById("modal_title").style.width = ((document.getElementById('title').textContent.length + 1) * 9.5) + 'px';

    document.getElementById("modal_author").value = document.getElementById('author').textContent;
    document.getElementById("modal_publisher").value = document.getElementById('publisher').textContent;
    document.getElementById("modal_year").value = document.getElementById('year').textContent;
    document.getElementById("modal_location").value = document.getElementById('location').textContent;


    document.getElementById("modal_availability").selectedIndex = 2;
    //console.log(document.getElementById("modal_availability").selectedIndex);
}

// When the user clicks on <span> (x), close the modal
edit_span.onclick = function() {
    edit_modal.style.display = "none";
}


// DELETE
// Get the modal
var delete_modal = document.getElementById("DeleteModal");

// Get the button that opens the modal
var delete_btn = document.getElementById("button_delete");


// Get the <span> element that closes the modal
var delete_span = document.getElementsByClassName("delete_close")[0];

// When the user clicks on the button, open the modal 
delete_btn.onclick = function() {
    delete_modal.style.display = "block";    
}

// When the user clicks on <span> (x), close the modal
delete_span.onclick = function() {
    delete_modal.style.display = "none";
}


//CONFIRM DELETE
// Get the modal
var confirm_delete_modal = document.getElementById("ConfirmDModal");

// Get the button that opens the modal
var delete_confirm = document.getElementById("modal_confirmD");

// Get the <span> element that closes the modal
var confirm_delete_span = document.getElementsByClassName("confirm_delete_close")[0];

// When the user clicks on <span> (x), close the modal
confirm_delete_span.onclick = function() {
    confirm_delete_modal.style.display = "none";
}

// When the user confirms, close the modal and apply the changes
delete_confirm.onclick = function() {
    // TODO APPLY CHANGES
    delete_modal.style.display = "none";
    confirm_delete_modal.style.display = "block";
}


//ADD
// Get the modal
var add_modal = document.getElementById("AddModal");

// Get the button that opens the modal
var add_btn = document.getElementById("button_add");
//var confirm = document.getElementById("modal_confirmD");

// Get the <span> element that closes the modal
var add_span = document.getElementsByClassName("add_close")[0];

// When the user clicks on the button, open the modal 
add_btn.onclick = function() {
    add_modal.style.display = "block";    
}

// When the user clicks on <span> (x), close the modal
add_span.onclick = function() {
    add_modal.style.display = "none";
}

