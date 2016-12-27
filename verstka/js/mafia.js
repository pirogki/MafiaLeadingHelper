/**
 * Created by Andrey on 08.11.2016.
 */

//            role = function(name, title)
//            {
//                this.name = name;
//                this.title = title;
//            }
var mafia = new Object();

/**
 * Объеки ролей мафии
 */
mafia.roles = {
	"mistress": "Любовница",
	"mafia": "Мафия",
	"commissioner": "Коммисар",
	"doctor": "Доктор",
	"civilians": "Мирный житель"
};
			
/**
 *
 * @param String name имя игрока
 * @param int id идентификатор в базе
 * @param bool dead признак смерти
 * @param bool disableSelecting признка того что игрока выбрать нельзя
 * @param String role роль
 * @constructor
 */
mafia.Player = function (name, id, dead, disableSelecting, role) {

    this.name = name;
    this.id = id;
    this.dead = dead;
    this.disableSelecting = disableSelecting;
    this.role = role;

    this.selected = false;

}

//кандидатуры

// mafia.Candidat = function(playerId, numberOfVotes, lastVoteTime)
// {
//     this.playerId = playerId;
//     this.numberOfPlayers = numberOfVotes;
//     this.lastVoteTime = lastVoteTime;
// }
//
// mafia.Candidats = function(arrCandidats) {
//     this.arrCandidats = arrCandidats;
//
//     this.getMaxVoteTime = function() {
//         var max  = 0;
//         for(i in this.arrCandidats) {
//             var candidat = this.arrCandidats[i];
//             if(candidat.lastVoteTime > max)
//                 max = candidat.lastVoteTime;
//         }
//         return max;
//     }
// }
utils = new Object();

