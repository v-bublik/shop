let counter = 0;

$(document).ready(function () {
    $.ajax({
        url: 'rest/',
        method: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: (data) => {
            // renderTable(data);
            renderSelect(data, "productChoosing");
            renderSelect(data, "userProductChoosing");
            renderUsersSelect(data);
            //console.log(data);
        }
    });
});

function renderSelect(payload, id) {
    payload.products.forEach(function (product) {
        $(`#${id}`).append($(`<option value="${product.id}">${product.title}</option>`));
    });
}

function renderUsersSelect(payload) {
    payload.users.forEach(function (user) {
        $("#userChoosing").append($(`<option value="${user.id}">${user.login}</option>`));
    });
}

function updateDiscountModel(tagId, user) {
    let model = [];
    let actionCheck = tagId === "userProductChoosing";
    $(`#${tagId}`).find(":selected").each(function () {
        model.push({
            id: this.value,
            title: this.text,
            userId: actionCheck ? [user.id] : null,
            //username: actionCheck ? [user.login] : null,
            discount: actionCheck ? user.discount : `${$("#discountProductInput").val()}`
        });
        sendDataToServer(model);
        // model.push({
        //     id: this.value,
        //     title: this.text,
        //     userId: actionCheck ? user.id : null,
        //     users: actionCheck ? [user.login] : null,
        //     discount: actionCheck ? user.discount : `${$("#discountProductInput").val()}%`
        // });
    });
   // renderTable(model);
}

function userProductUpdate() {
    $(`#userChoosing`).find(":selected").each(function () {
        updateDiscountModel('userProductChoosing', {
            id: this.value,
            //username: this.text,
            discount: `${$("#discountUserProductInput").val()}`
        })
    });
}

function sendDataToServer (user) {
    $.ajax({
        url: '/rest',
        method: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/json',
        success: () => {
            //TODO Save Create POST Request
        }
    });
}

// function removeRow() {
//     let disList = $("#discountList");
//     for (let i = 0; i < disList.find("input:checked").length; i++) {
//         let index = testInputJson.findIndex(item => {
//             let s = disList.find("input:checked")[i].id.split(' ');
//             if (parseInt(s[0]) === item.productId) {
//                 if (isNaN(parseInt(s[1]))) {
//                     return true;
//                 } else if (item.userId === parseInt(s[1])) {
//                     return true;
//                 }
//             }
//         });
//         testInputJson.splice(index, index);
//         document.getElementById(disList.find("input:checked")[i].id).parentNode.parentNode.remove();
//     }
// }


// function renderTable(payload) {
//     payload.products.forEach(t => {
//         let color = parseInt(t.getData) > 30 ? "red" : "green";
//         $(`<tr id=${counter++}>` +
//             `<th scope=\"row\"><input type="checkbox" id="${t.id}/${t.userId}"/></th>` +
//             `<th>${t.title}</th>` +
//             `<th>Pasha</th>` +
//             `<th><span style='color: ${color};'>${t.getData}</span></th>` +
//             "</tr>").insertAfter($("tr:first"));
//     });
//     this.data = payload;
// }