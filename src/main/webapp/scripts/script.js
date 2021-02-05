(() => setTimeout(printTasks, 0))();

function printTasks(checkPhoto) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/automarket/add.do',
        dataType: 'JSON'
    }).done(function (data) {
        if (data !== null) {
            let textRow = $('#ads').after().html('');
            let text = '';
            for (let i = 0; i < data.length; i++) {
                let status = data[i].status;
                if (status === false) {
                    let photo = data[i].photo;
                    /*if (checkPhoto === true && photo === 'empty') {
                        continue;
                    }*/
                    let addId = data[i].addId;
                    let city = data[i].city;
                    let date = data[i].date;
                    if (checkPhoto === true) {
                        if (!checkDate(date)) {
                            continue
                        }
                    }
                    let engineType = data[i].engineType;
                    let engineVolume = data[i].engineVolume;
                    let gear = data[i].gear;
                    let mileage = data[i].mileage;
                    let model = data[i].model;
                    let price = data[i].price;
                    let transmission = data[i].transmission;
                    let year = data[i].year;
                    printRow();

                    function printRow() {
                        if (photo === 'empty') {
                            text += '<tr><td><img src="/automarket/default.png" width="450px" height="350px" alt="photo"/></td>';

                        } else {
                            text += '<tr><td><img src="download?name=' + photo + '" width="450px" height="350px" alt="photo"/></td>';
                        }
                        text += '<td><a class="h4" href="/automarket/detail.jsp?id=' + addId + '">' + model + ', ' + year + ' год</a>';
                        text += '<div>Двигатель : ' + engineType + ', ' + engineVolume + ' л</div>';
                        text += '<div>Пробег : ' + mileage + ' </div>';
                        text += '<div>Коробка : ' + transmission + ' </div>';
                        text += '<div>' + gear + '</div>' + '</td>';
                        text += '<td><div class="h4"> ' + price + ' р</div>';
                        text += '<div class="m-0">' + city + '</div>';
                        text += '<div class="m-0">' + date + '</div></td></tr>';
                    }
                }
            }
            textRow.append(text);
            modelsFilter(data);
        }
    }).fail(function () {
        alert('An error occurred!');
    });
}

modelsFilter = data => {
    const modelsList = $('#models').html('');
    data.forEach(function (data) {
        modelsList.append("<option value=" + data.carId + ">" + data.model + "</option>");
    });
}

function showPhoto() {
    if (document.getElementById('checkPhoto').checked) {
        printTasks(true);
    } else {
        printTasks(false);
    }
}

function showLastDay() {
    if (document.getElementById('lastDay').checked) {
        printTasks(true);
    } else {
        printTasks(false);
    }
}

checkDate = date => {
    let parts = date.split(/[\s,.]+/, 3)
    let compareDate = new Date(parts[2], parts[1] - 1, parts[0])
    return compareDate.toDateString() === new Date().toDateString()
}
