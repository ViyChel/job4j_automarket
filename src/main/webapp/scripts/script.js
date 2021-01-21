(() => setTimeout(printTasks, 0))();

function printTasks() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/automarket/add.do',
        dataType: 'JSON'
    }).done(function (data) {
        console.log(data);
        if (data !== null) {
            let textRow = $('#ads').after().html('');
            let text = '';
            for (let i = 0; i < data.length; i++) {
                let status = data[i].status;
                if (status === false) {
                    let addId = data[i].addId;
                    let city = data[i].city;
                    let date = data[i].date;
                    let engineType = data[i].engineType;
                    let engineVolume = data[i].engineVolume;
                    let gear = data[i].gear;
                    let mileage = data[i].mileage;
                    let model = data[i].model;
                    let photo = data[i].photo;
                    let price = data[i].price;
                    let transmission = data[i].transmission;
                    let year = data[i].year;
                    printRow();

                    function printRow() {
                        text += '<tr><td><img src="download?name=' + photo + '" width="450px" height="300px" alt="photo"/></td>';
                        console.log(text);
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
        }
    }).fail(function () {
        alert('An error occurred!');
    });
}

