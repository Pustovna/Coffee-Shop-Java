document.addEventListener("DOMContentLoaded", () => {
    const paymentForm = document.querySelector('#payment-form');

    paymentForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(paymentForm); // создаём объект FormData, передаём в него элемент формы
        // теперь можно извлечь данные
        paymentForm.style.display = "none";

        const alert = document.querySelector('.alert');
        alert.classList.remove('hidden');
    })

    const credit = paymentForm.querySelector('.credit');
    const cash = paymentForm.querySelector('.cash');
    const paymentMethod = paymentForm.querySelectorAll('input[name="paymentMethod"]')
    for (const f of paymentMethod) {
        f.addEventListener('change', function () {
            if (f.value == "credit") {
                cash.classList.add('hidden');
                credit.classList.remove('hidden');
            } else {
                credit.classList.add('hidden');
                cash.classList.remove('hidden');
            }

        })
    }
});