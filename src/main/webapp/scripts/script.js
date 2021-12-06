let selectData;

(() => setTimeout(printTasks, 0))();

function getSelectData() {
    $.ajax({
        type: 'GET',
        url: 'brand.do',
        dataType: 'JSON'
    }).done(function (data) {
        selectPrint(data)
        selectData = data;
    }).fail(function () {
        alert('An error occurred!');
    });
}

function printTasks() {
    $.ajax({
        type: 'GET',
        url: 'add.do',
        dataType: 'JSON'
    }).done(function (data) {
        const latsDay = checkLastDay();
        const printPhoto = checkPhoto();
        if (selectData === undefined) {
            getSelectData();
        }
        let brandSelectIndex = document.getElementById('brands').selectedIndex;
        if (brandSelectIndex === -1 || brandSelectIndex === 0) {
            printAds(data, latsDay, printPhoto, '');
        } else {
            let brandSelect = selectData[brandSelectIndex - 1].brandName;
            printAds(data, latsDay, printPhoto, brandSelect);
        }
    }).fail(function () {
        alert('An error occurred!');
    });
}

function printAds(data, dayOption, photoOption, brandOption) {
    if (data !== null) {
        let textRow = $('#ads').after().html('');
        let text = '';
        for (let ad of data) {
            let status = ad.status;
            let brand = ad.brand;
            let addId = ad.addId;
            let city = ad.city;
            let date = ad.date;
            let engineType = ad.engineType;
            let enginePower = ad.enginePower;
            let engineVolume = ad.engineVolume;
            let gear = ad.gear;
            let mileage = ad.mileage;
            let model = ad.model;
            let price = ad.price;
            let transmission = ad.transmission;
            let year = ad.year;
            let photo = ad.photo;
            if (status === false) {
                if (brandOption !== '') {
                    if (brandOption === brand) {
                        if (photoOption === true && photo === 'empty') {
                            continue;
                        }
                        if (dayOption === true) {
                            if (!checkDate(date)) {
                                continue;
                            }
                        }
                        print();
                    }
                } else {
                    if (photoOption === true && photo === 'empty') {
                        continue;
                    }
                    if (dayOption === true) {
                        if (!checkDate(date)) {
                            continue;
                        }
                    }
                    print();
                }
            }

            function print() {
                if (photo === 'empty') {
                    text += '<tr class="border-top"><td style="width:  450px"><a href="./detail.jsp?id=' + addId + '"><img src="./default.png" class="border" width="450" height="350px" alt="photo"/></td>';

                } else {
                    text += '<tr class="border-top"><td style="width:  450px"><a href="./detail.jsp?id=' + addId + '"><img src="./image/' + photo + '" class="border" width="450" height="350px" alt="photo"/></td>';
                }
                text += '<td class="ml-1">'
                text += '<div class="h4 p-1"><a class="text-decoration-none" href="./detail.jsp?id=' + addId + '">' + brand + ' ' + model + ', ' + year + ' год</a></div>'

                text += '<table class="table">'
                text += '<tr>'
                text += '<td class="text-secondary p-1">Двигатель</td>'
                text += '<td class="p-1">' + engineType + ', ' + engineVolume + ' л</td>'
                text += '</tr>'

                text += '<tr>'
                text += '<td class="text-secondary p-1">Пробег, км</td>'
                text += '<td class="p-1">' + mileage + '</td>'
                text += '</tr>'

                text += '<tr>'
                text += '<td class="text-secondary p-1">Коробка</td>'
                text += '<td class="p-1">' + transmission + '</td>'
                text += '</tr>'

                text += '<tr>'
                text += '<td class="text-secondary p-1">Привод</td>'
                text += '<td class="p-1">' + gear + '</td>'
                text += '</tr>'

                text += '</tbody>'
                text += '</table>' + '</td>'
                text += '<td><div class="h4 p-1"> ' + price + ' ₽</div>';
                text += '<div class="p-1 pb-1">' + city + '</div>';
                text += '<div class="p-1">' + date + '</div></td></tr>';
            }
        }
        textRow.append(text);
    }
}

function selectPrint(data) {
    if (selectData === undefined) {
        const brandList = $('#brands').html('');
        brandList.append("<option value=''>Выберите марку</option>");
        data.forEach(item => brandList.append("<option value=" + item.brandName + ">" + item.brandName + "</option>"))
    } else {
        brandFilter(selectData);
    }
}


function brandFilter(data) {
    const sel = document.getElementById('brands').selectedIndex;
    const brandList = $('#brands').html('');
    brandList.append("<option value=''>Выберите марку</option>");
    let brandOption = data[sel].brandName;
    data.forEach(item => {
        if (item.brand === brandOption) {
            brandList.append("<option selected value=" + item.brand + ">" + item.brand + "</option>")
        } else {
            brandList.append("<option value=" + item.brand + ">" + item.brand + "</option>")
        }
    })
}

function checkPhoto() {
    return !!document.getElementById('checkPhoto').checked;
}

function checkLastDay() {
    return !!document.getElementById('lastDay').checked;
}

function checkDate(date) {
    const parts = date.split(/[\s,.]+/, 3)
    const compareDate = new Date(parts[2], parts[1] - 1, parts[0])
    return compareDate.toDateString() === new Date().toDateString()
}