utils.arraySwapItems = function (array, i, j)
{
    var temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

utils.sort = function(array, criterions) {
    for(i = 0; i < array.length; i++) {
        for(j = i; j < array.length; j++) {
            for(critIdx in criterions) {
                var crit = criterions[critIdx];


                if(array[j][crit.field] > array[i][crit.field] && crit.direcion == "desc"
                || array[j][crit.field] < array[i][crit.field] && crit.direcion == "asc"
                )
                {
                    this.arraySwapItems(array,i,j);
                }


                if(array[j][crit.field] != array[i][crit.field])
                {
                    break;
                }
            }
        }
    }
}

mafia.sortPlayersByCandidats = function () {
    var players = this.clonePlayers();
    var criterions = [
        {
            field: "numberOfVotes",
            direcion: "desc"
        },
        {
            field: "lastVoteTime",
            direcion: "asc"
        }

    ];
    utils.sort(players, criterions)
    return players;
}

mafia.clonePlayers = function () {
    var newPlayers = [];
    for(i in this.players) {
        newPlayers.push(this.players[i]);
    }
    return newPlayers;
}

mafia.getNumberOfLifePlayers = function () {

    return this.getNumberOfLifePlayersByRole();
}

mafia.getNumberOfLifePlayersByRole = function (role) {
    var counter = 0;
    for(i in this.players) {
        var player = this.players[i];
        if(!player.dead &&(player.role == role || role == null))
        {
            counter++;
        }
    }
    return counter;
}

mafia.isAnyPlayersAliveByRole = function(role) {
    if(this.getNumberOfLifePlayersByRole(role) > 0)
    {
        return true;
    }
    return false;
}

// mafia.singleRolePlayerIsAliveAsString = function(role) {
//     if(this.isAnyPlayersAliveByRole(role))
//     {
//         return "Жив";
//     }
//     else
//     {
//         return "Мертв";
//     }
// }

mafia.getSingleRolesPlayerIsAliveInfo = function ()
{
    var trs = Array();
    for(key in this.roles)
    {

        if(key != "mafia" && key != "civilians")
        {
            var content;
            var attributes;
            if(this.isAnyPlayersAliveByRole(key))
            {
                content = "Жив";
                attributes = 'class="green"';
            }
            else
            {
                content = "Мертв";
                attributes = 'class="red"';
            }
            trs.push([new Td(this.roles[key]), new Td(content, attributes)]);
        }

    }
    return trs;
}

circle = new Object();

circle.getCenterOfCircle = function () {
	
}

circle.getCenterOfCircle = function() {
	
}

circle.drowCircle = function (callBack) {
	
}

$(document).ready(function () {
        var size = $("#midle-side").width();
        //$("#midle-side").css("height", size);
        var canvas = document.getElementById("players-circle");


        var radius = size / 3;



        var center = {
            x: size / 2,
            y: radius + 5
        }

        $(canvas).attr("width", size / 2 + radius + 15);
        $(canvas).attr("height", radius * 2 + 15);

        var ctx = canvas.getContext("2d");

        ctx.beginPath();
        ctx.ellipse(center.x, center.y, radius, radius, 0, 0, 2 * Math.PI);
        ctx.stroke();

        //Нужно посчитать координаты точек отночительно midle-side

        var numberOfPoint = mafia.players.length;

        var centerCoords = {
            x: $("#midle-side").offset().left + center.x,
            y: $("#midle-side").offset().top + center.y
        }


        for (idx in mafia.players) {
            var player = mafia.players[idx];
            var numverOfPpl = mafia.players.length + 1;
            var a = Math.PI * 2 / (numverOfPpl);
            var angle = a * (numverOfPpl - (parseInt(idx) + 1));

            var coord = {
                x: radius * Math.sin(angle),
                y: radius * Math.cos(angle)
            };
            var left = coord.x + centerCoords.x;
            var top = coord.y + centerCoords.y;

            var absoluteCoords =
            {
                x: left,
                y: top
            }

            //var left = centerCoords.x;
            //var top = centerCoords.y;
            var playerDiv = '<div class="player " style="left:' + absoluteCoords.x + 'px;top:' + absoluteCoords.y + 'px;">' + player.name + '</div>';
            $("#midle-side").append(playerDiv);
			
            $playerDiv = $("#midle-side div.player").last();

            $playerDiv.attr("title", mafia.roles[player.role]);
            $playerDiv.attr("data-id", player.id);

            $playerDiv.addClass(player.role);

            if (player.dead)
                $playerDiv.addClass("dead");

            if (!player.disableSelecting && !player.dead)
                $playerDiv.addClass("selectible")

            var fixAbsoluteCoords =
            {
                x: absoluteCoords.x - ($playerDiv.width() / 2),
                y: absoluteCoords.y - ($playerDiv.height() / 2)
            };
            $playerDiv.css("left", fixAbsoluteCoords.x);
            $playerDiv.css("top", fixAbsoluteCoords.y);


            //      $("#midle-side").lastChild().css("left", "l")
        }
        $(".player.selectible").click(mafia.playerClick);
        mafia.drawInfo();
    }
);

mafia.findPlayer = function (playerId) {
    /*for (idx in this.players) {
        var player = this.players[idx];
        if (this.players[idx].id == playerId) {
            return player;
        }
    }*/
	return this.findPlayerBySign(function(player) {
		if(player.id == playerId)
			return true;
		else
			return false;
	});
}

mafia.findPlayerBySign = function (sign) {
	for (idx in this.players) {
        var player = this.players[idx];
        if (sign(player)) {
            return player;
        }
    }
}

mafia.drawInfo = function () {
    var table = new Table("mafia-info");
    table.addTr([new Td("Игроков:"), new Td(mafia.players.length)]);
    table.addTr([new Td("Живых:"), new Td(mafia.getNumberOfLifePlayers())]);

    table.addTr([new Td("Мафий:"), new Td(mafia.getNumberOfLifePlayersByRole("mafia"))]);
    table.addTr([new Td("Мирняков:"), new Td(mafia.getNumberOfLifePlayersByRole("civilians"))]);

    var singleRolesInfo = this.getSingleRolesPlayerIsAliveInfo();
    for(i in singleRolesInfo)
    {
        table.addTr(singleRolesInfo[i]);
    }
}

//Таблицы

Table = function (tableId) {
    this.tableId = tableId;

    this.addTr = function (tds)
    {
     //   console.log(tds);
        var result = "<tr>";
        for(i in tds)
        {
            result += "<td " + tds[i].attributes +">" + tds[i].content + "</td>";
        }
        result += "</tr>";
        document.getElementById(this.tableId).getElementsByTagName("tbody")[0].innerHTML += result;
    }

    this.clear = function()
    {
        document.getElementById(this.tableId).getElementsByTagName("tbody")[0].innerHTML = "";
    }
}

Td = function (content, attributes)
{
    if(attributes == null)
    {
        attributes = "";
    }
    this.content = content;
    this.attributes = attributes;
}

//Голосование

mafia.getNumberOfVotes = function () {
    var counter = 0;
    for(i in this.players)
    {
        var player = this.players[i];
        if(player.selected)
            counter++;
    }
    return counter;
}

mafia.checkMaxSelPlayers = function () {
	if(this.selectedLinit && this.getNumberOfVotes() >= this.maxNumberOfSelectedPlayers)
	{

		alert("Нельзя выбрать больше " + this.maxNumberOfSelectedPlayers + " человек.")
		return false;
	}
	return true;
} 

mafia.getNumberOfVotingPlayers = function () {
    var counter = 0;
    for(i in this.players)
    {
        var player = this.players[i];
        if(!player.disableSelecting && !player.dead)
            counter++;
    }
    return counter;
}

mafia.getNumberOfNeededPlayers = function () {
    return Math.ceil(this.getNumberOfVotingPlayers() / 2);
}

mafia.isEnoughVotes = function ()
{
    var isEnough = false;
    if( this.getNumberOfVotes() >= this.getNumberOfNeededPlayers())
        isEnough = true;
    return isEnough;
}

mafia.isEnoughVotesTd = function ()
{

    var content;
    var attributes;
    if(this.isEnoughVotes())
    {
        content = "Да";
        attributes = 'class="green"';
    }
    else
    {
        content = "Нет";
        attributes = 'class="red"';
    }
    return new Td(content, attributes);
}

