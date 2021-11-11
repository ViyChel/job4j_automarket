(() => setTimeout(printTasks, 0))();

function printTasks() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/private.do',
        dataType: 'JSON'
    }).done(function (data) {
        console.log(data);
        if (data !== null) {
            let textRow = $('#ads').after().html('');
            let text = '';
            for (let ad of data) {
                let status = ad.status;
                let addId = ad.addId;
                let city = ad.city;
                let date = ad.date;
                let engineType = ad.engineType;
                let engineVolume = ad.engineVolume;
                let gear = ad.gear;
                let mileage = ad.mileage;
                let model = ad.model;
                let photo = ad.photo;
                let price = ad.price;
                let transmission = ad.transmission;
                let year = ad.year;
                printRow();

                function printRow() {
                    if (photo === 'empty') {
                        text += '<tr><td><img src="./default.png" width="450px" height="350px" alt="photo"/></td>';

                    } else {
                        text += '<tr><td><img src="download?name=' + photo + '" width="450px" height="350px" alt="photo"/></td>';
                    }
                    console.log(text);
                    text += '<td><a class="h4" href="/automarket/detail.jsp?id=' + addId + '">' + model + ', ' + year + ' год</a>';
                    text += '<div>Двигатель : ' + engineType + ', ' + engineVolume + ' л</div>';
                    text += '<div>Пробег : ' + mileage + ' </div>';
                    text += '<div>Коробка : ' + transmission + ' </div>';
                    text += '<div>' + gear + '</div>';
                    if (status) {
                        text += '<button class="btn btn-secondary mt-5" type="submit">В архиве</button>';
                    } else {
                        text += '<form action="/automarket/private.do?action=remove&id=' + addId + '" method="post">';
                        text += '<button class="btn btn-warning mt-5" type="submit">Снять с публикации</button>';
                        text += '</form>';
                    }
                    text += '</td>';
                    text += '<td><div class="h4"> ' + price + ' р</div>';
                    text += '<div class="m-0">' + city + '</div>';
                    text += '<div class="m-0">' + date + '</div></td></tr>';
                }
            }
            textRow.append(text);
        }
    }).fail(function () {
        alert('An error occurred!');
    });
}

