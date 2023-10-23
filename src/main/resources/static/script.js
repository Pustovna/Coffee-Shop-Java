document.addEventListener("DOMContentLoaded", () => {

    /* управление количеством в корзине */

    const addAmount = function (element) {
        const plus = element.querySelector('.plus');
        const minus = element.querySelector('.minus');

        sum();
        counting(plus, 'increment', element);
        counting(minus, 'decriment', element);
    }



    const counting = function (node, value, parentItem) {
        node.addEventListener('click', function(e) {
            const visableQuantity = node.parentElement.querySelector('.button__count');

            value == 'increment' ? visableQuantity.textContent++ :
                visableQuantity.textContent--;

            if (visableQuantity.textContent == 0) {
                parentItem.remove();
            } else {
                const inputQuantity = visableQuantity.nextElementSibling;
                inputQuantity.value = visableQuantity.textContent;
            }

            sum();
        });
    }



    const sum = function () {
        let allSum = 0;
        const order = document.querySelector('.order');
        const sum = order.querySelector('.sum');
        const price = order.querySelectorAll('.item');

        price.forEach((node) => {
            const sum = node.querySelector('.button__count').textContent * node.querySelector('.price').textContent
            allSum = allSum + sum;
        })

        sum.textContent = allSum;
    }


    /* ---------------------------------------------- */

    /* Добавление элемента меню в корзину */


    /* шаблон новой позиции */
    const newPosition = (title, price, id) => {
        return `
        <li class="list-group-item d-flex justify-content-between align-items-start item">
          <div class="ms-2 me-auto">
            <div class="fw-bold">${title}</div>
          </div>
          <div class="d-flex buttons__count">
            <div class="button__small minus">
              <svg width="12" height="10" viewBox="0 0 12 10" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M1.41675 5H10.5834" stroke="#6E7D86" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path></svg>              </div>
            <span class="button__count">1</span>
             <input class="quantity" name="quantity" type="hidden" value="1">  <!--           это пример тега input, из него будут отпарвляться данные на сервер-->
            <input name="id_product" type="hidden" value="${id}">
           
            <div class="button__small plus">
              <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M6.00008 1.41699V10.5837M1.41675 6.00033H10.5834" stroke="#6E7D86" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path></svg>
            </div>
          </div>
          <span class="price">${price}</span>
        </li>`;
    };

    const menuList = document.querySelectorAll('.menu__item');
    const order = document.querySelector('.order__list');

    menuList.forEach((item) => {
        const button = item.querySelector('.btn');
        button.addEventListener('click', function (e) {
            const id = item.querySelector('.item__id').textContent;
            const price = item.querySelector('.price').textContent;
            const title = item.querySelector('.card-title').textContent;
            const newLine = newPosition(title, price, id);

            order.insertAdjacentHTML("beforeend", newLine);
            const lastPosition = order.lastElementChild;
            addAmount(lastPosition);
        })
    });

    /* ---------------------------------------------- */

    /*  Удаление из корзины всех элементов  */

    const resetButton = document.querySelector('.order').querySelector('.button__reset');
    console.log(resetButton);
    resetButton.addEventListener('click', (e) => {
        const  items = order.querySelectorAll('.item');
        items.forEach((item) => {
            item.remove();
        })
        sum();
    });

    /* ---------------------------------------------- */

});



