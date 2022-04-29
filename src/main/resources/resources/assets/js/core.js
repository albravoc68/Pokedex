var urlPokemonList = 'page?itemsPerPage=5&number=';
var urlPokemon = 'pokemon?name='

$(document).ready(function () {
    getPokemonList(1);
});

function getPokemonList(page) {
    $.ajax({
        url: urlPokemonList + page,
        "type": "GET",
        success: function (data) {
            var pokeList = data.pokemonList;
            var backPage = page - 1;
            if (backPage - 1 <= 0) backPage = 1;
            var nextPage = page + 1;
            if (nextPage > pokeList[pokeList.length - 1].id) nextPage = page;

            $("#current_page").empty();
            $("#current_page").append(page);
            $("#back_navigator").attr("onclick", 'getPokemonList(' + backPage + ')');
            $("#next_navigator").attr("onclick", 'getPokemonList(' + nextPage + ')');

            console.log(data);
            $("#poke_count").empty();
            $("#poke_count").append("showing 5 of " + data.total + " pokemon");
            $("#poke_table").css("margin-top", "10px");

            $("#poke_names").empty();
            $("#poke_description").empty();
            for (var i = 0; i < pokeList.length; i++) {
                $("#poke_names").append("<th>" + pokeList[i].name + "</th>");

                $("#poke_description").append("<th id='" + pokeList[i].name + "'>");
                $("#" + pokeList[i].name).append("<div id='" + pokeList[i].name + "_div' style='border: 1px solid #000; padding: 10px;' onclick='getPokemon(\"" + pokeList[i].name + "\")'>");
                $("#" + pokeList[i].name + "_div").append("<img src='" + pokeList[i].picture + "' width='100' height='100'/><br/>");
                $("#" + pokeList[i].name + "_div").append("<label>ID:" + pokeList[i].id + "</label><br/>");
                $("#" + pokeList[i].name + "_div").append("<label>" + pokeList[i].types.toString() + "</label><br/>");
                $("#" + pokeList[i].name + "_div").append("<label>Weight: " + pokeList[i].weight + " hg</label><br/>");
                $("#" + pokeList[i].name + "_div").append("<label>Abilities:</label><br/>");
                $("#" + pokeList[i].name + "_div").append("<label>" + pokeList[i].abilities.toString() + "</label><br/>");
                $("#" + pokeList[i].name).append("</div>");
                $("#poke_description").append("</th>");
            }
            $("#navigator").css("display", "");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Pokedex error.");
        }
    });
}

function getPokemon(name) {
    $.ajax({
        url: urlPokemon + name,
        "type": "GET",
        success: function (data) {
            $("#" + name + "_detail").empty();
            $("#" + name + "_div").append("<div id='" + name + "_detail'>");
            $("#" + name + "_detail").append("<label>Description:</label><br/>");
            $("#" + name + "_detail").append("<label>" + data.description + "</label><br/>");
            $("#" + name + "_detail").append("<label>Evolutions:</label><br/>");
            $("#" + name + "_detail").append("<label>" + data.evolutions + "</label><br/>");
            $("#" + name + "_div").append("<div/>");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Failed to search for " + name);
        }
    });
}