

let allPoints = [];
let allLines = [];
let connectedLines = [];
let background = new Image();
let color = '#ff00ff';
let path = [];

function shadeColor(color, percent) {
    let R = parseInt(color.substring(1,3),16);
    let G = parseInt(color.substring(3,5),16);
    let B = parseInt(color.substring(5,7),16);

    R = parseInt(R * (100 - percent) / 100);
    G = parseInt(G * (100 - percent) / 100);
    B = parseInt(B * (100 - percent) / 100);

    R = (R<255)?R:255;
    G = (G<255)?G:255;
    B = (B<255)?B:255;

    let RR = ((R.toString(16).length==1)?"0"+R.toString(16):R.toString(16));
    let GG = ((G.toString(16).length==1)?"0"+G.toString(16):G.toString(16));
    let BB = ((B.toString(16).length==1)?"0"+B.toString(16):B.toString(16));

    return "#"+RR+GG+BB;
}

function geoDistance(lat1, lon1, lat2, lon2){  // generally used geo measurement function
    let R = 6378.137; // Radius of earth in KM
    let dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
    let dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
    let a = Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
    Math.sin(dLon/2) * Math.sin(dLon/2);
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    let d = R * c;
    return d * 1000; // meters
}

function drawMap(ctx, data, canvas) {
    background.src = 'data:image/jpeg;base64,' + data;

    background.onload = function () {
        canvas.width = this.naturalWidth;
        canvas.height = this.naturalHeight;
        ctx.drawImage(background, 0, 0);
    };
}

function drawPoint(ctx, point, radius) {
    ctx.beginPath();
    ctx.arc(point['x'], point['y'], radius, 0, 2 * Math.PI);
    ctx.fill();
    ctx.fillText(point.name, point.x, point.y - 10);
}

function getMousePos(canvas, evt) {
let rect = canvas.getBoundingClientRect();
return {
    x: evt.clientX - rect.left,
    y: evt.clientY - rect.top
    };
}

function hover(pos, eps) {
    for (let i = 0; i < allPoints.length; i++) {
        let point = allPoints[i];
        if ((Math.abs(point.x - pos.x) < eps) && (Math.abs(point.y - pos.y) < eps)) {
            return point;
        }
    }
    return undefined;
}

function scalePoint(point) {
    point['x'] = (point['lon'] - 19.992521) * 25000 + 270;
    point['y'] = 700 - (point['lat'] - 49.21909) * 22000;
    return point
}
function drawLine(ctx, line) {
    let p1 = scalePoint(line['startPoint']);
    let p2 = scalePoint(line['endPoint']);
    ctx.beginPath();
    ctx.moveTo(p1.x, p1.y);
    ctx.lineTo(p2.x, p2.y);
    ctx.stroke();
}

function drawEverything(ctx, allLines, allPoints, path, connectedLines=[]) {
    ctx.drawImage(background, 0, 0);

    ctx.lineWidth = 1;
    ctx.strokeStyle = "#aaaaaa";
    for (let i = 0; i < allLines.length; i++) {
        let line = allLines[i];
        drawLine(ctx, line);
    }

    ctx.lineWidth = 2;
    ctx.strokeStyle = '#000000';
    for (let i = 0; i < connectedLines.length; i++) {
        drawLine(ctx, connectedLines[i]);
    }

    ctx.lineWidth = 3;
    let tempColor = color;
     for (let i = 0; i < path.length; i++) {
        tempColor = shadeColor(tempColor, i * 3);
        ctx.strokeStyle = tempColor;
        drawLine(ctx, path[i]);
    }

    ctx.strokeWidth = 1;
    ctx.fillStyle = "#FF0000";
    for (let i = 0; i < allPoints.length; i++) {
        let point = allPoints[i];
        scalePoint(point);
        drawPoint(ctx, point, 4);
    }
}

function writeList() {
    $('#path').empty();
    path.forEach(function (line) {
        let point1 = line['startPoint'];
        let point2 = line['endPoint'];
        let ul = $('<ul>').addClass('outerlist');
        ul.append($('<li>').text('od: ' + point1.name));
        ul.append($('<li>').text('do: ' + point2.name));
        $('#path').append(ul);
    });
}

function drawConnected(ctx, selectedPoint, allLines, allPoints, path) {
    $.ajax({
        type: 'GET',
        url: '/plan_trip/points/' + selectedPoint.idP,
        success: function (lines) {
            connectedLines = lines;
            drawEverything(ctx, allLines, allPoints, path, connectedLines);
        },
        async: false,
        error: function (xhr) {
            let error = JSON.parse(xhr.responseText);
            alert(error);
        }
    });
}

$(document).ready(function () {
    let canvas = document.getElementById("mapCanvas");
    let ctx = canvas.getContext("2d");
    $.ajax({
        type: 'GET',
        url: '/plan_trip/points/map'
    }).then(function (map) {
        drawMap(ctx, map, canvas);
        return $.ajax({
            type: 'GET',
            url: '/plan_trip/lines/all'
        });
    }).then(function (lines) {
        for (let i = 0; i < lines.length; i++) {
            allLines.push(lines[i]);
        }
       return $.ajax({
            type: 'GET',
            url: '/plan_trip/points/all'
        });
    }).done(function (points) {
        for (let i = 0; i < points.length; i++) {
            allPoints.push(points[i]);
        }

        drawEverything(ctx, allLines, allPoints, path);
    }, function (xhr, status, error) {
    });


    $('#mapCanvas').on('mousedown', function(e){
        let pos = getMousePos(canvas, e);
        let selected = hover(pos, 20);
        if (selected !== undefined) {
            let validSelectedLine = connectedLines.filter(
                function (line) { return line['endPoint'].idP === selected.idP});
            // console.log(validSelectedLine);
            if (connectedLines.length !== 0 && validSelectedLine.length === 0) {
                return;
            }

            if (validSelectedLine.length !== 0) {
                path.push(validSelectedLine[0]);
                writeList();
            }

            drawConnected(ctx, selected, allLines, allPoints, path);
            drawPoint(ctx, selected, 7);
        }
    });

    $('#revert').click(function () {
        connectedLines = [];
        path.pop();
        writeList();
        if (path.length > 0) {
            drawConnected(ctx, path[path.length - 1]['startPoint'], allLines, allPoints, path);
            connectedLines = [];
        }
        drawEverything(ctx, allLines, allPoints, path);
    });

    $('#saveTrip').click(function () {
        let name = $('#tripName').val();
        jsoned = JSON.stringify(path);
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            type: 'POST',
            url: '/plan_trip/save_trip',
            data: jsoned,
            error: function() {
                alert('whoopsie');
            }
        });
    })
});
