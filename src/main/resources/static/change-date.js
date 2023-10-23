document.addEventListener("DOMContentLoaded", () => {
    const exampleModal = document.getElementById('exampleModal')

    if (exampleModal !== null) {
        exampleModal.addEventListener('show.bs.modal', event => {
            // Button that triggered the modal
            const button = event.relatedTarget
            // Extract info from data-bs-* attributes
            const recipient = button.getAttribute('data-bs-whatever');
            const name = button.getAttribute('data-bs-name');
            const lastName = button.getAttribute('data-bs-lastname');
            const address = button.getAttribute('data-bs-address');
            const status = button.getAttribute('data-bs-status');
            const phone = button.getAttribute('data-bs-phone');
            const email = button.getAttribute('data-bs-email');
            // If necessary, you could initiate an AJAX request here
            // and then do the updating in a callback.
            //
            // Update the modal's content.
            const form = exampleModal.querySelector('form');
            const modalTitle = exampleModal.querySelector('.modal-title')
            const inputName = exampleModal.querySelector('.modal-body #name');
            const inputLastName = exampleModal.querySelector('.modal-body #last-name');
            const inputAddress = exampleModal.querySelector('.modal-body #address');
            const inputStatus = exampleModal.querySelector('.modal-body #status');
            const inputPhone = exampleModal.querySelector('.modal-body #phone');
            const inputEmail = exampleModal.querySelector('.modal-body #email');

            modalTitle.textContent = `Change information about client ${recipient}`
            inputName.value = name;
            inputLastName.value = lastName;
            inputAddress.value = address;
            inputStatus.value = status;
            inputPhone.value = phone;
            inputEmail.value = email;
            form.action =`/Clients/${recipient}/edit`;
        })
    }



    const changeProduct = document.getElementById('edit_product')

    if (changeProduct !== null) {
        changeProduct.addEventListener('show.bs.modal', event => {
            // Button that triggered the modal
            const button = event.relatedTarget
            // Extract info from data-bs-* attributes
            const recipient = button.getAttribute('data-bs-whatever');
            const name = button.getAttribute('data-bs-name');
            const fullText = button.getAttribute('data-bs-fullText');
            const price = button.getAttribute('data-bs-price');
            // If necessary, you could initiate an AJAX request here
            // and then do the updating in a callback.
            //
            // Update the modal's content.
            const form = changeProduct.querySelector('form');
            const modalTitle = changeProduct.querySelector('.modal-title')
            const inputName = changeProduct.querySelector('.modal-body #title');
            const inputFullText = changeProduct.querySelector('.modal-body #full_textChange');
            const inputPrice = changeProduct.querySelector('.modal-body #priceChange');

            modalTitle.textContent = `Change information about product ${recipient}`
            inputName.value = name;
            inputFullText.value = fullText;
            inputPrice.value = price;
            form.action =`/admin_menu/${recipient}/edit`;
        })
    }

});

